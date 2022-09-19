package com.lsl.smartrm.smartrmmonolith.commodity.adapter;

import com.lsl.smartrm.smartrmmonolith.commodity.domain.model.Commodity;

import java.io.IOException;

/**
 * @author: yoda
 * @description:
 */
public interface CommodityProcessor {

  public void onCommodity(Commodity commodity) throws IOException;

}
