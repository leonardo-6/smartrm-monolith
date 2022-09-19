package com.lsl.smartrm.smartrmmonolith.payment.adapter.api.controller;

import com.lsl.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import com.lsl.smartrm.smartrmmonolith.payment.application.dto.ChargeCommandDto;
import com.lsl.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.lsl.smartrm.smartrmmonolith.payment.application.dto.StartQrCodePayCommandDto;
import com.lsl.smartrm.smartrmmonolith.payment.application.service.PayService;
import com.lsl.smartrm.smartrmmonolith.payment.domain.AccountInfo;
import com.lsl.smartrm.smartrmmonolith.payment.domain.OrderInfo;
import com.lsl.smartrm.smartrmmonolith.trade.domain.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/pay")
public class PaymentController {

  @Autowired
  PayService payService;

  @RequestMapping(value = "/startQrcodePay", method = RequestMethod.POST)
  @ResponseBody
  public CommonResponse<PaymentQrCodeDto> startQrCodePayForOrder(
      @RequestBody StartQrCodePayCommandDto request) {
    OrderInfo orderInfo = OrderInfo.Builder().orderId(request.getOrderId())
        .machineId(request.getMachineId())
        .type(OrderType.SlotQrScanePaid)
        .totalAmount(request.getTotalAmount()).build();
    return CommonResponse.success(
        payService.startQrCodePayForOrder(request.getPlatformType(), orderInfo));
  }

  @RequestMapping(value = "/chargeForOrder", method = RequestMethod.POST)
  @ResponseBody
  public CommonResponse chargeForOrder(@RequestBody ChargeCommandDto request) {
    OrderInfo orderInfo = OrderInfo.Builder().orderId(request.getOrderId())
        .machineId(request.getMachineId())
        .type(OrderType.CabinetAutoDeduction)
        .totalAmount(request.getTotalAmount()).build();
    AccountInfo accountInfo = new AccountInfo(request.getAccountId(), request.getContractId());
    payService.chargeForOrder(orderInfo, accountInfo);
    return CommonResponse.success();
  }

}
