package com.lsl.smartrm.smartrmmonolith.trade.adapter.remote;

import com.lsl.smartrm.smartrmmonolith.device.application.dto.PopCommodityCmdDto;
import com.lsl.smartrm.smartrmmonolith.device.application.service.VendingMachineDeviceService;
import com.lsl.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.lsl.smartrm.smartrmmonolith.trade.domain.remote.InventoryInfo;
import com.lsl.smartrm.smartrmmonolith.trade.domain.remote.TradeDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
@Service
public class MonolithTradeDeviceServiceImpl implements TradeDeviceService {

  @Autowired
  VendingMachineDeviceService vendingMachineDeviceService;

  @Override
  public List<InventoryInfo> getInventory(long machineId) {
    return vendingMachineDeviceService.getInventory(machineId).stream().map(
        d -> new InventoryInfo(d.getCommodityId(),
            d.getCount())).collect(
        Collectors.toList());
  }

  @Override
  public void popCommodity(long machineId, String commodityId, long orderId)
      throws Exception {
    PopCommodityCmdDto cmd = new PopCommodityCmdDto();
    cmd.setCommodityId(commodityId);
    cmd.setMachineId(machineId);
    cmd.setOrderId(orderId);
    vendingMachineDeviceService.popCommodity(cmd);
  }

  @Override
  public void openCabinetVendingMachine(long machineId) throws DomainException {
    vendingMachineDeviceService.openCabinetVendingMachine(machineId);
  }
}
