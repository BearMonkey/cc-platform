package org.monkey.demo.alipay.dto;

import lombok.Data;

import java.util.Date;

/**
 * BaseDto
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Data
public class BaseDto {
    private Integer id;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String status;

    private String delFlag;

}
