package top.ks.common.util;

public class Client {
    private static Client client = new Client();

    public static int a;
    public static int b = 0;

    private Client() {
        a++;
        b++;
    }

    public static Client getInstance() {
        return client;
    }

    public static void main(String[] args) {
        Client instance = Client.getInstance();
        System.out.println("a= " + Client.a);
        System.out.println("b= " + Client.b);
    }

}

