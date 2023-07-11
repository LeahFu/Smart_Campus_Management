package ca.access.annotation.generation;

import ca.access.utils.HutoolJWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

final class UpdateByGenerators {
    private static final Map<Class<?>, ValueGenerator<?>> generators = new HashMap();

    public UpdateByGenerators() {
    }

    public static ValueGenerator<?> get(Class<?> type) {
        ValueGenerator<?> valueGeneratorSupplier = (ValueGenerator)generators.get(type);
        if (Objects.isNull(valueGeneratorSupplier)) {
            return null;
        } else {
            return valueGeneratorSupplier;
        }
    }
    static {
        generators.put(java.lang.Long.class, (session, owner) -> {
            Long updateBy = 2L;
            if(RequestContextHolder.getRequestAttributes()!=null){
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String token = (String)request.getServletContext().getAttribute("token");
                updateBy = HutoolJWTUtil.parseToken(token);
            }
            return updateBy;
        });
    }
}
