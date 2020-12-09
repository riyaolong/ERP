package com.xlzhou.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xlzhou.system.common.ActiveUser;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.vo.NotiveVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlzhou.system.domain.Notice;
import com.xlzhou.system.mapper.NoticeMapper;
import com.xlzhou.system.service.NoticeService;
/**
* @Auther: zxl
* @Date: 2020/12/3  - 14:01
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;
    
    @Override
    public DataGridView queryAllNotice(NotiveVo notiveVo) {

        IPage<Notice> page = new Page<>(notiveVo.getPage(),notiveVo.getLimit());
        QueryWrapper<Notice> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(notiveVo.getTitle()),"title",notiveVo.getTitle());
        qw.like(StringUtils.isNotBlank(notiveVo.getOperName()),"opername",notiveVo.getOperName());
        qw.ge(notiveVo.getStartTime()!=null,"createtime",notiveVo.getStartTime());
        qw.le(notiveVo.getEndTime()!=null,"createtime",notiveVo.getEndTime());
        qw.orderByDesc("createtime");
        IPage<Notice> noticeIPage = this.noticeMapper.selectPage(page, qw);
        DataGridView dataGridView = new DataGridView(noticeIPage.getTotal(), noticeIPage.getRecords());
        return dataGridView;
    }


}
