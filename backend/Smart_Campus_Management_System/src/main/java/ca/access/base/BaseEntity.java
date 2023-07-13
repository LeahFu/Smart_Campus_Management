package ca.access.base;

import ca.access.annotation.EnableAccessCreateBy;
import ca.access.annotation.EnableAccessUpdateBy;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * @author: Lei Fu
 * @date: 2023/05/07
 * @description: Base(public) entity
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    /**
     * create time
     */
    @Column(name = "create_time",nullable = false)
    @CreationTimestamp
    private Timestamp createTime;

    /**
     * create ID
     */
    @Column(name = "create_by",nullable = false)
    @EnableAccessCreateBy
    private Long createBy;

    /**
     * update time
     */
    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "update_by",nullable = false)
    @EnableAccessUpdateBy
    private Long updateBy;

    /**
     * remarks
     */
    @Column(name = "remarks")
    private String remarks;

    public @interface Update{}
    @Override
    public String toString(){
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try{
            for(Field f : fields){
                f.setAccessible(true);
                builder.append(f.getName(),f.get(this)).append("\n");
            }
        }catch (Exception e){
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
