package com.bestlmc.lihuamao.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.biz.vo.SysDictItemVO;
import com.bestlmc.lihuamao.commons.been.SysDict;
import com.bestlmc.lihuamao.commons.been.SysDictItem;
import com.bestlmc.lihuamao.commons.http.CommonResult;

import java.util.List;

/**
 * <p>
 * 字典数据项表 服务类
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
public interface SysDictItemService extends SuperService<SysDictItem> {

    /**
     * 获取字典项列表
     *
     * @param sysDictItemVO
     * @return
     */
    public IPage<SysDictItem> getPageList(SysDictItemVO sysDictItemVO);

    /**
     * 获取字典项列表
     *
     * @param sysDictItemVO
     * @return
     */
    public List<SysDictItem> getList(SysDictItemVO sysDictItemVO);

    /**
     * 获取字典项列表
     *
     * @param sysDictItemVO
     * @return
     */
    public CommonResult add(SysDictItemVO sysDictItemVO);

    /**
     * 获取字典项列表
     *
     * @param sysDictItemVO
     * @return
     */
    public CommonResult update(SysDictItemVO sysDictItemVO);

    /**
     * 获取字典项列表
     *
     * @param sysDictItemVO
     * @return
     */
    public CommonResult delete(SysDictItemVO sysDictItemVO);
}
