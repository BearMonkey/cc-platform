package org.monkey.platform.user.pojo;

import lombok.Data;

import java.util.Date;

/**
 * BaseEntity
 *
 * @author cc
 * @since 2024/5/31 9:32
 */
@Data
public class BaseEntity {

    /** 创建时间 */
    private Date createTime;

    /** 创建用户 */
    private String createUser;

    /** 修改时间 */
    private Date updateTime;

    /** 修改用户 */
    private String updateUser;

    /** 删除标记 */
    private String delFlag;
}
