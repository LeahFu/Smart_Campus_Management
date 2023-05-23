package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.exception.BadRequestException;
import ca.access.student.domain.SysUser;
import ca.access.student.service.ISysUserService;
import ca.access.student.service.dto.UserQueryCriteria;
import ca.access.utils.PageVo;
import ca.access.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: System User Front Controller
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final ISysUserService sysUserService;
    public UserController(ISysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    /**
     * Get user list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(UserQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1, pageVo.getPageSize(),
                Sort.Direction.DESC,"id");
        return new ResponseEntity<>(sysUserService.getList(queryCriteria,pageable), HttpStatus.OK);
    }

    /**
     * Add user information
     * @param sysUser
     * @return
     */
    @PostMapping
    public BaseResult addUser(@RequestBody SysUser sysUser){
        boolean result = sysUserService.addUser(sysUser);
        if(result){
            return BaseResult.success("Added successfully");
        }else {
            return BaseResult.fail("Add failed");
        }
    }

    /**
     * Get user details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to get information");
        }
        SysUser dbSysUser = sysUserService.getById(id);
        return BaseResult.success(dbSysUser);
    }
}
