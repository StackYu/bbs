package com.github.xiaoyu2017.bbs.pojo.dao;

import com.github.xiaoyu2017.bbs.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 积分-pojo
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Score extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -4379673017324213801L;

    /** 威望 */
    private Integer prestige;
    /** 贡献值 */
    private Integer contribution;
    /** 悬赏值 */
    private Integer reward;
    /** 采纳率 */
    private Integer adoption;
    /** 热心值 */
    private Integer hot;
    /** 违规 */
    private Integer violations;
    /** 总积分 */
    private Integer score;
    /** 论坛币 */
    private Integer money;
}
