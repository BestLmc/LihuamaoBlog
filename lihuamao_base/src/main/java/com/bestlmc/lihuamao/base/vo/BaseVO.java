package com.bestlmc.lihuamao.base.vo;

import lombok.Data;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author bestlmc
 * @Date 2021/6/24 14:49
 */
@Data
public class BaseVO<T> extends PageInfo<T> {

    /**
     * 唯一UID
     */
    private String uid;

    private Integer status;
}
