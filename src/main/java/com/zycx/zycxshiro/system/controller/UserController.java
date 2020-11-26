package com.zycx.zycxshiro.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import com.zycx.zycxshiro.common.annotation.Log;
import com.zycx.zycxshiro.common.controller.BaseController;
import com.zycx.zycxshiro.common.domain.FebsResponse;
import com.zycx.zycxshiro.common.domain.QueryRequest;
import com.zycx.zycxshiro.common.exception.FebsException;
import com.zycx.zycxshiro.common.util.AesEncryptUtil;
import com.zycx.zycxshiro.common.util.MD5Util;
import com.zycx.zycxshiro.system.domain.User;
import com.zycx.zycxshiro.system.domain.UserConfig;
import com.zycx.zycxshiro.system.service.UserConfigService;
import com.zycx.zycxshiro.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    private String message;

    @Autowired
    private UserService userService;
    @Autowired
    private UserConfigService userConfigService;
//    @Autowired
//    private RoleService roleService;

    @GetMapping("check/{username}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username) {
        return this.userService.findByName(username) == null;
    }

    @GetMapping("/{username}")
    public User detail(@NotBlank(message = "{required}") @PathVariable String username) {
        //UserServiceImpl中findByName方法改为调用baseMapper.findDetail(username)
        return this.userService.findByName(username);
    }

    @GetMapping
    @RequiresPermissions("user:view")
    public Map<String, Object> userList(QueryRequest queryRequest, User user) {
        return getDataTable(userService.findUserDetail(user, queryRequest));
    }

    @Log("新增用户")
    @PostMapping
    @RequiresPermissions("user:add")
    public FebsResponse addUser(@RequestBody @Valid User user) throws FebsException {
        try {
            this.userService.createUser(user);
            return new FebsResponse().code("200").message("新增用户成功").status("success");
        } catch (Exception e) {
            message = "新增用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改用户")
    @PutMapping
    @RequiresPermissions("user:update")
    public FebsResponse updateUser(@RequestBody @Valid User user) throws FebsException {
        try {
            this.userService.updateUser(user);
            return new FebsResponse().code("200").message("修改用户成功").status("success");
        } catch (Exception e) {
            message = "修改用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除用户")
    @DeleteMapping("/{userIds}")
    @RequiresPermissions("user:delete")
    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
            return new FebsResponse().code("200").message("删除用户成功").status("success");
        } catch (Exception e) {
            message = "删除用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("profile")
    public FebsResponse updateProfile(@RequestBody @Valid User user) throws FebsException {
        try {
            this.userService.updateProfile(user);
            return new FebsResponse().code("200").message("修改个人信息成功").status("success");
        } catch (Exception e) {
            message = "修改个人信息失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("avatar")
    public FebsResponse updateAvatar(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String avatar) throws FebsException {
        try {
            this.userService.updateAvatar(username, avatar);
            return new FebsResponse().code("200").message("修改头像成功").status("success");
        } catch (Exception e) {
            message = "修改头像失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("userconfig")
    public FebsResponse updateUserConfig(@RequestBody @Valid UserConfig userConfig) throws FebsException {
        try {
            this.userConfigService.update(userConfig);
            return new FebsResponse().code("200").message("修改个性化配置成功").status("success");
        } catch (Exception e) {
            message = "修改个性化配置失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @GetMapping("password/check")
    public boolean checkPassword(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) {
        String encryptPassword = MD5Util.encrypt(username, AesEncryptUtil.desEncrypt(password));
        User user = userService.findByName(username);
        if (user != null)
            return StringUtils.equals(user.getPassword(), encryptPassword);
        else
            return false;
    }

    @PutMapping("password")
    public FebsResponse updatePassword(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password) throws FebsException {
        try {
            userService.updatePassword(username, AesEncryptUtil.desEncrypt(password));
            return new FebsResponse().code("200").message("修改密码成功").status("success");
        } catch (Exception e) {
            message = "修改密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PutMapping("password/reset")
    @RequiresPermissions("user:reset")
    public FebsResponse resetPassword(@NotBlank(message = "{required}") String usernames) throws FebsException {
        try {
            String[] usernameArr = usernames.split(StringPool.COMMA);
            this.userService.resetPassword(usernameArr);
            return new FebsResponse().code("200").message("重置用户密码成功").status("success");
        } catch (Exception e) {
            message = "重置用户密码失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("user:export")
    public void export(QueryRequest queryRequest,@RequestBody User user, HttpServletResponse response) throws FebsException {
        try {
            List<User> users = this.userService.findUserDetail(user, queryRequest).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
