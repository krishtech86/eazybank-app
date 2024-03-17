package com.eazybytes.account.service;

import com.eazybytes.account.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobilenUmber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNUmber);
}
