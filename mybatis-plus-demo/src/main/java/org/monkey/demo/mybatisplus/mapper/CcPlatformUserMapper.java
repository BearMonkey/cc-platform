package org.monkey.demo.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.monkey.demo.mybatisplus.pojo.CcPlatformUser;

/**
 * CcPlatformUserMapper
 *
 * @author cc
 * @since 2024/5/31 9:42
 */
@Mapper
public interface CcPlatformUserMapper extends BaseMapper<CcPlatformUser> {
}
