package com.ilovesshan.mapper;

import com.ilovesshan.pojo.YdlAttachment;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 系统附件表(YdlAttachment)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-18 19:29:25
 */
public interface YdlAttachmentMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    YdlAttachment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param ydlAttachment 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<YdlAttachment> queryAllByLimit(@Param("ydlAttachment") YdlAttachment ydlAttachment, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param ydlAttachment 查询条件
     * @return 总行数
     */
    long count(YdlAttachment ydlAttachment);

    /**
     * 新增数据
     *
     * @param ydlAttachment 实例对象
     * @return 影响行数
     */
    int insert(YdlAttachment ydlAttachment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlAttachment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<YdlAttachment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlAttachment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<YdlAttachment> entities);

    /**
     * 修改数据
     *
     * @param ydlAttachment 实例对象
     * @return 影响行数
     */
    int update(YdlAttachment ydlAttachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

