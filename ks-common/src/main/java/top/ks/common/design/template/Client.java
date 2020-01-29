package top.ks.common.design.template;

public class Client {
    public static void main(String[] args) {
        ScanBicycle scanBicycle = new ScanBicycle();
        scanBicycle.use();
        System.out.println("=====================");
        CodeBicycle codeBicycle = new CodeBicycle();
        codeBicycle.use();
    }
}
