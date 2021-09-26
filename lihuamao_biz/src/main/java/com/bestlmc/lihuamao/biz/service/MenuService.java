package com.bestlmc.lihuamao.biz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.biz.vo.MenuVO;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.http.CommonResult;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
public interface MenuService extends SuperService<Menu> {

    public IPage<Menu> getExpandPageList(MenuVO menuVO);

    public CommonResult addMenu(MenuVO menuVO);

    public CommonResult getAllMenu();

    public CommonResult updateMenu(MenuVO menuVO);

    public CommonResult getMenuById(String uid);

    public CommonResult deleteMenu(MenuVO menuVO);
}
