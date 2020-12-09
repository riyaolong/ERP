package com.xlzhou.system.service;

import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.domain.Loginfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.vo.LoginfoVo;

import javax.servlet.http.HttpServletRequest;

/**
* @Auther: zxl
* @Date: 2020/12/1  - 11:22
*/
public interface LoginfoService extends IService<Loginfo>{


        DataGridView queryAllLoginfo(LoginfoVo loginfoVo);

        void saveLogInfo(User user, String remoteAddr);
}
