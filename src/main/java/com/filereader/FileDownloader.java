package com.filereader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {

	/**
	 * Method to download file from provided remote server
	 * @param url
	 * @param gzFile
	 * @throws IOException
	 */
	void downloadFile(final URL url, final String gzFile) throws IOException {

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
		FileOutputStream out = new FileOutputStream(gzFile);

		try {
			int i = 0;
			byte[] bytesIn = new byte[1];
			while ((i = in.read(bytesIn)) >= 0) {
				out.write(bytesIn, 0, i);
			}
		}finally {
			out.close();
			in.close();
		}
	}
}
