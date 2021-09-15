package com.bestlmc.lihuamao.services.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.commons.been.Admin;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.global.MessageConf;
import com.bestlmc.lihuamao.services.global.SQLConf;
import com.bestlmc.lihuamao.services.mapper.MenuMapper;
import com.bestlmc.lihuamao.services.service.MenuService;
import com.bestlmc.lihuamao.services.vo.MenuVO;
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
public class MenuServiceImpl extends SuperServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    MenuMapper menuMapper;


    @Override
    public CommonResult addMenu(MenuVO menuVO) {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.MENU_NAME, menuVO.getMenuName());
        Menu checkOne = menuMapper.selectOne(queryWrapper);

        if(checkOne == null){
            Menu menu = new Menu();
            menu.setUid(menuVO.getUid());
            menu.setMenuName(menuVO.getMenuName());
            menu.setParentUid(menuVO.getParentUid());
            menu.setMenuLevel(menuVO.getMenuLevel());
            menu.setMenuType(menuVO.getMenuType());
            int insert = menuMapper.insert(menu);
            if(insert > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.INSERT_SUCCESS, null);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.QUERY_NOT_FONUD, null);
    }

    @Override
    public CommonResult updateMenu(MenuVO menuVO) {

        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.UID, menuVO.getUid());
        Menu checkOne = menuMapper.selectOne(queryWrapper);

        if(checkOne != null){
            Menu menu = new Menu();
            menu.setUid(menuVO.getUid());
            menu.setMenuName(menuVO.getMenuName());
            menu.setParentUid(menuVO.getParentUid());
            menu.setMenuLevel(menuVO.getMenuLevel());
            menu.setMenuType(menuVO.getMenuType());
            int update = menuMapper.updateById(menu);
            if(update > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, null);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.OPERATION_FAIL, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.QUERY_NOT_FONUD, null);
    }

    @Override
    public CommonResult getMenuById(String uid) {
        Menu menu = menuMapper.getMenuById(uid);

        MenuVO menuVO = new MenuVO();
        menuVO.setUid(menu.getUid());
        menuVO.setMenuName(menu.getMenuName());
        menuVO.setMenuLevel(menu.getMenuLevel());
        menuVO.setMenuType(menu.getMenuType());
        menuVO.setParentUid(menu.getParentUid());
        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, menuVO);
    }

    @Override
    public CommonResult deleteMenu(MenuVO menuVO) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.UID, menuVO.getUid());
        Menu checkOne = menuMapper.selectOne(queryWrapper);

        if(checkOne == null){
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.QUERY_NOT_FONUD, null);
        }

        int delete = menuMapper.deleteById(menuVO.getUid());
        if(delete > 0){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.DELETE_SUCCESS, delete);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.DELETE_FAIL, null);
    }
}
