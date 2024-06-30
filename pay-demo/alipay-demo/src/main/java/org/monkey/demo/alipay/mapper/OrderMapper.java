package org.monkey.demo.alipay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.monkey.demo.alipay.dto.OrdOrder;

/**
 * OrderMapper
 *
 * @author Monkey
 * @since 2024/6/30
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrdOrder> {

    @Select("SELECT a.*, b.* FROM cc_order a JOIN cc_goods b ON a.goods_id = b.id WHERE a.order_no = #{orderId}")
    OrdOrder findOrder(String orderId);
}
