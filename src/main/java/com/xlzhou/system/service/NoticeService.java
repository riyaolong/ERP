package com.xlzhou.system.service;

import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xlzhou.system.vo.NotiveVo;

/**
* @Auther: zxl
* @Date: 2020/12/3  - 14:01
*/
public interface NoticeService extends IService<Notice>{


        DataGridView queryAllNotice(NotiveVo notiveVo);

}
