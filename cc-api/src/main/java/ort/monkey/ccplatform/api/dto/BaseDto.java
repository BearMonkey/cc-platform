package ort.monkey.ccplatform.api.dto;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * BaseDto
 *
 * @author cc
 * @since 2024/5/31 9:48
 */
@Data
public class BaseDto {

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 创建用户 */
    @ApiModelProperty("创建用户")
    private String createUser;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Date updateTime;

    /** 修改用户 */
    @ApiModelProperty("修改用户")
    private String updateUser;

    /** 删除标记 */
    @ApiModelProperty("删除标记")
    private String delFlag;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
