package Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Model.BookFoodModel;
import Model.BookRoomModel;
import Model.DB;

public class Book {
    public static Connection conn = DB.conn;
    public static int token = Account.token;

    // guest book a room
    public static void bookRoomByType(BookRoomModel brm) throws Exception, SQLException {
        String sql;
        int roomId;
        Statement statement = conn.createStatement();
        sql = "SELECT id FROM room WHERE status=0 and type=" + "\"" + brm.getRoomType() + "\"";
        ResultSet rs = statement.executeQuery(sql);
        // there do not have this type room;
        if (!rs.next()) {
            throw new Exception("There do not have this type room, Please try another type.");
        }
        // from room type to room id;
        roomId = rs.getInt("id");
        sql = "INSERT INTO book_room (room_id, guest_id, start_time, duration) VALUES(" + roomId + ", " + token + ", "
                + "\"" + brm.getStartTime() + "\"" + ", " + brm.getLastTime() + ")";
        statement.execute(sql);
        // update room status
        sql = "UPDATE room SET status = \"1\" WHERE id = " + roomId;
        statement.executeUpdate(sql);
    }

    public static void bookRoomById(BookRoomModel brm) throws Exception, SQLException {
        String sql;
        Statement statement = conn.createStatement();
        // check room status;
        sql = "SELECT status FROM room WHERE id=" + brm.getRoomId();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.getInt("status") == 1) {
            throw new Exception("This room has been booked, Please try another.");
        } else {

            sql = "INSERT INTO book_room (room_id, guest_id, start_time, duration) VALUES(" + brm.getRoomId() + ", "
                    + token + ", " + "\"" + brm.getStartTime() + "\"" + ", " + "\"" + brm.getLastTime() + "\"" + ")";
            statement.execute(sql);
            sql = "UPDATE room SET status = \"1\" WHERE id = " + brm.getRoomId();
            statement.executeUpdate(sql);
        }
    }

    // guest book a food
    public static void bookFood(BookFoodModel bfm) throws SQLException {
        String sql;
        Statement statement = conn.createStatement();
        sql = "INSERT INTO book_food (food_name, food_time, guest_id, status) values (" + "\""
                + bfm.getFoodName().trim() + "\"" + ", " + "\"" + bfm.getFoodTime() + "\"" + ", " + bfm.getGuestId()
                + ", " + "\"" + 0 + "\"" + ")";
        statement.execute(sql);
    }

    public static void cancelRoom(BookRoomModel brm) throws SQLException {
        String sql;
        Statement statement = conn.createStatement();
        sql = "DELETE FROM book_room WHERE room_id=" + brm.getRoomId() + " and " + "guest_id=" + brm.getGuestId();
        statement.execute(sql);
    }

    public static void cancelFood(BookFoodModel bfm) throws SQLException {
        String sql;
        Statement statement = conn.createStatement();
        sql = "DELETE FROM book_food WHERE food_name=" + "\"" + bfm.getFoodName() + "\"" + " and " + "guest_id="
                + bfm.getGuestId();
        statement.execute(sql);
    }

    public static void bookd() throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from book_room";
        ResultSet rs = statement.executeQuery(sql);
        int i = 0;
        int status, id;
        Date start_time;
        Date today = new Date();
        int duration;
        Date end_time = new Date(); // 取时间
        Calendar calendar = new GregorianCalendar();

        while (rs.next()) {
            start_time = rs.getDate("start_time");
            duration = rs.getInt("duration");

            calendar.setTime(end_time);
            calendar.add(Calendar.DATE, duration); // 把日期往后增加一天,整数 往后推,负数往前移动
            end_time = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            if (today.equals(end_time)) {
                // 把房间状态设置为0;
                status = 0;
                id = rs.getInt("id");
                sql = "UPDATE book_room SET status=0 WHERE id=" + id;
                statement.executeUpdate(sql);
            }
            if (today.equals(start_time)) {
                // 把房间状态设置为1;
                status = 1;
                id = rs.getInt("id");
                sql = "UPDATE book_room SET status=1 WHERE id=" + id;
                statement.executeUpdate(sql);
            }
            i++;
        }
    }
}