package com.xlzhou.system.service;

import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xlzhou.system.vo.DeptVo;

/**
* @Auther: zxl
* @Date: 2020/12/7  - 10:41
*/
public interface DeptService extends IService<Dept>{


    DataGridView queryAllDept(DeptVo deptVo);

    Integer queryDeptMaxOrderNum();

    Dept saveDept(Dept dept);

    Dept updateDept(Dept dept);

    Integer queryDeptChildrenCountById(Integer id);
}
