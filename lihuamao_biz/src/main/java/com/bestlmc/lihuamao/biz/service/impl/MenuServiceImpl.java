package com.bestlmc.lihuamao.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.base.enums.SysStatus;
import com.bestlmc.lihuamao.biz.global.MessageConf;
import com.bestlmc.lihuamao.biz.global.SQLConf;
import com.bestlmc.lihuamao.biz.global.SysConstant;
import com.bestlmc.lihuamao.biz.mapper.MenuMapper;
import com.bestlmc.lihuamao.biz.service.MenuService;
import com.bestlmc.lihuamao.biz.vo.MenuVO;
import com.bestlmc.lihuamao.commons.been.Admin;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.been.SysDictItem;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


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
    MenuService menuService;

    @Resource
    MenuMapper menuMapper;


    /**
     * 查询父子表列表数据
     * 先查一级菜单
     * 再查二级菜单
     * 最后查三级菜单
     * @param menuVO
     * @return
     */
    @Override
    public IPage<Menu> getExpandPageList(MenuVO menuVO) {
        // 一级菜单
        QueryWrapper<Menu> oneceWrapper = new QueryWrapper<>();
        oneceWrapper.eq(SQLConf.MENU_LEVEL, SysConstant.LEVEL_ONE);
        oneceWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        oneceWrapper.orderByDesc(SQLConf.SORT);

        Page<Menu> page = new Page<>();
        page.setCurrent(menuVO.getCurrentPage());
        page.setSize(menuVO.getPageSize());

        IPage<Menu> pageList = menuService.page(page, oneceWrapper);
        List<Menu> list = pageList.getRecords();

        List ids = new ArrayList();
        list.forEach(item -> {
            if(StringUtils.isNotEmpty(item.getUid())){
                ids.add(item.getUid());
            }
        });

        //二级菜单
        QueryWrapper<Menu> secondWrapper = new QueryWrapper<>();
        secondWrapper.in(SQLConf.PARENT_UID, ids);
        secondWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        List<Menu> secondList = menuService.list(secondWrapper);

        List<String> secondIds = new ArrayList<>();
        secondList.forEach(item -> {
            if(StringUtils.isNotEmpty(item.getUid())){
                secondIds.add(item.getUid());
            }
        });
        // 三级菜单
        QueryWrapper<Menu> thirdWrapper = new QueryWrapper<>();
        thirdWrapper.in(SQLConf.PARENT_UID, secondIds);
        thirdWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        List<Menu> thirdList = menuService.list(thirdWrapper);

        //将三级菜单列表导入二级菜单中
        secondList.forEach(parentItem -> {
            List<Menu> tempList = new ArrayList<>();
            for(Menu childItem : thirdList){
                if (childItem.getParentUid().equals(parentItem.getUid())) {
                    tempList.add(childItem);
                }
            }
            tempList.sort((a,b) -> Integer.compare(b.getSort(),a.getSort()));
            parentItem.setChildMenuList(tempList);
        });

        // 将二级菜单列表导入一级菜单中
        for(Menu parentItem : list){
            List<Menu> tempList = new ArrayList<>();

            for (Menu item : secondList) {
                if (item.getParentUid().equals(parentItem.getUid())) {
                    tempList.add(item);
                }
            }
            tempList.sort((a,b) -> Integer.compare(b.getSort(),a.getSort()));
            parentItem.setChildMenuList(tempList);
        }

        return pageList;
    }

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
            menu.setIcon(menuVO.getIcon());
            menu.setIntroduce(menuVO.getIntroduce());
            menu.setUrl(menuVO.getUrl());

            int insert = menuMapper.insert(menu);
            if(insert > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.INSERT_SUCCESS, null);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.ENTITY_EXIST, null);
    }

    @Override
    public CommonResult getAllMenu() {
        // 一级菜单
        QueryWrapper<Menu> oneceWrapper = new QueryWrapper<>();
        oneceWrapper.eq(SQLConf.MENU_LEVEL, SysConstant.LEVEL_ONE);
        oneceWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        oneceWrapper.orderByDesc(SQLConf.SORT);


        List<Menu> list = menuService.list(oneceWrapper);

        List ids = new ArrayList();
        list.forEach(item -> {
            if(StringUtils.isNotEmpty(item.getUid())){
                ids.add(item.getUid());
            }
        });

        //二级菜单
        QueryWrapper<Menu> secondWrapper = new QueryWrapper<>();
        secondWrapper.in(SQLConf.PARENT_UID, ids);
        secondWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        List<Menu> secondList = menuService.list(secondWrapper);

        List<String> secondIds = new ArrayList<>();
        secondList.forEach(item -> {
            if(StringUtils.isNotEmpty(item.getUid())){
                secondIds.add(item.getUid());
            }
        });
        // 三级菜单
        QueryWrapper<Menu> thirdWrapper = new QueryWrapper<>();
        thirdWrapper.in(SQLConf.PARENT_UID, secondIds);
        thirdWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        List<Menu> thirdList = menuService.list(thirdWrapper);

        //将三级菜单列表导入二级菜单中
        secondList.forEach(parentItem -> {
            List<Menu> tempList = new ArrayList<>();
            for(Menu childItem : thirdList){
                if (childItem.getParentUid().equals(parentItem.getUid())) {
                    tempList.add(childItem);
                }
            }
            tempList.sort((a,b) -> Integer.compare(b.getSort(),a.getSort()));
            parentItem.setChildMenuList(tempList);
        });

        // 将二级菜单列表导入一级菜单中
        for(Menu parentItem : list){
            List<Menu> tempList = new ArrayList<>();

            for (Menu item : secondList) {
                if (item.getParentUid().equals(parentItem.getUid())) {
                    tempList.add(item);
                }
            }
            tempList.sort((a,b) -> Integer.compare(b.getSort(),a.getSort()));
            parentItem.setChildMenuList(tempList);
        }

        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, list);
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
            menu.setIcon(menuVO.getIcon());
            menu.setIntroduce(menuVO.getIntroduce());
            menu.setUrl(menuVO.getUrl());
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
