package top.ks.learn.集合.手写ArrayList;


import java.util.Arrays;

public class KArrayList {

    private Object[] elementData;       //底层数组
    private int size;
    // 默认容量大小
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 空数组（用于空实例）。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /*
     * 最大容量
     * */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public KArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public KArrayList(int initCapacity) {
        if (initCapacity > 0) {
            //如果传入的参数大于0，创建initialCapacity大小的数组
            this.elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            //如果传入的参数等于0，创建空数组
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            //其他情况，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initCapacity);
        }
    }

    public boolean add(Object obj) {
        /*
         * 添加对象（不指定位置）
         * 注意数组扩容
         */
        ensureCapacity(size + 1);
        elementData[size++] = obj;
        return true;
    }

    private void ensureCapacity(int minCapacity) {
        // 1、如果数组是空的
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 获取默认的容量和传入参数的较大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        //2、如果容量已经达到顶点 则需要进行扩容
        if (minCapacity - elementData.length > 0) {
            int oldCapacity = elementData.length;
            // 右移一位相当于除以2
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }
            if (newCapacity - MAX_ARRAY_SIZE > 0) {
                if (minCapacity < 0) // overflow
                    throw new OutOfMemoryError();
                //对minCapacity和MAX_ARRAY_SIZE进行比较
                //若minCapacity大，将Integer.MAX_VALUE作为新数组的大小
                //若MAX_ARRAY_SIZE大，将MAX_ARRAY_SIZE作为新数组的大小
                //MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
                newCapacity = (minCapacity > MAX_ARRAY_SIZE) ?
                        Integer.MAX_VALUE :
                        MAX_ARRAY_SIZE;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}
