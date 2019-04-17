package top.ks.common.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import top.ks.framework.util.LogFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    private static final Log log = LogFactory.getLog(RedisService.class);

    /**
     * 设置失效时间
     *
     * @param key
     * @param value
     * @return
     */
    public Long setnx(String key, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.setnx", "system error::" + e.getMessage(), ""));
            jedisPool.returnResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;

    }

    /**
     * 设置key的有效期，单位是秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.expire", "system error::" + e.getMessage(), ""));
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 获取当个对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.get", "system error::" + e.getMessage(), ""));
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }


    public String getset(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.getset", "system error::" + e.getMessage(), ""));
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }

    /**
     * 设置对象
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 判断key是否存在
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除
     */
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    public Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.del", "system error::" + e.getMessage(), ""));
            jedisPool.returnBrokenResource(jedis);
            return result;
        }
        jedisPool.returnResource(jedis);
        return result;
    }


    public boolean delete(KeyPrefix prefix) {
        if (prefix == null) {
            return false;
        }
        List<String> keys = scanKeys(prefix.getPrefix());
        if (keys == null || keys.size() <= 0) {
            return true;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keys.toArray(new String[0]));
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> scanKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<String>();
            String cursor = "0";
            ScanParams sp = new ScanParams();
            sp.match("*" + key + "*");
            sp.count(100);
            do {
                ScanResult<String> ret = jedis.scan(cursor, sp);
                List<String> result = ret.getResult();
                if (result != null && result.size() > 0) {
                    keys.addAll(result);
                }
                //再处理cursor
                cursor = ret.getStringCursor();
            } while (!cursor.equals("0"));
            return keys;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :执行lua脚本
     * @author : brj
     * @CreateDate : 2019/4/8 15:05
     */

    public void init(String key) {
        String script = "local result=1\n" +
                "redis.pcall(\"HMSET\",KEYS[1],\n" +
                "        \"last_mill_second\",ARGV[1],\n" +
                "        \"curr_permits\",ARGV[2],\n" +
                "        \"max_burst\",ARGV[3],\n" +
                "        \"rate\",ARGV[4],\n" +
                "        \"app\",ARGV[5])\n" +
                "return result\n";

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> args = new ArrayList<>();
            args.add(new Date().getTime() + "");
            args.add(0 + "");
            args.add(1 + "");
            args.add(1 + "");
            args.add("ks");
            Object object = jedis.eval(script, Collections.singletonList(key), args);
            log.info(LogFormat.formatMsg("RedisService.init", "object is.." + JSON.toJSONString(object), ""));
        } catch (Exception e) {
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.init", "system error::" + e.getMessage(), ""));
        }
    }

    public boolean accquireToken(KeyPrefix prefix, String key) {
        return accquireToken(prefix, key, 1);
    }

    public boolean accquireToken(KeyPrefix prefix, String key, Integer permits) {
        String realKey = prefix.getPrefix() + key;
        try {
            Jedis jedis = jedisPool.getResource();
            List<String> args = new ArrayList<>();
            args.add(permits + "");
            args.add(new Date().getTime() + "");
            Long obj = (Long) jedis.eval(LuaScript.LIMIT_SCRIPT, Collections.singletonList(realKey), args);
            if (obj != null && obj == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            init(realKey);
            log.error("system exception:", e);
            log.info(LogFormat.formatMsg("RedisService.accquireToken", "system error::" + e.getMessage(), ""));
        }
        return false;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :重复校验检查
     * @author : brj
     * @CreateDate : 2019/4/8 19:34
     */
    public long repeatCheck(CommonKey repeatCheck, String repeatCheckKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = repeatCheck.getPrefix() + repeatCheckKey;
            Long countValue = jedis.incrBy(realKey, 1);
            int expirSeconds = repeatCheck.expireSeconds();
            if (countValue == 1 && expirSeconds > 0) {
                jedis.expire(realKey, 3);
            }
            return countValue;
        } finally {
            returnToPool(jedis);
        }
    }
}
