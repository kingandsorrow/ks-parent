package top.ks.common.base;

public class HelloB extends HelloA {
    public HelloB() {
        System.out.println("HelloB");
    }

    {
        System.out.println("im class B");
    }

    static {
        System.out.println(" static B");
    }

    public static void main(String[] args) {
        new HelloA();
    }
}
