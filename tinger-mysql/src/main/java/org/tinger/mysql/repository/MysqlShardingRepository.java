package org.tinger.mysql.repository;

import org.springframework.beans.factory.InitializingBean;
import org.tinger.common.utils.CollectionUtils;
import org.tinger.core.apps.Application;
import org.tinger.core.data.DataModule;
import org.tinger.core.data.JdbcDriver;
import org.tinger.core.data.annotation.DbName;
import org.tinger.core.data.annotation.SourceName;
import org.tinger.core.data.annotation.TableName;
import org.tinger.core.data.queryable.Criteria;
import org.tinger.core.data.queryable.Update;
import org.tinger.core.data.repository.ShardingRepository;
import org.tinger.core.model.Entity;
import org.tinger.mysql.common.Transfer;
import org.tinger.mysql.handler.JdbcHandler;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tinger on 2022-10-17
 */
public abstract class MysqlShardingRepository<T extends Entity<K>, S, K> extends AbstractMysqlRepository<T, K> implements ShardingRepository<T, S, K>, InitializingBean {
    private List<DataSource> sources;
    private String table;
    private String database;

    public int getSourceSize() {
        return this.sources.size();
    }

    @Override
    public void afterPropertiesSet() {
        this.table = this.getClass().getAnnotation(TableName.class).value();
        this.database = this.getClass().getAnnotation(DbName.class).value();
        String sourceName = this.getClass().getAnnotation(SourceName.class).value();
        this.sources = Application.getInstance().module(DataModule.class).sharding(JdbcDriver.MYSQL, sourceName);
    }

    @Override
    public T select(S shard, K id) {
        List<JdbcHandler<?>> handlers = new LinkedList<>();
        List<Object> parameters = new LinkedList<>();
        String command = buildSelectCommand(id, calculateDatabase(shard), calculateDatatable(shard), handlers, parameters);
        List<T> list = this.select(calculateDatasource(shard), command, handlers, parameters);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public T create(S shard, T document) {
        List<JdbcHandler<?>> handlers = new LinkedList<>();
        List<Object> parameters = new LinkedList<>();
        String command = buildCreateCommand(document, calculateDatabase(shard), calculateDatatable(shard), handlers, parameters);
        int i = this.update(this.calculateDatasource(shard), command, handlers, parameters);
        if (i > 0) {
            return select(shard, getId(document));
        } else {
            return null;
        }
    }

    @Override
    public T update(S shard, T document) {
        List<JdbcHandler<?>> handlers = new LinkedList<>();
        List<Object> parameters = new LinkedList<>();
        String command = buildUpdateCommand(document, this.calculateDatabase(shard), this.calculateDatatable(shard), handlers, parameters);
        int i = this.update(this.calculateDatasource(shard), command, handlers, parameters);
        if (i > 0) {
            return select(shard, getId(document));
        } else {
            return null;
        }
    }

    @Override
    public List<T> update(S shard, Update update, Criteria criteria) {
        List<JdbcHandler<?>> handlers = new LinkedList<>();
        List<Object> parameters = new LinkedList<>();
        String command = buildUpdateCommand(update, criteria, calculateDatabase(shard), calculateDatatable(shard), handlers, parameters);
        int i = update(calculateDatasource(shard), command, handlers, parameters);
        if (i > 0) {
            return select(shard, criteria);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public T upsert(S shard, T document) {
        T instance = this.select(shard, getId(document));
        return instance == null ? create(shard, document) : update(shard, document);
    }

    @Override
    public T delete(S shard, T document) {
        T object = this.select(shard, getId(document));
        if (object == null) {
            return null;
        }
        List<JdbcHandler<?>> handlers = new LinkedList<>();
        List<Object> parameters = new LinkedList<>();
        String command = buildDeleteCommand(getId(document), calculateDatabase(shard), calculateDatatable(shard), handlers, parameters);
        int i = this.update(calculateDatasource(shard), command, handlers, parameters);
        if (i > 0) {
            return object;
        } else {
            return null;
        }
    }

    @Override
    public List<T> delete(S shard, Criteria criteria) {
        List<T> result = select(shard, criteria);
        if (result.size() > 0) {
            List<JdbcHandler<?>> handlers = new LinkedList<>();
            List<Object> parameters = new LinkedList<>();
            String command = buildDeleteCommand(criteria, calculateDatabase(shard), calculateDatatable(shard), handlers, parameters);
            update(calculateDatasource(shard), command, handlers, parameters);
        }
        return result;
    }

    @Override
    public List<T> select(S shard, Criteria criteria) {
        Transfer transfer = Transfer.builder().metadata(this.metadata).criteria(criteria).build().resolve();
        String command = buildSelectCommand(calculateDatabase(shard), calculateDatatable(shard)) + " WHERE " + transfer.getWhereExpression();
        return this.select(calculateDatasource(shard), command, transfer.getJdbcHandlers(), transfer.getParameters());
    }
}
