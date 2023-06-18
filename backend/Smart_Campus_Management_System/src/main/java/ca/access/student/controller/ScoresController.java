package ca.access.student.controller;

import ca.access.student.service.ICourseService;
import ca.access.student.service.IScoresService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Lei Fu
 * @date: 2023/06/17
 * @description: Grade management front controller
 */
@RestController
@RequestMapping("scores")
public class ScoresController {
    private final IScoresService scoresService;

    public ScoresController(IScoresService scoresService) {
        this.scoresService = scoresService;
    }
}
