package ca.access.utils;

import ca.access.student.domain.SysUser;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lei Fu
 * @date: 2023/05/19
 * @description: JWT tool class
 */
public class HutoolJWTUtil {
    /**
     * generate token
     * @param sysUser
     * @return
     */
    public static String createToken(SysUser sysUser){
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE,120);
        Map<String,Object> payload = new HashMap<String,Object>();
        // Issue time
        payload.put(JWTPayload.ISSUED_AT,now);
        // Expiration
        payload.put(JWTPayload.EXPIRES_AT,newTime);
        // Effective time
        payload.put(JWTPayload.NOT_BEFORE,now);
        // Load
        payload.put("username",sysUser.getUsername());
        payload.put("aid",sysUser.getId());
        String key = "www.access.ca";
        String token = JWTUtil.createToken(payload,key.getBytes());
        return token;

    }
}
