package top.ks.redis.distributelock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import top.ks.common.util.LogFormat;
import top.ks.redis.KeyPrefix;
import top.ks.redis.LuaScript;
import top.ks.redis.RedisService;

import javax.annotation.Resource;
import java.util.Collections;

@Component
public class DistributedLock {
    @Resource
    private RedisService redisService;
    private static final Log log = LogFactory.getLog(DistributedLock.class);

    /**
     * @param :lockWaitTimeOut:获取锁超时时间
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2019-06-27 17:58
     */
    public boolean lock(KeyPrefix keyPrefix, String key, String value, Long lockExpireTimeOut,
                        Long lockWaitTimeOut) {

        //1.定义获取锁的超时时间
        long waitTimeOut = System.currentTimeMillis() + lockWaitTimeOut;
        //2.循环获取redis锁
        while (true) {
            //2.1往redis设置值如果没有值则返回为空
            String result = redisService.setNxAndExpire(keyPrefix, key, value, lockExpireTimeOut);
            if ("OK".equals(result)) {
                log.info(LogFormat.formatMsg("DistributedLock.lock", "get redis lock.." + value, ""));
                return true;
            }
            if (waitTimeOut < System.currentTimeMillis()) {
                log.info(LogFormat.formatMsg("DistributedLock.lock", "get redis lock.. time out", ""));
                return false;
            }
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2019-06-28 09:48
     */
    public boolean unLock(KeyPrefix keyPrefix, String key, String value) {
        Long result = (Long) redisService.evalScript(keyPrefix, key, Collections.singletonList(value), LuaScript.UNLOCK_SCRIPT);
        if (result == 1) {
            log.info(LogFormat.formatMsg("DistributedLock.unLock", "unLock success.." + key + "--" + value, ""));
            return true;
        }
        log.info(LogFormat.formatMsg("DistributedLock.unLock", "unLock fail.." + key + "--" + value, ""));
        return false;
    }
}
