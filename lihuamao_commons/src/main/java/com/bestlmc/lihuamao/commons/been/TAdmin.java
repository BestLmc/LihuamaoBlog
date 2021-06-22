package com.bestlmc.lihuamao.commons.been;

import com.bestlmc.lihuamao.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TAdmin extends BaseEntity {


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
    private LocalDate birthday;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱验证码
     */
    private String validCode;

    /**
     * 自我简介最多150字
     */
    private String introduce;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 拥有的角色uid
     */
    private String roleUid;


}