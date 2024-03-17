package com.eazybytes.loan.mapper;

import com.eazybytes.loan.dto.LoanDto;
import com.eazybytes.loan.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto)
    {
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());

        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan)
    {
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());

        return loan;
    }
}
