package ar.edu.utn.dds.utils.map

import spock.lang.Specification

class MapNormalizerSpec extends Specification {

    void "normalisar valores de mapa simple"(String value, Integer result) {
        setup:
        Map<String, Object> testMap = new HashMap<>()
        testMap.put("Key 1#", value)

        expect:
        (new MapNormalizer()).normalizeValues(testMap).size() == result

        where:
        value   | result
        null    | 0
        "key"   | 1
    }

    void "normalisar claves de mapa simple"(String str, Integer result) {
        setup:
        Map<String, Object> testMap = new HashMap<>()
        testMap.put("Key 1#", "value1")
        testMap.put("Key 2#.", "value2")
        testMap.put("Key 3", "value3")
        testMap.put("Key 4#.)", "value4")

        expect:
        (new MapNormalizer()).normalizeKeys(testMap, str).size() == result

        where:
        str | result
        "." | 2
        "#" | 1
        ")" | 3

    }

    void "normalizar claves mapa de compuesto"(String str, Integer result) {
        setup:
        Map<String, Object> testMap = new HashMap<>()
        Map<String, Object> subTestMap = new HashMap<>()
        testMap.put("subMap", subTestMap)
        subTestMap.put("Key 1#", "value1")
        subTestMap.put("Key 2#.", "value2")
        subTestMap.put("Key 3", "value3")
        subTestMap.put("Key 4#.)", "value4")

        expect:
        ((Map) (new MapNormalizer()).normalizeKeys(testMap, str).get("subMap")).size() == result

        where:
        str | result
        "." | 2
        "#" | 1
        ")" | 3
    }
}
