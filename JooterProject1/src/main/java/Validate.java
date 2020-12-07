import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validate {

    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public String getInput() {

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String validateCardNumber(){

        String cardNo = null;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert card number");
        while(flag) {
            if (sc.hasNextLine()) {
                cardNo = sc.nextLine();
                if(cardNo.length()==16){
                    flag = false;
                }else{
                    System.out.println("Invalid card number, enter 16 digit long number");
                }
            }
        }
        return cardNo;
    }

    public String validateEmail(){

        Scanner sc = new Scanner(System.in);
        String email = null;
        boolean flag = true;
        Pattern pattern = Pattern.compile(regex);
        while(flag) {

            System.out.println("Please insert email");
            if (sc.hasNextLine()) {

                email = sc.nextLine();
                Matcher matcher = pattern.matcher(email);
                if(matcher.matches()){

                    flag = false;
                }else{
                    System.out.println("Invalid email");
                }

            }
        }

        return email;
    }

    public String validateUserLogin() {

        String login = null;
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while (flag) {

            System.out.println("Insert login");

            if (sc.hasNextLine()) {

                login = sc.nextLine();
                if (login.length() >= 5) {
                    flag = false;
                }else{
                    System.out.println("Invalid login. Login must be at least 5 letters long");
                }
            }

        }
        return login;
    }

    public String validateUserPassword() {

        String password = null;
        boolean flag = true;
        String first_letter;
        boolean hasNumber = false;
        Scanner sc = new Scanner(System.in);

        while (flag) {

            System.out.println("Please insert password");

            if (sc.hasNextLine()) {

                password = sc.nextLine();
                for (int i = 0; i < password.length(); i++) {

                    if (Character.isDigit(password.charAt(i))) {
                        hasNumber = true;
                        break;
                    }
                }
                first_letter = password.substring(0, 1).toUpperCase();

                if ((password.length() >= 7) && (first_letter.equals(String.valueOf(password.charAt(0)))) && (hasNumber)) {
                    flag = false;
                }else{
                    System.out.println("Invalid password. Password must be 8 letters long, has at least one digit and starts with uppercase");
                }
            }
        }
        return password;
    }
}
