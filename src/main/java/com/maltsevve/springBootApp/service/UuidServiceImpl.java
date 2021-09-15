package com.maltsevve.springBootApp.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class UuidServiceImpl implements UuidService {

    public UUID getUuid(){

        log.debug("In UuidServiceImpl.getUuid - loan: {} found by loanNumber: {}");
        return UUID.randomUUID();
    }

    
}
