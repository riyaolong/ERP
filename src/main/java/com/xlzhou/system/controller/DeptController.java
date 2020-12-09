package com.xlzhou.system.controller;

import com.xlzhou.system.common.Constant;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.Dept;
import com.xlzhou.system.service.DeptService;
import com.xlzhou.system.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zxl
 * @Date: 2020/12/7  - 10:41
 */

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    /**
     * 查询部门管理
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("loadAllDept")
    public Object loadAllDept(DeptVo deptVo) {
        DataGridView dataGridView = this.deptService.queryAllDept(deptVo);
        return dataGridView;
    }

    /**
     * 查询部门最大的排序码
     */
    @RequestMapping("queryDeptMaxOrderNum")
    public Object queryDeptMaxOrderNum() {
        Integer maxValue = this.deptService.queryDeptMaxOrderNum();
        return new DataGridView(maxValue + 1);
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @RequestMapping("addDept")
    public ResultBean addDept(Dept dept) {
        try {
            dept.setSpread(Constant.SPREAD_FALSE);
            dept.setAvailable(Constant.AVAILABLE_TRUE);
            Dept d = this.deptService.saveDept(dept);
            return ResultBean.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.ADD_ERROR;
        }

    }


    /**
     * 修改部门管理
     *
     * @param dept
     * @return
     */
    @RequestMapping("updateDept")
    public ResultBean updateDept(Dept dept) {
        try {
            Dept d = this.deptService.updateDept(dept);
            return ResultBean.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.UPDATE_ERROR;
        }
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @RequestMapping("getDeptById")
    public Object getDeptById(Integer id) {
        Dept byId = this.deptService.getById(id);
        return new DataGridView(byId);
    }

    /**
     * 根据id查询当前部门子部门的个数
     *
     * @param id
     * @return
     */
    @RequestMapping("getDeptChildrenCountById")
    public Object getDeptChildrenCountById(Integer id) {
        Integer count = this.deptService.queryDeptChildrenCountById(id);
        return new DataGridView(count);
    }

    @RequestMapping("deleteDept")
    public ResultBean deleteDept(Integer id){
        try {
            this.deptService.removeById(id);
            return  ResultBean.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }
    }
}
