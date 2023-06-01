package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.exception.BadRequestException;
import ca.access.student.domain.GradeClass;
import ca.access.student.service.IGradeClassService;
import ca.access.student.service.dto.GradeClassQueryCriteria;
import ca.access.utils.PageVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class information front controller
 */
@RestController
@RequestMapping("gradeclass")
public class GradeClassController {
    private final IGradeClassService gradeClassService;

    public GradeClassController(IGradeClassService gradeClassService) {
        this.gradeClassService = gradeClassService;
    }

    /**
     * Get class list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(GradeClassQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(),
                Sort.Direction.DESC,"id");
        return new ResponseEntity<>(gradeClassService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
    /**
     * Add class information
     * @param gradeClass
     * @return
     */
    @PostMapping
    public BaseResult addGradeClass(@RequestBody GradeClass gradeClass){
        boolean result= gradeClassService.addGradeClass(gradeClass);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }
    /**
     * Get class details by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to get information");
        }
        GradeClass dbGradeClass = gradeClassService.getById(id);
        return BaseResult.success(dbGradeClass);
    }
    /**
     * Update class information
     * @param gradeClass
     * @return
     */
    @PutMapping
    public BaseResult editGradeClass(@RequestBody GradeClass gradeClass){
        gradeClassService.editGradeClass(gradeClass);
        return BaseResult.success("Update completed");
    }
    /**
     * Delete class information based on ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResult delete(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to delete information");
        }
        gradeClassService.deleteById(id);
        return BaseResult.success("Successfully deleted");
    }
}
