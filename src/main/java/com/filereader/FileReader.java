package com.filereader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FileReader {

    final static Logger logger = Logger.getLogger(FileDownloader.class);
    private static final String[] EMPTY_ARRAY = new String[0];
    private static final int DEVICE_WIDTH = 960;
    private static final int DEVICE_HEIGHT = 640;

    /**
     * Method read and parse csv file and collect data into Bean
     * @param fileName
     * @return ResultsCollector
     * @throws FileNotFoundException
     */
    public ResultsCollector readFile(final String fileName) throws IOException {
        logger.info("Parsing  "+fileName);
        int totalUsers = 0;
        int deviceResolutionCtr = 0;
        String firstUserId = null;
        Long totalSpend = 0L;

        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(fileName);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (isHeader(line))
                    continue;

                String[] splitArr = isValidLine(line);
                if (splitArr.length < 6 ) {
                    logger.error("Problem found in line, input fields count are not correct "+ splitArr.length);
                    continue;
                }
                // Total Device Resolution Counter
                try {
                    if (Integer.parseInt(splitArr[5]) == DEVICE_HEIGHT && Integer.parseInt(splitArr[4]) == DEVICE_WIDTH )
                        deviceResolutionCtr++;
                } catch (NumberFormatException nfe) {
                    logger.error("Problem found during parsing of Device Height and Device Width column: "+nfe.getMessage());
                    continue;
                }
                // First Joined
                try {
                    firstUserId = DateProcessor.findFirstJoined(splitArr[1], splitArr[0]);
                } catch (DateTimeParseException dtpe) {
                    logger.error("Problem found during parsing of Date column: "+dtpe.getMessage());
                    continue;
                }
                // Total amount spend
                try {
                    totalSpend = totalSpend + Long.parseLong(splitArr[2]);
                } catch (NumberFormatException nfe) {
                    logger.error("Problem found during parsing of spend column: "+nfe.getMessage());
                    continue;
                }
                totalUsers++;
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        ResultsCollector resultsCollector = new ResultsCollector();
        resultsCollector.setNumberOfUser(totalUsers);
        resultsCollector.setNumberOfDeviceResolution(deviceResolutionCtr);
        resultsCollector.setTotalSpend(totalSpend);
        resultsCollector.setFirstUserId(firstUserId);
        logger.info("Output after processing "+resultsCollector);
        return resultsCollector;
    }

    /**
     * Method check if line is header or not
     * @param line
     * @return boolean
     */
    private boolean isHeader(final String line) {
        if (line.contains("user_id") || line.contains("date_joined") ||
                line.contains("spend") || line.contains("milliseconds_played") ||
                line.contains("device_height") || line.contains("device_width")) {
            logger.debug("Ignoring header");
            return true;
        }
        return false;
    }

    /**
     * Method validate line against blank or no value for each element
     * @param line
     * @return String []
     */
    private String [] isValidLine(final String line) {
        String [] lineItems = line.split(",");

        if (line.split(",").length < 6 || (lineItems[0].isEmpty() || lineItems[0].equals(" ")) ||
                (lineItems[1].isEmpty() || lineItems[1].equals(" ")) || (lineItems[2].isEmpty() || lineItems[2].equals(" ")) ||
                (lineItems[3].isEmpty() || lineItems[3].equals(" ")) || (lineItems[4].isEmpty() || lineItems[4].equals(" ")) ||
                (lineItems[5].isEmpty() || lineItems[5].equals(" "))) {
            logger.error("Problem found during validation of line "+line);
            return EMPTY_ARRAY;
        }
        return lineItems;
    }
}