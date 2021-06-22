package com.bestlmc.lihuamao.commons.been;

import com.bestlmc.lihuamao.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TRole extends BaseEntity {


    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色介绍
     */
    private String introduce;

    /**
     * 角色管辖的菜单的UID
     */
    private String menuUids;


}