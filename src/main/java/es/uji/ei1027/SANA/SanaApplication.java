package es.uji.ei1027.SANA;

import es.uji.ei1027.SANA.dao.AreaRowMapper;
import es.uji.ei1027.SANA.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication{

	private static final Logger log = Logger.getLogger(SanaApplication.class.getName());

	public static void main(String[] args) {
		new SpringApplicationBuilder(SanaApplication.class).run(args);
	}

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
/*
	public void run(String... strings) throws Exception {
		log.info("Selecciona");
		Area n1 = jdbcTemplate.queryForObject(
				"SELECT * FROM Area",
				new AreaRowMapper());
		log.info(n1.toString());
	}
	*/
}
