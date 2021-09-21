package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.dto.WeatherDto;

import org.springframework.http.ResponseEntity;

public interface WeatherService {

    WeatherDto findByZipcode(Long id);


    public ResponseEntity<WeatherDto> findByCity(String city);
}
