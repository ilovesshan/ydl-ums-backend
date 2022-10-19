package com.ilovesshan.controller;

import com.ilovesshan.common.R;
import com.ilovesshan.pojo.YdlUserLoginLog;
import com.ilovesshan.service.YdlUserLoginLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录表(YdlUserLoginLog)表控制层
 *
 * @author makejava
 * @since 2022-10-19 09:20:53
 */
@RestController
@RequestMapping("/loginLog")
public class YdlUserLoginLogController {
    /**
     * 服务对象
     */
    @Resource
    private YdlUserLoginLogService ydlUserLoginLogService;

    /**
     * 分页查询
     *
     * @param ydlUserLoginLog 筛选条件
     * @param pageNum         分页页数
     * @param pageSize        分页条数
     * @return 查询结果
     */
    @GetMapping
    public R queryByPage(
            YdlUserLoginLog ydlUserLoginLog, @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum,
            @RequestParam(value = "startTime", required = false) String startTime, @RequestParam(value = "endTime", required = false) String endTime
    ) {
        Page<YdlUserLoginLog> loginLogs = this.ydlUserLoginLogService.queryByPage(ydlUserLoginLog, PageRequest.of(pageNum - 1, pageSize), startTime, endTime);
        return R.success(R.SUCCESS_MESSAGE_select, loginLogs);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResponseEntity<YdlUserLoginLog> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.ydlUserLoginLogService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param ydlUserLoginLog 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<YdlUserLoginLog> add(YdlUserLoginLog ydlUserLoginLog) {
        return ResponseEntity.ok(this.ydlUserLoginLogService.insert(ydlUserLoginLog));
    }

    /**
     * 编辑数据
     *
     * @param ydlUserLoginLog 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<YdlUserLoginLog> edit(YdlUserLoginLog ydlUserLoginLog) {
        return ResponseEntity.ok(this.ydlUserLoginLogService.update(ydlUserLoginLog));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteById(Long id) {
//        return ResponseEntity.ok(this.ydlUserLoginLogService.deleteById(id));
//    }


    /**
     * 删除数据
     *
     * @param ids 主键列表
     * @return 删除是否成功
     */
    @DeleteMapping
    public R deleteById(@RequestParam("ids") int[] ids) {
        this.ydlUserLoginLogService.deleteByIds(ids);
        return R.success("删除成功", null);
    }

}

