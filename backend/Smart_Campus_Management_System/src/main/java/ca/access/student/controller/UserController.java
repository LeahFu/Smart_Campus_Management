package ca.access.student.controller;

import ca.access.base.BaseResult;
import ca.access.email.MailService;
import ca.access.exception.BadRequestException;
import ca.access.student.domain.SysUser;
import ca.access.student.service.ISysUserService;
import ca.access.student.service.dto.UserQueryCriteria;
import ca.access.utils.AccessUtil;
import ca.access.utils.HutoolJWTUtil;
import ca.access.utils.NativeFileUtil;
import ca.access.utils.PageVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: System User Front Controller
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * Avatar storage path
     */
    @Value("${user.icon}")
    private String userIcon;
    /**
     * Email sender
     */
    @Value("${spring.mail.username}")
    private String from;
    private final ISysUserService sysUserService;
    private final MailService mailService;
    public UserController(ISysUserService sysUserService, MailService mailService){
        this.sysUserService = sysUserService;
        this.mailService = mailService;
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

    /**
     * Refresh user information
     * @param sysUser
     * @return
     */
    @PutMapping
    public BaseResult editUser(@RequestBody SysUser sysUser){
        sysUserService.editUser(sysUser);
        return BaseResult.success("Refresh success");
    }

    /**
     * Delete user information
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public  BaseResult delete(@PathVariable Long id){
        if(null==id){
            throw new BadRequestException("Failed to delete");
        }
        sysUserService.deleteById(id);
        return BaseResult.success("Successfully deleted");
    }
    /**
     * Upload avatar
     * @param file
     * @return
     */
    @PostMapping("userIcon")
    public BaseResult uploadFile(@RequestParam("fileResource") MultipartFile file){
        if(file==null){
            return BaseResult.fail("Failed to upload avatar, the file cannot be empty");
        }
        try {
            String tempFileResource = NativeFileUtil.uploadUserIcon(file,userIcon);
            Map<String,Object> result = new HashMap<>();
            result.put("userIcon",tempFileResource);
            return BaseResult.success(result);
        }catch (Exception e){
            e.printStackTrace();
            return BaseResult.fail(e.getMessage());
        }
    }
    /**
     * Update personal information
     * @param sysUser
     * @return
     */
    @PutMapping("updateInfo")
    public BaseResult updateInfo(@RequestBody SysUser sysUser){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // Get the login user Id
        String token = (String)request.getServletContext().getAttribute("token");
        Long userId = HutoolJWTUtil.parseToken(token);
        sysUser.setId(userId);
        sysUserService.editUser(sysUser);
        return BaseResult.success("Update completed");
    }
    /**
     * Send the verification code
     * @param email
     * @return
     */
    @GetMapping("sendEmail")
    public BaseResult sendEmail(@RequestParam("email")String email, HttpServletRequest request){
        // Send to old mailbox
        if(email==null||email==""){
            // Get the login user ID
            String token = (String)request.getServletContext().getAttribute("token");
            Long userId = HutoolJWTUtil.parseToken(token);
            SysUser dbSysUser = sysUserService.getById(userId);
            email = dbSysUser.getEmail();
        }
        int code = AccessUtil.randomSixNums();
        String content = "verification code: "+code+"This verification code is used to replace the email binding, " +
                "please do not tell others the verification code, it is valid for 3 minutes, please keep it safe.";
        mailService.sendSimpleMail(from,email,email,"Modify email verification code",content);
        request.getServletContext().setAttribute("code",code);
        return BaseResult.success("Sent successfully");
    }
}
