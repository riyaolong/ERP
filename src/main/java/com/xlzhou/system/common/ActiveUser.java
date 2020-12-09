package com.xlzhou.system.common;

import com.xlzhou.system.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zxl
 * @Date: 2020/11/24 - 11 - 24 - 9:39
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser implements Serializable {

    private static final long serialVersionUID = -2026900391947175544L;
    private User user ;
    private List<String> roles;
    private List<String> permissions;
}
