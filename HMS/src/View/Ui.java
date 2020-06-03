package View;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import Controller.Account;
import Controller.Book;
import Controller.GuestQuery;
import Controller.StuffQuery;
import Model.BookFoodModel;
import Model.BookRoomModel;
import Model.Guest;

public class Ui {
    // To connect all ui parts;
    public static void userInteractive() {
        signUpOrLogin();
        menu();
    }

    // To know user signUp or login
    public static void signUpOrLogin() {
        // role: 1.stuff or 2.guest(as default); model: 1.login or 2.signUp(as
        // default);
        String password, userName, model, role;
        // user role, come from id
        // chose role
        UiUtils.print("Welcome to HMS\n[1]Stuff\n[2]Guest (as default)");
        role = UiUtils.userInput();
        // chose model
        UiUtils.print("You are\n[1]login\n[2]sign up (as default)");
        model = UiUtils.userInput();
        if (model.equals("1")) {
            // login
            UiUtils.print("UserName:");
            userName = UiUtils.userInput();
            UiUtils.print("Password:");
            password = UiUtils.userInput();
            // docking with controller;
            Account.login(role, userName, password);
        } else {
            // signUp
            UiUtils.print("Please input your user name for Sign up:");
            // check userName is valid; userInput is in userNameVerification();
            userName = UiUtils.userNameVerification();
            // check password is valid; userInput is in passwordVerification();
            password = UiUtils.passwordVerification();
            // docking with controller;
            UiUtils.print("Sign up successful, you do not have to login again.");
            Account.signUp(role, userName, password);
        }
    }

    // main menu;
    public static void menu() {
        try {
            if (0 < Account.token && Account.token < 10000) {
                // stuff menu;
                stuffmenu();
            } else {
                // guest menu;
                guestmenu();
            }
            // after have chooses
            menu();
        } catch (Exception a) {
            a.printStackTrace();
            UiUtils.print("there have some problems");
        }
        return;
    }

    // guset menu
    public static void guestmenu() throws Exception {
        UiUtils.print(
                "[1]book room(as default)\n[2]book food\n[3]check booked room\n[4]check booked food\n[5]cancel a room\n[6]cancel a food\n[7]update info");
        String roomOrFood = UiUtils.userInput();
        switch (Integer.valueOf(roomOrFood)) {
            case 2:
                bookFood();
                break;
            case 3:
                // check booked room
                checkBookedRoom();
                break;
            case 4:
                // check book food
                checkBookedFood();
                break;
            case 5:
                // back to index menu
                cancelRoom();
                break;
            case 6:
                cancelFood();
                break;
            case 7:
                updateInfo();
            default:
                // book room
                bookRoom();
                break;
        }
        router();
    }

    // stuff menu
    public static void stuffmenu() throws SQLException, Exception {
        UiUtils.print("[1]check all room status(default)\n" + "[2]check all empty room\n" + "[3]check all booked room\n"
                + "[4]check one room status by roomid\n" + "[5]check room by guest name\n"
                + "[6]check food by guest name");
        String checkType = UiUtils.userInput();
        switch (Integer.valueOf(checkType)) {
            case 2:
                checkAllEmptyRoom();
                break;
            case 3:
                checkAllBookedRoom();
                break;
            case 4:
                checkOneRoomStatusByRoomId();
                break;
            case 5:
                checkRoomByGuestName();
                break;
            case 6:
                checkFoodByGuestName();
                break;
            default:
                checkAllRoomStatus();
                break;
        }
        router();
    }

    public static void router() throws Exception {
        UiUtils.print("What do you want to do now?\n[1]log out\n[2]back to menu");
        String router = UiUtils.userInput();
        switch (Integer.valueOf(router)) {
            case 1:
                signUpOrLogin();
                break;
            case 2:
                menu();
                break;
            default:
                new Exception("please give a number in menu");
                break;
        }
    }

    public static void updateInfo() {
        String realname, passportId, telenumber;
        UiUtils.print("Please input your realname:");
        realname = UiUtils.userInput();
        UiUtils.print("Please input your passport id:");
        passportId = UiUtils.userInput();
        UiUtils.print("Please input your telephone number:");
        telenumber = UiUtils.userInput();
        Guest guest = new Guest().realname(realname).passportId(passportId).telenumber(telenumber);
        Account.update(guest);
        UiUtils.print("Update successfully");
    }

    // book food
    public static void bookFood() throws SQLException {
        int foodWeekDay, foodDay;
        String foodHour;
        int weekday = UiUtils.getWeekDay();
        UiUtils.print("Today is " + weekday
                + "day of this week, when do you want to book:\n[1]today\n[2]tomorrow\n[3]the day after tomorrow");
        foodDay = Integer.valueOf(UiUtils.userInput());
        if ((foodDay + weekday - 1) > 7) {
            foodWeekDay = foodDay + weekday - 8;
        } else {
            foodWeekDay = foodDay + weekday - 1;
        }
        UiUtils.print("When do you want food? specific time (hour)? Format: hh:mm");
        foodHour = UiUtils.userInput().trim();
        String foodTime = String.valueOf(foodWeekDay);
        String getFoodHave = GuestQuery.getFoodHave(foodTime);
        foodTime = "week " + foodDay + ", " + foodHour + " hour.";
        UiUtils.print(getFoodHave);
        UiUtils.print("Which food do you want to have? Please give me a name.");
        String foodName = UiUtils.userInput();
        BookFoodModel bfm = new BookFoodModel().foodName(foodName).guestId(Account.token).foodTime(foodTime);
        Book.bookFood(bfm);
        UiUtils.print("Congratulations! You book this food successfully.");
    }

    public static void checkBookedRoom() {
        ArrayList<String[]> rooms = GuestQuery.bookedRoom();
        String[] passedRooms = rooms.get(0);
        String[] processingRooms = rooms.get(1);
        UiUtils.print("Here is your booked rooms, which is passed.");
        int i = 0;
        while (passedRooms[i] != null) {
            System.out.println(passedRooms[i]);
            i++;
        }
        UiUtils.print("Here is your booked rooms, which is on processing.");
        int j = 0;
        while (processingRooms[j] != null) {
            System.out.println(processingRooms[j]);
            j++;
        }

    }

    public static void checkBookedFood() {
        ArrayList<String[]> bookedFood = GuestQuery.bookedFood();
        String[] passeFoods = bookedFood.get(0);
        String[] processingFoods = bookedFood.get(1);
        UiUtils.print("Here is your booked food which is passed.");
        int j = 0;
        while (passeFoods[j] != null) {
            System.out.println(passeFoods[j]);
            j++;
        }
        UiUtils.print("Here is your booked food which is on processing.");
        int i = 0;
        while (processingFoods[i] != null) {
            System.out.println(processingFoods[i]);
            i++;
        }
    }

    public static void baseOnRoomId() throws SQLException, Exception {
        UiUtils.print("please input room id which you want to book:");
        int roomId = Integer.valueOf(UiUtils.userInput());
        String startTime;
        UiUtils.print(
                "When do you want to start booking? format: yyyy-MM-dd HH:MM (Required: yyyy-MM-dd, HH:MM is optional). ");
        UiUtils.print("If you put 0, room are available from now to next day this time");
        startTime = UiUtils.userInput().trim();
        if (startTime.length() <= 3) {
            Date date = new Date();
            SimpleDateFormat dateFormat_min = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");// 设置当前时间的格式，为年-月-日 时-分-秒
            startTime = dateFormat_min.format(date);
        }
        UiUtils.print("How many days do you want to book, please input a number");
        int lastTime = Integer.valueOf(UiUtils.userInput());
        BookRoomModel brm = new BookRoomModel().guestId(Account.token).lastTime(lastTime).startTime(startTime)
                .roomId(roomId);
        Book.bookRoomById(brm);
    }

    public static void baseOnRoomType() throws SQLException, Exception {
        UiUtils.print("[1]Large doulbe bed\n[2]Large single bed\n[3]Small single bed\n[4]VIP room(as default)");
        String roomType = UiUtils.userInput();
        // from int to room types
        switch (Integer.valueOf(roomType)) {
            case 1:
                roomType = "Large doulbe bed";
                break;
            case 2:
                roomType = "Large single bed";
                break;
            case 3:
                roomType = "Small single bed";
                break;
            default:
                roomType = "VIP room";
                break;
        }
        String startTime;

        UiUtils.print(
                "When do you want to start booking? format: yyyy-MM-dd HH:MM (Required: yyyy-MM-dd, HH:MM is optional). ");
        UiUtils.print("If you put 0, room are available from now to next day this time");
        startTime = UiUtils.userInput().trim();
        if (startTime.length() <= 3) {
            Date date = new Date();
            SimpleDateFormat dateFormat_min = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");// 设置当前时间的格式，为年-月-日 时-分-秒
            startTime = dateFormat_min.format(date);
        }
        UiUtils.print("How many days do you want to book, please input a number");
        int lastTime = Integer.valueOf(UiUtils.userInput());
        // BookRoomModel brm1 = new BookRoomModel(roomType, token, startTimeDate1,
        // lastTime1);
        BookRoomModel brm = new BookRoomModel().roomType(roomType).id(Account.token).startTime(startTime)
                .lastTime(lastTime);
        Book.bookRoomByType(brm);
        UiUtils.print("Reservation successful!");
    }

    public static void bookRoom() throws Exception, SQLException {
        System.out.println("[1]base on room type(as default)\n[2]base on room id");
        String idOrType = UiUtils.userInput();
        switch (Integer.valueOf(idOrType)) {
            case 2:
                // base on room id
                baseOnRoomId();
                guestmenu();
                break;
            default:
                // base on room type
                baseOnRoomType();
                break;
        }
    }

    public static void cancelRoom() throws SQLException {
        checkBookedRoom();
        UiUtils.print("Which room do you want to cancel? Please give me a room id");
        int roomId = Integer.valueOf(UiUtils.userInput());
        BookRoomModel brm = new BookRoomModel().roomId(roomId).guestId(Account.token);
        Book.cancelRoom(brm);
        UiUtils.print("You cancel seccessfully");
    }

    private static void cancelFood() throws SQLException {
        checkBookedFood();
        UiUtils.print("Which food do you want to cancel? Please give me a name.");
        String foodName = UiUtils.userInput();
        BookFoodModel bfm = new BookFoodModel().foodName(foodName).guestId(Account.token);
        Book.cancelFood(bfm);
        UiUtils.print("You cancel seccessfully");
    }

    public static void checkAllEmptyRoom() throws SQLException {
        int i = 0;
        String[] emptyRoom = StuffQuery.emptyRoom();
        while (emptyRoom[i] != null) {
            System.out.print(emptyRoom[i] + ", ");
            i++;
        }
    }

    public static void checkAllBookedRoom() throws SQLException {
        String[] bookedRoom = StuffQuery.bookedRoom();
        int i = 0;
        while (bookedRoom[i] != null) {
            System.out.print(bookedRoom[i]);
            i++;
        }
    }

    public static void checkOneRoomStatusByRoomId() throws SQLException {
        UiUtils.print("Which room do you want to check? Please input a room id");
        String roomId = UiUtils.userInput();
        int oneStatus = StuffQuery.oneStatus(roomId);
        if (oneStatus == 0) {
            UiUtils.print("This room is empty.");
        } else {
            UiUtils.print("This room is booked.");
        }
    }

    public static void checkRoomByGuestName() throws SQLException {
        UiUtils.print("Which guest do you want to check?");
        String guestName = UiUtils.userInput();
        String[] roomByGuestName = StuffQuery.roomByGuestName(guestName);
        int i = 0;
        while (roomByGuestName[i] != null) {
            System.out.print(roomByGuestName[i] + ", ");
            i++;
        }
    }

    public static void checkFoodByGuestName() throws SQLException {
        UiUtils.print("Which guest do you want to check?");
        String guestName2 = UiUtils.userInput();
        ArrayList<String[]> foodByGuestName = StuffQuery.foodByGuestName(guestName2);

        int i = 0;
        while (foodByGuestName.iterator().hasNext()) {
            String[] ans = foodByGuestName.get(i);
            System.out.println("food name is: " + ans[1] + ", food time is: " + ans[2]);
            i++;
        }
    }

    public static void checkAllRoomStatus() throws SQLException {
        UiUtils.print("All room status are fellowing: ");
        Map<String, String> map = StuffQuery.allStatus();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            UiUtils.print("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
    }

}
