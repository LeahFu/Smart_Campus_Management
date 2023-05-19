package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.student.domain.SysUser;
import ca.access.student.service.ISysUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        } else if (!dbSysUser.getPassword().equals(Md5Util.Md5(sysUser.getPassword()))) {
            return BaseResult.fail("Login failed, password is incorrect");
        } else if (dbSysUser.getStatus()==0) {
            return BaseResult.fail("Login failed, account is banned");
        } else {
            // generate token
            String token = HutoolJWTUtil.createToken(sysUser);
            request.getServletContext().setAttribute("token",token);
            return BaseResult.success("Login successful",token);
        }
    }
}
