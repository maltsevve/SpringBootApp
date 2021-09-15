package com.maltsevve.springBootApp.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
public class Loan extends BaseEntity {
    
   
    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "loan_number")
    private UUID loanNumber;    

    @Column(name = "balance")
    private Long balance;   

    @Column(name = "city")
    private String city;   

    @Column(name = "state")
    private String state;   

    @Column(name = "zipcode")
    private Integer zipcode;   

    @Override
    public String toString() {
        return "Loan{" +
                "loan id: " + super.getId() + ", " +
                "loan_number: " + loanNumber + 
                "balance: " + balance + 
                "branch_name: " + branchName + "}";
    }
}
