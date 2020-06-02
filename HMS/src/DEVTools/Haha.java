package DEVTools;

public class Haha implements Runnable {

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                Thread.sleep(600);
                System.out.println("World" + i);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}