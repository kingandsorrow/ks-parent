package top.ks.learn.设计模式.观察者模式;

public class Admin implements ProductObserver{
    @Override
    public void onPublished(Product product) {
        System.out.println("[Admin] on product published: " + product);
    }

    @Override
    public void onPriceChanged(Product product) {
        System.out.println("[Admin] on product price changed: " + product);
    }
}
