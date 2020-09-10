package co.com.educacion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.transferencia.PersonaTO;

@SpringBootTest
class EducacionApplicationTests {

	@Autowired
	PersonaServicio personaServicio;

	@Test
	void deletePersona() {
		PersonaTO p = new PersonaTO();
		boolean elimino = personaServicio.eliminar(p);
		assertThat(elimino).isEqualTo(true);
	}

	@Test
	void contextLoads() {
	}

}
