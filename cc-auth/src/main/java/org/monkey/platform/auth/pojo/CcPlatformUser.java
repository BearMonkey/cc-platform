package org.monkey.platform.auth.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * CcPlatformUser
 *
 * @author cc
 * @since 2024/5/31 9:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cc_platform_user")
public class CcPlatformUser extends BaseEntity {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /** 名字 */
    private String name;

    /** 性别 */
    private String gender;

    /** 出生日期 */
    private Date birth;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
