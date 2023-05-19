package ca.access.student.service.impl;

import ca.access.student.domain.SysUser;
import ca.access.student.repository.SysUserRepository;
import ca.access.student.service.ISysUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author: Lei Fu
 * @date: 2023/05/18
 * @description: System user business implementation class
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements ISysUserService {
    private final SysUserRepository sysUserRepository;
    public SysUserServiceImpl(SysUserRepository sysUserRepository){
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * login
     * @param sysUser
     * @return
     */
    @Override
    public SysUser login(SysUser sysUser){
        SysUser dbSysUser = sysUserRepository.findByUserName(sysUser.getUsername());
        return dbSysUser;
    }
}
