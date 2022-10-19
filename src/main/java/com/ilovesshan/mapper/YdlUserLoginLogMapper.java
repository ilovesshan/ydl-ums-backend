package com.ilovesshan.mapper;

import com.ilovesshan.pojo.YdlUserLoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户登录表(YdlUserLoginLog)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-19 09:20:53
 */
public interface YdlUserLoginLogMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param loginId 主键
     * @return 实例对象
     */
    YdlUserLoginLog queryById(Long loginId);

    /**
     * 查询指定行数据
     *
     * @param ydlUserLoginLog 查询条件
     * @param pageable        分页对象
     * @return 对象列表
     */
    List<YdlUserLoginLog> queryAllByLimit(
            @Param("ydlUserLoginLog") YdlUserLoginLog ydlUserLoginLog,
            @Param("pageable") Pageable pageable,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime
    );

    /**
     * 统计总行数
     *
     * @param ydlUserLoginLog 查询条件
     * @return 总行数
     */
    long count(@Param("ydlUserLoginLog") YdlUserLoginLog ydlUserLoginLog, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 新增数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 影响行数
     */
    int insert(YdlUserLoginLog ydlUserLoginLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlUserLoginLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<YdlUserLoginLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<YdlUserLoginLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<YdlUserLoginLog> entities);

    /**
     * 修改数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 影响行数
     */
    int update(YdlUserLoginLog ydlUserLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param loginId 主键
     * @return 影响行数
     */
    int deleteById(Long loginId);


    int deleteByIds(@Param("ids") int[] ids);
}

