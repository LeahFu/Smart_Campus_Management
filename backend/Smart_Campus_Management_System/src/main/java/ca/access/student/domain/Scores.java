package ca.access.student.domain;

import ca.access.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Student grade entity class
 */
@Data
@Entity
@Table(name = "s_student_score")
@org.hibernate.annotations.Table(appliesTo = "s_student_score",comment="student score")
public class Scores extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Class
     */
    @OneToOne
    @JoinColumn(name = "gradeclass_id",referencedColumnName="id")
    private GradeClass gradeClass;

    /**
     * Student object
     */
    @OneToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    /**
     * Course object
     */
    @OneToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;

    /**
     * Student's score
     */
    @Column(name = "score")
    private float score;

    /**
     * Whether to correct or not: not corrected, corrected
     */
    @Column(name = "type")
    public String type;
}
