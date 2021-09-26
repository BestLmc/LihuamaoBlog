package com.bestlmc.lihuamao.biz.vo;

import com.bestlmc.lihuamao.base.vo.BaseVO;
import lombok.Data;

/**
 * @Description: 字典实体类
 * @Author bestlmc
 * @Date 2021/6/24 14:50
 */
@Data
public class SysDictVO extends BaseVO<SysDictVO> {
    /**
     * 自增键oid
     */
    private Integer oid;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序字段
     */
    private Integer sort;

}
