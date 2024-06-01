package org.monkey.platform.user.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Account
 *
 * @author cc
 * @since 2024/5/31 16:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cc_account")
public class Account extends BaseEntity {
    /** 账号 */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 账号 */
    private String account;

    /** 账号 */
    private String password;

    /** 账号 */
    private String nickName;

    /** 账号 */
    private String tel;

    /** 账号 */
    private String email;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
