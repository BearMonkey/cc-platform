package org.monkey.platform.warehouse.service;

import org.monkey.platform.api.dto.CcWarehouseResp;

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
