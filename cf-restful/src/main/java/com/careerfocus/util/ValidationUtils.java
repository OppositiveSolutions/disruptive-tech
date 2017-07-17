package com.careerfocus.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class ValidationUtils {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[a-zA-Z][a-zA-Z]*");
    }

    public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
    }

    public static boolean validateGender(String gender) {
        if (gender != null && (gender.equals("Male") || gender.equals("Female"))) {
            return true;
        }
        return false;
    }

    public static boolean isValidMMDDYYYY(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        return sdf.parse(date, new ParsePosition(0)) != null;
    }

}
