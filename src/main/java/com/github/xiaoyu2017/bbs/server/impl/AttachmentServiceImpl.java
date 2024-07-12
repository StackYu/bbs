package com.github.xiaoyu2017.bbs.server.impl;

import com.github.xiaoyu2017.bbs.config.AttachmentConfig;
import com.github.xiaoyu2017.bbs.mapper.AttachmentMapper;
import com.github.xiaoyu2017.bbs.pojo.bo.AttachmentBo;
import com.github.xiaoyu2017.bbs.pojo.dao.AttachmentDao;
import com.github.xiaoyu2017.bbs.pojo.dto.AttachmentDto;
import com.github.xiaoyu2017.bbs.server.AttachmentService;
import com.github.xiaoyu2017.bbs.tool.BeanTool;
import com.github.xiaoyu2017.bbs.util.SnowFlakeUtil;
import com.github.xiaoyu2017.bbs.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 附件服务实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentConfig attachmentConfig;
    @Autowired
    AttachmentMapper attachmentMapper;

    @Override
    public List<AttachmentDto> save(int type, MultipartFile... files) throws RuntimeException, IOException {
        List<AttachmentBo> attachmentBoList = new ArrayList<>();
        // 获得AttachmentBo
        for (MultipartFile file : files) {
            AttachmentBo attachmentBo = new AttachmentBo();
            attachmentBo.setAid(SnowFlakeUtil.getStrSnowFlakeId());
            attachmentBo.setType(type);
            StrUtil.setFileSize(attachmentBo, file.getSize());
            attachmentBo.setMime(file.getContentType());
            attachmentBo.setOriginalFilename(file.getOriginalFilename());
            attachmentBo.setPath(attachmentConfig.getPath());
            attachmentBo.setMultipartFile(file);
            attachmentBoList.add(attachmentBo);
        }
        // 转换成AttachmentDao
        List<AttachmentDao> attachmentDaoList = attachmentBoList.stream().map(i -> BeanTool.toTargetBean(i, AttachmentDao.class)).collect(Collectors.toList());
        // 保存
        int insert = attachmentMapper.insert(attachmentDaoList.toArray(new AttachmentDao[0]));
        if (insert <= 0) {
            return null;
        }
        // 保存文件
        for (AttachmentBo attachmentBo : attachmentBoList) {
            String filePath = attachmentBo.getMime().trim().replace("image/", "");
            filePath = attachmentBo.getPath() + "/" + attachmentBo.getAid() + "." + filePath;
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            attachmentBo.getMultipartFile().transferTo(file);
        }

        // 转AttachmentDto
        return BeanTool.toTargetBean(AttachmentDto.class, attachmentDaoList.toArray());
    }

    @Override
    public Resource getPath(String aid) {
        AttachmentDao attachmentDao = attachmentMapper.selectByAid(aid);
        String mime = attachmentDao.getMime().trim().replace("image/", "");
        String path = attachmentDao.getPath() + "/" + attachmentDao.getAid() + "." + mime;
        Path file = Paths.get(path);

        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to load file: " + aid, e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("File not found: " + aid);
        }
    }

}
