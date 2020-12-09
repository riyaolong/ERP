package com.xlzhou.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.mapper.UserMapper;
import com.xlzhou.system.service.UserService;
/**
* @Auther: zxl
* @Date: 2020/11/23 - 11 - 23 - 16:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    private Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByLoginName(String loginName) {

        QueryWrapper<User>  queryWrapper  = new QueryWrapper<>();
        if(StringUtils.isBlank(loginName)){
            log.error("登录名不能为空");
            return null;
        }

        queryWrapper.eq("loginName",loginName);
        User user = userMapper.selectOne(queryWrapper);
        return user;

    }
}
