package com.bestlmc.lihuamao.commons.been;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bestlmc.lihuamao.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
@Data
@TableName("t_sys_dict")
public class SysDict extends BaseEntity {


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
     * 创建人UID
     */
    private String createByUid;

    /**
     * 最后更新人UID
     */
    private String updateByUid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序字段
     */
    private Integer sort;


}
