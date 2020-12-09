package com.xlzhou.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zxl
 * @Date: 2020/11/24 - 11 - 24 - 9:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean {


    public static final ResultBean IS_LOGIN =new ResultBean(200,"已登陆");
    public static final ResultBean UN_LOGIN =new ResultBean(-1,"未登陆");

    public static final ResultBean DELETE_SUCCESS = new ResultBean(200,"删除成功");
    public static final ResultBean DELETE_ERROR =  new ResultBean(-1,"删除失败") ;


    public static final ResultBean ADD_SUCCESS = new ResultBean(200,"添加成功");
    public static final ResultBean ADD_ERROR =  new ResultBean(-1,"添加失败") ;

    public static final ResultBean UPDATE_SUCCESS = new ResultBean(200,"更新成功");
    public static final ResultBean UPDATE_ERROR =  new ResultBean(-1,"更新失败") ;
    public static final ResultBean DISPATCH_SUCCESS = new ResultBean(200,"分配成功");
    public static final ResultBean DISPATCH_ERROR = new ResultBean(-1,"分配失败");
    public static final ResultBean RESET_SUCCESS = new ResultBean(200,"重置成功");
    public static final ResultBean RESET_ERROR = new ResultBean(-1,"重置成功");





    private Integer code = 200;
    private String  msg = "";
    private String token = "";



    public ResultBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
