package Model;

import Controller.Book;
import View.Ui;

public class Main {
    public static void main(String[] args) {
        try {
            // get connent with db
            DB.connection();
            // db init
            DB.init();
            // start book daemon
            Book bookd = new Book();
            new Thread(bookd).start();
            // start user interactive
            Ui.userInteractive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}