package top.ks.common.jvm;

import java.lang.ref.SoftReference;

public class TestGC {

    public static void main(String[] args) {
        SoftReference<String> softReference = new SoftReference<String>("abc");
        System.out.println(softReference.get());

    }
}
