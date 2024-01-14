package com.dlc.dlc.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.dlc.dlc.system.dto.Pagedto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author dlc
 * @since 2024-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Systems extends Pagedto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    @TableField("isDeleted")
    @TableLogic
    private Integer isdeleted;


}
