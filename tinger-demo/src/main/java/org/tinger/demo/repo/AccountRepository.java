package org.tinger.demo.repo;

import org.springframework.beans.factory.InitializingBean;
import org.tinger.core.data.annotation.DbName;
import org.tinger.core.data.annotation.Repo;
import org.tinger.core.data.annotation.SourceName;
import org.tinger.core.data.annotation.TableName;
import org.tinger.core.data.queryable.Criteria;
import org.tinger.core.data.queryable.Operation;
import org.tinger.data.cacheable.CacheMethod;
import org.tinger.demo.entity.Account;
import org.tinger.mysql.repository.AbstractMysqlRepository;
import org.tinger.mysql.repository.MysqlRepository;

import javax.sql.DataSource;
import java.net.CacheRequest;
import java.util.List;

/**
 * Created by tinger on 2022-10-21
 */
@Repo
@SourceName("tinger_account")
@DbName("tinger_account")
@TableName("tinger_account")
public class AccountRepository extends MysqlRepository<Account, String> {
    public List<Account> findByUsername(String username) {
        Criteria criteria = Criteria.where("username", Operation.EQ, username);
        return select(criteria);
    }
}