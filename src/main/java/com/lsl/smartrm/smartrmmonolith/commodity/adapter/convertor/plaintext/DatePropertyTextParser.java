package com.lsl.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext;

import com.lsl.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import com.lsl.smartrm.smartrmmonolith.commodity.domain.model.DateParser;
import com.lsl.smartrm.smartrmmonolith.commodity.infrastructure.CommodityError;
import com.lsl.smartrm.smartrmmonolith.infracore.exception.DomainException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

/**
 * @author: yoda
 * @description:
 */
public class DatePropertyTextParser implements
    CommodityPropertyParser<LocalDate, String> {

  @Override
  public LocalDate parse(String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }

    LocalDate date = DateParser.asLocalDate(value);
    if (date == null) {
      throw new DomainException(CommodityError.CommodityParseError);
    }
    return date;
  }
}
