package ca.access.student.domain;

import ca.access.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class Information Entity
 */

@Entity
@Data
@Table(name = "s_grade_class")
@org.hibernate.annotations.Table(appliesTo = "s_grade_class",comment = "class information")
public class GradeClass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    /**
     * class code
     */
    @Column(name = "code",nullable = false)
    private String code;
    /**
     * grade
     */
    @Column(name = "grade",nullable = false)
    private Integer grade;
    /**
     * class name
     */
    @Column(name = "name",nullable = false)
    private String name;
    /**
     * class
     */
    @Column(name = "clazz",nullable = false)
    private String clazz;
}
