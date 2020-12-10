package com.xlzhou.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlzhou.system.domain.Menu;

/**
* @Auther: zxl
* @Date: 2020/11/23 - 11 - 23 - 14:23
*/
public interface MenuMapper extends BaseMapper<Menu> {
    Integer queryMenuMaxOrderNum();

    Integer queryMenuChildrenCountById(Integer id);
}