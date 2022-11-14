package org.tinger.demo.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tinger.demo.api.UserService;
import org.tinger.demo.entity.Account;
import org.tinger.demo.repo.AccountRepository;
import org.tinger.spring.controller.WebInvoker;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by tinger on 2022-10-04
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private AccountRepository repo;

    @WebInvoker
    @Override
    public Account load(String id) {
        return repo.select(id);
    }

    @WebInvoker
    @Override
    public Account create(Account account) {
        account.setId(UUID.randomUUID().toString());
        return repo.create(account);
    }

    @WebInvoker
    @Override
    public List<Account> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}