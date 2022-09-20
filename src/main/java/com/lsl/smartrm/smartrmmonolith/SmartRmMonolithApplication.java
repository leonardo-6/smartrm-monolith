package com.lsl.smartrm.smartrmmonolith;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


/**
* boot的启动类
* @Description: 启动类
* @Author: LSL
* @Date: 2022/9/20 10:04
* @ProjectName: com.lsl.smartrm.smartrmmonolith
* @FileName: SmartRmMonolithApplication.java
*/
@SpringBootApplication(scanBasePackages = "com.lsl.smartrm.smartrmmonolith")
@MapperScan({"com.lsl.smartrm.smartrmmonolith.device.infrastructure.mapper",
		"com.lsl.smartrm.smartrmmonolith.user.infrastructure.mapper",
		"com.lsl.smartrm.smartrmmonolith.payment.infrastructure.mapper",
		"com.lsl.smartrm.smartrmmonolith.trade.infrastructure.mapper",
		"com.lsl.smartrm.smartrmmonolith.infracore.idgenerator.impl.mapper",
		"com.lsl.smartrm.smartrmmonolith.operation.infrastructure.mapper"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartRmMonolithApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartRmMonolithApplication.class, args);
	}

}
