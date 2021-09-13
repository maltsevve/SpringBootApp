package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Loan;

import java.util.List;

public interface LoanService {
    Loan save(Loan loan);

    Loan getById(Long id);

    List<Loan> getAll();

    Loan deleteById(Long id);

    Loan findByLoanName(String loanName);
}
