package ca.access.student.repository;

import ca.access.student.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Lei Fu
 * @date: 2023/05/18
 * @description: System User Persistence Layer
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor {
    /**
     * Find user information based on login user name
     * @param username
     * @return
     */
    SysUser findByUserName(String username);

}
