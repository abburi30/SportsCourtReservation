package com.project.service.EmailService;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {
	
	private static String username = "ucmjavaproject@gmail.com";
	private static String password = "Praveen@12345";

	public static void sendconfirmationlink(int booking_id, String fn, String eid, String reason) {
		
		//using smtp protocol for sending email
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ucmjavaproject@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(eid));
			
			if (reason == "booking") {
				//Defining subject
				message.setSubject("Booking Pending!");
				//defining Body of email
				message.setText("Dear " + fn + " ," + "\n\n\t Your booking Id is : " + booking_id
						+ "\n\n Please click on this URL to Confirm the reservation "
						+ "http://localhost:8080/ProjectTest/ConfirmCourt?booking_id="
						+ booking_id);
				// ec2-54-210-70-217.compute-1.amazonaws.com
			} 
			
			else if (reason == "cancelByDate") {
				
				
				message.setSubject("Your Booking has cancelled");
				message.setText("Dear " + fn + " ," + "\n\n\t Your booking Id is : " + booking_id
						+ "\n\n Your Booking is cancelled By Admin");
				
			} 
			
			else if (reason == "broadcast") {
				
				message.setSubject("Broadcast message");
				message.setText("Dear," + "\n\n\t " + fn);
				
			}

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
