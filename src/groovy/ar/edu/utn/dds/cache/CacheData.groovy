package ar.edu.utn.dds.cache

class CacheData {
    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";

    public static final String DEFAULT_CACHE_NAME = "default";
    public static final String EMPRESA_CACHE_NAME = "empresa";
    public static final String PERIODO_CACHE_NAME = "periodo";
    public static final String PERIODO_EMPRESA_CACHE_NAME = "periodo_empresa";
    public static final String CUENTA_CACHE_NAME = "cuenta";
    public static final String INDICADOR_CACHE_NAME = "indicador";
    public static final String METODOLOGIA_CACHE_NAME = "metodologia";

    public static final List<String> getAllCacheNames() {
        return [DEFAULT_CACHE_NAME, EMPRESA_CACHE_NAME, PERIODO_CACHE_NAME, PERIODO_EMPRESA_CACHE_NAME, CUENTA_CACHE_NAME, INDICADOR_CACHE_NAME, METODOLOGIA_CACHE_NAME]
    }
}
