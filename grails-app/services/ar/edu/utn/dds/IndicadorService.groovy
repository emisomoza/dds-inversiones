package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate

@Transactional
class IndicadorService {

    @Autowired
    private MongoTemplate mongoTemplate

    public Indicador insertIndicador(String name) {
        return mongoTemplate.save(name, "test")
    }

    public Indicador findIndicadorByName(String name) {
        return mongoTemplate.findById(name, Indicador.class)
    }

    public Indicador findIndicadorByNameWithGorm(String name) {
        return Indicador.get(name)
    }
}
