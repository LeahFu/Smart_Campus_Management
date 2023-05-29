package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.exception.BadRequestException;
import ca.access.student.domain.SysRole;
import ca.access.student.service.IRoleService;
import ca.access.student.service.dto.RoleQueryCriteria;
import ca.access.utils.PageUtil;
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
 * @date: 2023/05/26
 * @description: System Role Front Controller
 */

@RestController
@RequestMapping("role")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Get role list data
     * @param queryCriteria
     * @param pageVo
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> getList(RoleQueryCriteria queryCriteria, PageVo pageVo){
        Pageable pageable = PageRequest.of(pageVo.getPageIndex()-1, pageVo.getPageSize(),
                Sort.Direction.DESC,"id");
        return new ResponseEntity<>(roleService.getList(queryCriteria,pageable), HttpStatus.OK);
    }

    /**
     * Add role information
     * @param sysRole
     * @return
     */
    @PostMapping
    public BaseResult addRole(@RequestBody SysRole sysRole){
        boolean result = roleService.addRole(sysRole);
        if(result){
            return BaseResult.success("Successfully added role!");
        }else {
            return BaseResult.fail("Failed to add role");
        }
    }

    /**
     * Get role details by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public BaseResult detail(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to get information");
        }
        SysRole dbSysRole = roleService.getById(id);
        return BaseResult.success(dbSysRole);
    }

    /**
     * Update role information
     * @param sysRole
     * @return
     */
    @PutMapping
    public BaseResult editRole(@RequestBody SysRole sysRole){
        roleService.editRole(sysRole);
        return BaseResult.success("Update completed");
    }

    /**
     * Delete role information based on ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public BaseResult delete(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to delete information");
        }
        roleService.deleteById(id);
        return BaseResult.success("Successfully deleted");
    }
}
