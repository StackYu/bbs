package com.github.xiaoyu2017.bbs.controller;

import com.github.xiaoyu2017.bbs.pojo.bean.Result;
import com.github.xiaoyu2017.bbs.pojo.bean.ResultCode;
import com.github.xiaoyu2017.bbs.pojo.dto.AttachmentDto;
import com.github.xiaoyu2017.bbs.server.AttachmentService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

/**
 * 附件控制类
 *
 * @author xiaoyu
 * @version 1.0
 */

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;
    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping
    public ResponseEntity upload(MultipartFile[] files, int type) {
        if (Objects.isNull(files)) {
            return ResponseEntity.ok(Result.error(ResultCode.UPLOAD_ERROR_400_1));
        }
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                return ResponseEntity.ok(Result.error(ResultCode.UPLOAD_ERROR_400_1));
            }
        }
        List<AttachmentDto> save;
        try {
            save = attachmentService.save(type, files);
        } catch (Exception e) {
            return ResponseEntity.ok(Result.error(ResultCode.UPLOAD_ERROR_500, e));
        }
        if (CollectionUtils.isEmpty(save)) {
            return ResponseEntity.ok(Result.success(ResultCode.UPLOAD_ERROR_500));
        }
        return ResponseEntity.ok(Result.success(ResultCode.UPLOAD_SUCCESS_200, save));
    }

    @GetMapping("/{aid}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String aid, HttpServletResponse response) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setExpires(0);
        headers.setCacheControl("no-store, no-cache, must-revalidate");
        headers.setCacheControl("post-check=0, pre-check=0");
        headers.setPragma("no-cache");
        headers.setContentType(MediaType.IMAGE_JPEG);

        Resource path = attachmentService.getPath(aid);

        Resource resource = new FileSystemResource(path.getFile());
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

}
