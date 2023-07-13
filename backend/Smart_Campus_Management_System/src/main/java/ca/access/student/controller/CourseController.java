package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.exception.BadRequestException;
import ca.access.student.domain.Course;
import ca.access.student.service.ICourseService;
import ca.access.student.service.dto.CourseQueryCriteria;
import ca.access.utils.PageVo;
import ca.access.utils.ResultVo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Lei Fu
 * @date: 2023/06/09
 * @description: Course information front controller
 */
@RestController
@RequestMapping("course")
public class CourseController {
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }
    /**
     * Get course list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(CourseQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1,pageVo.getPageSize(), Sort.Direction.DESC, "id");
        return new ResponseEntity<>(courseService.getList(queryCriteria,pageable), HttpStatus.OK);
    }
    /**
     * Add course information
     * @param course
     * @return
     */
    @PostMapping
    public BaseResult addCourse(@RequestBody Course course){
        boolean result= courseService.addCourse(course);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }
    /**
     * Get course details by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to get information");
        }
        Course dbCourse = courseService.getById(id);
        return BaseResult.success(dbCourse);
    }
    /**
     * Update course information
     * @param course
     * @return
     */
    @PutMapping
    public BaseResult editCourse(@RequestBody Course course){
        courseService.editCourse(course);
        return BaseResult.success("Update completed");
    }
    /**
     * Delete course information based on ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResult deleteCourse(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to delete information");
        }
        courseService.deleteById(id);
        return BaseResult.success("Successfully deleted");
    }
    /**
     * Get all course information
     * @param
     * @return
     */
    @GetMapping(value="/all")
    public BaseResult getAll(){
        List<Course> list =  courseService.queryAll();
        List<ResultVo> resultVoList = list.stream().map(temp -> {
            ResultVo obj = new ResultVo();
            obj.setName(temp.getCoursename());
            obj.setId(temp.getId());
            return obj;
        }).collect(Collectors.toList());
        return BaseResult.success(resultVoList);
    }
}
