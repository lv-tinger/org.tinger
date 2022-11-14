package org.tinger.demo.repo;

import org.tinger.core.data.annotation.Repo;
import org.tinger.core.data.queryable.Criteria;
import org.tinger.core.data.queryable.Operation;
import org.tinger.demo.entity.Profile;
import org.tinger.mysql.repository.MysqlRepository;

import java.util.List;

/**
 * Created by tinger on 2022-11-07
 */
//@Repo
public class ProfileRepository extends MysqlRepository<Profile, String> {
    public List<Profile> find(Integer status, Integer gender) {
        Criteria criteria = Criteria.where("status", Operation.EQ, status).and("gender", Operation.EQ, gender);
        return select(criteria);
    }
}