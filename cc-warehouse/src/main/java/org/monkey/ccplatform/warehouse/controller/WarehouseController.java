package org.monkey.ccplatform.warehouse.controller;

import org.monkey.ccplatform.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ort.monkey.ccplatform.api.dto.CcWarehouseResp;
import ort.monkey.ccplatform.api.entity.CcWarehouse;

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
