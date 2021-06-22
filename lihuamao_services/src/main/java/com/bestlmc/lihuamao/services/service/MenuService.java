package com.bestlmc.lihuamao.services.service;


import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.commons.been.TMenu;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
public interface MenuService extends SuperService<TMenu> {

    public TMenu getTest();
}
