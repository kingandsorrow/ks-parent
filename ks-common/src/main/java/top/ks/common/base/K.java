package top.ks.common.base;

import java.util.List;

public class K {
    private List<K> ks;

    public List<K> getKs() {
        return ks;
    }

    public void setKs(List<K> ks) {
        this.ks = ks;
    }

    public static void main(String[] args) {
        K k = new K();
        int count = 0;
        while (k.getKs().size() != 0) {
            count += k.getKs().size();
        }
    }
}
