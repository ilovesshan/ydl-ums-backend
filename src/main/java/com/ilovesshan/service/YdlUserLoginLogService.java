package com.ilovesshan.service;

import com.ilovesshan.pojo.YdlUserLoginLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户登录表(YdlUserLoginLog)表服务接口
 *
 * @author makejava
 * @since 2022-10-19 09:20:53
 */
public interface YdlUserLoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param loginId 主键
     * @return 实例对象
     */
    YdlUserLoginLog queryById(Long loginId);

    /**
     * 分页查询
     *
     * @param ydlUserLoginLog 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<YdlUserLoginLog> queryByPage(YdlUserLoginLog ydlUserLoginLog, PageRequest pageRequest, String startTime, String endTime);

    /**
     * 新增数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 实例对象
     */
    YdlUserLoginLog insert(YdlUserLoginLog ydlUserLoginLog);

    /**
     * 修改数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 实例对象
     */
    YdlUserLoginLog update(YdlUserLoginLog ydlUserLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param loginId 主键
     * @return 是否成功
     */
    boolean deleteById(Long loginId);

    boolean deleteByIds(int[] ids);
}
