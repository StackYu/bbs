package com.stackyu.bbs.pojo.dao;

import com.stackyu.bbs.pojo.bean.CollectCode;
import com.stackyu.bbs.pojo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏类-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Collect extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1456133883784934357L;

    private Date time;
    private CollectCode code;
}
