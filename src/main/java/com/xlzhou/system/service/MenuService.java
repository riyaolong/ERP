package com.xlzhou.system.service;

import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xlzhou.system.vo.MenuVo;

import java.util.List;

/**
* @Auther: zxl
* @Date: 2020/11/23 - 11 - 23 - 14:23
*/
public interface MenuService extends IService<Menu>{

    /**
     * 查询全部菜单
     * @return
     */
    List<Menu> queryAllMenuForList();

    /**
     * 根据用户id查询部分菜单
     * @return
     */
    List<Menu> queryMenuForListByUserId(Integer id);

    DataGridView queryAllMenu(MenuVo menuVo);

    Integer queryMenuMaxOrderNum();

    Menu saveMenu(Menu menu);

    Menu updateMenu(Menu menu);

    Integer queryMenuChildrenCountById(Integer id);
}
