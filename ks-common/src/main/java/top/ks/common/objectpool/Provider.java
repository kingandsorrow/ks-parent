package top.ks.common.objectpool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Provider {
    private Map<Class<?>, ObjectUnit<?>> providers = new ConcurrentHashMap<Class<?>, ObjectUnit<?>>();
    private static Provider instance = new Provider();

    private Provider() {
    }

    public static Provider getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObj(Class<T> key) {
        ObjectUnit value = providers.get(key);
        if (value != null) {
            return (T) value.checkOut();
        } else {
            value = new ObjectUnit<T>(key);
            providers.put(key, value);
            return (T) value.addItem();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void renObj(T x) {
        if (providers.containsKey(x.getClass())) {
            ObjectUnit value = providers.get(x.getClass());
            value.checkIn(x);
        }
    }
}

