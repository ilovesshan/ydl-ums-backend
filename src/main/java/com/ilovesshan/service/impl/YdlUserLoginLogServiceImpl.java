package com.ilovesshan.service.impl;

import com.ilovesshan.anotation.Log;
import com.ilovesshan.mapper.YdlUserLoginLogMapper;
import com.ilovesshan.pojo.YdlUserLoginLog;
import com.ilovesshan.service.YdlUserLoginLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录表(YdlUserLoginLog)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 09:20:53
 */
@Service("ydlUserLoginLogService")
public class YdlUserLoginLogServiceImpl implements YdlUserLoginLogService {
    @Resource
    private YdlUserLoginLogMapper ydlUserLoginLogMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param loginId 主键
     * @return 实例对象
     */
    @Override
    public YdlUserLoginLog queryById(Long loginId) {
        return this.ydlUserLoginLogMapper.queryById(loginId);
    }

    /**
     * 分页查询
     *
     * @param ydlUserLoginLog 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    @Override
    public Page<YdlUserLoginLog> queryByPage(YdlUserLoginLog ydlUserLoginLog, PageRequest pageRequest, String startTime, String endTime) {
        long total = this.ydlUserLoginLogMapper.count(ydlUserLoginLog, startTime, endTime);
        return new PageImpl<>(this.ydlUserLoginLogMapper.queryAllByLimit(ydlUserLoginLog, pageRequest, startTime, endTime), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUserLoginLog insert(YdlUserLoginLog ydlUserLoginLog) {
        this.ydlUserLoginLogMapper.insert(ydlUserLoginLog);
        return ydlUserLoginLog;
    }

    /**
     * 修改数据
     *
     * @param ydlUserLoginLog 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUserLoginLog update(YdlUserLoginLog ydlUserLoginLog) {
        this.ydlUserLoginLogMapper.update(ydlUserLoginLog);
        return this.queryById(ydlUserLoginLog.getLoginId());
    }

    /**
     * 通过主键删除数据
     *
     * @param loginId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long loginId) {
        return this.ydlUserLoginLogMapper.deleteById(loginId) > 0;
    }


    @Override
    @Log(business_module = "日志模块", business_type = "delete", business_describe = "根据ids删除登录日志")
    public boolean deleteByIds(int[] ids) {
        return this.ydlUserLoginLogMapper.deleteByIds(ids) > 0;
    }
}
