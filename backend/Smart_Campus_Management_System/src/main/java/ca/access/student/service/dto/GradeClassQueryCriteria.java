package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/05/30
 * @description: Class query condition
 */

@Data
public class GradeClassQueryCriteria {
    /**
     * Query according to class name or class code
     */
    @EnableAccessQuery(blurry = "name,code")
    private String searchValue;
}
