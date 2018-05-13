package com.careerfocus.dao;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Repository;

import com.careerfocus.constants.Constants;

@Repository
public class MailDAO {

	private static final String HOSTNAME = "smtp.gmail.com";
	private static final int SMTP_PORT = 587;// or 587 465
	private static final String USERNAME = "alexgp.think@gmail.com";
	private static final String PASSWORD = "alex-10Bfb632";
	private static final String FROM_ADDRESS = "noreply@careerfocus.in";
	private static final String EMAIL_LOGO = "http://localhost:8080/CF_UI/img/mail_logo.png";

	public void welcomeMailUser(String patientEmailId, String password, String welcomeMessage)
			throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(FROM_ADDRESS, "CareerFocus Support");
		email.setSubject("Welcome to Career Focus");
		// embed the image and get the content id
		URL url = new URL(EMAIL_LOGO);
		String cid = "";
		try {
			cid = email.embed(url, "Career Focus logo");
		} catch (Exception e) {
		}

		StringBuilder htmlBody = new StringBuilder();
		htmlBody.append("<html>");

		htmlBody.append("<div style=\" max-width:600px;margin:0 auto;font-size:14px;width:auto; \">");
		htmlBody.append("<a href=\"https://www.careerfocus.in\">");
		htmlBody.append("<img style=\"width: 100% !important\" width=\"595\" align=\"left\" src=\"cid:");
		htmlBody.append(cid);
		htmlBody.append("\">");
		htmlBody.append("</a>");
		htmlBody.append("<div style=\"background:#ffffff;padding:10px 0; clear: both;\">");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\">Hello</p>");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\">" + welcomeMessage + "</p>");

		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\">You may begin using this service by logging on to <a href=\"https://www.careerfocus.in\">https://www.careerfocus.in</a> with the following credentials:</p>");
		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\">Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		htmlBody.append(patientEmailId);
		htmlBody.append("<br>");
		htmlBody.append("Password: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		htmlBody.append(password);
		htmlBody.append("</p>");
		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\"> For any questions that you may have, write to us at <a href=\"mailto:career.focus@ymail.com\">career.focus@ymail.com</a></p>");
		htmlBody.append("<p style=\"margin-top: 25px; margin-bottom: 10px;\">");
		htmlBody.append("Thank you");
		htmlBody.append("<br>");
		htmlBody.append("CareerFocus Team");
		htmlBody.append("<br>");
		htmlBody.append("</p>");

		htmlBody.append("</div></html>");
		System.out.println("HTML\n" + htmlBody);
		email.setHtmlMsg(htmlBody.toString());
		if (patientEmailId == null || patientEmailId == "") {
			return;
		}
		email.addTo(patientEmailId.toLowerCase());
		email.addBcc("alexgp007@gmail.com");
		System.out.println("EMAIL\n" + email);
		try {
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendContentMail(String contactEmailId, String phone, String content)
			throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(FROM_ADDRESS, "CareerFocus Support");
		email.setSubject("Welcome to Career Focus");
		// embed the image and get the content id
		URL url = new URL(EMAIL_LOGO);
		String cid = "";
		try {
			cid = email.embed(url, "Career Focus logo");
		} catch (Exception e) {
		}

		StringBuilder htmlBody = new StringBuilder();
		htmlBody.append("<html>");

		htmlBody.append("<div style=\" max-width:600px;margin:0 auto;font-size:14px;width:auto; \">");
		htmlBody.append("<a href=\"https://www.careerfocus.in\">");
		htmlBody.append("<img style=\"width: 100% !important\" width=\"595\" align=\"left\" src=\"cid:");
		htmlBody.append(cid);
		htmlBody.append("\">");
		htmlBody.append("</a>");
		htmlBody.append("<div style=\"background:#ffffff;padding:10px 0; clear: both;\">");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\">Hello</p>");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\"> Inquiry from Website</p>");

		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\">You may begin using this service by logging on to <a href=\"https://www.careerfocus.in\">https://www.careerfocus.in</a> with the following credentials:</p>");
		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\">Contact Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		htmlBody.append(contactEmailId);
		htmlBody.append("<br>");
		htmlBody.append("Phone no: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		htmlBody.append(phone);
		htmlBody.append("</p><p>");
		htmlBody.append("<b>Content:</b><br>");
		htmlBody.append(content);
		htmlBody.append("</p>");
		htmlBody.append("<p style=\"margin-top: 25px; margin-bottom: 10px;\">");
		htmlBody.append("Thank you");
		htmlBody.append("<br>");
		htmlBody.append("CareerFocus Team");
		htmlBody.append("<br>");
		htmlBody.append("</p>");

		htmlBody.append("</div></html>");

		email.setHtmlMsg(htmlBody.toString());
		if (contactEmailId == null || contactEmailId == "") {
			return;
		}
		email.addTo(Constants.CF_EMAIL_ID);
		email.addBcc("alexgp007@gmail.com");
		try {
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendPasswordResetLinkMail(String patientEmailId, String uq_, String welcomeMessage)
			throws EmailException, MalformedURLException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOSTNAME);
		email.setSmtpPort(SMTP_PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		email.setSSLOnConnect(true);
		email.setFrom(FROM_ADDRESS, "CareerFocus Support");
		email.setSubject("Career Focus Password Reset Link");
		// embed the image and get the content id
		URL url = new URL(EMAIL_LOGO);
		String cid = "";
		try {
			cid = email.embed(url, "Career Focus logo");
		} catch (Exception e) {
		}

		StringBuilder htmlBody = new StringBuilder();
		htmlBody.append("<html>");

		htmlBody.append("<div style=\" max-width:600px;margin:0 auto;font-size:14px;width:auto; \">");
		htmlBody.append("<a href=\"https://www.careerfocus.in\">");
		htmlBody.append("<img style=\"width: 100% !important\" width=\"595\" align=\"left\" src=\"cid:");
		htmlBody.append(cid);
		htmlBody.append("\">");
		htmlBody.append("</a>");
		htmlBody.append("<div style=\"background:#ffffff;padding:10px 0; clear: both;\">");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\">Hello</p>");
		htmlBody.append("<p style=\"margin-top: 10px; margin-bottom: 10px;\">" + welcomeMessage + "</p>");

		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\">You can reset your password by clicking the link <a href=\"https://www.careerfocus.in/password/change?uq_="
						+ uq_ + "\">reset password</a> with the following credentials:</p>");
		htmlBody.append(
				"<p style=\"margin-top: 10px; margin-bottom: 10px;\"> For any questions that you may have, write to us at <a href=\"mailto:career.focus@ymail.com\">career.focus@ymail.com</a></p>");
		htmlBody.append("<p style=\"margin-top: 25px; margin-bottom: 10px;\">");
		htmlBody.append("Thank you");
		htmlBody.append("<br>");
		htmlBody.append("CareerFocus Team");
		htmlBody.append("<br>");
		htmlBody.append("</p>");

		htmlBody.append("</div></html>");
		System.out.println("HTML\n" + htmlBody);
		email.setHtmlMsg(htmlBody.toString());
		if (patientEmailId == null || patientEmailId == "") {
			return;
		}
		email.addTo(patientEmailId.toLowerCase());
		email.addBcc("alexgp007@gmail.com");
		System.out.println("EMAIL\n" + email);
		try {
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
