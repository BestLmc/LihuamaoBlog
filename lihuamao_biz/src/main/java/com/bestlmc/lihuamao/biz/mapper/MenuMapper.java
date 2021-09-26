package com.bestlmc.lihuamao.biz.mapper;

import com.bestlmc.lihuamao.base.mapper.SuperMapper;
import com.bestlmc.lihuamao.commons.been.Menu;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2021-06-20
 */
public interface MenuMapper extends SuperMapper<Menu> {

    public Menu getMenuById(@Param("uid") String uid);
}
