package com.xlzhou.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: zxl
 * @Date: 2020/12/1  - 11:46
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class DeptVo  extends BaseVo{

    private String title;

    private Integer isAvailable;

}
