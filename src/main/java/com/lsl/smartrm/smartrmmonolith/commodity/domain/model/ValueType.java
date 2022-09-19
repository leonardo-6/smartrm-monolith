package com.lsl.smartrm.smartrmmonolith.commodity.domain.model;

public interface ValueType<T> {

  ValueTypeCode getType();

  Class<T> getValueClass();

}
