package com.ilovesshan.service.impl;

import com.ilovesshan.anotation.HasPermission;
import com.ilovesshan.anotation.Log;
import com.ilovesshan.mapper.YdlUserMapper;
import com.ilovesshan.pojo.YdlUser;
import com.ilovesshan.service.YdlUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息表(YdlUser)表服务实现类
 *
 * @author makejava
 * @since 2022-10-15 17:34:12
 */
@Service("ydlUserService")
public class YdlUserServiceImpl implements YdlUserService {
    @Resource
    private YdlUserMapper ydlUserMapper;

    @Override
    public YdlUser selectUserByName(String username) {
        return ydlUserMapper.selectUserByName(username);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    @Log(business_module = "用户模块", business_type = "select", business_describe = "根据ID查询用户")
    @HasPermission({"/system-management/user-management"})
    public YdlUser queryById(Long userId) {
        return this.ydlUserMapper.queryById(userId);
    }

    /**
     * 分页查询
     *
     * @param ydlUser     筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<YdlUser> queryByPage(YdlUser ydlUser, PageRequest pageRequest) {
        long total = this.ydlUserMapper.count(ydlUser);
        return new PageImpl<>(this.ydlUserMapper.queryAllByLimit(ydlUser, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param ydlUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUser insert(YdlUser ydlUser) {
        this.ydlUserMapper.insert(ydlUser);
        return ydlUser;
    }

    /**
     * 修改数据
     *
     * @param ydlUser 实例对象
     * @return 实例对象
     */
    @Override
    public YdlUser update(YdlUser ydlUser) {
        this.ydlUserMapper.update(ydlUser);
        return this.queryById(ydlUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.ydlUserMapper.deleteById(userId) > 0;
    }
}
