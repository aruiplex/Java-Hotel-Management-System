package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Model.BookFoodModel;
import Model.BookRoomModel;
import Model.DB;

public class Book implements Runnable {

    // guest book a room
    public static void bookRoomByType(BookRoomModel brm) throws Exception, SQLException {
        String sql;
        int roomId;
        Statement statement = DB.conn.createStatement();
        sql = "SELECT id FROM room WHERE status=0 and type=" + "\"" + brm.getRoomType() + "\"";
        ResultSet rs = statement.executeQuery(sql);
        // there do not have this type room;
        if (!rs.next()) {
            throw new Exception("There do not have this type room, Please try another type.");
        }
        // from room type to room id;
        roomId = rs.getInt("id");
        sql = "INSERT INTO book_room (room_id, guest_id, start_time, duration) VALUES(" + roomId + ", " + Account.token + ", "
                + "\"" + brm.getStartTime() + "\"" + ", " + brm.getLastTime() + ")";
        statement.execute(sql);
        // update room status
        sql = "UPDATE room SET status = \"1\" WHERE id = " + roomId;
        statement.executeUpdate(sql);
    }

    public static void bookRoomById(BookRoomModel brm) throws Exception, SQLException {
        String sql;
        Statement statement = DB.conn.createStatement();
        // check room status;
        sql = "SELECT status FROM room WHERE id=" + brm.getRoomId();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.getInt("status") == 1) {
            throw new Exception("This room has been booked, Please try another.");
        } else {

            sql = "INSERT INTO book_room (room_id, guest_id, start_time, duration) VALUES(" + brm.getRoomId() + ", "
                    + Account.token + ", " + "\"" + brm.getStartTime() + "\"" + ", " + "\"" + brm.getLastTime() + "\"" + ")";
            statement.execute(sql);
            sql = "UPDATE room SET status = \"1\" WHERE id = " + brm.getRoomId();
            statement.executeUpdate(sql);
        }
    }

    // guest book a food
    public static void bookFood(BookFoodModel bfm) throws SQLException {
        String sql;
        Statement statement = DB.conn.createStatement();
        sql = "INSERT INTO book_food (food_name, food_time, guest_id, status) values (" + "\""
                + bfm.getFoodName().trim() + "\"" + ", " + "\"" + bfm.getFoodTime() + "\"" + ", " + bfm.getGuestId()
                + ", " + "\"" + 0 + "\"" + ")";
        statement.execute(sql);
    }

    public static void cancelRoom(BookRoomModel brm) throws SQLException {
        String sql;
        Statement statement = DB.conn.createStatement();
        sql = "UPDATE book_room SET status=0 WHERE room_id=" + brm.getRoomId() + " and " + "guest_id="
                + brm.getGuestId();
        statement.executeUpdate(sql);
    }

    public static void cancelFood(BookFoodModel bfm) throws SQLException {
        String sql;
        Statement statement = DB.conn.createStatement();
        sql = "UPDATE book_food SET status=0 WHERE food_name=" + "\"" + bfm.getFoodName() + "\"" + " and " + "guest_id="
                + bfm.getGuestId();
        statement.executeUpdate(sql);
    }

    public static void bookd() {
        Statement statement;
        try {
            statement = DB.conn.createStatement();
            statement.execute("use Robin_HMS");
            String sql = "select * from book_room";
            ResultSet rs = statement.executeQuery(sql);
            int id;
            Date start_time;
            Date today = new Date();
            int duration;
            Date end_time = new Date(); // 取时间
            Calendar calendar = new GregorianCalendar();

            while (true) {
                // 设置暂停的时间 5 秒
                Thread.sleep(5000);
                while (rs.next()) {
                    start_time = rs.getDate("start_time");
                    duration = rs.getInt("duration");
                    calendar.setTime(end_time);
                    calendar.add(Calendar.DATE, duration);
                    end_time = calendar.getTime();
                    // end this book room.
                    if (today.after(end_time)) {
                        // 把房间状态设置为0;
                        id = rs.getInt("id");
                        // booking status
                        sql = "UPDATE book_room SET status=0 WHERE id=" + id;
                        statement.executeUpdate(sql);
                        // room status
                        sql = "UPDATE room SET status=0 WHERE id=" + id;
                        statement.executeUpdate(sql);
                    }
                    // start this book room.
                    if (today.before(start_time)) {
                        // 把房间状态设置为1;
                        id = rs.getInt("id");
                        // room status
                        sql = "UPDATE room SET status=1 WHERE id=" + id;
                        statement.executeUpdate(sql);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("bookd is on.");
        bookd();
    }
}