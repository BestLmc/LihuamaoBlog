package com.bestlmc.lihuamao.biz.vo;

import com.bestlmc.lihuamao.base.vo.BaseVO;
import lombok.Data;

/**
 * @Description: 字典实体类
 * @Author bestlmc
 * @Date 2021/6/24 14:50
 */
@Data
public class SysDictItemVO extends BaseVO<SysDictItemVO> {
    /**
     * 自增键oid
     */
    private Integer oid;

    /**
     * t_sys_dict字典UID
     */
    private String dictUid;

    /**
     * t_sys_dict字典代码
     */
    private String dictCode;

    /**
     * 字典标签
     */
    private String itemLabel;

    /**
     * 字典键值
     */
    private String itemValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序字段
     */
    private Integer sort;

}
