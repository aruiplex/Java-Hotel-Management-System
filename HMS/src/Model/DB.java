package Model;

import java.sql.*;
import java.util.ArrayList;
import View.UiUtils;

public class DB {
    public static Connection conn = connection();

    // connect with database
    public static Connection connection() {
        final String userName = "root";
        final String passWord = "xingyudong";
        final String DataBaseURL = "jdbc:mysql://localhost:3306/";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DataBaseURL, userName, passWord);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return conn;
    }

    // drop original databases
    public static void initDrop(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            statement.execute("use Robin_HMS");
            statement.execute("DROP TABLE IF EXISTS stuff");
            statement.execute("DROP TABLE IF EXISTS guest");
            statement.execute("DROP TABLE IF EXISTS room");
            statement.execute("DROP TABLE IF EXISTS book_room");
            statement.execute("DROP TABLE IF EXISTS book_food");
            statement.execute("DROP TABLE IF EXISTS food");
        } catch (SQLException e) {
            System.out.println("Drop original databases crash");
            e.printStackTrace();
        }
    }

    // initialize database
    static void initCreate(Connection conn) {
        try {
            Statement statement = conn.createStatement();
            try {
                statement.execute("use Robin_HMS");
            } catch (SQLException sqle) {
                System.out.println("Robin_HMS is not exist, so i create it now");
                statement.execute("CREATE DATABASE Robin_HMS");
                statement.execute("use Robin_HMS");
            }
            // create stuff table
            statement.execute(
                    "CREATE TABLE stuff (id INT(5) PRIMARY KEY AUTO_INCREMENT, name VARCHAR(64) NOT NULL, password VARCHAR(64) NOT NULL, telenumber VARCHAR(32), UPDATED_TIME DATETIME DEFAULT CURRENT_TIMESTAMP );");
            // create guest table
            statement.execute(
                    "CREATE TABLE guest (id INT(5) PRIMARY KEY AUTO_INCREMENT, name VARCHAR(128) NOT NULL, real_name VARCHAR(128), password VARCHAR(128) NOT NULL, telenumber VARCHAR(32), passport_id VARCHAR(64), UPDATED_TIME DATETIME DEFAULT CURRENT_TIMESTAMP ) AUTO_INCREMENT = 10001;");
            // create room table
            statement.execute(
                    "CREATE TABLE room (id INT(4) PRIMARY KEY, type VARCHAR(32) NOT NULL, status TINYINT NOT NULL DEFAULT '0')");
            // create food table
            statement.execute(
                    "CREATE TABLE food (id INT(4) NOT NULL AUTO_INCREMENT , name VARCHAR(128) NOT NULL, food_time SET('1','2','3','4','5','6','7') NOT NULL, chef VARCHAR(128),PRIMARY KEY (id))");
            // create book_room table
            statement.execute(
                    "CREATE TABLE book_room (id INT NOT NULL AUTO_INCREMENT, room_id INT NOT NULL, guest_id INT NOT NULL, start_time DATETIME NOT NULL, duration INT NOT NULL, status TINYINT NOT NULL DEFAULT '1', order_time DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id))");
            // create book_food table
            statement.execute(
                    "CREATE TABLE book_food ( id INT(5) NOT NULL AUTO_INCREMENT , food_name VARCHAR(128) NOT NULL, guest_id INT NOT NULL, food_time VARCHAR(32) NOT NULL, status TINYINT NOT NULL DEFAULT '1', order_time DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id))");
            // create cook table
            // statement.execute(
            // "CREATE TABLE cook ( id INT(5) NOT NULL AUTO_INCREMENT COMMENT '厨师号' , name
            // VARCHAR(128) NOT NULL COMMENT '名字' , PRIMARY KEY (id))");
            // default account
            statement.execute("INSERT INTO stuff (id, name, password) VALUES (09999, 'root_stuff', 12345)");
            statement.execute("INSERT INTO guest (id, name, password) VALUES (10000, 'root_guest', 12345)");

            // init food
            String[] chefList = new String[] { "Karen Adam", "Hari Philip", "Thalia Hensley", "Nisha Moss",
                    "Karen Adam & Hari Philip & Thalia Hensley & Nisha Moss" };
            ArrayList<String[]> foodWeekDay = new ArrayList<String[]>();
            // Karen Adam:
            foodWeekDay.add(new String[] { "1", "2", "3", "4", "5" });
            // Hari Philip:
            foodWeekDay.add(new String[] { "3", "4", "5", "6", "7" });
            // Thalia Hensley
            foodWeekDay.add(new String[] { "1", "4", "6" });
            // Nisha Moss
            foodWeekDay.add(new String[] { "2", "6", "7" });
            // fried egg, curry rice.
            foodWeekDay.add(new String[] { "1", "2", "3", "4", "5", "6", "7" });

            ArrayList<String[]> foodNameList = new ArrayList<String[]>();

            foodNameList.add(new String[] { "shrimp soup", "cauliflower and mushroom stew", "spicy chicken nuggets",
                    "steamed cod fish", "turkey burger", "veggie burger" });
            foodNameList.add(new String[] { "chicken curry", "chicken masala", "mutton Korma", "keema curry",
                    "mushroom tikka" });
            foodNameList.add(
                    new String[] { "tofu teriyaki", "shrimp tempura", "yaki udon", "chicken katsu", "salmon sashimi" });
            foodNameList.add(new String[] { "black pepper beef", "pork chowmein", "sweet & sour pork",
                    "gongbao chicken", "pork jiaozi", "soy glazed pork chops" });
            foodNameList.add(new String[] { "fried egg", "curry rice" });
            String foodName;
            String foodTime;
            String sqlFood;
            String chef;
            for (int i = 0; i < 5; i++) {
                String[] oneFoodList = foodNameList.get(i);
                chef = chefList[i];
                foodTime = UiUtils.toString(foodWeekDay.get(i));
                for (int j = 0; j < oneFoodList.length; j++) {
                    foodName = oneFoodList[j];
                    sqlFood = "INSERT INTO food (name, food_time, chef) VALUES (" + "\"" + foodName + "\"" + ", "
                            + foodTime + ", " + "\"" + chef + "\"" + ")";
                    statement.execute(sqlFood);
                }
            }

            // init room
            String roomId, floor, digit, type;
            for (int i = 1; i <= 10; i++) {
                floor = Integer.toString(i);
                for (int j = 1; j <= 13; j++) {
                    // if room id less than 2 digits, make it become 2 digits.
                    if (j < 10) {
                        digit = "0" + Integer.toString(+j);
                    } else {
                        digit = Integer.toString(j);
                    }
                    roomId = floor + digit;
                    // init room type.
                    switch (j) {
                        case 1:
                            type = "Large doulbe bed";
                            break;
                        case 2:
                            type = "Large doulbe bed";
                            break;
                        case 3:
                            type = "Large single bed";
                            break;
                        case 4:
                            type = "Large single bed";
                            break;
                        case 5:
                            type = "Small single bed";
                            break;
                        case 6:
                            type = "Small single bed";
                            break;
                        case 7:
                            type = "Small single bed";
                            break;
                        case 8:
                            type = "Small single bed";
                            break;
                        case 9:
                            type = "Large doulbe bed";
                            break;
                        case 10:
                            type = "Large doulbe bed";
                            break;
                        case 11:
                            type = "Large single bed";
                            break;
                        case 12:
                            type = "Large single bed";
                            break;
                        case 13:
                            type = "VIP room";
                            break;
                        default:
                            type = "unregistered room";
                            break;
                    }
                    String sql = "INSERT INTO room (id, type, status) VALUES (" + roomId + ", " + "\"" + type + "\""
                            + ", " + "0" + ")";
                    statement.execute(sql);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    // 这是一个测试
    static void query(Connection conn) {
        // System.out.println("Creating statement...");
        Statement statement;
        String sql;
        ResultSet rs;
        try {
            statement = conn.createStatement();
            sql = "SELECT name, birthday FROM birth";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                // Retrieve by column name
                Date birthday = rs.getDate("birthday");
                String name = rs.getString("name");
                // Display values
                System.out.println("Name: " + name);
                System.out.println("Birthday: " + birthday + "\n");

            }
            // release source
            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}