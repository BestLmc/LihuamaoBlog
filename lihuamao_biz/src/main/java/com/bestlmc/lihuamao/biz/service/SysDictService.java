package com.bestlmc.lihuamao.biz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bestlmc.lihuamao.base.service.SuperService;
import com.bestlmc.lihuamao.biz.vo.SysDictVO;
import com.bestlmc.lihuamao.commons.been.SysDict;
import com.bestlmc.lihuamao.commons.http.CommonResult;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
public interface SysDictService extends SuperService<SysDict> {

    /**
     * 获取字典列表
     *
     * @param sysDictVO
     * @return
     */
    public IPage<SysDict> getPageList(SysDictVO sysDictVO);

    /**
     * 获取字典列表
     *
     * @param sysDictVO
     * @return
     */
    public CommonResult addSysDict(SysDictVO sysDictVO);

    /**
     * 获取字典列表
     *
     * @param sysDictVO
     * @return
     */
    public CommonResult updateSysDict(SysDictVO sysDictVO);

    /**
     * 获取字典列表
     *
     * @param sysDictVO
     * @return
     */
    public CommonResult deleteSysDict(SysDictVO sysDictVO);
}
