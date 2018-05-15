package com.filereader;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FileReaderTest {

    private FileReader fileReader;

    @Before
    public void init() {
        fileReader = new FileReader();
    }

    @Test
    public void testReadFileSuccessfully() throws Exception {
        String csvFile = "test_file.csv";
        ResultsCollector resultsCollector = fileReader.readFile(csvFile);

        assertEquals(resultsCollector.getNumberOfDeviceResolution(), 28);
        assertEquals(resultsCollector.getTotalSpend(), 51621l);
        assertEquals(resultsCollector.getFirstUserId(), "a888a1c57cf6af2ffee687bfdd7dc4c5");
        assertEquals(resultsCollector.getNumberOfUser(), 100);
    }

    @Test(expected = FileNotFoundException.class)
    public void testDownloadFileThrowException() throws Exception {
        String csvFile = "test_file1.csv";
        fileReader.readFile(csvFile);
    }

    @Test
    public void testReadFileWhenDateNotAvailableAndFormatIssue() throws Exception {
        String csvFile = "test_file_date_issue.csv";
        ResultsCollector resultsCollector = fileReader.readFile(csvFile);

        assertEquals(resultsCollector.getNumberOfDeviceResolution(), 28);
        assertEquals(resultsCollector.getTotalSpend(), 49613);
        assertEquals(resultsCollector.getFirstUserId(), "a888a1c57cf6af2ffee687bfdd7dc4c5");
        assertEquals(resultsCollector.getNumberOfUser(), 97);
    }

    @Test
    public void testReadFileWhenSpendAmountNotAvailableAndFormatIssue() throws Exception {
        String csvFile = "test_file_spend_issue.csv";
        ResultsCollector resultsCollector = fileReader.readFile(csvFile);

        assertEquals(resultsCollector.getNumberOfDeviceResolution(), 28);
        assertEquals(resultsCollector.getTotalSpend(), 50085);
        assertEquals(resultsCollector.getFirstUserId(), "a888a1c57cf6af2ffee687bfdd7dc4c5");
        assertEquals(resultsCollector.getNumberOfUser(), 98);
    }

    @Test
    public void testReadFileWhenUserIdNotAvailableAndFormatIssue() throws Exception {
        String csvFile = "test_file_userid_issue.csv";
        ResultsCollector resultsCollector = fileReader.readFile(csvFile);
        System.out.println(resultsCollector);
        assertEquals(resultsCollector.getNumberOfDeviceResolution(), 28);
        assertEquals(resultsCollector.getTotalSpend(), 50085);
        assertEquals(resultsCollector.getFirstUserId(), "a888a1c57cf6af2ffee687bfdd7dc4c5");
        assertEquals(resultsCollector.getNumberOfUser(), 98);
    }

    @Test
    public void testReadFileWhenDeviceNotAvailableAndFormatIssue() throws Exception {
        String csvFile = "test_file_device_issue.csv";
        ResultsCollector resultsCollector = fileReader.readFile(csvFile);

        assertEquals(resultsCollector.getNumberOfDeviceResolution(), 28);
        assertEquals(resultsCollector.getTotalSpend(), 49031);
        assertEquals(resultsCollector.getFirstUserId(), "a888a1c57cf6af2ffee687bfdd7dc4c5");
        assertEquals(resultsCollector.getNumberOfUser(), 96);
    }
}