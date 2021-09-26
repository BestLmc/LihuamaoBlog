package com.bestlmc.lihuamao.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.base.ServiceImpl.SuperServiceImpl;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.base.enums.SysStatus;
import com.bestlmc.lihuamao.commons.been.SysDict;
import com.bestlmc.lihuamao.commons.been.SysDictItem;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.biz.global.MessageConf;
import com.bestlmc.lihuamao.biz.global.SQLConf;
import com.bestlmc.lihuamao.biz.mapper.SysDictItemMapper;
import com.bestlmc.lihuamao.biz.service.SysDictItemService;
import com.bestlmc.lihuamao.biz.service.SysDictService;
import com.bestlmc.lihuamao.biz.vo.SysDictItemVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典数据项表 服务实现类
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
@Service
public class SysDictItemServiceImpl extends SuperServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Resource
    SysDictService sysDictService;

    @Autowired
    SysDictItemService sysDictItemService;

    @Resource
    SysDictItemMapper sysDictItemMapper;

    @Override
    public IPage<SysDictItem> getPageList(SysDictItemVO sysDictItemVO) {
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();

        // 父字典UID
        if (StringUtils.isNotEmpty(sysDictItemVO.getDictUid()) && !StringUtils.isEmpty(sysDictItemVO.getDictUid().trim())) {
            queryWrapper.like(SQLConf.DICT_UID, sysDictItemVO.getDictUid().trim());
        }

        // 字典名称
        if (StringUtils.isNotEmpty(sysDictItemVO.getItemLabel()) && !StringUtils.isEmpty(sysDictItemVO.getItemLabel().trim())) {
            queryWrapper.like(SQLConf.DICT_ITEM_LABEL, sysDictItemVO.getItemLabel().trim());
        }

        // 字典代码
        if (StringUtils.isNotEmpty(sysDictItemVO.getItemValue()) && !StringUtils.isEmpty(sysDictItemVO.getItemValue().trim())) {
            queryWrapper.like(SQLConf.DICT_ITEM_VALUE, sysDictItemVO.getItemValue().trim());
        }

        Page<SysDictItem> page = new Page<>();
        page.setCurrent(sysDictItemVO.getCurrentPage());
        page.setSize(sysDictItemVO.getPageSize());
        queryWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        queryWrapper.orderByDesc(SQLConf.SORT, SQLConf.CREATE_TIME);
        IPage<SysDictItem> pageList = sysDictItemService.page(page, queryWrapper);
        return pageList;
    }

    @Override
    public List<SysDictItem> getList(SysDictItemVO sysDictItemVO) {
        // 查询字典
        QueryWrapper<SysDict> dictWrapper = new QueryWrapper<>();
        // 父字典代码
        if (StringUtils.isNotEmpty(sysDictItemVO.getDictCode()) && !StringUtils.isEmpty(sysDictItemVO.getDictCode().trim())) {
            dictWrapper.like(SQLConf.DICT_CODE, sysDictItemVO.getDictCode().trim());
        }
        dictWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        SysDict dict = sysDictService.getOne(dictWrapper);
        sysDictItemVO.setDictUid(dict.getUid());

        // 查询字典项
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();

        // 父菜单UID
        if (StringUtils.isNotEmpty(sysDictItemVO.getDictUid()) && !StringUtils.isEmpty(sysDictItemVO.getDictUid().trim())) {
            queryWrapper.like(SQLConf.DICT_UID, sysDictItemVO.getDictUid().trim());
        }

        // 字典名称
        if (StringUtils.isNotEmpty(sysDictItemVO.getItemLabel()) && !StringUtils.isEmpty(sysDictItemVO.getItemLabel().trim())) {
            queryWrapper.like(SQLConf.DICT_ITEM_LABEL, sysDictItemVO.getItemLabel().trim());
        }

        // 字典键值
        if (StringUtils.isNotEmpty(sysDictItemVO.getItemValue()) && !StringUtils.isEmpty(sysDictItemVO.getItemValue().trim())) {
            queryWrapper.like(SQLConf.DICT_ITEM_VALUE, sysDictItemVO.getItemValue().trim());
        }

        queryWrapper.eq(SQLConf.STATUS, SysStatus.ENABLE);
        queryWrapper.orderByDesc(SQLConf.SORT, SQLConf.CREATE_TIME);
        List<SysDictItem> dictItemList = sysDictItemService.list(queryWrapper);
        return dictItemList;
    }

    @Override
    public CommonResult add(SysDictItemVO sysDictItemVO) {
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.DICT_ITEM_LABEL,sysDictItemVO.getItemLabel());
        queryWrapper.eq(SQLConf.STATUS,sysDictItemVO.getStatus());
        queryWrapper.last(" LIMIT 1 ");

        SysDictItem temp = sysDictItemService.getOne(queryWrapper);
        if(temp != null){
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.ENTITY_EXIST);
        }

        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem.setDictUid(sysDictItemVO.getDictUid());
        sysDictItem.setItemLabel(sysDictItemVO.getItemLabel());
        sysDictItem.setItemValue(sysDictItemVO.getItemValue());
        sysDictItem.setRemark(sysDictItemVO.getRemark());
        sysDictItem.setSort(sysDictItemVO.getSort());

        boolean insert = sysDictItemService.save(sysDictItem);

        if(insert){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.INSERT_SUCCESS);
        }

        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.INSERT_FAIL);
    }

    @Override
    public CommonResult update(SysDictItemVO sysDictItemVO) {
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.DICT_ITEM_LABEL,sysDictItemVO.getItemLabel());
        queryWrapper.eq(SQLConf.STATUS,sysDictItemVO.getStatus());
        queryWrapper.last(" LIMIT 1 ");

        SysDictItem temp = sysDictItemService.getOne(queryWrapper);
        if(temp != null){
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.ENTITY_EXIST);
        }

        SysDictItem sysDictItem = new SysDictItem();
        sysDictItem.setUid(sysDictItemVO.getUid());
        sysDictItem.setDictUid(sysDictItemVO.getDictUid());
        sysDictItem.setItemLabel(sysDictItemVO.getItemLabel());
        sysDictItem.setItemValue(sysDictItemVO.getItemValue());
        sysDictItem.setRemark(sysDictItemVO.getRemark());
        sysDictItem.setSort(sysDictItemVO.getSort());

        boolean insert = sysDictItemService.updateById(sysDictItem);

        if(insert){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.UPDATE_SUCCESS);
        }

        return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.UPDATE_FAIL);
    }

    @Override
    public CommonResult delete(SysDictItemVO sysDictItemVO) {
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SQLConf.UID,sysDictItemVO.getUid());
        queryWrapper.eq(SQLConf.STATUS,sysDictItemVO.getStatus());
        queryWrapper.last(" LIMIT 1 ");

        SysDictItem temp = sysDictItemService.getOne(queryWrapper);
        if(temp == null){
            return new CommonResult(APIRquest.REQUEST_FAILED, MessageConf.ENTITY_NOT_EXIST);
        }
        boolean b = sysDictItemService.removeById(sysDictItemVO.getUid());
        if(b){
            return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.DELETE_SUCCESS);
        }
        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.DELETE_FAIL);
    }


}
