package top.ks.learn.集合.手写HashMap;

public class KHashMap<K, V> {
    //默认的数组的大小
    private final static int DEFAULT_CAPACITY = 16;
    // 默认的负载因子 如果达到16*0.75 则进行扩容了
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private KNode[] table;

    private int size;

    public KHashMap() {
        table = new KNode[DEFAULT_CAPACITY];
    }

    public V put(K key, V val) {
        if (key == null) {
            throw new RuntimeException("key is null");
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        KNode e = table[index];
        while (e != null) {
            if (e.hash == hash && (key == e.key || e.key.equals(key))) {
                V oldValue = (V) e.value;
                e.value = val;
                return oldValue;
            }
            e = e.next;
        }
        KNode<K, V> next = table[index];
        table[index] = new KNode(key, val, next, hash);
        if (size++ > table.length * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("Key is not null");
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        KNode<K, V> e = table[index];
        while (e != null) {
            if (hash == e.hash && (key == e.key || key.equals(e.value))) {
                return e.value;
            }
            e = e.next;
        }
        return null;
    }

    //扩容，元素的个数大于 table.length * 0.75
    //数组扩容到原来大小的2倍
    private void resize() {
        int newCapacity = table.length * 2;
        KNode[] newTable = new KNode[newCapacity];

        KNode[] src = table;
        for (int i = 0; i < table.length; i++) {
            KNode e = table[i];
            //释放旧的数组
            src[i] = null;
            while (e != null) {
                KNode next = e.next;
                int hash = hash(e);
                int index = indexFor(hash, newCapacity);
                e.next = newTable[i];
                newTable[index] = e;
                e = next;
            }
        }
        this.table = newTable;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-02-16 15:13
     */
    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


}
