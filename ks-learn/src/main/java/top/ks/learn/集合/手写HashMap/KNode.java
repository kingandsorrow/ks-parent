package top.ks.learn.集合.手写HashMap;

public class KNode<K, V> {

    K key;

    V value;

    KNode<K, V> next;

    int hash;

    public KNode() {
    }

    public KNode(K key, V value, KNode<K, V> next, int hash) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

}
