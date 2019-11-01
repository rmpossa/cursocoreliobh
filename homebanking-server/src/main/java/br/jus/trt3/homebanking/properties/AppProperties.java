package br.jus.trt3.homebanking.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppProperties {
	private String hostnameGateway;
	private Boolean withPort;

	public String getHostnameGateway() {
		return hostnameGateway;
	}

	public void setHostnameGateway(String hostnameGateway) {
		this.hostnameGateway = hostnameGateway;
	}

	public Boolean getWithPort() {
		return withPort;
	}

	public void setWithPort(Boolean withPort) {
		this.withPort = withPort;
	}

	
	
}
