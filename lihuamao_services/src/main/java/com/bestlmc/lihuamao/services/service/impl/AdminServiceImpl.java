package com.bestlmc.lihuamao.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.HttpCon;
import com.bestlmc.lihuamao.commons.been.TAdmin;
import com.bestlmc.lihuamao.services.mapper.AdminMapper;
import com.bestlmc.lihuamao.services.service.AdminService;
import com.bestlmc.lihuamao.services.vo.AdminVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author bestlmc
 * @Date 2021/6/22 13:19
 */
@Service
public class AdminServiceImpl extends SuperServiceImpl<AdminMapper, TAdmin> implements AdminService {

    @Resource
    AdminService adminService;

    @Resource
    AdminMapper adminMapper;

    @Override
    public String addAdmin(AdminVO adminVO) {
        String userName = adminVO.getUserName();


        TAdmin tAdmin = new TAdmin();
//        tAdmin.setUid(UUID.randomUUID().toString().replace("-",""));
        tAdmin.setUsername(adminVO.getUserName());
        tAdmin.setNickName(adminVO.getNickName());
        tAdmin.setPassword(adminVO.getPassWord());
        tAdmin.setGender(adminVO.getGender());
        tAdmin.setEmail(adminVO.getEmail());
        tAdmin.setMobile(adminVO.getMobile());
        tAdmin.setIntroduce(adminVO.getIntroduce());
        tAdmin.setBirthday(adminVO.getBirthday());

        QueryWrapper<TAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        TAdmin checkOne = adminMapper.selectOne(queryWrapper);

        if(checkOne == null){
            System.out.println(tAdmin.getUid());
            adminMapper.insert(tAdmin);
            return HttpCon.success;
        }
        return HttpCon.fail;
    }

    @Override
    public AdminVO getAdminById(String uid) {

        TAdmin tAdmin = adminMapper.selectById(uid);
        AdminVO adminVO = new AdminVO();
        adminVO.setUid(tAdmin.getUid());
        adminVO.setUserName(tAdmin.getUsername());
        adminVO.setNickName(tAdmin.getNickName());
        adminVO.setPassWord(tAdmin.getPassword());
        adminVO.setComfirmPassword(tAdmin.getPassword());
        adminVO.setGender(tAdmin.getGender());
        adminVO.setBirthday(tAdmin.getBirthday());
        adminVO.setEmail(tAdmin.getEmail());
        adminVO.setMobile(tAdmin.getMobile());
        adminVO.setIntroduce(tAdmin.getIntroduce());

        return adminVO;
    }
}
