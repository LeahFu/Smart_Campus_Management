package ca.access.student.vo;

import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/22
 * @description: Registration class subject grade parameters
 */
@Data
public class RegisterScoresModel {
    /**
     * Class ID
     */
    private Long gradeClassId;

    /**
     * Course ID
     */
    private Long courseId;
}
