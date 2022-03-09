package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private Integer id;
    private String userCode;
    private String content;
    private Integer isAdd;
}
