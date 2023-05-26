package ca.access.student.service.impl;

import ca.access.student.repository.SysRoleRepository;
import ca.access.student.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lei Fu
 * @date: 2023/05/26
 * @description: System role interface implementation class
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl implements IRoleService {
    private final SysRoleRepository sysRoleRepository;

    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }
}
