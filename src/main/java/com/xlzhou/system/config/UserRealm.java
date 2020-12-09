package com.xlzhou.system.config;

import com.xlzhou.system.common.ActiveUser;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.service.MenuService;
import com.xlzhou.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;


public class UserRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    private UserService userService;
//
//    @Autowired
//    @Lazy
//    private RoleService roleService;

    @Autowired
    @Lazy
    private MenuService menuService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("进入UserRealm类doGetAuthenticationInfo认证方法");

        String userName=token.getPrincipal().toString();
        User user = this.userService.queryUserByLoginName(userName);
        if(null!=user){
            ActiveUser activeUser=new ActiveUser();
            activeUser.setUser(user);

//            //根据用户ID查询角色名称的集合
//            List<String   > roles=this.roleService.queryRoleNamesByUserId(user.getId());
//            //根据用户ID去查询权限编码的集合
//            List<String> permissions=this.menuService.queryPermissionCodesByUserId(user.getId());
//            activeUser.setRoles(roles);
//            activeUser.setPermissions(permissions);
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(activeUser,user.getPwd(), ByteSource.Util.bytes(user.getSalt()),getName());
            return info;
        }
        return null;
    }


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进入UserRealm类doGetAuthorizationInfo授权方法");
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        ActiveUser activeUser= (ActiveUser) principals.getPrimaryPrincipal();
        List<String> permissions = activeUser.getPermissions();
        List<String> roles = activeUser.getRoles();
        User user = activeUser.getUser();
//        if(user.getType().equals(Constant.USER_TYPE_SUPER)){
//            info.addStringPermission("*:*");
//        }else{
//            if(null!=roles&&roles.size()>0){
//                info.addRoles(roles);
//            }
//            if(null!=permissions&&permissions.size()>0){
//                info.addStringPermissions(permissions);
//            }
//        }
        return info;
    }

}
