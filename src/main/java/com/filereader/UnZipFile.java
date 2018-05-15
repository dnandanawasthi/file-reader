package com.filereader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class UnZipFile {

	/**
	 * Method unzip downloaded file from remote server
	 * @param gzFile
	 * @param csvFile
	 * @throws IOException
	 */
	void unZipFile(final String gzFile, final String csvFile) throws IOException {
		
		byte[] buffer = new byte[1024];

		GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(gzFile));

		FileOutputStream fileOutputStream = new FileOutputStream(csvFile);

		try {
			int len;
			while ((len = gzipInputStream.read(buffer)) > 0) {
				fileOutputStream.write(buffer, 0, len);
			}
		} finally {
			gzipInputStream.close();
			fileOutputStream.close();
		}
	}
}
