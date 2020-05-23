package DEVTools;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Model.DB;
import View.Ui;

public class Test {
    public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append("\"");
        for (int i = 0;; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append("\"").toString();
            b.append(", ");
        }
    }

    public static void test1() {
        // init food
        Map<String, String> foodMap = new HashMap<String, String>();
        // String[] foodName = new String[50];
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
        foodNameList.add(
                new String[] { "chicken curry", "chicken masala", "mutton Korma", "keema curry", "mushroom tikka" });
        foodNameList.add(
                new String[] { "tofu teriyaki", "shrimp tempura", "yaki udon", "chicken katsu", "salmon sashimi" });
        foodNameList.add(new String[] { "black pepper beef", "pork chowmein", "sweet & sour pork", "gongbao chicken",
                "pork jiaozi", "soy glazed pork chops" });
        foodNameList.add(new String[] { "fried egg", "curry rice" });
        String foodName;
        String foodTime;
        for (int i = 0; i < 5; i++) {
            String[] oneFoodList = foodNameList.get(i);
            foodTime = Arrays.toString(foodWeekDay.get(i));
            for (int j = 0; j < oneFoodList.length; j++) {
                foodName = oneFoodList[j];
                System.out.println("food name is: " + foodName + "food time is " + foodTime);
            }
        }
    }

    public static void main(String[] args) {
        String[] strArray = new String[20];
        strArray[0] = "a";
        strArray[1] = "b";
        strArray[2] = "c";
        strArray[3] = "d";
        int i = 0;
        while (strArray[i] != null) {
            System.out.println(strArray[i]);
            i++;
        }
        // if (strArray[4].equals(null)) {
        // System.out.println("yes");
        // } else {
        // System.out.println("No");
        // }
        // for (String x : strArray) {
        // System.out.println(x);
        // }
    }
}