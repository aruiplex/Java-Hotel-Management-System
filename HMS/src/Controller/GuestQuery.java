package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.DB;

public class GuestQuery {

    // guest before check in room
    // through type to check in room
    public static String[] checkByType(String roomType) throws SQLException {
        String sql;
        String[] ids = new String[20];

        Statement statement = DB.conn.createStatement();
        sql = "USE Robin_HMS";
        statement.executeQuery(sql);
        sql = "SELECT id FROM room WHERE status=0 and type=" + "\"" + roomType + "\"";
        ResultSet rs = statement.executeQuery(sql);

        int i = 0;
        while (rs.next()) {
            ids[i] = rs.getString("id");
            i++;
        }
        return ids;
    }

    // through room id to check in room
    public static int checkById(String roomId) {
        String sql;
        int status;
        try {
            Statement statement = DB.conn.createStatement();

            sql = "SELECT status FROM room WHERE id=" + "\"" + roomId + "\"";
            ResultSet rs = statement.executeQuery(sql);
            status = rs.getInt("status");
            if (status == 1) {
                return 0;
            }
            sql = "UPDATE room SET status = \"1\" WHERE id = " + roomId;
            statement.executeUpdate(sql);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // guest after check in room
    // guest check booked food
    public static ArrayList<String[]> bookedFood() {
        String sql;
        ArrayList<String[]> food_name = new ArrayList<String[]>();
        String[] passedName = new String[20];
        String[] processingName = new String[20];
        ResultSet rs;
        try {
            Statement statement = DB.conn.createStatement();
            // passed booking
            sql = "SELECT food_name FROM book_food WHERE status=0 and guest_id=" + "\"" + Account.token + "\"";
            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                passedName[i] = rs.getString("food_name");
                i++;
            }
            // processing booking
            sql = "SELECT food_name FROM book_food WHERE status=1 and guest_id=" + "\"" + Account.token + "\"";
            rs = statement.executeQuery(sql);
            int j = 0;
            while (rs.next()) {
                processingName[j] = rs.getString("food_name");
                j++;
            }
            food_name.add(passedName);
            food_name.add(processingName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food_name;
    }

    // guest check guest-self booked room
    public static ArrayList<String[]> bookedRoom() {
        String sql;
        ArrayList<String[]> room_id = new ArrayList<String[]>();
        String[] passedId = new String[20];
        String[] processingId = new String[20];
        ResultSet rs;
        try {
            Statement statement = DB.conn.createStatement();
            // passed booking
            sql = "SELECT room_id FROM book_room WHERE status=0 and guest_id=" + Account.token;
            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                passedId[i] = rs.getString("room_id");
                i++;
            }
            // processing booking
            sql = "SELECT room_id FROM book_room WHERE status=1 and guest_id=" + Account.token;
            rs = statement.executeQuery(sql);
            int j = 0;
            while (rs.next()) {
                processingId[j] = rs.getString("room_id");
                j++;
            }
            room_id.add(passedId);
            room_id.add(processingId);
            return room_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return room_id;
        }
    }

    // when guest book a food, what food there have?
    public static String getFoodHave(String time) throws SQLException {
        Statement statement = DB.conn.createStatement();

        String sql = "SELECT name FROM food WHERE FIND_IN_SET(" + "\"" + time + "\"" + ", food_time" + ")";
        ResultSet rs = statement.executeQuery(sql);
        StringBuilder foodHave = new StringBuilder();
        foodHave.append("There have: ");
        // int iMax = rs.getRow();
        int i = 1;
        while (rs.next()) {
            foodHave.append(" [" + i + "] " + rs.getString("name"));
            i++;
        }
        return foodHave.toString();
    }
}