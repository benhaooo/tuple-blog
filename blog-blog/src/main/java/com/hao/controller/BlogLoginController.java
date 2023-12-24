package com.hao.controller;

import com.hao.domain.ResponseResult;
import com.hao.domain.dto.PasswordDTO;
import com.hao.domain.entity.LoginUser;
import com.hao.domain.entity.Menu;
import com.hao.domain.entity.User;
import com.hao.domain.vo.BlogUserRegisterVo;
import com.hao.domain.vo.RoutersVo;
import com.hao.domain.vo.UserInfoAdminVo;
import com.hao.domain.vo.UserInfoVo;
import com.hao.service.BlogLoginService;
import com.hao.service.MenuService;
import com.hao.service.RoleService;
import com.hao.utils.BeanCopyUtils;
import com.hao.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) {
        return blogLoginService.login(user);
    }

    @PostMapping("/admin/login")
    public ResponseResult adminLogin(@RequestBody User user) {
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return blogLoginService.logout();
    }


    @PostMapping("/admin/logout")
    public ResponseResult adminLogout() {
        return blogLoginService.logout();
    }


    @PostMapping("/register")
    public ResponseResult register(@RequestBody BlogUserRegisterVo blogUserRegisterVo) {
        return blogLoginService.register(blogUserRegisterVo);
    }

    @PostMapping("/userInfo")
    public ResponseResult<UserInfoAdminVo> userInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoAdminVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoAdminVo.class);
        //userName与username的爱恨情仇
        userInfoVo.setUsername(user.getUserName());
        //根据用户id查询角色权限
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        userInfoVo.setPermissions(roleKeyList);

        return ResponseResult.okResult(userInfoVo);
    }


    @PostMapping("/changePwd")
    public ResponseResult changePwd(@RequestBody PasswordDTO passwordDTO){
        return blogLoginService.changePwd(passwordDTO);
    }


}
