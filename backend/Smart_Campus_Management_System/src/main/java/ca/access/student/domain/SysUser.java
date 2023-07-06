package ca.access.student.domain;

import ca.access.base.BaseEntity;
import ca.access.base.BaseResult;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/05/18
 * @description: User entity class
 */
@Data
@Entity
@Table(name = "sys_user")
@org.hibernate.annotations.Table(appliesTo = "sys_user",comment = "user information table")
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "realname")
    private String realname;
    @Column(name = "gender")
    private String gender;
    @Column(name = "status")
    private Integer status;
    @Column(name = "email")
    private String email;
    @Column(name = "user_icon")
    private String userIcon;
    @OneToOne
    @JoinColumn(name = "role_id",referencedColumnName="id")
    private SysRole sysRole;
}
