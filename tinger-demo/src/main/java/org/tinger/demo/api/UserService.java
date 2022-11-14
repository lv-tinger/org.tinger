package org.tinger.demo.api;

import org.tinger.demo.entity.Account;

import java.util.List;

/**
 * Created by tinger on 2022-10-04
 */
public interface UserService {

    Account load(String id);
    Account create(Account account);

    List<Account> findByUsername(String username);
}
