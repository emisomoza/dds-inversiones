package ar.edu.utn.dds.indicador.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.MongoInaccesibleException
import ar.edu.utn.dds.model.Indicador
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.core.MongoTemplate

@Transactional
class IndicadorRepositoryService {
    @Autowired
    private MongoTemplate mongoTemplate

    @CacheEvict(cacheNames = CacheData.INDICADOR_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardarIndicador(Indicador indicador) {
        try {
            mongoTemplate.save(indicador)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al guardar indicador " + indicador.getNombre(), e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.INDICADOR_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Indicador obtenerIndicador(String name) {
        try {
            return mongoTemplate.findById(name, Indicador.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener indicador " + name, e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.INDICADOR_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    ArrayList<Indicador> listar() {
        try {
            ArrayList<Indicador> indicadores = []
            indicadores = mongoTemplate.findAll(Indicador.class)
            return indicadores
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener todos los indicadores", e.getCause())
        }
    }
}
