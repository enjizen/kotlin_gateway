package com.wanchalerm.tua.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class KotlinGatewayApplication

fun main(args: Array<String>) {
	runApplication<KotlinGatewayApplication>(*args)
}
