package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.dao.CustomerDao;
import ir.maktab.homeServiceProvider.dao.ExpertDao;
import ir.maktab.homeServiceProvider.model.enumration.Role;
import ir.maktab.homeServiceProvider.model.enumration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.service.UserService;
import ir.maktab.homeServiceProvider.util.ValidateInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static UserService userService = new UserService();
    static ExpertDao expertDao = new ExpertDao();
    static CustomerDao customerDao = new CustomerDao();

    public static void main(String[] args) {
        //UserService userservice = context.getBean(UserService.class);
        mainMenu();
    }


    public static void mainMenu() {
        System.out.println("********* welcome *********");
        while (true) {
            try {
                System.out.println("1.managerMenu   2.userMenu");
                String choice = scanner.nextLine().trim();
                System.out.println(choice);
                if (choice.equals("1")) {
                    managerMenu();
                } else if (choice.equals("2")) {
                    userMenu();
                } else if (choice.equals(null) || choice.length() == 0) {
                    throw new RuntimeException("❌❌❌ field can not be empty ❌❌❌");
                } else
                    throw new RuntimeException("❌❌❌ enter true number ❌❌❌");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void userMenu() {
        System.out.println("********* userMenu *********");
        while (true) {
            System.out.println("1.Log in   2.Sign Up    3.back to menu");
            try {
                String choice = scanner.nextLine().trim();
                System.out.println(choice);
                if (choice.equals("1")) {
                    logIn();
                } else if (choice.equals("2")) {
                    signUp();
                } else if (choice.equals("3")) {
                    mainMenu();
                } else if (choice.equals(null) || choice.length() == 0) {
                    throw new RuntimeException("❌❌❌ field can not be empty ❌❌❌");
                } else
                    throw new RuntimeException("❌❌❌ enter true number ❌❌❌");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void logIn() {
        System.out.println("enter your username");
        String username = scanner.nextLine();
        System.out.println("enter your password");
        String password = scanner.nextLine();
        UserService userService = context.getBean(UserService.class);
        User foundUser = userService.findUserByUseAndPass(username, password);
        if (foundUser == null) {
            System.out.println("you are new , you have to signUp ");
            signUp();
        } else {
            if (foundUser.getRole().equals(Role.EXPERT))
                expertMenu((Expert) foundUser);
            customerMenu((Customer) foundUser);
        }
    }

    public static void signUp() {
        User user = new User();
        user.setStatus(UserRegistrationStatus.NEW);
        System.out.println("********* which one: 1.expert  2.customer ***********");
        String role = scanner.nextLine();
        if (role.equals("1")) {
            user.setRole(Role.EXPERT);
        } else if (role.equals("2")) {
            user.setRole(Role.CUSTOMER);
        } else
            throw new RuntimeException("insert true number");

        String firstName;
        String lastName;
        String password;
        String email;

        outerName:
        while (true) {
            try {
                System.out.println("enter your name => without space,number,character ");
                String name = scanner.nextLine();
                if (ValidateInput.validName(name)) {
                    firstName = name;
                    break outerName;
                } else
                    throw new RuntimeException("input true option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        outerFamily:
        while (true) {
            try {
                System.out.println("enter your lastName => without space,number,character ");
                String family = scanner.nextLine();
                if (ValidateInput.validName(family)) {
                    lastName = family;
                    break outerFamily;
                } else
                    throw new RuntimeException("input true option");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        outereEmail:
        while (true) {
            try {
                System.out.println("enter your lastName => without space,number,character ");
                String emailInput = scanner.nextLine();
                if (ValidateInput.validEmail(emailInput)) {
                    if (!userService.duplicateEmail(emailInput)) {
                        email = emailInput;
                        break outereEmail;
                    } else {
                        throw new RuntimeException("duplicate email");
                    }

                } else
                    throw new RuntimeException("input true email => like neda_1373@gmail.com");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("enter your username");
        String username = scanner.nextLine();

        outerPasswoord:
        while (true) {
            try {
                System.out.println("enter your password => include number,character,uppercase,and 8 limit ");
                String readPassword = scanner.nextLine().substring(0, 8);
                boolean checkPassword = ValidateInput.validPassword(readPassword);
                if (checkPassword) {
                    password = readPassword;
                    break outerPasswoord;
                } else
                    throw new RuntimeException("password must include these items:✔ At least 8 chars\n" +
                            "✔Contains at least one digit\n" +
                            " ✔Contains at least one lower alpha char and one upper alpha char\n" +
                            " ✔Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                            " ✔Does not contain space, tab");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        // user = User.builder().name(firstName).lastName(lastName).email(email).username(username).password(password)..build();//?????درسته تا اینجا ؟ اون بال دوتاشو ست کردم که رولش و وضعیتش بعد اینجا بیلد میشه؟
        user.setStatus(UserRegistrationStatus.WAITING_FOR_CONFIRM);
        user.setLastName(lastName);
        user.setName(firstName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        if (role.equals("1")) {
            //todo give picture
            expertDao.save((Expert) user);//???? اینجا چیکار کنم؟ بهتره که واسه هر متخصص و مشتری دایو بسازم و سرویس جدا؟
            expertMenu((Expert) user);
        }
        userService.saveUser(user);
        customerMenu((Customer) user);

    }

    public static void customerMenu(Customer customer) {
        System.out.println("******* welcome " + customer.getName() + " " + customer.getLastName() + " ******");
        System.out.println("1.change password   2.see main services  3.new order");

    }

    public static void expertMenu(Expert expert) {
        System.out.println("******* welcome " + expert.getName() + " " + expert.getLastName() + " ******");
        System.out.println("1.change password   2.see main services  3.see orders  4.offer  3.add an service to yor profile");

    }

    public static void changePass(User user) {
    }

    public static void managerMenu() {
        System.out.println("********* managerMenu *********");

        while (true) {
            try {
                System.out.println("2.back to menu");
                String choiceStr = scanner.nextLine().trim();
                int choice = Integer.parseInt(choiceStr);
                if (choice == 1) {
                    //todo
                } else if (choice == 2) {
                    mainMenu();
                } else if (choiceStr.equals(null) || choiceStr.length() == 0 || choice == 0) {
                    throw new RuntimeException("field can not be empty");
                } else
                    throw new RuntimeException("enter true number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
     /* System.out.println("1.add new user(Expert / Client)\n" +
                "2.change password(for user)\n" +
                "3.report list of users and filtering them\n" +
                "4.add a new services or sub-services\n" +
                "5.add,edit,and remove specialists from existing services in the system\n" +
                "6.Ability to add, receive lists, edit and delete for any entity");

    }*/
}