package co.com.educacion.manejador;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class PrefijoSecuenciaIdGenerador extends SequenceStyleGenerator {

	public PrefijoSecuenciaIdGenerador() {
	}

	public static final String PREFIJO_PARAMETRO = "valuePrefix";
	public static final String PREFIJO_DEFAULT = "";
	private String prefijo;

	public static final String FORMATO_PARAMETRO = "numberFormat";
	public static final String FORMATO_DEFAULT = "%d";

	private String formato;

	@Override
	public Serializable generate( SharedSessionContractImplementor session, Object object) throws HibernateException {
		return prefijo + String.format(formato, super.generate(session, object));
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure( LongType.INSTANCE, params, serviceRegistry);
		prefijo = ConfigurationHelper.getString(PREFIJO_PARAMETRO, params, PREFIJO_DEFAULT);
		formato = ConfigurationHelper.getString(FORMATO_PARAMETRO, params, FORMATO_DEFAULT);
	}

}
