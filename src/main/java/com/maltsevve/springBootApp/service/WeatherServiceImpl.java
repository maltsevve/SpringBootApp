package com.maltsevve.springBootApp.service;

import java.util.HashMap;
import java.util.Map;

import com.maltsevve.springBootApp.dto.WeatherDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private RestTemplate restTemplate;
    private String URI_WEATHER = "https://community-open-weather-map.p.rapidapi.com/weather";



    public ResponseEntity<WeatherDto> findByCity(String city){

        //Using RestTemplate
 

 //       ResponseEntity<Object[]> responseEntity = restTemplate
//                  .getForEntity(URI_WEATHER, Object[].class);
 
        // "users/{id}"
        log.info("In WeatherServiceImpl class, findByCity(Sting city) method for city: " + city);
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", city);
        
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.add("x-rapidapi-key", "6fb778fcd9mshf8c7025c321255fp1de9e5jsne7a4f3f6a732");
        ResponseEntity<WeatherDto> entity = restTemplate.exchange(
                URI_WEATHER  + "?q=" + city, HttpMethod.GET, new HttpEntity<Object>(headers),
                    WeatherDto.class);

        var weatherDto = entity.getBody();

        log.info("WeatherDto values, Base: " + weatherDto.getBase() + " Degree: " + weatherDto.getWind().getDeg());

        //ResponseEntity<WeatherDto> responseEntity = restTemplate
        //              .getForEntity(URI_WEATHER, WeatherDto.class, params);
        //return responseEntity;

        return entity;
    }

        public WeatherDto findByZipcode(Long id){

            return null;
        }

}
