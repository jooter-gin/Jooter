import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DataSource ds = DataSource.getInstance();
        boolean result = ds.open();

        if(result) {
            System.out.println("Connection successfully established");
            System.out.println("********");

        }else {
            System.out.println("Cannot connect to the database");
            System.out.println("********");
        }

        while (true) {

            Scanner sc = new Scanner(System.in);
            int choice =0;
            boolean isTrue = true;
            while(isTrue){
                System.out.println("Choose the option(1.To registry 2.To sign in 3.To quit)");
                if(sc.hasNextInt()){
                    choice = sc.nextInt();
                    isTrue = false;
                }else{
                    System.out.println("Please enter a number");
                    sc.next();
                }
            }

            switch (choice) {

                case 1:
                    User user = new User();
                    System.out.println("Insert name");
                    user.setUserName(user.getInput());
                    System.out.println("Insert surname");
                    user.setUserSurname(user.getInput());

                    boolean flag = true;

                    while (flag) {

                        user.setUserLogin(user.validateUserLogin());
                        user.setUserEmail(user.validateEmail());
                        boolean isRegistered = Registration.checkIfUserExists(user.getUserLogin(),user.getUserEmail());
                        if (!isRegistered) {

                            flag = false;
                            user.setUserPassword(user.validateUserPassword());
                            user.setUserCardNo(user.validateCardNumber());
                            Registration.registerUser(user);
                        }else{
                            System.out.println("User already exists");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Nothing yet");
                    break;

                case 3:
                    System.out.println("Closing connections");
                    DataSource.getInstance().close();
                    System.exit(0);
                    break;

            }
        }
    }

}

