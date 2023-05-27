package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;
import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/05/27
 * @description: System Role Query Conditions
 */
@Data
public class RoleQueryCriteria {
    /**
     * Query by role name or role code
     */
    @EnableAccessQuery(blurry = "name,code")
    private String searchValue;
}
