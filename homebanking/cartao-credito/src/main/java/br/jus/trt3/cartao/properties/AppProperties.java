package br.jus.trt3.cartao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppProperties {
	private String hostnameGateway;

	public String getHostnameGateway() {
		return hostnameGateway;
	}

	public void setHostnameGateway(String hostnameGateway) {
		this.hostnameGateway = hostnameGateway;
	}

	
}
