package ar.edu.utn.dds.utils.map

import java.util.concurrent.ConcurrentHashMap

class MapNormalizer {

    def normalize(Map<String, Object> map, String str) {
        return this.normalizeKeys(this.normalizeValues(map), str)
    }

    def normalizeValues(Map<String, Object> map) {
        Map<String, Object> normalizedMap = new ConcurrentHashMap<>()

        map.keySet().parallelStream()
                .forEach({k ->
            Object result
            Object value = map.get(k)

            if(value != null) {
                if (value instanceof Map)
                    result = this.normalizeValues(value)
                else
                    result = value

                normalizedMap.put(k, result)
            }
        })

        return normalizedMap
    }

    def normalizeKeys(Map<String, Object> map, String str) {
        Map<String, Object> normalizedMap = new ConcurrentHashMap<>()

        map.keySet().parallelStream()
                .filter({k -> k.indexOf(str) == -1})
                .forEach({k ->
                    Object result
                    Object value = map.get(k)

                    if(value instanceof Map)
                        result = this.normalizeKeys(value, str)
                    else
                        result = value

                    normalizedMap.put(k, result)
                })

        return normalizedMap
    }
}
