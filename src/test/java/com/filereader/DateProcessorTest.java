package com.filereader;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class DateProcessorTest {

    private DateProcessor dateProcessor;

    @Before
    public void init() {
        dateProcessor = new DateProcessor();
    }

    @Test(expected = DateTimeParseException.class)
    public void testFindFirstJoinedThrowNullException() throws ParseException {
        String userId = null;
        String inputDate = "2015-11-2922:43:34+00";
        //InputDate: 2015-11-2922:43:34+00  userId: null is missing
        dateProcessor.findFirstJoined(inputDate, userId);
    }

    @Test(expected = DateTimeParseException.class)
    public void testFindFirstJoinedThrowParseException() throws ParseException {
        String userId = "a888a1c57cf6af2ffee6879999999999";
        String inputDate = "2015-11-2922:43:34+00";
        dateProcessor.findFirstJoined(inputDate, userId);
    }

    @Test
    public void testFindFirstJoined() throws DateTimeParseException {
        String userId = "a888a1c57cf6af2ffee6879999999999";
        String inputDate = "2015-11-29T22:43:34+00:00";
        assertNotNull(dateProcessor.findFirstJoined(inputDate, userId));
        assertEquals(dateProcessor.findFirstJoined(inputDate, userId), userId);
    }

}
