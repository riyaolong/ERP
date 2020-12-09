package com.xlzhou.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlzhou.system.domain.Dept;

/**
* @Auther: zxl
* @Date: 2020/12/7  - 10:41
*/
public interface DeptMapper extends BaseMapper<Dept> {
    Integer queryDeptMaxOrderNum();

    Integer queryDeptChildrenCountById(Integer id);
}