package com.liuwei.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsVo implements Serializable {
    private Long value;
    private String label;
    private List<OptionsVo> children;
}
