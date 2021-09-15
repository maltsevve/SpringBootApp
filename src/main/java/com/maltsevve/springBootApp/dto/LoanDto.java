package com.maltsevve.springBootApp.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.model.Status;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanDto {
    private Long id;
    private String branchName;
    private UUID loanNumber;
    private Long balance;
    private String city;
    private String state;
    private Integer zipcode;
    private Status loanStatus;
    private Date created;
    private Date updated;

    public Loan toLoan() {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setBranchName(branchName);
        loan.setLoanNumber(loanNumber);
        loan.setBalance(balance);
        loan.setCity(city);
        loan.setState(state);
        loan.setZipcode(zipcode);
        loan.setStatus(loanStatus);
        loan.setCreated(created);
        loan.setUpdated(updated);

        return loan;
    }

    public static LoanDto fromLoan(Loan loan) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loan.getId());
        loanDto.setBranchName(loan.getBranchName());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setBalance(loan.getBalance());  
        loanDto.setCity(loan.getCity()); 
        loanDto.setState(loan.getState());
        loanDto.setZipcode(loan.getZipcode());
        loanDto.setLoanStatus(loan.getStatus());
        loanDto.setCreated(loan.getCreated());
        loanDto.setUpdated(loan.getUpdated());

        return loanDto;
    }

    public static List<LoanDto> fromLoans(List<Loan> loans) {
        return loans.stream().map(LoanDto::fromLoan).collect(Collectors.toList());
    }
}

