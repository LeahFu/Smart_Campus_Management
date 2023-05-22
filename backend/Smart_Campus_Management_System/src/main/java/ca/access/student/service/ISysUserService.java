package ca.access.student.service;

import ca.access.student.domain.SysUser;
import ca.access.student.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/05/18
 * @description: System user business interface
 */
public interface ISysUserService {
    SysUser login(SysUser sysUser);

    /**
     * Get user list data
     * @param pageable
     * @return
     */
    Object getList(UserQueryCriteria queryCriteria,Pageable pageable);

    /**
     * Add user information
     * @param sysUser
     * @return
     */
    boolean addUser(SysUser sysUser);
}
