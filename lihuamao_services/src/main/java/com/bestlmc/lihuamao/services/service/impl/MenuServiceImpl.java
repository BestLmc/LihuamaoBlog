package com.bestlmc.lihuamao.services.service.impl;


import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.commons.been.TMenu;
import com.bestlmc.lihuamao.services.mapper.MenuMapper;
import com.bestlmc.lihuamao.services.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-20
 */
@Service
public class MenuServiceImpl extends SuperServiceImpl<MenuMapper, TMenu> implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public TMenu getTest() {
        return menuMapper.getMenuById("111");
    }
}
