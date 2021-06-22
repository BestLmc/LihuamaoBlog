package com.bestlmc.lihuamao.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.commons.been.TAdmin;
import com.bestlmc.lihuamao.commons.been.TMenu;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: t_admin
 * @Author bestlmc
 * @Date 2021/6/22 13:00
 */
@RestController
@RequestMapping("/admin")
@Api(value = "管理员接口", tags = {"管理员接口"})
public class AdminController {

    @Resource
    AdminService adminService;

    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    @GetMapping("/getList")
    public CommonResult getMenuList(HttpServletRequest request,
                                    @ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword,
                                    @ApiParam(name = "currentPage", value = "当前页数", required = false) @RequestParam(name = "currentPage", required = false, defaultValue = "1") Long currentPage,
                                    @ApiParam(name = "pageSize", value = "每页显示数目", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize){
        QueryWrapper<TAdmin> queryWrapper = new QueryWrapper<>();
        Page<TAdmin> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        IPage<TAdmin> pageList = adminService.page(page, queryWrapper);
        List<TAdmin> list = pageList.getRecords();

        return new CommonResult(200, "success", list);
    }



}
