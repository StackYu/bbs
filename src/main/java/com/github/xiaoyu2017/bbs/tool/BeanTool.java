package com.github.xiaoyu2017.bbs.tool;

import org.springframework.beans.BeanUtils;

/**
 * bean相关工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class BeanTool {

    public static <T> T toTargetBean(Object source, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
