package com.bestlmc.lihuamao.services.service;


import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.vo.MenuVO;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
public interface MenuService extends SuperService<Menu> {

    public CommonResult addMenu(MenuVO menuVO);

    public CommonResult updateMenu(MenuVO menuVO);

    public CommonResult getMenuById(String uid);

    public CommonResult deleteMenu(MenuVO menuVO);
}
