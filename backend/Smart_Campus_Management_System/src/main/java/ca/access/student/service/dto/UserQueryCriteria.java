package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;

/**
 * @author: Lei Fu
 * @date: 2023/05/20
 * @description: System user query condition parameters
 */
public class UserQueryCriteria {
    /**
     * query by username,realname,gender,email
     */
    @EnableAccessQuery(blurry = "username,realname,gender,email")
    private String searchValue;
    /**
     * query by status
     */
    @EnableAccessQuery
    private String status;

}
