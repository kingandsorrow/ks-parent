package top.ks.common.thread;

public class TestThread {

    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                ping();
            }
        };
        t.run();
        System.out.println("pong");
    }

    static void ping() {
        System.out.println("ping");
    }
}
