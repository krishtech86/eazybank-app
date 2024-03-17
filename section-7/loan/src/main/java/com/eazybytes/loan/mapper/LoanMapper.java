package com.eazybytes.loan.mapper;

import com.eazybytes.loan.dto.LoansDto;
import com.eazybytes.loan.entity.Loans;

public class LoanMapper {

    public static LoansDto mapToLoanDto(Loans loans, LoansDto loansDto)
    {
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());

        return loansDto;
    }

    public static Loans mapToLoan(LoansDto loansDto, Loans loans)
    {
        loans.setLoanType(loansDto.getLoanType());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());

        return loans;
    }
}
