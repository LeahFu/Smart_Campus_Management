package ca.access.student.controller;

import ca.access.student.service.ISysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: System User Front Controller
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final ISysUserService sysUserService;
    public UserController(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }
}
