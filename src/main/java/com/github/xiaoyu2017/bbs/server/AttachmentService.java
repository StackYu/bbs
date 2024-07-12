package com.github.xiaoyu2017.bbs.server;

import com.github.xiaoyu2017.bbs.pojo.dto.AttachmentDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 附件服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface AttachmentService {

    List<AttachmentDto> save(int type, MultipartFile... files) throws IOException;

    Resource getPath(String aid);
}
