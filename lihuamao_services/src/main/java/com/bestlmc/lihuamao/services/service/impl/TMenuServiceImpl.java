package com.bestlmc.lihuamao.services.service.impl;


import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.commons.been.TMenu;
import com.bestlmc.lihuamao.services.mapper.TMenuMapper;
import com.bestlmc.lihuamao.services.service.ITMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
@Service
public class TMenuServiceImpl extends SuperServiceImpl<TMenuMapper, TMenu> implements ITMenuService {

    @Resource
    TMenuMapper tMenuMapper;

    @Override
    public TMenu getTest() {
        return tMenuMapper.getMenuById("111");
    }
}
