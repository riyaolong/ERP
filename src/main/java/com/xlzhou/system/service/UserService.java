package com.xlzhou.system.service;

import com.xlzhou.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
* @Auther: zxl
* @Date: 2020/11/23 - 11 - 23 - 16:40
*/
public interface UserService extends IService<User>{


    User queryUserByLoginName(String loginName);


}
