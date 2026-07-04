package com.mmcoe.test;

// Import assertion method used to compare expected and actual results
import static org.junit.jupiter.api.Assertions.assertEquals;

// Import Mockito method for creating mock behavior
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mmcoe.pojo.CurrencyExchange;
import com.mmcoe.pojo.ExchangeService;

// Enable Mockito support in JUnit 5
@ExtendWith(MockitoExtension.class)
public class TestCurrencyExchange {

    // Create a mock object of ExchangeService
    // Mockito will simulate its behavior without calling the real implementation
    @Mock
    private ExchangeService service;

    // Create an instance of CurrencyExchange and inject
    // the mocked ExchangeService automatically
    @InjectMocks
    private CurrencyExchange ex;

    // Mark this method as a test case
    @Test
    public void testConvert() {

        // Define behavior of mock object:
        // If usdToInr(100) is called, return 9100.0
        // instead of executing real logic
        when(service.usdToInr(100)).thenReturn(9100.0);

        // Call convert() method from CurrencyExchange class
        // This internally uses ExchangeService
        double result = ex.convert(100);

        // Verify whether expected value and actual value are equal
        // Test passes if result = 9100.0
        assertEquals(9100.0, result);
    }
}