package com.xlzhou.system.controller;

import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.service.LoginfoService;
import com.xlzhou.system.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zxl
 * @Date: 2020/12/1  - 11:23
 */

@RestController
@RequestMapping("loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;


    /**
     * 查询登录日志
     *
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loadAllLoginfo")
    public Object loadAllLoginfo(LoginfoVo loginfoVo) {
        DataGridView dataGridView = this.loginfoService.queryAllLoginfo(loginfoVo);
        return dataGridView;
    }


    /**
     * 删除日志
     *
     * @param logInfoId
     * @return
     */
    @RequestMapping("deleteLoginfo")
    public ResultBean deleteLoginfo(Integer logInfoId) {
        try {
            this.loginfoService.removeById(logInfoId);
            return ResultBean.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     *
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultBean batchDeleteLoginfo(Integer[] ids) {
        try {
            if (ids != null && ids.length > 0) {
                List<Integer> idList = new ArrayList<>();
                for (Integer id : ids) {
                    idList.add(id);
                }
                this.loginfoService.removeByIds(idList);
                return ResultBean.DELETE_SUCCESS;
            } else {
                return new ResultBean(-1, "数据不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }

    }
}
