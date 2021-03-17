package top.ks.learn.集合.手写LinkedList;

import java.util.LinkedList;

/**
 * <b>类名称:</b>KLinkedList<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2021-03-03
 */
public class KLinkedList<E> {
    /**
     * <b>类名称:</b>KLinkedList<br/>
     * <b>类注释:</b>双向链表<br/>
     * <b>类描述:</b><br/>
     * <b>创建人:</b>birjc<br/>
     * <b>修改人:</b>birjc<br/>
     * <b>修改时间:</b><br/>
     * <b>修改备注:</b><br/>
     *
     * @version 1.0.0<br />
     * <p>
     * Copyright KS 2021-03-03
     */
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    //指向第一个节点
    transient Node<E> first;
    // 指向最后一个节点
    transient Node<E> last;
    // 链表的长度
    transient int size = 0;
    protected transient int modCount = 0;

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 增--增一个元素
     * @author : birjc
     * @CreateDate : 2021-03-03 00:12
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :增--在指定位置增加element
     * @author : birjc
     * @CreateDate : 2021-03-03 00:18
     */
    public void add(int index, E element) {
        if (index >= 0 && index <= size) {
            throw new IndexOutOfBoundsException("index is error");
        }

        if (index == size) {
            linkLast(element);
        } else {
            linkBefore(element, node(index));
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 删--移除某个元数据
     * @author : birjc
     * @CreateDate : 2021-03-03 00:33
     */
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 删--完全清除
     * @author : birjc
     * @CreateDate : 2021-03-03 23:12
     */
    public void clear() {
        Node var2;
        for (Node var1 = this.first; var1 != null; var1 = var2) {
            var2 = var1.next;
            var1.element = null;
            var1.next = null;
            var1.prev = null;
        }

        this.first = this.last = null;
        this.size = 0;
        ++this.modCount;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :改--改变指定位置
     * @author : birjc
     * @CreateDate : 2021-03-03 23:14
     */
    public E set(int index, E e) {
        if (index >= 0 && index <= size) {
            throw new IndexOutOfBoundsException("index is error");
        }
        Node<E> oldNode = this.node(index);
        E oldElement = oldNode.element;
        oldNode.element = e;
        return oldElement;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :查-- 查某个位置的元素
     * @author : birjc
     * @CreateDate : 2021-03-03 23:23
     */
    public E get(int index) {
        if (index >= 0 && index <= size) {
            throw new IndexOutOfBoundsException("index is error");
        }
        Node<E> node = this.node(index);
        return node.element;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :在某个节点前追加
     * @author : birjc
     * @CreateDate : 2021-03-03 00:25
     */
    private void linkBefore(E element, Node<E> node) {
        final Node<E> pred = node.prev;
        final Node<E> newNode = new Node<>(pred, element, node);
        node.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :根据位置取节点
     * @author : birjc
     * @CreateDate : 2021-03-03 00:28
     */
    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :移除某个node
     * @author : birjc
     * @CreateDate : 2021-03-03 00:32
     */
    private E unlink(Node<E> x) {
        // assert x != null;
        final E e = x.element;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.element = null;
        size--;
        modCount++;
        return e;
    }
}
