package com.xlzhou.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther: zxl
 * @Date: 2020/12/3  - 14:05
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class NotiveVo extends BaseVo{

    private String title;
    private String operName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;



}
