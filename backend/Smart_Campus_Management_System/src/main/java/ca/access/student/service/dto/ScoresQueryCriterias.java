package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;

/**
 * @author: Lei Fu
 * @date: 2023/06/20
 * @description: Grades query condition parameters
 */
public class ScoresQueryCriterias {
    /**
     * Query by class ID
     */
    @EnableAccessQuery(joinName = "gradeClass",propName = "id",type = EnableAccessQuery.Type.EQUAL)
    private Long gradeClassId;
    /**
     * Query by class student number
     */
    @EnableAccessQuery(joinName = "student",propName = "stuno",type = EnableAccessQuery.Type.INNER_LIKE)
    private String stuno;
    /**
     * Query by class student name
     */
    @EnableAccessQuery(joinName = "student",propName = "name",type = EnableAccessQuery.Type.INNER_LIKE)
    private String name;
    /**
     * Query by class course name
     */
    @EnableAccessQuery(joinName = "course",propName = "coursename",type = EnableAccessQuery.Type.INNER_LIKE)
    private String coursename;
    /**
     * Query by class course ID
     */
    @EnableAccessQuery(joinName = "course",propName = "id",type = EnableAccessQuery.Type.EQUAL)
    private Long courseId;
}
