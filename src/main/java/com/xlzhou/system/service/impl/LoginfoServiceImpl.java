package com.xlzhou.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlzhou.system.domain.Loginfo;
import com.xlzhou.system.mapper.LoginfoMapper;
import com.xlzhou.system.service.LoginfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @Auther: zxl
* @Date: 2020/12/1  - 11:22
*/
@Service
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements LoginfoService{

    @Autowired
    private  LoginfoMapper loginfoMapper;


    @Override
    public DataGridView queryAllLoginfo(LoginfoVo loginfoVo) {

        IPage<Loginfo> page = new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<Loginfo> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginName()),"loginname",loginfoVo.getLoginName());        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginName()),"loginname",loginfoVo.getLoginName());
        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginIp()),"loginip",loginfoVo.getLoginIp());        qw.like(StringUtils.isNotBlank(loginfoVo.getLoginName()),"loginname",loginfoVo.getLoginName());
        qw.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        qw.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        qw.orderByDesc("logintime");
        IPage<Loginfo> loginfoIPage = this.loginfoMapper.selectPage(page, qw);
        DataGridView dataGridView = new DataGridView(loginfoIPage.getTotal(), loginfoIPage.getRecords());
        return dataGridView;

    }

    @Override
    public void saveLogInfo(User user, String remoteAddr) {
        Loginfo loginfo=new Loginfo();
            loginfo.setLoginname(user.getName()+"-"+user.getLoginname());
            loginfo.setLoginip(remoteAddr);
            loginfo.setLogintime(new Date());
            loginfoMapper.insert(loginfo);
    }
}
