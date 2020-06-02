package DEVTools;

public class Test implements Runnable {

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                Thread.sleep(800);
                System.out.println("hello" + i);
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Test a = new Test();
        Haha b = new Haha();
        Thread athread = new Thread(a);
        Thread bthread = new Thread(b);
        athread.start();
        bthread.start();
    }
}
