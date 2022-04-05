package com.jack.jpastd.domain;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class OrderSearch {
    
    private String memberName;
    private OrderStatus orderStatus;
}
