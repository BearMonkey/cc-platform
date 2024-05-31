package org.monkey.demo.mybatisplus.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * CcPlatformUserDto
 *
 * @author cc
 * @since 2024/5/31 9:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class CcPlatformUserDto extends BaseDto {

    /** 主键 */
    @ApiModelProperty("主键")
    private Integer id;

    /** 名字 */
    @ApiModelProperty("名字")
    private String name;

    /** 性别 */
    @ApiModelProperty("性别")
    private String gender;

    /** 出生日期 */
    @ApiModelProperty("出生日期")
    private Date birth;
}
