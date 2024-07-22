package com.stackyu.bbs.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class SystemInfoVo {
    /** ID */
    protected Integer id;
    /** 系统ID */
    private String systemId;
    private String name;
    /** 系统名称 */
    private String systemName;
    /** 对外连接 */
    private String url;
    /** 内部链接 */
    private String link;

    private Integer parentId;

    private List<SystemInfoVo> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.name = systemName;
        this.systemName = systemName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<SystemInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<SystemInfoVo> children) {
        this.children = children;
    }
}
