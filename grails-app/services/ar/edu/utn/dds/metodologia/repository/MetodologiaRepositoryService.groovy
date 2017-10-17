package ar.edu.utn.dds.metodologia.repository

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.exceptions.MongoInaccesibleException
import ar.edu.utn.dds.model.Metodologia
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.core.MongoTemplate

@Transactional
class MetodologiaRepositoryService {
    @Autowired
    private MongoTemplate mongoTemplate

    @CacheEvict(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    void guardar(Metodologia metodologia) {
        try {
            mongoTemplate.save(metodologia)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al guardar metodologia " + metodologia.getNombre(), e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    Metodologia obtener(String name) {
        try {
            return mongoTemplate.findById(name, Metodologia.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener metodologia " + name, e.getCause())
        }
    }

    @Cacheable(cacheNames = CacheData.METODOLOGIA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    ArrayList<Metodologia> listar() {
        try {
            return mongoTemplate.findAll(Metodologia.class)
        } catch (Exception e) {
            throw new MongoInaccesibleException("Error al obtener todas las metodologias", e.getCause())
        }
    }
}
