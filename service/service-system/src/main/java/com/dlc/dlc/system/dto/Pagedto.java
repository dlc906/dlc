package com.dlc.dlc.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Pagedto {


    @TableField(exist = false)
    private Integer correct;

    @TableField(exist = false)
    private Integer size;
}
