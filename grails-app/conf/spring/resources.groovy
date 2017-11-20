import ar.edu.utn.dds.importador.ImportadorCuentas
import ar.edu.utn.dds.mappers.empresa.EmpresaIdMapper
import ar.edu.utn.dds.permisos.evaluadores.CustomPermissionEvaluator
import ar.edu.utn.dds.utils.map.MapNormalizer
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler

// Place your Spring DSL code here
beans = {

    jdbcTemplate(JdbcTemplate) {
        dataSource = ref('dataSource')
    }

    importadorCuentas(ImportadorCuentas) {
        empresaService = ref('empresaService')
        periodoService = ref('periodoService')
        tipoCuentaService = ref('tipoCuentaService')
        cuentaService = ref('cuentaService')
    }

    //Mappers
    mapNormalizer(MapNormalizer)
    empIdMapper(EmpresaIdMapper)

    /*
     * Eso es necesario porque el pluggin de ACL por default espera que usemos la
     * manera de crear objetos de dominio de Grails y el GORM. Como no estamos usando
     * ninguno de los dos trae problemas el manejo de cache con EhCache y el
     * PermissionEvaluator. Para solucionarlo estoy redefiniendo el bean pisando el
     * PermissionEvaluator por uno Custom y dejando el cache en null (por default).
     * A futuro deberiamos tener nuestra propia impl, actualmente no da el tiempo.
     */
    //PrePostAnnotations configuration
    permissionEvaluator(CustomPermissionEvaluator)
    expressionHandler(DefaultMethodSecurityExpressionHandler) {
        parameterNameDiscoverer = ref('parameterNameDiscoverer')
        expressionParser = ref('expressionParser')
        roleHierarchy = ref('roleHierarchy')
        permissionEvaluator = ref('permissionEvaluator')
    }
}
