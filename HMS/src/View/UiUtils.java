package View;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UiUtils {
    // To store user input;
    public static String userInput() {
        String userInput;
        Scanner scanner = new Scanner(System.in);
            
            userInput = scanner.nextLine();
            // blank content is invaild
            while (userInput.isBlank()) {
                print("Please input unempty content");
                userInput = scanner.nextLine();
            }
            return userInput;

    }

    // userName verification
    public static String userNameVerification() {
        
        String userName;
        userName = userInput();
        while (userName.isBlank()) {
            userName = userInput();
            print("User name cannot be empty. Please input again.");
        }
        return userName;
    }

    // To password verification
    public static String passwordVerification() {
        // first user input password;
        String password = null;
        // second user input password;
        String passwordAgain = null;
        // verfy password is vaild;
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        print("please input your password. For safty concern, at least eight characters, at least contain one letter and one number.");
        // gather user input for password
        password = userInput();
        while (!Pattern.matches(pattern, password)) {
            print("Password is invalid. At least eight characters, at least one letter and one number");
            password = userInput();
        }
        print("Please input your password again:");
        passwordAgain = userInput();
        isSamePassword(password, passwordAgain);
        return password;
    }

    public static void isSamePassword(String password, String passwordAgain) {
        if (!passwordAgain.equals(password)) {
            print("Passwords entered twice are inconsistent");
            passwordVerification();
        }
    }

    // To get today's week day
    public static Integer getWeekDay() {
        Date date = new Date();
        Integer[] weeks = { 7, 1, 2, 3, 4, 5, 6 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    // TO string
    public static String toString(Object[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append("\"");
        for (int i = 0;; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append("\"").toString();
            b.append(",");
        }
    }

    // To make a beatiful print;
    public static void print(String str) {
        int strLength = str.length();
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        for (int i = 0; i < strLength; i++) {
            sb.append("-");
        }
        sb.append("\n");
        System.out.println(sb.toString() + str + sb.toString());
    }
}