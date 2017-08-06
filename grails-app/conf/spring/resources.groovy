// Place your Spring DSL code here
beans = {
    jdbcTemplate(JdbcTemplate) {
        dataSource = ref('dataSource')
    }
}
