package org.monkey.ccplatform.warehouse.service;

import ort.monkey.ccplatform.api.dto.CcWarehouseResp;

import java.util.List;

public interface WarehouseService {

    /**
     * 查询 多个warehouse
     * @return List<CcWarehouseResp>
     */
    List<CcWarehouseResp> selectListWarehouse();

    /**
     * 查询单个 warehouse
     * @return CcWarehouseResp
     */
    CcWarehouseResp selectWarehouse();
}
