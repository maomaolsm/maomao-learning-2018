package com.maomao.domain;

import lombok.Data;

@Data
public class Resource {
    private Long id;
    private String name;
    private ResourceDomainType domain;
    private ResourceOperate operate;
    private String code;
}
