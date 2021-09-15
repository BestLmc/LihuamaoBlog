package com.bestlmc.lihuamao.admin.api;


import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.base.enums.HttpCon;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.global.MessageConf;
import com.bestlmc.lihuamao.services.service.SysDictService;
import com.bestlmc.lihuamao.services.vo.SysDictVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author bestlmc
 * @since 2021-08-24
 */
@RestController
@RequestMapping("/sysdict")
@Api(value = "字典接口", tags = {"字典接口"})
public class SysDictApi {

    @Resource
    SysDictService sysDictService;

    @ApiOperation(value = "获取字典列表", notes = "获取字典列表")
    @PostMapping("/getPageList")
    public CommonResult getList(@RequestBody SysDictVO sysDictVO){


        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, sysDictService.getPageList(sysDictVO));
    }

    @ApiOperation(value = "新增字典", notes = "新增字典")
    @PostMapping("/add")
    public CommonResult addSysDict(@RequestBody SysDictVO sysDictVO){


        return sysDictService.addSysDict(sysDictVO);
    }

    @ApiOperation(value = "修改字典", notes = "修改字典")
    @PostMapping("/update")
    public CommonResult updateSysDict(@RequestBody SysDictVO sysDictVO){

        return sysDictService.updateSysDict(sysDictVO);
    }

    @ApiOperation(value = "删除字典", notes = "删除字典")
    @PostMapping("/delete")
    public CommonResult deleteSysDict(@RequestBody SysDictVO sysDictVO){

        return sysDictService.deleteSysDict(sysDictVO);
    }

}
