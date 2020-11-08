package com.rostertwo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rostertwo.ExchangeRatesAPI;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LatestRateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Create a neat value object to hold the URL
        URL url = new URL("https://api.exchangeratesapi.io/latest?base=USD");

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method, headers etc.
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Make the request
        InputStream responseStream = connection.getInputStream();

        // Convert the response body InputStream to ExchangeRatesAPI using Jackson
        ObjectMapper mapper = new ObjectMapper();
        ExchangeRatesAPI exchangeRatesAPI = mapper.readValue(responseStream, ExchangeRatesAPI.class);

        // Set response attributes
        req.setAttribute("rate", exchangeRatesAPI.rates.RUB);
        req.setAttribute("date", exchangeRatesAPI.date);

        // Finally we have the response
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("currencyRateView.jsp");
        requestDispatcher.forward(req, resp);
    }
}
