package ar.edu.utn.dds.indicador.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.MongoInaccesibleException
import ar.edu.utn.dds.model.Indicador
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery

@Transactional
class IndicadorRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate

    @CacheEvict(cacheNames = CacheData.INDICADOR_CACHE_NAME, key = "#indicador.owner", cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardarIndicador(Indicador indicador) {
        try {
            mongoTemplate.save(indicador)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al guardar indicador " + indicador.getNombre(), e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.INDICADOR_CACHE_NAME, key = "#nombre.concat('-').concat(#userId)", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Indicador obtenerIndicador(String nombre, Long userId) {
        BasicQuery query = new BasicQuery("{nombre: '" + nombre + "', owner: " + userId + "}")
        try {
            return mongoTemplate.findOne(query, Indicador.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener indicador " + nombre, e.getCause())
        }
    }

    @Cacheable(value = CacheData.INDICADOR_CACHE_NAME, key = "#userId", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    ArrayList<Indicador> listarTodo(Long userId) {
        BasicQuery query = new BasicQuery("{owner: " + userId + "}")
        try {
            return mongoTemplate.find(query, Indicador.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener todos los indicadores", e.getCause())
        }
    }
}
