package ca.access.student.service.impl;

import ca.access.base.BaseResult;
import ca.access.student.domain.SysUser;
import ca.access.student.repository.SysUserRepository;
import ca.access.student.service.ISysUserService;
import ca.access.student.service.dto.UserQueryCriteria;
import ca.access.utils.Md5Util;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
     * Login
     * @param sysUser
     * @return
     */
    @Override
    public SysUser login(SysUser sysUser){
        SysUser dbSysUser = sysUserRepository.findByUsername(sysUser.getUsername());
        return dbSysUser;
    }

    /**
     * Get user list data
     * @param pageable
     * @return
     */
    @Override
    public Object getList(UserQueryCriteria queryCriteria, Pageable pageable){
        Page<SysUser> page = sysUserRepository.findAll((root, query, criteriaBuilder) ->
                QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }

    /**
     * Add user information
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(SysUser sysUser) {
        SysUser dbSysUser = sysUserRepository.save(sysUser);
        return dbSysUser.getId() != null;
    }

    /**
     * Get user details by id
     * @param id
     * @return
     */
    @Override
    public SysUser getById(Long id){
        return sysUserRepository.findById(id).orElseGet(SysUser::new);
    }

    /**
     * Refresh user information
     * @param sysUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editUser(SysUser sysUser){
        SysUser dbSysUser = sysUserRepository.getReferenceById(sysUser.getId());
        BeanUtil.copyProperties(sysUser,dbSysUser, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        sysUserRepository.save(dbSysUser);
    }

    /**
     * Delete user information based on ID
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        sysUserRepository.deleteById(id);
    }
}
