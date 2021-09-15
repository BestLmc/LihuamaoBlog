package com.bestlmc.lihuamao.admin.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bestlmc.lihuamao.base.enums.APIRquest;
import com.bestlmc.lihuamao.commons.been.Menu;
import com.bestlmc.lihuamao.commons.http.CommonResult;
import com.bestlmc.lihuamao.services.global.MessageConf;
import com.bestlmc.lihuamao.services.service.MenuService;
import com.bestlmc.lihuamao.services.vo.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: t_menu表
 * @Author bestlmc
 * @Date 2021/6/20 21:49
 */
@RestController
@RequestMapping("/menu")
@Api(value = "菜单接口", tags = {"菜单接口"})
public class MenuApi {

    @Resource
    private MenuService iTMenuService;

    @ApiOperation(value = "获取菜单列表", notes = "获取菜单列表")
    @GetMapping("/getPageList")
    public CommonResult getMenuList(HttpServletRequest request,
                                    @ApiParam(name = "keyword", value = "关键字", required = false) @RequestParam(name = "keyword", required = false) String keyword,
                                    @ApiParam(name = "currentPage", value = "当前页数", required = false) @RequestParam(name = "currentPage", required = false, defaultValue = "1") Long currentPage,
                                    @ApiParam(name = "pageSize", value = "每页显示数目", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "10") Long pageSize){
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        Page<Menu> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        int total = iTMenuService.count(queryWrapper);
        page.setTotal(total);
        IPage<Menu> pageList = iTMenuService.page(page, queryWrapper);
        List<Menu> list = pageList.getRecords();

        return new CommonResult(APIRquest.REQUEST_SUCCESS, MessageConf.OPERATION_SUCCESS, pageList);
    }

    @ApiOperation(value = "新增菜单", notes = "新增菜单")
    @PostMapping("/add")
    public CommonResult addMenu(@RequestBody MenuVO menuVO){

        return iTMenuService.addMenu(menuVO);
    }

    @ApiOperation(value = "修改菜单信息", notes = "修改菜单信息")
    @PostMapping("/update")
    public CommonResult updateMenu(@RequestBody MenuVO menuVO){

        return iTMenuService.updateMenu(menuVO);
    }

    @ApiOperation(value = "查询菜单信息", notes = "查询菜单信息")
    @GetMapping("/get")
    public CommonResult getMenu( @ApiParam(name = "uid", value = "菜单id", required = false) @RequestParam(name = "uid", required = false) String uid){

        return iTMenuService.getMenuById(uid);
    }

    @ApiOperation(value = "删除菜单信息", notes = "删除菜单信息")
    @GetMapping("/delete")
    public CommonResult deleteMenu(@RequestBody MenuVO menuVO){

        return iTMenuService.deleteMenu(menuVO);
    }

}
