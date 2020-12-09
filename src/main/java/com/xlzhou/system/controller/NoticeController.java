package com.xlzhou.system.controller;

import com.xlzhou.system.common.ActiveUser;
import com.xlzhou.system.common.DataGridView;
import com.xlzhou.system.common.ResultBean;
import com.xlzhou.system.domain.Notice;
import com.xlzhou.system.domain.User;
import com.xlzhou.system.service.NoticeService;
import com.xlzhou.system.vo.NotiveVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: zxl
 * @Date: 2020/12/3  - 14:02
 */

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询系统公告
     *
     * @param notiveVo
     * @return
     */
    @RequestMapping("loadAllNotice")
    public Object loadAllNotice(NotiveVo notiveVo) {
        DataGridView dataGridView = this.noticeService.queryAllNotice(notiveVo);
        return dataGridView;
    }


    /**
     * 添加系统公告
     *
     * @param notice
     * @return
     */
    @RequestMapping("addNotice")
    public ResultBean addNotice(Notice notice) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            notice.setOpername(activeUser.getUser().getName());
            notice.setCreatetime(new Date());
            this.noticeService.save(notice);
            return ResultBean.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultBean.ADD_ERROR;
        }
    }

    /**
     * 修改系统公告
     * @param notice
     * @return
     */
    @RequestMapping("updateNotice")
    public ResultBean updateNotice(Notice notice){
        try {
            this.noticeService.updateById(notice);
            return ResultBean.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultBean.UPDATE_ERROR;
        }

    }

    /**
     * 删除日志
     *
     * @param noticeId
     * @return
     */
    @RequestMapping("deleteNotice")
    public ResultBean deleteNotice(Integer noticeId) {
        try {
            this.noticeService.removeById(noticeId);
            return ResultBean.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     *
     * @param ids
     * @return
     */
    @RequestMapping("batchDeleteNotice")
    public ResultBean batchDeleteNotice(Integer[] ids) {
        try {
            if (ids != null && ids.length > 0) {
                List<Integer> idList = new ArrayList<>();
                for (Integer id : ids) {
                    idList.add(id);
                }
                this.noticeService.removeByIds(idList);
                return ResultBean.DELETE_SUCCESS;
            } else {
                return new ResultBean(-1, "数据不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBean.DELETE_ERROR;
        }

    }
}
