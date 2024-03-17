package com.eazybytes.loan.service.impl;

import com.eazybytes.loan.constants.LoanConstants;
import com.eazybytes.loan.dto.LoansDto;
import com.eazybytes.loan.entity.Loans;
import com.eazybytes.loan.exception.LoanAlreadyExistException;
import com.eazybytes.loan.exception.ResourceAlreadyExistException;
import com.eazybytes.loan.mapper.LoanMapper;
import com.eazybytes.loan.repository.LoanRepository;
import com.eazybytes.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent())
        {
            throw new LoanAlreadyExistException("Loan already registered with given mobileNumber" + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber) {

        Loans newLoan = new Loans();

        Long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
       Loans loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceAlreadyExistException("Loan", "mobileNumber", mobileNumber)
       );
       return LoanMapper.mapToLoanDto(loan, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loanDto) {
    Loans loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
            () -> new ResourceAlreadyExistException("Loan", "loanNumber", loanDto.getLoanNumber())
    );
    LoanMapper.mapToLoan(loanDto, loan);
    loanRepository.save(loan);
    return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceAlreadyExistException("loan", "mobilenumber", mobileNumber)
        );
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }
}
