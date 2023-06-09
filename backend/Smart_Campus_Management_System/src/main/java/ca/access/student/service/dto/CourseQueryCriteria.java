package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course Query Criteria
 */
@Data
public class CourseQueryCriteria {
    /**
     * Query by course number or course name
     */
    @EnableAccessQuery(blurry = "courseno,coursename")
    private String searchValue;
}
