package Model;

import java.sql.SQLException;
import Controller.Account;
import Controller.Book;
import View.Ui;

public class Main {
    public static void test() {
        DB.connection();
        Account.token = 10010;
        Ui.menu();
    }

    public static void main(String[] args) {
        try {
            DB.connection();
            Book bookd = new Book();
            new Thread(bookd).start();
            Ui.userInteractive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}