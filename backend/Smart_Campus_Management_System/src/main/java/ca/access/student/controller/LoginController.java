package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.student.domain.SysUser;
import ca.access.student.service.ISysUserService;
import ca.access.utils.HutoolJWTUtil;
import ca.access.utils.Md5Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: System background login front controller
 */
@RestController
@RequestMapping("login")
public class LoginController {
    private final ISysUserService sysUserService;
    public LoginController(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    /**
     * system login
     * @param sysUser
     * @param request
     * @return
     */
    @PostMapping
    public BaseResult login(@RequestBody SysUser sysUser, HttpServletRequest request){
        SysUser dbSysUser = sysUserService.login(sysUser);
        if(dbSysUser==null){
            return BaseResult.fail("Login failed, account does not exist");
        } else if (!dbSysUser.getPassword().equals(Md5Util.MD5(sysUser.getPassword()))) {
            return BaseResult.fail("Login failed, password is incorrect");
        } else if (dbSysUser.getStatus()==0) {
            return BaseResult.fail("Login failed, account is banned");
        } else {
            // generate token
            String token = HutoolJWTUtil.createToken(sysUser);
            request.getServletContext().setAttribute("token",token);
            // return user information
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("username",dbSysUser.getUsername());
            resultMap.put("realname",dbSysUser.getRealname());
            resultMap.put("token",token);
            resultMap.put("email",dbSysUser.getEmail());
            resultMap.put("gender",dbSysUser.getGender());
            resultMap.put("createTime",dbSysUser.getCreateTime());
            resultMap.put("userIcon",dbSysUser.getUserIcon());
            resultMap.put("role",dbSysUser.getSysRole());

            return BaseResult.success("Login successful",resultMap);
        }
    }

    /**
     * logout
     * @param request
     * @return
     */
    @GetMapping("logOut")
    public BaseResult logOut(HttpServletRequest request){
        request.getServletContext().removeAttribute("token");
        return BaseResult.success("Logout success");
    }
}
