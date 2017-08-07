import org.springframework.jdbc.core.JdbcTemplate

// Place your Spring DSL code here
beans = {
    jdbcTemplate(JdbcTemplate) {
        dataSource = ref('dataSource')
    }
}
