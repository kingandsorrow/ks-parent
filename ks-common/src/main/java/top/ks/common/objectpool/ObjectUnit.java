package top.ks.common.objectpool;

import java.util.ArrayList;
import java.util.List;

public class ObjectUnit<T> {
    private Class<T> type;
    private List<T> items = new ArrayList<T>();
    private List<Boolean> checkedOut = new ArrayList<Boolean>();
    private int semaphore;

    public ObjectUnit(Class<T> type) {
        this.type = type;
    }

    public synchronized T addItem() {
        T obj;
        try {
            obj = type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        items.add(obj);
        checkedOut.add(false);
        return obj;
    }

    public synchronized T checkOut() {
        if (semaphore < items.size()) {
            semaphore++;
            return getItem();
        } else
            return addItem();
    }

    public synchronized void checkIn(T x) {
        if (releaseItem(x))
            semaphore--;
    }

    private synchronized T getItem() {
        for (int index = 0; index < checkedOut.size(); index++)
            if (!checkedOut.get(index)) {
                checkedOut.set(index, true);
                return items.get(index);
            }
        return null;
    }

    private synchronized boolean releaseItem(T item) {
        int index = items.indexOf(item);
        if (index == -1)
            return false; // Not in the list
        if (checkedOut.get(index)) {
            checkedOut.set(index, false);
            return true;
        }
        return false;
    }

}
