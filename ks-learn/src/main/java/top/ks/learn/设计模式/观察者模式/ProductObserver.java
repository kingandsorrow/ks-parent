package top.ks.learn.设计模式.观察者模式;

public interface ProductObserver {

	void onPublished(Product product);

	void onPriceChanged(Product product);
}
