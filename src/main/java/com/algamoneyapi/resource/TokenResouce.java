package com.algamoneyapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algamoneyapi.config.property.AgamoneyApiProperty;

// para fazer validação com login
@RestController
@RequestMapping("/tokens")
public class TokenResouce {
	@Autowired
	private AgamoneyApiProperty agamoneyApiProperty  ;
	
	// quer dizer pegar de volta 
	// o logaut tira o refreche token
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
      Cookie cookie = new Cookie("refreshToken", null);
      cookie.setHttpOnly(true);
      cookie.setSecure(true);
      cookie.setSecure(agamoneyApiProperty.getSeguranca().isEnableHttps());  
      cookie.setPath(req.getContextPath() + "/oauth/token");
      cookie.setMaxAge(0);
      
      resp.addCookie(cookie);
      resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

	
	
}// quer dizer pegar de volta 
// o logaut tira o refreche token
/*  nao é altomatico o deplo para o servidor 
 * @DeleteMapping("/revoke") public void revoke(HttpServletRequest req,
 * HttpServletResponse resp) { Cookie cookie = new Cookie("refreshToken", null);
 * cookie.setHttpOnly(true); cookie.setSecure(true); cookie.setSecure(false); <= a qui
 * //TODO: Em produção sera true cookie.setPath(req.getContextPath() +
 * "/oauth/token"); cookie.setMaxAge(0);
 * 
 * resp.addCookie(cookie); resp.setStatus(HttpStatus.NO_CONTENT.value()); }
 */
