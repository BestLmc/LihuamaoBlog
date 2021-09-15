package com.bestlmc.lihuamao.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.base.enums.HttpCon;
import com.bestlmc.lihuamao.base.enums.SysStatus;
import com.bestlmc.lihuamao.commons.been.Admin;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.global.MessageConf;
import com.bestlmc.lihuamao.services.global.SQLConf;
import com.bestlmc.lihuamao.services.mapper.AdminMapper;
import com.bestlmc.lihuamao.services.service.AdminService;
import com.bestlmc.lihuamao.services.vo.AdminVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author bestlmc
 * @Date 2021/6/22 13:19
 */
@Service
public class AdminServiceImpl extends SuperServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    AdminService adminService;

    @Resource
    AdminMapper adminMapper;

    @Override
    public CommonResult addAdmin(AdminVO adminVO) {

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.USER_NAME, adminVO.getUserName());
        Admin checkOne = adminMapper.selectOne(queryWrapper);

        if(checkOne == null){
            Admin admin = new Admin();
//        tAdmin.setUid(UUID.randomUUID().toString().replace("-",""));
            admin.setUsername(adminVO.getUserName());
            admin.setNickName(adminVO.getNickName());
            admin.setPassword(adminVO.getPassWord());
            admin.setGender(adminVO.getGender());
            admin.setEmail(adminVO.getEmail());
            admin.setMobile(adminVO.getMobile());
            admin.setIntroduce(adminVO.getIntroduce());
            admin.setBirthday(adminVO.getBirthday());

            System.out.println(admin.getUid());
            int insert = adminMapper.insert(admin);
            if(insert > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.INSERT_SUCCESS, insert);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL, null);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL, null);
    }

    @Override
    public CommonResult updateAdmin(AdminVO adminVO) {

        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.UID, adminVO.getUid());
        Admin checkOne = adminMapper.selectOne(queryWrapper);

        if(checkOne != null){
            Admin admin = new Admin();
//        tAdmin.setUid(UUID.randomUUID().toString().replace("-",""));
            admin.setUid(adminVO.getUid());
            admin.setStatus(adminVO.getStatus());
            admin.setUsername(adminVO.getUserName());
            admin.setNickName(adminVO.getNickName());
            admin.setPassword(adminVO.getPassWord());
            admin.setGender(adminVO.getGender());
            admin.setEmail(adminVO.getEmail());
            admin.setMobile(adminVO.getMobile());
            admin.setIntroduce(adminVO.getIntroduce());
            admin.setBirthday(adminVO.getBirthday());

            System.out.println(admin.getUid());
            int update = adminMapper.updateById(admin);
            if(update > 0){
                return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, update);
            }
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.OPERATION_FAIL, update);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.OPERATION_FAIL, null);
    }

    @Override
    public CommonResult getAdminById(String uid) {

        Admin admin = adminMapper.selectById(uid);
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(admin.getUid());
        adminVO.setUserName(admin.getUsername());
        adminVO.setNickName(admin.getNickName());
        adminVO.setPassWord(admin.getPassword());
        adminVO.setComfirmPassword(admin.getPassword());
        adminVO.setGender(admin.getGender());
        adminVO.setBirthday(admin.getBirthday());
        adminVO.setEmail(admin.getEmail());
        adminVO.setMobile(admin.getMobile());
        adminVO.setIntroduce(admin.getIntroduce());

        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, adminVO);
    }

    @Override
    public CommonResult deleteAdmin(AdminVO adminVO) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.UID, adminVO.getUid());
        Admin checkOne = adminMapper.selectOne(queryWrapper);

        if(checkOne == null){
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.QUERY_NOT_FONUD, null);
        }
        checkOne.setStatus(SysStatus.DISABLED);
        int update = adminMapper.updateById(checkOne);
        if(update > 0){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.DELETE_SUCCESS, update);
        }
        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.DELETE_FAIL, null);
    }
}
