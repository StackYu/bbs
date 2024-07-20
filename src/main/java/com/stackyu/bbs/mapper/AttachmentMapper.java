package com.stackyu.bbs.mapper;

import com.stackyu.bbs.pojo.dao.AttachmentDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Attachment附件数据库操作类-mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
@Mapper
public interface AttachmentMapper {

    int insert(@Param("attachments") AttachmentDao... attachments);

    AttachmentDao selectByAid(String aid);
}
