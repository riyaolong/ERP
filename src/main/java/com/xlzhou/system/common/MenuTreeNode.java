package com.xlzhou.system.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zxl
 * @Date: 2020/11/30 - 11 - 30 - 10:37
 */
@Data
public class MenuTreeNode {

    private Integer id;
    private Integer pid;
    //字段空值不显示 ""
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;
    private String href;
    private String icon;
    private Boolean spread;
    private String target;
    private String typecode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)//字段空值不显示,"child":[]
    private List<MenuTreeNode> child = new ArrayList<>();


    /**
     * 构造主页左侧的树菜单
     *
     * @param id
     * @param pid
     * @param title
     * @param href
     * @param icon
     * @param spread
     * @param target
     */
    public MenuTreeNode(Integer id, Integer pid, String title, String href, String icon, Boolean spread, String target, String typecode) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.spread = spread;
        this.target = target;
        this.typecode = typecode;
    }


    public static class MenuTreeNodeBuilder {//处理菜单显示格式
        public static List<MenuTreeNode> builder(List<MenuTreeNode> menuTreeNodeList, Integer topId) {

            List<MenuTreeNode> menuTreeNodes = new ArrayList<>();

            for (MenuTreeNode n1 : menuTreeNodeList) {
                if (n1.getPid().equals(topId)) {
                    menuTreeNodes.add(n1);
                }
                for (MenuTreeNode n2 : menuTreeNodeList) {
                    if (n2.getPid().equals(n1.getId())) {
                        n1.getChild().add(n2);
                    }
                }
            }
            return menuTreeNodes;
        }

    }
}
