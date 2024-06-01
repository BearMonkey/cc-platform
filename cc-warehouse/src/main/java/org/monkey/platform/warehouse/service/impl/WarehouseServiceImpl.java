package org.monkey.platform.warehouse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.monkey.platform.warehouse.mapper.CcWarehouseMapper;
import org.monkey.platform.warehouse.service.WarehouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.monkey.platform.api.dto.CcWarehouseResp;
import org.monkey.platform.api.entity.CcWarehouse;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WarehouseServiceImpl extends ServiceImpl<CcWarehouseMapper, CcWarehouse> implements WarehouseService {
    @Override
    public List<CcWarehouseResp> selectListWarehouse() {
        LambdaQueryWrapper<CcWarehouse> wrapper = new LambdaQueryWrapper<>();
        List<CcWarehouse> list = baseMapper.selectList(wrapper);
        List<CcWarehouseResp> ccWarehouseResps = new ArrayList<>();
        BeanUtils.copyProperties(list, ccWarehouseResps);
        return ccWarehouseResps;
    }

    @Override
    public CcWarehouseResp selectWarehouse() {
        return null;
    }
}
