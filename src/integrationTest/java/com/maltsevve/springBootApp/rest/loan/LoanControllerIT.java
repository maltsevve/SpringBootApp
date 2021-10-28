package com.maltsevve.springBootApp.rest.loan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maltsevve.springBootApp.dto.LoanDto;
import com.maltsevve.springBootApp.service.LoanService;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@SpringBootTest
@AutoConfigureMockMvc
public class LoanControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;



    @Test
    public void findAll() throws Exception {
        

        
        List<LoanDto> data = this.loanService.getAll();
         
        // given(loanService.getAll().willReturn(ResponseEntity.ok(data)));
        HashMap<String, String> userObj = new HashMap<String, String>();
        userObj.put("username", "bsehgal");
        userObj.put("password", "Sanya123");

        this.mockMvc.perform(post("/api/v1/auth/login"));

            this.mockMvc.perform(get("/api/v1/loans")
                 .header("Authorization", "Bearer_" + "Get Token from Line 54"))
                 .andExpect(jsonPath("$.*", hasSize(data.size())))
                 .andExpect(status().isOk());


    }

    // @Test
    // public void findById() throws Exception {
    //     // Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal(System.currentTimeMillis()));
    //     // given(stockAPI.findById(stock.getId())).willReturn(ResponseEntity.ok(stock));

    //     // this.mockMvc.perform(get("/api/v1/stocks/" + stock.getId()))
    //     //         .andExpect(status().isOk())
    //     //         .andExpect(jsonPath("$.name").value(stock.getName()))
    //     //         .andExpect(jsonPath("$.currentPrice").value(stock.getCurrentPrice()));
    // }

    @Test
    public void createJWT() throws Exception {
        // Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal( System.currentTimeMillis()));
        // given(stockAPI.createANewStock(stock)).willReturn(ResponseEntity.ok(stock));

        HashMap<String, String> userObj = new HashMap<String, String>();
        userObj.put("username", "bsehgal");
        userObj.put("password", "Sanya123");

        this.mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userObj)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value(userObj.get("username")))
                .andExpect(jsonPath("token").exists());

        // this.mockMvc.perform(post("/api/v1/stocks")
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("name").value(stock.getName()))
        //         .andExpect(jsonPath("currentPrice").value(stock.getCurrentPrice()));
    }

   // @Test
   // public void deleteLoan() throws Exception {
        // Stock stock = new Stock("S" + System.currentTimeMillis(), new BigDecimal( System.currentTimeMillis()));
        // BigDecimal updatingPrice = stock.getCurrentPrice().add(BigDecimal.ONE);
        // Stock updatedStock = stock.clone();
        // updatedStock.setCurrentPrice(updatingPrice);
        // given(stockAPI.updatePriceOfAStock(stock.getId(), updatingPrice)).willReturn(ResponseEntity.ok(updatedStock));

        // this.mockMvc.perform(put("/api/v1/stocks/" + stock.getId())
        //         .param("currentPrice", stock.getCurrentPrice().add(BigDecimal.ONE).toString())
        //         .content(new ObjectMapper().writeValueAsString(stock)))
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("name").value(stock.getName()))
        //         .andExpect(jsonPath("currentPrice").value(updatedStock.getCurrentPrice()));
   // }

}
