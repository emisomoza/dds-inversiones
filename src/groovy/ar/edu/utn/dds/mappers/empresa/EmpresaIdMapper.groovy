package ar.edu.utn.dds.mappers.empresa

import java.util.stream.Collectors

class EmpresaIdMapper {

    protected static final String EMPRESAS = "empresas"
    protected static final String ID = "empresa"

    List<Long> mapear(Map<String, Object> empsMap) {
        return ((Map<String, Object>)empsMap.get(EMPRESAS)).values().stream()
            .map({empMap -> Long.valueOf((String) ((Map<String, Object>) empMap).get(ID))})
            .collect(Collectors.toList())
    }
}
