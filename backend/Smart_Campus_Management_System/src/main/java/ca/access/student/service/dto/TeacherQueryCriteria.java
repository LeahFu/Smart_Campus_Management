package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher Information Query Conditions
 */
@Data
public class TeacherQueryCriteria {
    /**
     * Query by teacher's name, teacher's number, phone number, or email
     */
    @EnableAccessQuery(blurry = "name,teachno,phone,qq")
    private String searchValue;
}
