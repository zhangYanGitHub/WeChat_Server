package com.zhang.chat.netty.pool;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 维护token
 * <p>
 *
 * @author Yohann.
 */
public class TokenPool {
    private static final Logger logger = Logger.getLogger(TokenPool.class);

    // 用于存放已生成的token
    private static Map<Long, String> tokenMap = new HashMap<Long, String>();

    private TokenPool() {
    }

    /**
     * 添加token
     *
     * @param token
     * @return
     */
    public synchronized static boolean add(Long user_id, String token) {
        tokenMap.put(user_id, token);

        return false;
    }

    /**
     * 删除token
     *
     * @param user_id
     * @return
     */
    public synchronized static boolean remove(Long user_id) {
        tokenMap.remove(user_id);
        return false;
    }

    /**
     * 查询token是否存在
     *
     * @param token
     * @return
     */
    public synchronized static boolean query(Long token) {
       return tokenMap.containsKey(token);
    }
}
