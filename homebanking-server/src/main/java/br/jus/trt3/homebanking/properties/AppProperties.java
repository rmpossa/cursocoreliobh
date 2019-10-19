package br.jus.trt3.homebanking.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppProperties {
	private String hostnameCoaf;

	public String getHostnameCoaf() {
		return hostnameCoaf;
	}

	public void setHostnameCoaf(String hostnameCoaf) {
		this.hostnameCoaf = hostnameCoaf;
	}

	
	
}
