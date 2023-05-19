package ca.access.student.service;

import ca.access.student.domain.SysUser;

/**
 * @author: Lei Fu
 * @date: 2023/05/18
 * @description: System user business interface
 */
public interface ISysUserService {
    SysUser login(SysUser sysUser);
}
