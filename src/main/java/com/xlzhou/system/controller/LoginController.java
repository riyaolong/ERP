package com.xlzhou.system.controller;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlzhou.system.common.ActiveUser;
import com.xlzhou.system.common.Constant;
import com.xlzhou.system.common.MenuTreeNode;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.Menu;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.service.LoginfoService;
import com.xlzhou.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * @Auther: zxl
 * @Date: 2020/11/24 - 11 - 24 - 9:25
 */

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private LoginfoService loginfoService;

    //用户登录
    @ResponseBody
    @RequestMapping("doLogin")
    public ResultBean doLoing(String loginName, String password, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
            subject.login(usernamePasswordToken);
            //获取shiro的sessionid  相当于token
            String token = subject.getSession().getId().toString();
            //写入登陆日志
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            User user = activeUser.getUser();
            String remoteAddr = request.getRemoteAddr();
            loginfoService.saveLogInfo(user, remoteAddr);

            return new ResultBean(200, "登录成功", token);
        } catch (UnknownAccountException e) { //用户名不存在
            return new ResultBean(-1, "登录名不存在");
        } catch (IncorrectCredentialsException e) {//密码错误
            return new ResultBean(-1, "密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResultBean(-1, "登录名或密码错误");

        }
    }


    /**
     * 加载登录首页顶部菜单和底部菜单
     */

    @RequestMapping("loadIndexMenu")
    @ResponseBody //将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给客户端。
    public Object loadIndexMenu() {
        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
        User user = activeUser.getUser();
        if (null == user) {
            return null;
        }
        List<Menu> menuList = null;
        if (user.getType().equals(Constant.USER_TYEPE_SUPER)) {//超级管理员
            menuList = menuService.queryAllMenuForList();
        } else {//普通用户
            menuList = menuService.queryMenuForListByUserId(user.getId());
        }

        List<MenuTreeNode> menuTreeNodes = new ArrayList<>();//应用于前端菜单格式的类
        for (Menu menu : menuList) {
            Boolean spread = menu.getSpread() == Constant.SPREAD_TRUE ? true : false;
            menuTreeNodes.add(new MenuTreeNode(menu.getId(),
                    menu.getPid(),
                    menu.getTitle(),
                    menu.getHref(),
                    menu.getIcon(),
                    spread,
                    menu.getTarget(),
                    menu.getTypecode()));
        }
        List<MenuTreeNode> builder = MenuTreeNode.MenuTreeNodeBuilder.builder(menuTreeNodes, 0);

        Map<String, Object> map = new HashMap<>();
        for (MenuTreeNode menuTreeNode : builder) {
            map.put(menuTreeNode.getTypecode(), menuTreeNode);
        }
        return map;
    }


    /**
     * 验证用户是否登录
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public ResultBean checkLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) { //是否认证登录
            return ResultBean.IS_LOGIN;
        } else {
            return ResultBean.UN_LOGIN;
        }
    }
}
