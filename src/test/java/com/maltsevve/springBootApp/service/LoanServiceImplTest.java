package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.dto.LoanDto;
import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.repository.LoanRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {
    
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private UuidServiceImpl uuidService;

    @Mock
    private WeatherServiceImpl weatherService;
    
    @Test
    void test_NullCreatedSave(){
        Loan loan = getLoan();
        UUID uuid = UUID.randomUUID();
        loan.setLoanNumber(uuid);

        when(uuidService.getUuid()).thenReturn(uuid);
        when(loanRepository.save(any())).thenReturn(loan);

        loan.setCreated(null);

        loanService.save(loan);

        Assertions.assertEquals(Status.ACTIVE, loan.getStatus());
        Assertions.assertNotNull(loan.getId());

        Assertions.assertNotNull(loan.getUpdated());
        Assertions.assertEquals(uuid, loan.getLoanNumber());


        


        Assertions.assertNotNull(loan.getCreated());

    }

    @Test
    void testSave() {
        Loan loan = getLoan();
        UUID uuid = UUID.randomUUID();
        loan.setLoanNumber(uuid);

        when(uuidService.getUuid()).thenReturn(uuid);
        when(loanRepository.save(any())).thenReturn(loan);

        loanService.save(loan);

        Assertions.assertEquals(Status.ACTIVE, loan.getStatus());
        Assertions.assertNotNull(loan.getId());
        Assertions.assertNotNull(loan.getCreated());
        Assertions.assertNotNull(loan.getUpdated());
        Assertions.assertEquals(uuid, loan.getLoanNumber());

        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    void getById() {
        Loan loan = getLoan();

        when(loanRepository.getById(1L)).thenReturn(loan);

        loanService.getById(loan.getId());

        Assertions.assertEquals(1L, loan.getId());

        verify(loanRepository, times(1)).getById(1L);
    }

    @Test
    void getAll() {
        Loan loan1 = getLoan();
        Loan loan2 = getLoan();
        loan2.setId(1L);

        List<Loan> loans = new ArrayList<>();

        loans.add(loan1);
        loans.add(loan2);

        when(loanRepository.findAll()).thenReturn(loans);

        List<LoanDto> result = loanService.getAll();

        Assertions.assertEquals(loans.size(), result.size());

        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        Loan loan = getLoan();

        when(loanRepository.getById(1L)).thenReturn(loan);

        loan = loanService.deleteById(loan.getId());

        Assertions.assertEquals(Status.DELETED, loan.getStatus());

        verify(loanRepository, times(1)).save(loan);
    }

    private Loan getLoan() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(Status.ACTIVE);
        loan.setCreated(new Date());
        loan.setUpdated(new Date());



        return loan;
    }
}