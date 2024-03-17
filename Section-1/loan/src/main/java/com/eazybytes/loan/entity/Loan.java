package com.eazybytes.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}
