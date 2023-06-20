package ca.access.student.controller;

import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.student.service.IScoresService;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    /**
     * Get grades list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(ScoresQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(scoresService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
}
