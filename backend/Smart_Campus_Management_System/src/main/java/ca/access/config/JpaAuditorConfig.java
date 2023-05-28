package ca.access.config;

import ca.access.annotation.EnableAccessQuery;
import ca.access.utils.HutoolJWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

/**
 * @author: Lei Fu
 * @date: 2023/05/28
 * @description: JPA configuration class
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditorConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor(){
        // Get user ID
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = (String)request.getServletContext().getAttribute("token");
        if(token.isEmpty()){
            return Optional.empty();
        }else{
            Long userID = HutoolJWTUtil.parseToken(token);
            return Optional.of(userID);
        }
    }
}
