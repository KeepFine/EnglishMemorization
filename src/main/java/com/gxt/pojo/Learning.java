package com.gxt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Learning {

    private String userCode;
    private String learningBookId;
    private Integer completion;
    private Integer learningNo;
    private Integer errorNo;
}
