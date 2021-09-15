package com.bestlmc.lihuamao.admin.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.commons.been.Role;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.global.MessageConf;
import com.bestlmc.lihuamao.services.service.RoleService;
import com.bestlmc.lihuamao.services.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bestlmc
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色接口", tags = {"角色接口"})
public class RoleApi {

    @Resource
    RoleService roleService;

    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @GetMapping("/getPageList")
    public CommonResult getMenuList(HttpServletRequest request,
                                    @ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword,
                                    @ApiParam(name = "currentPage", value = "当前页数", required = false) @RequestParam(name = "currentPage", required = false, defaultValue = "1") Long currentPage,
                                    @ApiParam(name = "pageSize", value = "每页显示数目", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        Page<Role> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        int total = roleService.count(queryWrapper);
        page.setTotal(total);
        IPage<Role> pageList = roleService.page(page, queryWrapper);
        List<Role> list = pageList.getRecords();

        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, pageList);
    }

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @PostMapping("/add")
    public CommonResult addRole(@RequestBody RoleVO roleVO){

        return roleService.addRole(roleVO);
    }

    @ApiOperation(value = "修改角色信息", notes = "修改角色信息")
    @PostMapping("/update")
    public CommonResult updateRole(@RequestBody RoleVO roleVO){

        return roleService.updateRole(roleVO);
    }

    @ApiOperation(value = "查询角色信息", notes = "查询角色信息")
    @GetMapping("/get")
    public CommonResult getRoleById( @ApiParam(name = "uid", value = "角色id", required = false) @RequestParam(name = "uid", required = false) String uid){

        return roleService.getRoleById(uid);
    }

    @ApiOperation(value = "删除角色信息", notes = "删除角色信息")
    @GetMapping("/delete")
    public CommonResult deleteRoleById(@RequestBody RoleVO roleVO){

        return roleService.deleteRole(roleVO);
    }
}
