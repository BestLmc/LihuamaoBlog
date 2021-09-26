package com.bestlmc.lihuamao.biz.service;


import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.biz.vo.RoleVO;
import com.bestlmc.lihuamao.commons.been.Role;
import com.bestlmc.lihuamao.commons.http.CommonResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-22
 */
public interface RoleService extends SuperService<Role> {

    CommonResult addRole(RoleVO roleVO);

    CommonResult updateRole(RoleVO roleVO);

    CommonResult getRoleById(String uid);

    CommonResult deleteRole(RoleVO roleVO);
}
