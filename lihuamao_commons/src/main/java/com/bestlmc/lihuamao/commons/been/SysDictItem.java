package com.bestlmc.lihuamao.commons.been;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bestlmc.lihuamao.base.entity.BaseEntity;
import lombok.Data;

/**
 * <p>
 * 字典数据项表
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
@Data
@TableName("t_sys_dict_item")
public class SysDictItem extends BaseEntity {


    /**
     * 自增键oid
     */
    private Integer oid;

    /**
     * t_sys_dict字典UID
     */
    private String dictUid;

    /**
     * 字典标签
     */
    private String itemLabel;

    /**
     * 字典键值
     */
    private String itemValue;

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
