package com.bestlmc.lihuamao.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.been.Role;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.biz.global.MessageConf;
import com.bestlmc.lihuamao.biz.global.SQLConf;
import com.bestlmc.lihuamao.biz.mapper.RoleMapper;
import com.bestlmc.lihuamao.biz.service.RoleService;
import com.bestlmc.lihuamao.biz.vo.RoleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-22
 */
@Service
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    RoleMapper roleMapper;

    @Override
    public CommonResult addRole(RoleVO roleVO) {

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.ROLE_NAME, roleVO.getRoleName());
        Role checkOne = roleMapper.selectOne(queryWrapper);

        if(checkOne == null){
            Role role = new Role();
            role.setIntroduce(roleVO.getIntroduce());
            role.setRoleName(roleVO.getRoleName());
            role.setMenuUids(roleVO.getMenuUids());
            int insert = roleMapper.insert(role);
            if(insert > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.INSERT_SUCCESS, null);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.QUERY_NOT_FONUD, null);
    }

    @Override
    public CommonResult updateRole(RoleVO roleVO) {

        Role role = new Role();
        role.setUid(roleVO.getUid());
        role.setIntroduce(roleVO.getIntroduce());
        role.setRoleName(roleVO.getRoleName());
        role.setMenuUids(roleVO.getMenuUids());
        int update = roleMapper.updateById(role);
        if(update > 0){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.OPERATION_FAIL, null);
    }

    @Override
    public CommonResult getRoleById(String uid) {
        Role role = roleMapper.selectById(uid);
        RoleVO roleVO = new RoleVO();
        roleVO.setUid(role.getUid());
        roleVO.setIntroduce(role.getIntroduce());
        roleVO.setRoleName(role.getRoleName());
        roleVO.setMenuUids(role.getMenuUids());
        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, roleVO);
    }

    @Override
    public CommonResult deleteRole(RoleVO roleVO) {
        int delete = roleMapper.deleteById(roleVO.getUid());
        if(delete > 0){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.DELETE_SUCCESS, delete);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.DELETE_FAIL, null);
    }
}
