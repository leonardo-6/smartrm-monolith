package com.lsl.smartrm.smartrmmonolith.payment.adapter.remote;

import com.lsl.smartrm.smartrmmonolith.payment.application.remote.TradeService;
import com.lsl.smartrm.smartrmmonolith.payment.domain.PaymentStateChangeEvent;
import com.lsl.smartrm.smartrmmonolith.trade.application.AppTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class MonolithTradeServiceImpl implements TradeService {

  @Autowired
  private AppTradeService appTradeService;

  @Override
  public void onPaymentStateChange(PaymentStateChangeEvent event) {
    appTradeService.onPaymentStateChange(event);
  }
}
