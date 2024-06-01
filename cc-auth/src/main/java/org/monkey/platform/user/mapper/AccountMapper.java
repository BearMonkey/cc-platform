package org.monkey.platform.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.monkey.platform.user.pojo.Account;

/**
 * AccountMapper
 *
 * @author cc
 * @since 2024/5/31 9:42
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
