package com.bestlmc.lihuamao.services.vo;

import com.bestlmc.lihuamao.base.vo.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author bestlmc
 * @Date 2021/6/24 14:50
 */
@Data
public class AdminVO extends BaseVO<AdminVO> {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 确认密码
     */
    private String comfirmPassword;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别(1:男2:女)
     */
    private String gender;

    /**
     * 个人头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生年月日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    /**
     * 手机
     */
    private String mobile;


    /**
     * 自我简介最多150字
     */
    private String introduce;

    /**
     * 角色Uid
     */
    private String roleUid;

}
