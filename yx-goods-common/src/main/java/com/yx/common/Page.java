package com.yx.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<T> implements Serializable {

    /**
     * 当前页数
     */
    private int num = 1;

    /**
     * 每页条数
     */
    private int size = 10;

    /**
     * 总条数
     */
    private long total;

    /**
     * 查询总记录
     */
    private List<T> records = Collections.emptyList();

}
