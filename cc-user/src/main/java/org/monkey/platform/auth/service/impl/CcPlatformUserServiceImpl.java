package org.monkey.platform.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.monkey.platform.auth.mapper.CcPlatformUserMapper;
import org.monkey.platform.auth.api.pojo.CcPlatformUser;
import org.monkey.platform.auth.service.CcPlatformUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.monkey.platform.auth.api.dto.CcPlatformUserDto;
import org.monkey.platform.auth.api.enums.DelFlagEnum;
import org.monkey.platform.auth.api.enums.GenderEnum;
import org.monkey.platform.api.exception.CommException;

import java.util.Date;

/**
 * CcPlatformUserServiceImpl
 *
 * @author cc
 * @since 2024/5/31 9:44
 */
@Service
@Slf4j
public class CcPlatformUserServiceImpl extends ServiceImpl<CcPlatformUserMapper, CcPlatformUser> implements CcPlatformUserService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(CcPlatformUserDto ccPlatformUserDto) throws CommException {
        Assert.isTrue(null != ccPlatformUserDto, "请求体不能为空");
        Assert.isTrue(null != ccPlatformUserDto.getName(), "姓名不能为空");
        Assert.isTrue(null != ccPlatformUserDto.getGender(), "性别不能为空");
        Assert.isTrue(null != ccPlatformUserDto.getBirth(), "出生日期为空");
        CcPlatformUser ccPlatformUser = new CcPlatformUser();
        ccPlatformUser.setName(ccPlatformUserDto.getName());
        ccPlatformUser.setGender(GenderEnum.getCodeByValue(ccPlatformUserDto.getGender()));
        ccPlatformUser.setBirth(ccPlatformUserDto.getBirth());

        ccPlatformUser.setCreateUser(
                StringUtils.isNotEmpty(ccPlatformUserDto.getCreateUser())
                ? ccPlatformUserDto.getCreateUser()
                : "System"
        );
        ccPlatformUser.setCreateTime(new Date());
        ccPlatformUser.setDelFlag(DelFlagEnum.USEING.getCode());

        //BeanUtils.copyProperties(ccPlatformUserDto, ccPlatformUser);
        log.info("start to add CcPlatformUser: {}", ccPlatformUser);
        int row = baseMapper.insert(ccPlatformUser);
        log.info("add CcPlatformUser finished, row: {}", row);
        if (row != 1) {
            throw new CommException("add ccPlatformUser failed");
        }
        /*if (true) {
            throw new CommException("测试异常时回滚");
        }*/
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUser(CcPlatformUserDto user) throws CommException {
        Assert.isTrue(null != user, "请求体不能为空");
        Assert.isTrue(null != user.getId(), "用户ID不能为空");

        LambdaUpdateWrapper<CcPlatformUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CcPlatformUser::getId, user.getId());
        wrapper.set(StringUtils.isNotEmpty(user.getName()), CcPlatformUser::getName, user.getName());
        wrapper.set(checkBirth(user.getBirth()), CcPlatformUser::getBirth, user.getBirth());
        wrapper.set(StringUtils.isNotEmpty(user.getGender()), CcPlatformUser::getGender, GenderEnum.getCodeByValue(user.getGender()));
        wrapper.set(StringUtils.isNotEmpty(user.getDelFlag()), CcPlatformUser::getDelFlag, user.getDelFlag());
        wrapper.set(CcPlatformUser::getUpdateTime, new Date());
        wrapper.set(CcPlatformUser::getUpdateUser, StringUtils.isNotEmpty(user.getUpdateUser()) ? user.getUpdateUser() : "System");
        int row = baseMapper.update(wrapper);
        if (row != 1) {
            throw new CommException("modify ccPlatformUser failed");
        }
    }

    /**
     * 检查生日的格式
     * @param birth birth
     * @return 通过检查，true 否则 false
     */
    private boolean checkBirth(Date birth) {
        return null != birth;
    }
}
