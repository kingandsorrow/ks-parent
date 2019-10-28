package top.ks.common.tbreak;

public class Break {
    public static void main(String[] args) {
        boolean judgement = true;
        label88:
        {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    break label88;
                }
            }
            System.out.println("88");
        }
        System.out.println("99");
    }
}
