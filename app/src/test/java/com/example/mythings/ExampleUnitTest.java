package com.example.mythings;

import com.example.mythings.service.DataService;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void generatedDataIsNotEmptyOrNull() {
        assertTrue(DataService.getInstance().getPreparedData().length() > 0);
    }
}