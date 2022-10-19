package com.ilovesshan.service;

import com.ilovesshan.pojo.YdlAttachment;
import com.ilovesshan.pojo.YdlUserLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 系统附件表(YdlAttachment)表服务接口
 *
 * @author makejava
 * @since 2022-10-18 19:29:25
 */
public interface YdlAttachmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdlAttachment queryById(Integer id);

    /**
     * 分页查询
     *
     * @param ydlAttachment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<YdlAttachment> queryByPage(YdlAttachment ydlAttachment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param multipartFile 实例对象
     * @return 实例对象
     */
    YdlAttachment insert(MultipartFile multipartFile);

    /**
     * 修改数据
     *
     * @param ydlAttachment 实例对象
     * @return 实例对象
     */
    YdlAttachment update(YdlAttachment ydlAttachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    ResponseEntity<byte[]> download(Integer id);

    ResponseEntity<byte[]>  downloadExcel(List<YdlUserLoginLog> ydlUserLoginLogs);
}
