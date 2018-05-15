package com.filereader;

import org.apache.log4j.Logger;
import java.net.URL;

public class FileDownloaderApp {

	final static Logger logger = Logger.getLogger(FileDownloaderApp.class);

	private static String gzFile = "test_file.gz";
	private static String csvFile = "test_file.csv";


	public static void main(String[] args) {
		String fileName = null;
		try {
			if (args.length > 0) {
				fileName = args[0];
			} else {
				fileName = "https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz";
				logger.warn("No input file provided, default file will be used");
			}
			URL url = new URL(fileName);
			FileDownloaderApp fileDownloaderApp = new FileDownloaderApp();
			System.out.println(fileDownloaderApp.testFunctionality(url));
		} catch (Exception e) {
			logger.error("Problem found during execution : "+ e.getMessage());
		}
	}

	public ResultsCollector testFunctionality(URL url) throws Exception{
		FileDownloader fileDownloader = new FileDownloader();
		UnZipFile unZipFile = new UnZipFile();
		FileReader fileReader = new FileReader();

		fileDownloader.downloadFile(url, gzFile);
		unZipFile.unZipFile(gzFile, csvFile);
		return fileReader.readFile(csvFile);
	}
}
