package ca.access.student.service;

import ca.access.student.domain.SysRole;
import ca.access.student.service.dto.RoleQueryCriteria;
import org.springframework.data.domain.Pageable;

/**
 * @author: Lei Fu
 * @date: 2023/05/26
 * @description: System role interface
 */
public interface IRoleService {
    /**
     * Get role list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    Object getList(RoleQueryCriteria queryCriteria, Pageable pageable);

    /**
     * Add role information
     * @param sysRole
     * @return
     */
    boolean addRole(SysRole sysRole);

    /**
     * Get details by id
     * @param id
     * @return
     */
    SysRole getById(Long id);
}
