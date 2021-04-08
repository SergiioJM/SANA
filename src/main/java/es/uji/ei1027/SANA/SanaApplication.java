package es.uji.ei1027.SANA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.logging.Logger;

@SpringBootApplication
public class SanaApplication{

	public static void main(String[] args) {
		new SpringApplicationBuilder(SanaApplication.class).run(args);
	}

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
