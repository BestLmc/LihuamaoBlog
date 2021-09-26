package com.bestlmc.lihuamao.biz.service;

import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.biz.vo.AdminVO;
import com.bestlmc.lihuamao.commons.been.Admin;
import com.bestlmc.lihuamao.commons.http.CommonResult;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author bestlmc
 * @Date 2021/6/22 13:18
 */
public interface AdminService extends SuperService<Admin> {

    public CommonResult addAdmin(AdminVO adminVO);

    public CommonResult updateAdmin(AdminVO adminVO);

    public CommonResult getAdminById(String uid);

    public CommonResult deleteAdmin(AdminVO adminVO);
}
