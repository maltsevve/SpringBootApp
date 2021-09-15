package com.maltsevve.springBootApp.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoanServiceImpl implements LoanService {
 
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private UuidService uuidService;

    @Override
    public Loan findByLoanName(String loanNumber) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber);
        log.info("IN findByLoanName - loan: {} found by loanNumber: {}", loan, loanNumber);
        return loan;
    }

    @Override
    public Loan save(Loan loan) {
        if (Objects.isNull(loan.getCreated())) {
            loan.setCreated(new Date());
        }

        loan.setLoanNumber(this.uuidService.getUuid());
        loan.setUpdated(new Date());
        loan.setStatus(Status.ACTIVE);


        log.info("IN EventServiceImpl save {}", loan);

        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    public Loan getById(Long id) {
        log.info("IN LoanServiceImpl getById {}", id);
        return loanRepository.getById(id);
    }

    @Override
    public List<Loan> getAll() {
        log.info("IN LoanServiceImpl getAll");
        return loanRepository.findAll();
    }

    @Override
    public Loan deleteById(Long id) {
        Loan loan = getById(id);
        loan.setStatus(Status.DELETED);
        loan.setUpdated(new Date());
        loanRepository.save(loan);

        log.info("IN LoanServiceImpl delete {}", id);
//        loanRepository.deleteById(id);
        return loan;
    }


}
