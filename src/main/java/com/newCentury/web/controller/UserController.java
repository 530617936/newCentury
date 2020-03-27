package com.newCentury.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newCentury.web.request.IdKeyRequest;
import com.newCentury.web.request.UserRequest;
import com.newCentury.web.service.IUserService;
import com.newCentury.web.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author: 53061
 * @Date:2020/3/24
 */
@RestController
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UserController {
    @Autowired
    IUserService userService;
    @GetMapping("/list")
    public Response listByPage(@RequestParam(value = "pageSize",required = false,defaultValue = "10")Integer pageSize,
                               @RequestParam(value = "pageIndex",required = false,defaultValue = "1")Integer pageIndex){
        Page page = userService.listByPage(pageSize, pageIndex);
        return Response.success(page);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody UserRequest userRequest){
        return userService.save(userRequest) ? Response.success():Response.error("操作失败");
    }

    @PostMapping(value = "/edit")
    public Response edit(@RequestBody UserRequest userRequest){
        return userService.updateById(userRequest) ? Response.success() : Response.error("操作失败");
    }

    @PostMapping(value = "/delete")
    public Response delete(@RequestBody IdKeyRequest idKeyRequest){
        return userService.removeById(idKeyRequest.getId()) ? Response.success() : Response.error("操作失败");
    }
}
