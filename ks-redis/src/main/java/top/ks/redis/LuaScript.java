package top.ks.redis;

/**
 * <b>类名称:</b>LuaScript$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/8<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/8
 */
public class LuaScript {

    public static final String LIMIT_SCRIPT = "local ratelimit_info=redis.pcall(\"HMGET\",KEYS[1],\"last_mill_second\",\"curr_permits\",\"max_burst\",\"rate\",\"app\")\n" +
            "local last_mill_second=ratelimit_info[1]\n" +
            "local curr_permits=tonumber(ratelimit_info[2])\n" +
            "local max_burst=tonumber(ratelimit_info[3])\n" +
            "local rate=tonumber(ratelimit_info[4])\n" +
            "local app=tostring(ratelimit_info[5])\n" +
            "if app == nil then\n" +
            "    return 0\n" +
            "end\n" +
            "\n" +
            "local local_curr_permits=max_burst;\n" +
            "\n" +
            "if(type(last_mill_second) ~='boolean' and last_mill_second ~=nil) then\n" +
            "    local reverse_permits=math.floor((ARGV[2]-last_mill_second)/1000)*rate\n" +
            "    if(reverse_permits>0) then\n" +
            "        redis.pcall(\"HMSET\",KEYS[1],\"last_mill_second\",ARGV[2])\n" +
            "    end\n" +
            "\n" +
            "    local expect_curr_permits=reverse_permits+curr_permits\n" +
            "    local_curr_permits=math.min(expect_curr_permits,max_burst);\n" +
            "\n" +
            "else\n" +
            "    redis.pcall(\"HMSET\",KEYS[1],\"last_mill_second\",ARGV[2])\n" +
            "end\n" +
            "\n" +
            "local result=local_curr_permits-ARGV[1]\n" +
            "if((local_curr_permits-ARGV[1])>=0) then\n" +
            "    result=1\n" +
            "    redis.pcall(\"HMSET\",KEYS[1],\"curr_permits\",local_curr_permits-ARGV[1])\n" +
            "else\n" +
            "    redis.pcall(\"HMSET\",KEYS[1],\"curr_permits\",local_curr_permits)\n" +
            "end\n" +
            "\n" +
            "return result\n";

    public static final String UNLOCK_SCRIPT = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
}
