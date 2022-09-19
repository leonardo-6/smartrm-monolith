package com.lsl.smartrm.smartrmmonolith.device.domain.slot;

import com.lsl.smartrm.smartrmmonolith.device.domain.DeviceModel;
import com.lsl.smartrm.smartrmmonolith.device.domain.InventoryInfo;
import com.lsl.smartrm.smartrmmonolith.device.domain.VendingMachineType;
import com.lsl.smartrm.smartrmmonolith.device.domain.event.DeviceFailureEvent;
import com.lsl.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceId;
import com.lsl.smartrm.smartrmmonolith.device.domain.iot.IoTDeviceService;
import com.lsl.smartrm.smartrmmonolith.device.infrastructure.DeviceError;
import com.lsl.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.lsl.smartrm.smartrmmonolith.infracore.exception.DomainException;

import java.util.List;

/**
 * @author: yoda
 * @description:
 */
public class NormalSlotVendingMachine extends SlotVendingMachine {

  private IoTDeviceId deviceId;

  private IoTDeviceService iotService;

  private SlotCommodityService slotCommodityService;

  public IoTDeviceId deviceId() {
    return deviceId;
  }

  private NormalSlotVendingMachine() {

  }

  @Override
  public void popCommodity(String commodityId, Long orderId) throws Exception {
    if (!checkInventory(commodityId, 1)) {
      throw new DomainException(DeviceError.InventoryNotCorrect);
    }
    int slot = slotCommodityService.findSlotForCommodity(this.machineId, commodityId);
    iotService.popCommodity(deviceId, slot, orderId);
    changeInventory(commodityId, -1);
  }

  @Override
  public void onDeviceFailure(DeviceFailureEvent event) {
    int slot = Integer.parseInt((String) event.getFailure().getData().get("slotId"));
    String commodityId = slotCommodityService.findCommodityBySlot(event.getMachineId(), slot);
    rollbackInventory(commodityId, 1);
  }

  public static Builder Builder() {
    return new Builder();
  }

  @Override
  public void onDeviceFailure(com.lsl.smartrm.smartrmmonolith.device.domain.DeviceFailureEvent event) {

  }

  public static class Builder {

    //设备id
    private long machineId;
    //库存信息
    private List<InventoryInfo> inventoryInfo;

    private IoTDeviceId deviceId;

    private VendingMachineType type;

    private DeviceModel model;

    private IoTDeviceService iotService;

    private SlotCommodityService slotCommodityService;

    public Builder machineId(long machineId) {
      this.machineId = machineId;
      return this;
    }

    public Builder inventoryInfo(List<InventoryInfo> inventoryInfo) {
      this.inventoryInfo = inventoryInfo;
      return this;
    }

    public Builder deviceId(IoTDeviceId deviceId) {
      this.deviceId = deviceId;
      return this;
    }

    public Builder type(VendingMachineType type) {
      this.type = type;
      return this;
    }

    public Builder model(DeviceModel model) {
      this.model = model;
      return this;
    }

    public Builder iotService(IoTDeviceService iotService) {
      this.iotService = iotService;
      return this;
    }

    public Builder slotCommodityService(
        SlotCommodityService slotCommodityService) {
      this.slotCommodityService = slotCommodityService;
      return this;
    }

    public NormalSlotVendingMachine build() {
      if (this.slotCommodityService == null
          || this.iotService == null
          || this.machineId == 0
          || this.inventoryInfo == null
          || this.type == null
          || this.model == null
          || this.deviceId == null) {
        throw new DomainException(CommonError.InvalidProperty);
      }
      NormalSlotVendingMachine ret = new NormalSlotVendingMachine();
      ret.deviceId = deviceId;
      ret.machineId = machineId;
      ret.model = model;
      ret.type = type;
      ret.setInventory(this.inventoryInfo);
      ret.slotCommodityService = this.slotCommodityService;
      ret.iotService = this.iotService;
      return ret;
    }

  }

}
