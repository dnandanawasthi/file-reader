package com.filereader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

public class FileDownloaderTest {

    private FileDownloader fileDownloader;

    @Before
    public void init() {
        fileDownloader = new FileDownloader();
    }

    //@Test
    public void testDownloadFileSuccessfully() throws IOException {
        URL url = new URL("https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz");
        String gzFile = "test_file.gz";
        fileDownloader.downloadFile(url, gzFile);
        File f = new File(gzFile);
        assertTrue(f.exists());
        assertTrue(f.isFile());
        assertEquals(f.getName(), gzFile);
    }

    //@Test(expected = IOException.class)
    public void testDownloadFileThrowException() throws IOException {
        URL url = new URL("https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data1.csv.gz");
        String gzFile = "test_file.gz";
        fileDownloader.downloadFile(url, gzFile);
    }

}
