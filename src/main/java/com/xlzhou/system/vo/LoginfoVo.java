package com.xlzhou.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zxl
 * @Date: 2020/12/1  - 11:27
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginfoVo extends  BaseVo{

    private String loginName;
    private String loginIp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
