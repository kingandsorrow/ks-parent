package top.ks.common.objectpool;

import java.io.InputStream;
import java.util.Vector;

public class TestObjectPool {

    public static void main(String[] args) {
        /*Provider provider = Provider.getInstance();
        String s = provider.getObj(String.class);
        System.out.println(s.hashCode());

        String s1 = provider.getObj(String.class);

        System.out.println(s1.hashCode());*/

        Vector v = new Vector(10);

        for (int i = 0; i < 100; i++) {
            String o = new String("aa" + i);
            v.add(o);
            o = null;
        }
        System.out.println(v.size() + "--" + v.get(0));
    }
}
