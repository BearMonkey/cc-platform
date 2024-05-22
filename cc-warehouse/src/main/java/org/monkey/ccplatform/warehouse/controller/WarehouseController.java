package org.monkey.ccplatform.warehouse.controller;

import org.monkey.ccplatform.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ort.monkey.ccplatform.api.common.Result;
import ort.monkey.ccplatform.api.entity.CcWarehouse;

@RestController
@RequestMapping("/cc/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    public Result<CcWarehouse> addWarehouse(CcWarehouse warehouse) {

    }
}
