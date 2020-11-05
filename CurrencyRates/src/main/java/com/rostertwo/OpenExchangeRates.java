package com.rostertwo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenExchangeRates {
    public long timestamp;
    public String base;
    public Rates rates;
}