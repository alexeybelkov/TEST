package famcs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {

    private static String INUSERS_FILE_NAME = "users.txt";
    private static String OUTUSERS_FILE_NAME = "outUsers.txt";
    private static String INPUT_FILE_NAME = "data.txt";

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        if (args.length >= 3) {
            INUSERS_FILE_NAME = args[0];
            INPUT_FILE_NAME = args[1];
            OUTUSERS_FILE_NAME = args[2];
        } else {
            System.out.println("...Default files are used...");
        }

        Scanner inputUsers = new Scanner(new FileReader(INUSERS_FILE_NAME));
        Scanner inputFile = new Scanner(new FileReader(INPUT_FILE_NAME));
        Map<String, List<SingleArl>> companyMap = readMapOfCompanies(inputFile);

        User currentUser = new User();
        Map<String, User> usersMap = regList(inputUsers);
        try (Scanner inSwitch = new Scanner(System.in)) {

            System.out.println("\"-1\" for exit\n\"1\" for  registration\n\"2\" for login\n\"3\" to see all companies\n" +
                    "\"4\" to find planes by their numbers\n\"5\" for  statistics\n\"6\" for top plane");

            int choice = -404;
            while (choice != -1) {
                System.out.println("Choose:");
                choice = inSwitch.nextInt();
                switch (choice) {

                    case -1:
                        break;

                    case 1:

                        if (!currentUser.getReg()) {
                            currentUser = registration();

                            while (usersMap.containsKey(currentUser.getLogin())) {

                                System.out.println("Please choose another login");
                                Scanner in = new Scanner(System.in);
                                currentUser.setLogin(in.nextLine());

                            }
                            usersMap.put(currentUser.getLogin(), currentUser);
                            System.out.println("Registration completed!");
                        } else {
                            System.out.println("You are already registered");
                        }
                        break;

                    case 2:

                        Scanner in = new Scanner(System.in);
                        System.out.println("enter your login:");
                        String login = in.nextLine();
                        System.out.println("enter your password:");
                        String password = in.nextLine();

                        if (usersMap.containsKey(login) && usersMap.get(login).getPassword().compareTo(password) == 0) {
                            currentUser = new User(usersMap.get(login));
                            System.out.println("login comleted");
                        } else {
                            System.out.println("Wrong login or password or User with this login and password didn't register");
                        }

                        break;
                    case 3:
                        for (Map.Entry<String, List<SingleArl>> e : companyMap.entrySet()) {

                            for (SingleArl sa : e.getValue()) {
                                System.out.println(sa.toString());
                            }
                        }
                        break;

                    case 4:
                        if (!currentUser.getReg() && !currentUser.getAdminCheck()) {
                            System.out.println("Only for admins");
                            break;
                        } else {
                            System.out.println("Enter num");
                            in = new Scanner(System.in);
                            String num = in.nextLine();
                            for (Map.Entry<String, List<SingleArl>> e : companyMap.entrySet()) {
                                for (SingleArl sa : e.getValue()) {
                                    if (sa.getNumber() == num) {
                                        System.out.println(sa.toString());
                                    }
                                }
                            }

                        }

                        break;

                    case 5:
                        for (Map.Entry<String, List<SingleArl>> e : companyMap.entrySet()) {
                            float mean = 0;
                            for (SingleArl sa : e.getValue()) {
                                mean += sa.getqControl();
                            }
                            System.out.println("amount of planes = " + e.getValue().size() + " Mean quality = " + mean / e.getValue().size());
                        }
                        break;

                    case 6:
                        System.out.println("will be soon");
                        break;
                }

                FileWriter outputFile = new FileWriter(OUTUSERS_FILE_NAME, true);
                writeToFiles(outputFile, usersMap);

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public static User registration() {

        String[] args = new String[5];

        String[] messages = {"Name:", "Login:", "Email:", "Password:", "Role [ADMIN or USER]:"};

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < args.length; i++) {
            System.out.println(messages[i]);
            args[i] = in.nextLine();
        }

        return new User(args);
    }

    public static Map<String, User> regList(Scanner input) {

        String[] args;
        Map<String, User> usersMap = new HashMap<>();

        while (input.hasNextLine()) {
            args = input.nextLine().split(";");
            usersMap.put(args[1], new User(args));
        }

        return usersMap;
    }

    public static Map<String, List<SingleArl>> readMapOfCompanies(Scanner input) throws ParseException {

        String[] args;
        Map<String, List<SingleArl>> AirlineMap = new HashMap();

        while (input.hasNextLine()) {

            args = input.nextLine().split(";");

            if (AirlineMap.containsKey(args[1])) {
                AirlineMap.get(args[1]).add(new SingleArl(args));
            } else {
                AirlineMap.put(args[1], new ArrayList<>());
                AirlineMap.get(args[1]).add(new SingleArl(args));
            }

        }

        return AirlineMap;
    }

    private static void writeToFiles(FileWriter output, Map<String, User> regList) throws IOException {
        if (regList.isEmpty()) {
            output.write("NONE");
        }
        for (Map.Entry user : regList.entrySet()) {
            output.write(user.toString() + System.lineSeparator());
        }
    }
}


