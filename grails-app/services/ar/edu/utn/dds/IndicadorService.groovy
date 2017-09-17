package ar.edu.utn.dds

import ar.edu.utn.dds.model.Indicador
import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate

@Transactional
class IndicadorService {

    @Autowired
    private MongoTemplate mongoTemplate

    public void guardarIndicador(Indicador indicador) {
        mongoTemplate.save(indicador)
    }

    public Indicador getIndicador(String name) {
        return mongoTemplate.findById(name, Indicador.class)
    }
}