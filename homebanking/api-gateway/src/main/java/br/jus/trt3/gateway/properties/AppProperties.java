package br.jus.trt3.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="app")
public class AppProperties {
	private String hostnameHomeBanking;
	private String hostnameCartaoCredito;
	private String hostnameCoaf;

	public String getHostnameHomeBanking() {
		return hostnameHomeBanking;
	}

	public void setHostnameHomeBanking(String hostnameHomeBanking) {
		this.hostnameHomeBanking = hostnameHomeBanking;
	}

	public String getHostnameCartaoCredito() {
		return hostnameCartaoCredito;
	}

	public void setHostnameCartaoCredito(String hostnameCartaoCredito) {
		this.hostnameCartaoCredito = hostnameCartaoCredito;
	}

	public String getHostnameCoaf() {
		return hostnameCoaf;
	}

	public void setHostnameCoaf(String hostnameCoaf) {
		this.hostnameCoaf = hostnameCoaf;
	}
}
