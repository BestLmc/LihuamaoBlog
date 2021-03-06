package com.bestlmc.lihuamao.commons.been;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bestlmc.lihuamao.base.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
@Data
@TableName("t_menu")
public class Menu extends BaseEntity {


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

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> childMenuList;

}
