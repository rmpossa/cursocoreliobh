package br.jus.trt3.cartao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppProperties {
	private String hostnameHomeBanking;

	public String getHostnameHomeBanking() {
		return hostnameHomeBanking;
	}

	public void setHostnameHomeBanking(String hostnameHomeBanking) {
		this.hostnameHomeBanking = hostnameHomeBanking;
	}

		
	
}
