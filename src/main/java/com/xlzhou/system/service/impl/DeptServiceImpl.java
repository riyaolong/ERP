package com.xlzhou.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlzhou.system.domain.Dept;
import com.xlzhou.system.mapper.DeptMapper;
import com.xlzhou.system.service.DeptService;

/**
 * @Auther: zxl
 * @Date: 2020/12/7  - 10:41
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    private DataGridView dataGridView;

    @Override
    public DataGridView queryAllDept(DeptVo deptVo) {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.orderByAsc("ordernum");
        List<Dept> depts = this.deptMapper.selectList(qw);
        DataGridView dataGridView = new DataGridView(Long.valueOf(depts.size()), depts);
        return dataGridView;
    }

    @Override
    public Integer queryDeptMaxOrderNum() {
        Integer num = this.deptMapper.queryDeptMaxOrderNum();
        return num;
    }

    @Override
    public Dept saveDept(Dept dept) {
        this.deptMapper.insert(dept);
        return dept;
    }

    @Override
    public Dept updateDept(Dept dept) {
        this.deptMapper.updateById(dept);
        return dept;
    }



    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public Integer queryDeptChildrenCountById(Integer id) {
        Integer count = this.deptMapper.queryDeptChildrenCountById(id);
        return count;
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
