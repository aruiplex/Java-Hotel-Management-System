package Model;

import java.sql.Connection;
import java.sql.SQLException;

import Controller.Account;
import View.Ui;

public class Main {
    public static void test() throws SQLException {
        DB.connection();
        Account.token = 10010;
        Ui.meau();
    }

    public static void initDB(Connection conn) {
        DB.initDrop(conn);
        DB.initCreate(conn);
    }

    public static void main(String[] args) throws SQLException {
        // initDB(conn);
        // Ui.userInteractive();
        try {
            test();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}