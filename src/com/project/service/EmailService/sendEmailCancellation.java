package com.project.service.EmailService;

import java.util.ArrayList;

public class sendEmailCancellation {
	static String reason = "cancelByDate";

	public static void sendEmailConfirmation(ArrayList email_list) {
		//Taking the booking id from the list of all values.
		for (int j = 0; j < email_list.size(); j = j + 3) {
			int book_id = Integer.parseInt((String) email_list.get(0));
			
			//calling email service to send the status.
			EmailService.sendconfirmationlink(book_id, (String) email_list.get(j + 1), (String) email_list.get(j + 2),
					reason);

		}
	}

}
