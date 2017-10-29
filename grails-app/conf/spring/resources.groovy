import ar.edu.utn.dds.utils.map.MapNormalizer
import org.springframework.jdbc.core.JdbcTemplate

// Place your Spring DSL code here
beans = {
    jdbcTemplate(JdbcTemplate) {
        dataSource = ref('dataSource')
    }

    mapNormalizer(MapNormalizer) {
    }
}
