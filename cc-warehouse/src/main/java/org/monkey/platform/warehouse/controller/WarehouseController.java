package org.monkey.platform.warehouse.controller;

import org.monkey.platform.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.monkey.platform.api.common.Result;
import org.monkey.platform.api.dto.CcWarehouseResp;
import org.monkey.platform.api.entity.CcWarehouse;

import java.util.List;

@RestController
@RequestMapping("/cc/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    public Result<CcWarehouse> addWarehouse(CcWarehouse warehouse) {
        List<CcWarehouseResp> warehouseRespList = warehouseService.selectListWarehouse();
        CcWarehouseResp warehouseResp = warehouseService.selectWarehouse();
        return null;
    }
}
