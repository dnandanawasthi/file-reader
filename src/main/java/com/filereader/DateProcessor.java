package com.filereader;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class DateProcessor {

	private static OffsetDateTime firstDate = null;
	private static String firstUserId = null;

	/**
	 * Method compare date to find user id
	 * @param inputDate
	 * @param userId
     * @return
     */
	public static String findFirstJoined(String inputDate, String userId) {

		OffsetDateTime tmpDate = OffsetDateTime.parse (inputDate);

		if (firstDate == null) {
			firstDate = tmpDate;
			firstUserId = userId;
		}

		if (firstDate.isAfter(tmpDate)) {
			firstDate = tmpDate;
			firstUserId = userId;
		}

		return firstUserId;
	}

}
