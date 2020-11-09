package com.rostertwo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesAPI {
    public String date;
    public String base;
    public Rates rates;
}
