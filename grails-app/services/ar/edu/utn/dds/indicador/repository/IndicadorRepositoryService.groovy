package ar.edu.utn.dds.indicador.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.IndicadorExistenteException
import ar.edu.utn.dds.exceptions.MongoInaccesibleException
import ar.edu.utn.dds.model.Indicador
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery

@Transactional
class IndicadorRepositoryService {

    @Autowired
    private MongoTemplate mongoTemplate

    @CacheEvict(cacheNames = CacheData.INDICADOR_CACHE_NAME, key = "#indicador.owner", cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Indicador indicador) {
        try {
            mongoTemplate.save(indicador)
        } catch(DuplicateKeyException e) {
            throw new IndicadorExistenteException(String.format("Ya existe un indicador con nombre %s", indicador.getNombre()), e.getCause())
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al guardar indicador " + indicador.getNombre(), e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.INDICADOR_CACHE_NAME, key = "#nombre.concat('-').concat(#userId)", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Indicador obtener(String nombre, Long userId) {
        BasicQuery query = new BasicQuery("{nombre: '" + nombre + "', owner: " + userId + "}")
        try {
            return mongoTemplate.findOne(query, Indicador.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener indicador " + nombre, e.getCause())
        }
    }

    @Cacheable(value = CacheData.INDICADOR_CACHE_NAME, key = "#userId", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    ArrayList<Indicador> listar(Long userId) {
        BasicQuery query = new BasicQuery("{owner: " + userId + "}")
        try {
            return mongoTemplate.find(query, Indicador.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener todos los indicadores", e.getCause())
        }
    }
}
