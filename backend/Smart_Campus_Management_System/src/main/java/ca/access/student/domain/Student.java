package ca.access.student.domain;

import ca.access.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/01
 * @description: Student Information Entity Class
 */
@Entity
@Data
@Table(name = "s_student")
@org.hibernate.annotations.Table(appliesTo = "s_student",comment = "student information")
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    /**
     * Student number
     */
    @Column(name = "student_number",nullable = false)
    private String studentNumber;
    /**
     * Student name
     */
    @Column(name = "student_name",nullable = false)
    private String name;
    /**
     * The class the student belongs to
     */
    @OneToOne
    @JoinColumn(name = "grade_class_id",referencedColumnName = "id")
    private GradeClass gradeClass;
    /**
     * Gender
     */
    @Column(name = "gender",nullable = false)
    private String gender;
    /**
     * Phone number
     */
    @Column(name = "phone",nullable = false)
    private String phone;
    /**
     * Email
     */
    @Column(name = "email",nullable = false)
    private String email;
}
