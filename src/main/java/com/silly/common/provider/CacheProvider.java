package com.silly.common.provider;

import com.silly.common.utils.StringUtils;
import com.silly.core.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存提供者
 *
 * @Author: wei.wang7
 * @Date: 2020/8/30 13:05
 */
@Component
public class CacheProvider {

    private final Map<String, User> CACHE = new HashMap<>();

    public void set(String token, User user) {
        CACHE.put(token, user);
    }

    public User get(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return CACHE.get(token);
    }
}
