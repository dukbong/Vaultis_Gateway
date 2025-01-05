package com.vaultis.vaultis_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class VaultisGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultisGatewayApplication.class, args);
	}

}
