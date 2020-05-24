package Model;

import java.sql.Connection;
import java.sql.SQLException;

import Controller.Account;
import View.Ui;

public class Main {
    public static void test() throws SQLException {
        DB.connection();
        Account.token = 10010;
        Ui.menu();
    }

    public static void initDB(Connection conn) {
        DB.initDrop(conn);
        DB.initCreate(conn);
    }

    public static void main(String[] args) throws SQLException {
        // Ui.userInteractive();
        try {
            initDB(DB.connection());
            // test();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}