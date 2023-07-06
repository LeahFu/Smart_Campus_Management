package ca.access.utils;

import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/06/05
 * @description:
 */
@Data
public class ResultVo {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
