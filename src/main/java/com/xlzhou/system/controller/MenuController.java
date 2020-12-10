package com.xlzhou.system.controller;

import com.xlzhou.system.common.Constant;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.Menu;
import com.xlzhou.system.service.MenuService;
import com.xlzhou.system.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: zxl
 * @Date: 2020/12/7  - 10:41
 */

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 查询菜单和权限管理
     *
     * @param menuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public Object loadAllMenu(MenuVo menuVo) {
        DataGridView dataGridView = this.menuService.queryAllMenu(menuVo);
        return dataGridView;
    }

    /**
     * 查询菜单
     *
     * @param menuVo
     * @return
     */
    @RequestMapping("loadMenu")
    public Object loadMenu(MenuVo menuVo) {
        List<Menu> menuList = this.menuService.queryAllMenuForList();
        DataGridView dataGridView = new DataGridView(Long.valueOf(menuList.size()),menuList);
        return dataGridView;
    }

    /**
     * 查询菜单和权限最大的排序码
     */
    @RequestMapping("queryMenuMaxOrderNum")
    public Object queryMenuMaxOrderNum() {
        Integer maxValue = this.menuService.queryMenuMaxOrderNum();
        return new DataGridView(maxValue + 1);
    }

    /**
     * 添加菜单和权限
     *
     * @param menu
     * @return
     */
    @RequestMapping("addMenu")
    public ResultBean addMenu(Menu menu) {
        try {
            if("topmenu".equals(menu.getType())){
                menu.setPid(0);
            }
            menu.setSpread(Constant.SPREAD_TRUE);
            menu.setAvailable(Constant.AVAILABLE_TRUE);
            Menu d = this.menuService.saveMenu(menu);
            return ResultBean.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.ADD_ERROR;
        }

    }


    /**
     * 修改菜单和权限管理
     *
     * @param menu
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultBean updateMenu(Menu menu) {
        try {
            if("topmenu".equals(menu.getType())){
                menu.setPid(0);
            }
            Menu d = this.menuService.updateMenu(menu);
            return ResultBean.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.UPDATE_ERROR;
        }
    }

    /**
     * 根据id查询菜单和权限
     *
     * @param id
     * @return
     */
    @RequestMapping("getMenuById")
    public Object getMenuById(Integer id) {
        Menu byId = this.menuService.getById(id);
        return new DataGridView(byId);
    }

    /**
     * 根据id查询当前菜单和权限子菜单和权限的个数
     *
     * @param id
     * @return
     */
    @RequestMapping("getMenuChildrenCountById")
    public Object getMenuChildrenCountById(Integer id) {
        Integer count = this.menuService.queryMenuChildrenCountById(id);
        return new DataGridView(count);
    }

    @RequestMapping("deleteMenu")
    public ResultBean deleteMenu(Integer id){
        try {
            this.menuService.removeById(id);
            return  ResultBean.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }
    }
}
