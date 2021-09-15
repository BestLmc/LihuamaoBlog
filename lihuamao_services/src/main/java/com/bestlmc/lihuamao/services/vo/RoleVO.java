package com.bestlmc.lihuamao.services.vo;

import com.bestlmc.lihuamao.base.vo.BaseVO;
import lombok.Data;

/**
 * @Description: 角色实体类
 * @Author bestlmc
 * @Date 2021/6/24 14:50
 */
@Data
public class RoleVO extends BaseVO<RoleVO> {
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
