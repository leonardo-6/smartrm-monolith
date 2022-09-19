package com.lsl.smartrm.smartrmmonolith.trade.adapter.remote;

import com.lsl.smartrm.smartrmmonolith.trade.domain.remote.TradeUserService;
import com.lsl.smartrm.smartrmmonolith.trade.domain.remote.UserInfo;
import com.lsl.smartrm.smartrmmonolith.user.application.dto.UserInfoDto;
import com.lsl.smartrm.smartrmmonolith.user.application.dto.UserInfoQueryDto;
import com.lsl.smartrm.smartrmmonolith.user.application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class MonolithTradeUserServiceImpl implements TradeUserService {

  @Autowired
  AppUserService userService;

  @Override
  public UserInfo getUserInfo(String openId) {
    UserInfoQueryDto query = new UserInfoQueryDto();
    query.setOpenId(openId);
    UserInfoDto dto = userService.getUserInfo(query);
    return UserInfo.Builder().accountId(dto.getAccountId())
        .contractId(dto.getContractId())
        .wxOpenId(dto.getWxOpenId())
        .wxUnionId(dto.getWxUnionId()).build();
  }
}
