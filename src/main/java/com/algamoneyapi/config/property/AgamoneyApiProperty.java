package com.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

// para configura autommaticamento o servidor em produção 
@ConfigurationProperties("algamoney")
public class AgamoneyApiProperty {

	private String originPermitida = "http://localhost:8000";
	

	private final Segurancao seguranca = new Segurancao();
	
	
	
	
	public Segurancao getSeguranca() {
		return seguranca;
	}

	public String getOriginPermitida() {
		return originPermitida;
	}




	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}



	public static class Segurancao {
	private boolean enableHttps;

	public boolean isEnableHttps() {
		return enableHttps;
	}

	public void setEnableHttps(boolean enableHttps) {
		this.enableHttps = enableHttps;
	}
	
	
	
	}
}

