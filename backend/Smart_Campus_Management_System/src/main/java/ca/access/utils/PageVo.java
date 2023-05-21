package ca.access.utils;

import lombok.Data;

/**
 * @author: Lei Fu
 * @date: 2023/05/20
 * @description: Pagination query parameters
 */
@Data
public class PageVo {
    /**
     * current page number
     */
    private int pageIndex;
    /**
     * number of items displayed per page
     */
    private int pageSize;
}
