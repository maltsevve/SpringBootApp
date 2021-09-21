package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    private String base;
    private Wind wind;


    @Data
    public class Wind {

        private Integer speed;
        private Integer deg;

      }

}

