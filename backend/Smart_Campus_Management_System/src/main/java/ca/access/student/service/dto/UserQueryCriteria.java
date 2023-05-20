package ca.access.student.service.dto;

import ca.access.annotation.EnableAccessQuery;

/**
 * @author: Lei Fu
 * @date: 2023/05/20
 * @description: System user query condition parameters
 */
public class UserQueryCriteria {
    /**
     * query by username
     */
    @EnableAccessQuery(blurry = "username")
    private String username;
    /**
     * query by realname
     */
    @EnableAccessQuery(blurry = "realname")
    private String realname;
    /**
     * query by gender
     */
    @EnableAccessQuery
    private String gender;
    /**
     * query by email
     */
    @EnableAccessQuery(blurry = "email")
    private String email;
    /**
     * query by status
     */
    @EnableAccessQuery
    private String status;

}
