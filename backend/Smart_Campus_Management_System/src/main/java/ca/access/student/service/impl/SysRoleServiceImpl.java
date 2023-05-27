package ca.access.student.service.impl;

import ca.access.student.domain.SysRole;
import ca.access.student.repository.SysRoleRepository;
import ca.access.student.service.IRoleService;
import ca.access.student.service.dto.RoleQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Get role list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(RoleQueryCriteria queryCriteria, Pageable pageable) {
        Page<SysRole> page = sysRoleRepository.findAll((root, query, criteriaBuilder)
                -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }

    /**
     * Add role information
     * @param sysRole
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(SysRole sysRole) {
        SysRole dbSysRole = sysRoleRepository.save(sysRole);
        return dbSysRole.getId() != null;
    }
}
