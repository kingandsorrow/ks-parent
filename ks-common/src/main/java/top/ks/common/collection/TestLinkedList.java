package top.ks.common.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class TestLinkedList {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("a");
        linkedList.add("e");

        linkedList.add("c");

        linkedList.add("99");

        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
