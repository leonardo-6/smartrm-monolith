package com.lsl.smartrm.smartrmmonolith.trade.adapter.eventhandler;

import com.lsl.smartrm.smartrmmonolith.device.domain.event.CabinetVendingMachineLockedEvent;
import com.lsl.smartrm.smartrmmonolith.infracore.event.DomainEventHandler;
import com.lsl.smartrm.smartrmmonolith.trade.application.AppTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class CabinetLockedEventHandler implements
    DomainEventHandler<CabinetVendingMachineLockedEvent> {

  @Autowired
  AppTradeService tradeService;

  @Override
  public void onApplicationEvent(CabinetVendingMachineLockedEvent event) {
    tradeService.onCabinetLocked(event);
  }
}
