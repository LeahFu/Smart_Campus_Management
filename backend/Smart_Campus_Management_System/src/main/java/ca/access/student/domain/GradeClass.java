package ca.access.student.domain;

import ca.access.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"gradeClass"})
    @OneToMany(mappedBy = "gradeClass",fetch=FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "GradeClass{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", grade=" + grade +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
