package com.github.xiaoyu2017.bbs.pojo.bean;

import com.github.xiaoyu2017.bbs.pojo.dao.UserDao;
import lombok.Data;

/**
 * 请求上下文内容
 * <p>
 * 线程间隔离
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class BbsContext {
    private static final ThreadLocal<BbsContext> THREAD_LOCAL = new ThreadLocal<>();

    private String token;
    private UserDao userDao;

    public BbsContext() {
    }

    public BbsContext(String token) {
        this.token = token;
    }

    public static void set(BbsContext bbsContext) {
        THREAD_LOCAL.set(bbsContext);
    }

    public static BbsContext get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
