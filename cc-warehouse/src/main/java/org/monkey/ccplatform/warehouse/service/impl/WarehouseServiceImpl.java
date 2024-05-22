package org.monkey.ccplatform.warehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.monkey.ccplatform.warehouse.mapper.CcWarehouseMapper;
import org.monkey.ccplatform.warehouse.service.WarehouseService;
import org.springframework.stereotype.Service;
import ort.monkey.ccplatform.api.entity.CcWarehouse;

@Service
@Slf4j
public class WarehouseServiceImpl extends ServiceImpl<CcWarehouseMapper, CcWarehouse> implements WarehouseService {
}
