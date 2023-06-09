package ca.access.student.controller;

import ca.access.exception.BadRequestException;
import ca.access.student.domain.Scores;
import ca.access.base.BaseResult;
import ca.access.student.service.dto.ScoresQueryCriteria;
import ca.access.student.service.IScoresService;
import ca.access.student.vo.RegisterScoresModel;
import ca.access.utils.PageVo;
import ca.access.student.vo.EchartsSeriesModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * Register class course grades
     * @param scoresModel
     * @return
     */
    @PostMapping
    public BaseResult registerScores(@RequestBody RegisterScoresModel scoresModel){
        scoresService.registerScores(scoresModel);
        return BaseResult.success("Registration successful");
    }
    /**
     * Update grades
     * @param scores
     * @return
     */
    @PutMapping
    public BaseResult editScores(@RequestBody Scores scores){
        scoresService.editScores(scores);
        return BaseResult.success("Update completed");
    }
    /**
     * Delete grades information based on ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResult delete(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to delete information");
        }
        scoresService.deleteById(id);
        return BaseResult.success("Successfully deleted");
    }
    /**
     * Statistical class course grades
     * @param courseId
     * @param gradeClassId
     * @return
     */
    @GetMapping("getScoreCensus")
    public BaseResult getScoreCensus(@RequestParam("courseId")Long courseId,
                                     @RequestParam("gradeClassId")Long gradeClassId){
        List<EchartsSeriesModel> list = scoresService.getScoreCensus(courseId,gradeClassId);
        return BaseResult.success(list);
    }
    /**
     * Comparison of class course scores
     * @param courseId
     * @return
     */
    @GetMapping("getScoresContrastCensus")
    public BaseResult getScoresContrastCensus(@RequestParam("courseId")Long courseId){
        return BaseResult.success(scoresService.getScoresContrastCensus(courseId));
    }
}
