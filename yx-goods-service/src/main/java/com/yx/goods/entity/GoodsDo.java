package com.yx.goods.entity;

import java.util.Date;
import lombok.Data;

@Data
public class GoodsDo {
    private Long id;

    private String number;

    private String name;

    private String color;

    private String remark;

    private String userCreate;

    private String userModified;

    private Date gmtCreate;

    private Date gmtModified;

    private Date ts;
}