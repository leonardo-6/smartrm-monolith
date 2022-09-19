package com.lsl.smartrm.smartrmmonolith.commodity.adapter.convertor;

import com.lsl.smartrm.smartrmmonolith.commodity.domain.model.Property;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityPropertyDumper<T> {

  void dump(Property<T> from);

}
