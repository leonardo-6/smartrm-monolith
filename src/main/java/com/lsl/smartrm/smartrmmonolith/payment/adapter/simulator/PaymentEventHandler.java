package com.lsl.smartrm.smartrmmonolith.payment.adapter.simulator;

import com.lsl.smartrm.smartrmmonolith.infracore.event.DomainEventHandler;
import com.lsl.smartrm.smartrmmonolith.payment.application.dto.PlatformPaymentResultDto;
import com.lsl.smartrm.smartrmmonolith.payment.application.dto.PlatformResultCode;
import com.lsl.smartrm.smartrmmonolith.payment.application.service.PayService;
import com.lsl.smartrm.smartrmmonolith.payment.domain.PaymentState;
import com.lsl.smartrm.smartrmmonolith.payment.domain.PaymentStateChangeEvent;
import com.lsl.smartrm.smartrmmonolith.payment.domain.PlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class PaymentEventHandler implements DomainEventHandler<PaymentStateChangeEvent> {

  @Autowired
  PayService payService;

  @Override
  public void onApplicationEvent(PaymentStateChangeEvent paymentStateChangeEvent) {
    if (paymentStateChangeEvent.getCurState() == PaymentState.WaitingForDeduction) {
      PlatformPaymentResultDto result = new PlatformPaymentResultDto();
      result.setPlatformType(PlatformType.Wechat);
      result.setOrderId(paymentStateChangeEvent.getOrderInfo().getOrderId());
      result.setResultCode(PlatformResultCode.Success);
      payService.onPaymentResult(result);
    }
  }
}
