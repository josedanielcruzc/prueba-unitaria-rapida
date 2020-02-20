package co.com.educacion.manejador.configuracion;

import org.springframework.security.oauth2.provider.authentication.TokenExtractor;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class ExtractorTokensPersonalizado implements TokenExtractor  {
	

	@Override
	public Authentication extract(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaders("Authorization");
		
		while (headers.hasMoreElements()) { 
			//normalmente solo hay uno (la mayoría de los servidores hacen cumplir)
			String value = headers.nextElement();
			
			if ( value.toLowerCase().startsWith( OAuth2AccessToken.BEARER_TYPE.toLowerCase() ) ) {
				String authHeaderValue = value.substring( OAuth2AccessToken.BEARER_TYPE.length() ).trim();
				
				// Agregue esto aquí para los detalles de autenticación más adelante. Sería mejor cambiar la firma de este método.
				
				request.setAttribute( OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
						value.substring( 0, OAuth2AccessToken.BEARER_TYPE.length() ).trim() );
				int commaIndex = authHeaderValue.indexOf(',');
				if (commaIndex > 0) {
					authHeaderValue = authHeaderValue.substring(0, commaIndex);
				}
				return new PreAuthenticatedAuthenticationToken( authHeaderValue, "" );
			}//FIN IF BEARER
			
		}

		return null;
	}

}
