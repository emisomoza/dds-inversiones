import ar.edu.utn.dds.permisos.evaluadores.CustomPermissionEvaluator
import ar.edu.utn.dds.utils.map.MapNormalizer
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler

// Place your Spring DSL code here
beans = {
    jdbcTemplate(JdbcTemplate) {
        dataSource = ref('dataSource')
    }

    mapNormalizer(MapNormalizer)

    //PrePostAnnotations configuration
    permissionEvaluator(CustomPermissionEvaluator)
    expressionHandler(DefaultMethodSecurityExpressionHandler) {
        parameterNameDiscoverer = ref('parameterNameDiscoverer')
        expressionParser = ref('expressionParser')
        roleHierarchy = ref('roleHierarchy')
        permissionEvaluator = ref('permissionEvaluator')
    }
}
