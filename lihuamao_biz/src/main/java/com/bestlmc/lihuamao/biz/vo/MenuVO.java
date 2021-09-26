package com.bestlmc.lihuamao.biz.vo;

import com.bestlmc.lihuamao.base.vo.BaseVO;
import lombok.Data;

/**
 * @Description: 菜单实体类
 * @Author bestlmc
 * @Date 2021/6/24 14:50
 */
@Data
public class MenuVO extends BaseVO<MenuVO> {
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单级别
     */
    private Integer menuLevel;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 父uid
     */
    private String parentUid;

    /**
     * url地址
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序字段，越大越靠前
     */
    private Integer sort;

    /**
     * 菜单类型 0: 菜单   1: 按钮
     */
    private Integer menuType;

}
