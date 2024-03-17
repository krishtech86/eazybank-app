package com.eazybytes.loan.service;

import com.eazybytes.loan.dto.LoansDto;


public interface ILoanService {

    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
