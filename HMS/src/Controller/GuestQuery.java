package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.DB;

public class GuestQuery{
    static Connection conn = DB.conn;
    static int token = Account.token;

    // guest before check in room
    // through type to check in room
    public static String[] checkByType(String roomType) throws SQLException {
        String sql;
        String[] ids = new String[20];

        Statement statement = conn.createStatement();
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
        String sql, status;
        try {
            Statement statement = conn.createStatement();

            sql = "SELECT status FROM room WHERE id=" + "\"" + roomId + "\"";
            ResultSet rs = statement.executeQuery(sql);
            status = rs.getString("status");
            if (status == "1") {
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
    public static String[] bookedFood() {
        String sql;
        String[] food_name = new String[10];
        try {
            Statement statement = conn.createStatement();
            sql = "SELECT food_name FROM book_food WHERE guest_id=" + "\"" + token + "\"";
            ResultSet rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                food_name[i] = rs.getString("food_name");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food_name;
    }

    // guest check guest-self booked room
    public static String[] bookedRoom() {
        String sql;
        String room_id[] = new String[10];
        try {
            Statement statement = conn.createStatement();
            sql = "SELECT room_id FROM book_room WHERE guest_id=" + token;
            ResultSet rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                room_id[i] = rs.getString("room_id");
                i++;
            }
            return room_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return room_id;
        }
    }

    // when guest book a food, what food there have?
    public static String getFoodHave(String time) throws SQLException {
        Statement statement = conn.createStatement();

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