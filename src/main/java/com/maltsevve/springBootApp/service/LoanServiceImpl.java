package com.maltsevve.springBootApp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.maltsevve.springBootApp.dto.LoanDto;
import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
 
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private UuidService uuidService;

    @Autowired
    private WeatherService weatherService;


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
        var loan = loanRepository.getById(id);
        weatherService.findByCity(loan.getCity());

        return loan;
    }

    @Override
    public List<LoanDto> getAll() {
        log.info("IN LoanServiceImpl getAll");

        List<Loan> loanList = loanRepository.findAll();

        loanList.forEach( (ln) -> {if (ln.getCity() == null) {
                                        ln.setCity("LosAngeles");
                                        log.debug("Loan City"+ ln.getCity());
                                        loanRepository.save(ln);    
                                    } });

                                    loanList.forEach( n -> System.out.println("printing n:" + n) );

        List<LoanDto> loanDtoList = LoanDto.fromLoans(loanList);

       Collections.sort(loanDtoList, new SortbyBalance());

       
       List<Integer> number = Arrays.asList(2,3,4,5);
       
       int even =
       number.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);

        return loanDtoList;
    }

    @Override
    public LoanDto findByBranchNameAndCity(String branchName, String city) {
        var optLoan = loanRepository.findByBranchNameAndCity(branchName, city);
        log.info("IN findByBranchNameAndCity - loan: {} found by BranchName, City: {}", optLoan);

        if (optLoan.isPresent()){return LoanDto.fromLoan(optLoan.get());} else {return null; }
        //return (LoanDto) Optional.ofNullable(optLoan).orElse(LoanDto.fromLoan(optLoan.get()));



        
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


    class SortbyBalance implements Comparator<LoanDto> {
        // Used for sorting in ascending order of
        // name
        public int compare(LoanDto a, LoanDto b)
        {
            return a.getBalance().compareTo(b.getBalance());
        }
    }


}
