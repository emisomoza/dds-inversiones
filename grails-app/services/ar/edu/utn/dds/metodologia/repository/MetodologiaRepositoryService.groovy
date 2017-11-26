package ar.edu.utn.dds.metodologia.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.MetodologiaExistenteException
import ar.edu.utn.dds.exceptions.MongoInaccesibleException
import ar.edu.utn.dds.model.Metodologia
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.BasicQuery

@Transactional
class MetodologiaRepositoryService {
    @Autowired
    private MongoTemplate mongoTemplate

    @CacheEvict(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Metodologia metodologia) {
        try {
            mongoTemplate.save(metodologia)
        } catch(DuplicateKeyException e) {
            throw new MetodologiaExistenteException(String.format("Ya existe una metodologia con nombre %s", metodologia.getNombre()), e.getCause())
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al guardar metodologia " + metodologia.getNombre(), e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, key = "#nombre.concat('-').concat(#userId)", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Metodologia obtener(String nombre, Long userId) {
        BasicQuery query = new BasicQuery("{nombre: '" + nombre + "', owner: " + userId + "}")
        try {
            return mongoTemplate.findOne(query, Metodologia.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener metodologia " + nombre, e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, key = "#userId", cacheManager = CacheData.REDIS_CACHE_MANAGER)
    ArrayList<Metodologia> listar(Long userId) {
        BasicQuery query = new BasicQuery("{\$or: [{owner: " + userId + "}, {visibilidad: {\$ne: 'ROLE_NULL'}}]}")
        try {
            return mongoTemplate.find(query, Metodologia.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener todas las metodologias", e.getCause())
        }
    }
}
