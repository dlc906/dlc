package com.dlc.dlc.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dlc.dlc.system.dto.Pagedto;
import com.dlc.dlc.system.entity.Systems;
import com.dlc.dlc.system.service.SystemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dlc.dlc.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dlc
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/system/systems")
@CrossOrigin
@Api
public class SystemsController {
    @Autowired
    private SystemsService systemsService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/user")
    public Result user(Systems systems){
        Page<Systems> page = new Page<>(systems.getCorrect(), systems.getSize());
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper();
        wrapper.setEntity(systems);
        IPage<Systems> list=systemsService.page(page,wrapper);
        return Result.ok(list.getRecords());
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户信息")
    public Result delete(@PathVariable Integer id){
        systemsService.removeById(id);
        return Result.ok("");
    }

    @PostMapping ("/adduser")
    public Result addUser(Systems systems){
        systemsService.save(systems);
        return Result.ok("");
    }
}

