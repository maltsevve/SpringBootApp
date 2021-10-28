package com.maltsevve.springBootApp.rest.loan;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.maltsevve.springBootApp.dto.LoanDto;
import com.maltsevve.springBootApp.model.Loan;
import com.maltsevve.springBootApp.service.LoanService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanRestControllerV1 {
    private final LoanService loanService;


    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public ResponseEntity<LoanDto> saveLoan(@RequestHeader(value = "Authorization") String token,
                                                    @RequestBody LoanDto loanDto) {
    //                                            @RequestBody MultiValueMap paramMap) {
        HttpHeaders headers = new HttpHeaders();

        if (Objects.isNull(loanDto) | Objects.isNull(token)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Loan savedLoan = new Loan();
        savedLoan.setBranchName(loanDto.getBranchName());
        savedLoan.setBalance(loanDto.getBalance());
        savedLoan.setCity(loanDto.getCity());
        savedLoan.setState(loanDto.getState());
        savedLoan.setZipcode(loanDto.getZipcode());
        this.loanService.save(savedLoan);

        return new ResponseEntity<>(LoanDto.fromLoan(savedLoan), headers, HttpStatus.CREATED);
    
    //    return null;
    }

    @GetMapping(value = "/branchAndCity/{branchName}/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanDto> getLoanByBranchNameAndCity(@RequestHeader(value = "Authorization") String token,
                                                                @PathVariable Map<String, String> pathVarsMap) {
    //                                            @RequestBody MultiValueMap paramMap) {
        LoanDto loanDto = loanService.findByBranchNameAndCity(pathVarsMap.get("branchName"), pathVarsMap.get("city"));

        if (Objects.isNull(loanDto)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<LoanDto>(loanDto, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        List<LoanDto> loans = this.loanService.getAll();

        if (loans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanDto> getLoan(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable("id") Long loanId) {
        Loan loan = loanService.getById(loanId);

        if (Objects.isNull(loanId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<LoanDto>(LoanDto.fromLoan(loan), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> deleteLoan(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable("id") Long loanId) {
        Loan loan = loanService.getById(loanId);

        if (Objects.isNull(loanId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.loanService.deleteById(loan.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
