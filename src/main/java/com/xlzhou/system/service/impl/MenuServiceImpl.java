package com.xlzhou.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlzhou.system.common.Constant;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlzhou.system.domain.Menu;
import com.xlzhou.system.mapper.MenuMapper;
import com.xlzhou.system.service.MenuService;

/**
 * @Auther: zxl
 * @Date: 2020/11/23 - 11 - 23 - 14:23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuForList() {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type", Constant.MENYU_TYPE_TOP)
                        .or().eq("type", Constant.MENYU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        List<Menu> menuList = this.menuMapper.selectList(qw);
        return menuList;
    }

    @Override
    public List<Menu> queryMenuForListByUserId(Integer id) {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq("available", Constant.AVAILABLE_TRUE);
        qw.and(new Consumer<QueryWrapper<Menu>>() {
            @Override
            public void accept(QueryWrapper<Menu> menuQueryWrapper) {
                menuQueryWrapper.eq("type", Constant.MENYU_TYPE_TOP)
                        .or().eq("type", Constant.MENYU_TYPE_LEFT);
            }
        });
        qw.orderByAsc("ordernum");
        List<Menu> menuList = this.menuMapper.selectList(qw);
        return menuList;
    }

    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {

        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.eq(menuVo.getAvailable() != null, "available", menuVo.getAvailable());
        qw.orderByAsc("ordernum");
        List<Menu> menuList = this.menuMapper.selectList(qw);
        Long size = Long.valueOf(menuList.size());
        DataGridView dataGridView = new DataGridView(size, menuList);
        return dataGridView;
    }

    @Override
    public Integer queryMenuMaxOrderNum() {
        return null;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        this.menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        this.menuMapper.updateById(menu);
        return menu;
    }

    @Override
    public Integer queryMenuChildrenCountById(Integer id) {
        return null;
    }
}
