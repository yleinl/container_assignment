package com.vu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "course table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @TableId(value = "course_id", type = IdType.AUTO)
    @ApiModelProperty(value = "course id")
    private Long courseId;

    @TableField(value = "course_name")
    @ApiModelProperty(value = "course name")
    private String courseName;

    @TableField(value = "lecturer")
    @ApiModelProperty(value = "lecturer")
    private String lecturer;

    @TableField(value = "study_guide")
    @ApiModelProperty(value = "study guide")
    private String studyGuide;

}
