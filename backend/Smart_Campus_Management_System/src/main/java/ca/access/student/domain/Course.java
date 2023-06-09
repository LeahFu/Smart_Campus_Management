package ca.access.student.domain;

import ca.access.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course Information Entity Class
 */
@Data
@Entity
@Table(name = "s_course")
@org.hibernate.annotations.Table(appliesTo = "s_course",comment="course information")
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Course number
     */
    @Column(name = "course_no")
    private String courseno;

    /**
     * Course name
     */
    @Column(name = "course_name")
    private String coursename;

}
