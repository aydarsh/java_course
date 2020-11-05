package com.rostertwo.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rostertwo.OpenExchangeRates;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class ArchivedRateServlet extends HttpServlet {
    private static final String KEY = "YOUR_KEY";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("currencyArchiveDateForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");

        // Create a neat value object to hold the URL
        URL url = new URL("https://openexchangerates.org/api/historical/" + date + ".json?app_id=" + KEY);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method, headers etc.
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Make the request
        InputStream responseStream = connection.getInputStream();

        // Convert the response body InputStream to OpenExchangeRate using Jackson
        ObjectMapper mapper = new ObjectMapper();
        OpenExchangeRates openExchangeRates = mapper.readValue(responseStream, OpenExchangeRates.class);

        // Finally we have the response
        req.setAttribute("rate", openExchangeRates.rates.RUB);
        req.setAttribute("date", LocalDate.parse(date));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("currencyRateView.jsp");
        requestDispatcher.forward(req, resp);
    }
}
