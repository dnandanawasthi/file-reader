package com.filereader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class UnZipFileTest
{
    private UnZipFile unZipFile;

    @Before
    public void init() {
        unZipFile = new UnZipFile();
    }

    @Test
    public void testUnZipFileSuccessfully() throws IOException{
        String gzFile = "test_file.gz";
        String csvFile = "test_file.csv";
        unZipFile.unZipFile(gzFile, csvFile);
        File f = new File(csvFile);
        assertTrue(f.exists());
        assertTrue(f.isFile());
        assertEquals(f.getName(), csvFile);
    }

    @Test(expected = IOException.class)
    public void testUnZipFileThrowException() throws IOException {
        String gzFile = "test_file1.gz";
        String csvFile = "test_file1.csv";
        unZipFile.unZipFile(gzFile, csvFile);
    }
}
