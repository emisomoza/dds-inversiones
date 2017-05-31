package ar.edu.utn.dds.utilidades.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andres on 29/05/17.
 */
public class ObjectMapper<K, V> {

    public Map<K, List<V>> map(List<Mapeable<K, V>> listaMapeable) {
        Map<K, List<V>> map = new HashMap<>();

        listaMapeable.forEach(mapeable -> {
            List<V> lista = map.get(mapeable.getKey());
            if(lista == null) {
                lista = new ArrayList<>();
                map.put(mapeable.getKey(), lista);
            }
            lista.add(mapeable.getValue());
        });

        return map;
    }
}
