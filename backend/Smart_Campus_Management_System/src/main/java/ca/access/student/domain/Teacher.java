package ca.access.student.domain;

import ca.access.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher information entity class
 */
@Data
@Entity
@Table(name = "s_teacher")
@org.hibernate.annotations.Table(appliesTo = "s_teacher",comment="teacher information")
public class Teacher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Teacher number
     */
    @Column(name = "teach_no")
    private String teachno;

    /**
     * Teacher name
     */
    @Column(name = "name")
    private String name;

    /**
     * Teacher gender
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Phone number
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Email
     */
    @Column(name = "email")
    private String email;


    /**
     * Teaching course
     */
    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName="id")
    private Course course;
}
