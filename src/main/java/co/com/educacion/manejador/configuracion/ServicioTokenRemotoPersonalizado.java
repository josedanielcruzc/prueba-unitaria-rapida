package co.com.educacion.manejador.configuracion;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import co.com.educacion.negocio.rest.PersonaRest;

public class ServicioTokenRemotoPersonalizado implements ResourceServerTokenServices {

	Logger logger = LoggerFactory.getLogger(ServicioTokenRemotoPersonalizado.class);

	private RestOperations restTemplate;

	private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();

	@Autowired
	public ServicioTokenRemotoPersonalizado() {
		restTemplate = new RestTemplate();
		((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			// Ignore 400
			public void handleError(ClientHttpResponse response) throws IOException {
				if (response.getRawStatusCode() != 400) {
					super.handleError(response);
				}
			}
		});
	}

	@Override
	public OAuth2Authentication loadAuthentication(String accessToken)
			throws AuthenticationException, InvalidTokenException {
		HttpHeaders headers = new HttpHeaders();
		Map<String, Object> map = executeGet("http://localhost:8081/oauth/check_token?token=" + accessToken, headers);

		logger.error("daniel ");
		logger.error("mapa : " + (map != null ? (map.isEmpty() ? "vacio" : "no vacio") : "NULO"));

		if (map != null) {
			for (Map.Entry<String, Object> pair : map.entrySet()) {
				logger.error("maps : " + pair.getKey() + ", " + pair.getValue());
			}
		}

		String error = "Token not allowed";
		if (map == null || map.isEmpty() || map.get("error") != null) {			
			error = map.get("error") != null ? (String) map.get("error") : "Token not allowed";
		}else {
			throw new InvalidTokenException(error);
			
		}
		return tokenConverter.extractAuthentication(map);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String accessToken) {
		throw new UnsupportedOperationException("Not supported: read access token");
	}

	private Map<String, Object> executeGet(String path, HttpHeaders headers) {
		try {
			if (headers.getContentType() == null) {
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			}
			@SuppressWarnings("rawtypes")
			Map map = restTemplate.exchange(path, HttpMethod.GET,
					new HttpEntity<MultiValueMap<String, String>>(null, headers), Map.class).getBody();
			@SuppressWarnings("unchecked")
			Map<String, Object> result = map;

			return result;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
