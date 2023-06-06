package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/02
 * @description: Student information query conditions
 */
@Data
public class StudentQueryCriteria {
    /**
     * Query by name,stuno,phone or email
     */
    @EnableAccessQuery(blurry = "name,stuno,phone,email")
    private String searchValue;
}
