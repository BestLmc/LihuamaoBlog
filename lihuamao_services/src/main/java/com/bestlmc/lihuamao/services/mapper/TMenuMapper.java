package com.bestlmc.lihuamao.services.mapper;

import com.bestlmc.lihuamao.base.mapper.SuperMapper;
import com.bestlmc.lihuamao.commons.been.TMenu;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
public interface TMenuMapper extends SuperMapper<TMenu> {

    public TMenu getMenuById(@Param("uid") String uid);
}
