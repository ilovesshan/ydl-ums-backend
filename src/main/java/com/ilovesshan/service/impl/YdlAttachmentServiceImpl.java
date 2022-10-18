package com.ilovesshan.service.impl;

import com.ilovesshan.mapper.YdlAttachmentMapper;
import com.ilovesshan.pojo.YdlAttachment;
import com.ilovesshan.service.YdlAttachmentService;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 系统附件表(YdlAttachment)表服务实现类
 *
 * @author makejava
 * @since 2022-10-18 19:29:25
 */
@Service("ydlAttachmentService")
public class YdlAttachmentServiceImpl implements YdlAttachmentService {
    @Resource
    private YdlAttachmentMapper ydlAttachmentMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public YdlAttachment queryById(Integer id) {
        return this.ydlAttachmentMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param ydlAttachment 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @Override
    public Page<YdlAttachment> queryByPage(YdlAttachment ydlAttachment, PageRequest pageRequest) {
        long total = this.ydlAttachmentMapper.count(ydlAttachment);
        return new PageImpl<>(this.ydlAttachmentMapper.queryAllByLimit(ydlAttachment, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param multipartFile 实例对象
     * @return 实例对象
     */
    @Override
    public YdlAttachment insert(MultipartFile multipartFile) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 客户端访问地址
        String requestPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        // 文件原始名称
        String originName = multipartFile.getOriginalFilename();
        // 随机UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 根据日期进行分文件保存
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileDistName = localDateTime.getYear() + "" + localDateTime.getMonth().getValue() + "" + localDateTime.getDayOfMonth();
        // 保存在服务器的地址
        String saveFileName = "ssm-upload/" + fileDistName + "/" + fileDistName + "_" + uuid + originName.substring(originName.lastIndexOf("."));
        try {
            File parentFileDistName = new File("D:/ssm-upload/" + fileDistName);
            if (!parentFileDistName.exists()) {
                parentFileDistName.mkdirs();
            }
            requestPath += saveFileName;
            multipartFile.transferTo(new File("D:/" + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        YdlAttachment attachment = YdlAttachment.builder()
                .createBy(request.getHeader("username"))
                .createByUserId(Integer.valueOf(request.getHeader("userId")))
                .createTime(new Date())
                .updateTime(new Date())
                .url(requestPath)
                .build();
        this.ydlAttachmentMapper.insert(attachment);
        return attachment;
    }

    /**
     * 修改数据
     *
     * @param ydlAttachment 实例对象
     * @return 实例对象
     */
    @Override
    public YdlAttachment update(YdlAttachment ydlAttachment) {
        this.ydlAttachmentMapper.update(ydlAttachment);
        return this.queryById(ydlAttachment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        YdlAttachment attachment = this.queryById(id);
        if (attachment == null) {
            throw new RuntimeException("该附件信息不存在");
        }
        return this.ydlAttachmentMapper.deleteById(id) > 0;
    }

    @Override
    public ResponseEntity<byte[]> download(Integer id) {
        YdlAttachment attachment = this.queryById(id);
        if (attachment == null) {
            throw new RuntimeException("该附件信息不存在");
        }

        byte[] bytes = new byte[0];
        HttpHeaders headers = null;
        try {
            String targetFileName = attachment.getUrl().substring(attachment.getUrl().lastIndexOf("ums")).replace("ums", "");
            bytes = FileUtils.readFileToByteArray(new File("D:/" + targetFileName));
            headers = new HttpHeaders();
            // Content-Disposition就是当用户想把请求所得的内容存为一个文件的时候提供一个默认的文件名。
            headers.set("Content-Disposition", "attachment;filename=" + URLEncoder.encode(targetFileName, "UTF-8"));
            headers.set("charsetEncoding", "utf-8");
            headers.set("content-type", "multipart/form-data");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("该附件信息不存在");
        }
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}
