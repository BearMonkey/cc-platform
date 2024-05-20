package ort.monkey.ccplatform.api.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("Api Base Model")
public class BaseEntity {
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建人")
    private String createBy;
    @ApiModelProperty("修改时间")
    private Date updateTime;
    @ApiModelProperty("修改人")
    private String updateBy;
    @ApiModelProperty("删除标记")
    private String delFlag;
}
