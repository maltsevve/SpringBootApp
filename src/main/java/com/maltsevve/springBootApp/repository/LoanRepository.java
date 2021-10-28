package com.maltsevve.springBootApp.repository;

import java.util.Optional;

import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan findByLoanNumber(String loanNumber);

    Loan findByLoanNumberAndStatus(String loanNumber, Status status);

    Optional<Loan> findByBranchNameAndCity(String branchName, String city);
}
