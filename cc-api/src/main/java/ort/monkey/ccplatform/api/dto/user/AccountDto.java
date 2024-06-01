package ort.monkey.ccplatform.api.dto.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ort.monkey.ccplatform.api.dto.BaseDto;

/**
 * Account
 *
 * @author cc
 * @since 2024/5/31 16:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel
public class AccountDto extends BaseDto {
    /** 主键 */
    @ApiModelProperty("账号")
    private Integer id;

    /** 账号 */
    @ApiModelProperty("账号")
    private String account;

    /** 密码 */
    @ApiModelProperty("密码")
    private String password;

    /** 名称 */
    @ApiModelProperty("名称")
    private String nickName;

    /** 手机 */
    @ApiModelProperty("手机")
    private String tel;

    /** 邮箱 */
    @ApiModelProperty("邮箱")
    private String email;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
