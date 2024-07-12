package com.github.xiaoyu2017.bbs.tool;

import com.github.xiaoyu2017.bbs.pojo.dao.AttachmentDao;
import com.github.xiaoyu2017.bbs.pojo.dto.AttachmentDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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

    public static <T> List<T> toTargetBean(Class<T> clazz, Object... sources) {
        List<T> list;
        try {
            list = new ArrayList<>(sources.length);
            for (Object source : sources) {
                T target = clazz.newInstance();
                BeanUtils.copyProperties(source, target);
                list.add(target);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
