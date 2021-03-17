package top.ks.learn.设计模式.观察者模式;

public class Customer implements ProductObserver {
    @Override
    public void onPublished(Product product) {
        System.out.println("[Customer] on product published: " + product);
    }

    @Override
    public void onPriceChanged(Product product) {
        System.out.println("[Customer] on product price changed: " + product);
    }
}
