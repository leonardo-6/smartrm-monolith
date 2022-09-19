package com.lsl.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.lsl.smartrm.smartrmmonolith.commodity.domain.model.ValueType;

/**
 * @author: yoda
 * @description:
 */
public interface PropertyConvertor {

  CommodityPropertyParser parser(ValueType type);

  CommodityPropertyDumper dumper(ValueType type);

}
