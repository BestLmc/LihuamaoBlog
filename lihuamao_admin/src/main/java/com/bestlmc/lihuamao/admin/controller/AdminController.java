package com.bestlmc.lihuamao.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.commons.been.TAdmin;
import com.bestlmc.lihuamao.commons.been.TMenu;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.service.AdminService;
import com.bestlmc.lihuamao.services.vo.AdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "获取管理员列表", notes = "获取管理员列表")
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



    @ApiOperation(value = "新增管理员", notes = "新增管理员")
    @PostMapping("/add")
    public CommonResult addAdmin(@RequestBody AdminVO adminVO){
        String result = adminService.addAdmin(adminVO);
        return new CommonResult(200, "success", result);
    }

    @ApiOperation(value = "修改管理员信息", notes = "修改管理员信息")
    @PostMapping("/update")
    public CommonResult updateAdmin(@RequestBody AdminVO adminVO){
        String result = adminService.addAdmin(adminVO);
        return new CommonResult(200, "success", result);
    }

    @ApiOperation(value = "查询管理员信息", notes = "查询管理员信息")
    @GetMapping("/get")
    public CommonResult getAdmin( @ApiParam(name = "uid", value = "管理员id", required = false) @RequestParam(name = "uid", required = false) String uid){
        AdminVO adminVO = adminService.getAdminById(uid);
        return new CommonResult(200, "success", adminVO);
    }

    @ApiOperation(value = "删除管理员信息", notes = "删除管理员信息")
    @GetMapping("/delete")
    public CommonResult deleteAdmin(@ApiParam(name = "uid", value = "管理员id", required = false) @RequestParam(name = "uid", required = false) String uid){
        boolean removeById = adminService.removeById(uid);
        return new CommonResult(200, "success", removeById);
    }

}
