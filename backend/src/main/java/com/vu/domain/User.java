package com.vu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "student table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "student id")
    private Long id;

    @TableField(value = "vunetid")
    @ApiModelProperty(value = "VUnet ID")
    private String vunetId;

    @TableField(value = "password")
    @ApiModelProperty(value = "password")
    private String password;

    @TableField(value = "courses")
    @ApiModelProperty(value = "courses")
    private String courses;

    @TableField(value = "email")
    @ApiModelProperty(value = "email")
    private String email;

    @TableField(value = "student_name")
    @ApiModelProperty(value = "student name")
    private String studentName;
}
