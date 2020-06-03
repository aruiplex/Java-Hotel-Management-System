package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.DB;
import View.UiUtils;

public class StuffQuery {

    // stuff query which room is booked
    public static String[] bookedRoom() throws SQLException {
        String sql;
        String[] room_ids = new String[20];
        String ans;
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id, start_time, duration FROM book_room WHERE status=1";
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            ans = rs.getInt("room_id") + "; start_time: " + rs.getDate("start_time") + ", duration: "
                    + rs.getInt("duration");
            room_ids[i] = ans;
            i++;
        }
        return room_ids;
    }

    // stuff query empty room
    public static String[] emptyRoom() throws SQLException {
        String sql;
        String[] room_ids = new String[140];
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id FROM room WHERE status=0";
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            room_ids[i] = rs.getString("id");
            i++;
        }
        return room_ids;
    }

    // stuff check all room status
    public static Map<String, String> allStatus() throws SQLException {
        Map<String, String> map = new HashMap<String, String>();
        String sql;
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id, status FROM room";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            map.put(rs.getString("id"), rs.getString("status"));
        }
        return map;
    }

    // stuff query all booked food
    public static String[] bookedFood() throws SQLException {
        String sql;
        String food_id;
        String guest_id;
        String[] res = new String[50];
        Statement statement = DB.conn.createStatement();
        sql = "SELECT food_id, guest_id FROM book_food WHERE status = 1";
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            food_id = rs.getString("id");
            guest_id = rs.getString("guest_id");
            res[i] = "food id is: " + food_id + "guest id is: " + guest_id;
            i++;
        }
        return res;
    }

    // check one room is booked or not now
    public static int oneStatus(String roomId) throws SQLException {
        String sql;
        int status;
        Statement statement = DB.conn.createStatement();
        sql = "SELECT status FROM room WHERE id=" + "\"" + roomId + "\"";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        status = rs.getInt("status");
        if (status == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // Stuff check
    public static ArrayList<String[]> foodByGuestName(String guestName) throws SQLException {
        String sql;
        String[] foodQuery = new String[2];
        ArrayList<String[]> ansArrayList = new ArrayList<String[]>();
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id FROM guest WHERE name=" + "\"" + guestName + "\"";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String guest_id = rs.getString("id");
        sql = "SELECT food_name, food_time FROM book_food WHERE guest_id=" + "\"" + guest_id + "\"";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            foodQuery[0] = rs.getString("food_name");
            foodQuery[1] = rs.getString("food_time");
            ansArrayList.add(foodQuery);
        }
        return ansArrayList;
    }

    // stuff check booked room by guest name
    public static String[] roomByGuestName(String guestName) throws SQLException {
        String sql;
        String[] respense = new String[10];
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id FROM guest WHERE name=" + "\"" + guestName + "\"";
        ResultSet rs = statement.executeQuery(sql);
        rs.next();
        String guest_id = rs.getString("id");
        sql = "SELECT room_id FROM book_room WHERE status=1 and guest_id=" + "\"" + guest_id + "\"";
        rs = statement.executeQuery(sql);
        int i = 0;
        while (rs.next()) {
            respense[i] = rs.getString("room_id");
            i++;
        }
        if (i == 0) {
            UiUtils.print("This guest did not book any room.");
        }
        return respense;
    }
}