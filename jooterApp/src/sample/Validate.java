package sample;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validate {

    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public String getInput() {

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String validateCardNumber() {

        String cardNo = null;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert card number");
        while (flag) {
            if (sc.hasNextLine()) {
                cardNo = sc.nextLine();
                if (cardNo.length() == 16) {
                    flag = false;
                } else {
                    System.out.println("Invalid card number, enter 16 digit long number");
                }
            }
        }
        return cardNo;
    }

    public boolean validateEmail(String email) {

        boolean flag = false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateUserLogin(String login) {

        boolean flag = false;

                if (login.length() >= 5) {
                    flag = true;
                }

        return flag;
    }

    public boolean validateUserPassword(String password) {

        boolean flag = false;
        String first_letter;
        boolean hasNumber = false;

                for (int i = 0; i < password.length(); i++) {

                    if (Character.isDigit(password.charAt(i))) {
                        hasNumber = true;
                        break;
                    }
                }
                first_letter = password.substring(0, 1).toUpperCase();

                if ((password.length() >= 8) && (first_letter.equals(String.valueOf(password.charAt(0)))) && (hasNumber)) {
                    flag = true;
                }

        return flag;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}