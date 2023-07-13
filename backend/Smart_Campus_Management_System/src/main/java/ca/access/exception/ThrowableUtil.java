package ca.access.exception;

import org.hibernate.exception.ConstraintViolationException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author: Lei Fu
 * @date: 2023/05/08
 * @description: Exception handling
 */
public class ThrowableUtil {
    /**
     * Get stack information
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try(PrintWriter pw = new PrintWriter(sw)){
            throwable.printStackTrace();
            return sw.toString();
        }
    }
    /**
     * Delete exception
     * @param e
     * @param msg
     */
    public static void throwForeignKeyException(Throwable e, String msg){
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new BadRequestException(msg);
        }
        assert false;
        throw new BadRequestException("Failed to delete " + t.getMessage());
    }
}
