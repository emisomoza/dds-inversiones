package ar.edu.utn.dds.utils.queries

import spock.lang.Specification

class QueryUtilsSpec extends Specification {

    QueryUtils queryUtils

    def setup() {
        queryUtils = new QueryUtils()
    }

    void "sql query root statement"(String rootStatement) {
        setup:
            queryUtils.setRootStatement(rootStatement)

        expect:
            queryUtils.getSqlQuery() == rootStatement

        where:
            rootStatement   | _
            "ROOT_STATEMENT"| _
            "SARASA"        | _
    }

    void "sql query params"(String p1, String v1, String p2, String v2, String result) {
        setup:
            queryUtils.setRootStatement("")
            queryUtils.addParam(p1, v1)
            queryUtils.addParam(p2, v2)

        expect:
            queryUtils.getSqlQuery() == result

        where:
            p1          | v1                | p2        | v2                | result
            "PARAM_1"   | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | "PARAM_1 = ?, PARAM_2 = ?"
            "PARAM_1"   | null              | "PARAM_2" | "PARAM_2_VALUE"   | "PARAM_2 = ?"
            null        | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | "PARAM_2 = ?"
            null        | null              | "PARAM_2" | "PARAM_2_VALUE"   | "PARAM_2 = ?"
            null        | null              | null      | "PARAM_2_VALUE"   | ""
    }

    void "sql query where params"(String p1, String v1, String p2, String v2, String result) {
        setup:
        queryUtils.setRootStatement("")
        queryUtils.addWhereParam(p1, v1)
        queryUtils.addWhereParam(p2, v2)

        expect:
        queryUtils.getSqlQuery() == result

        where:
        p1          | v1                | p2        | v2                | result
        "PARAM_1"   | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | " WHERE ( PARAM_1  = ? )  AND  ( PARAM_2  = ? ) "
        "PARAM_1"   | null              | "PARAM_2" | "PARAM_2_VALUE"   | " WHERE ( PARAM_2  = ? ) "
        null        | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | " WHERE ( PARAM_2  = ? ) "
        null        | null              | "PARAM_2" | "PARAM_2_VALUE"   | " WHERE ( PARAM_2  = ? ) "
        null        | null              | null      | "PARAM_2_VALUE"   | ""
    }

    void "sql query params arguments"(String p1, String v1, String p2, String v2, List<Object> result) {
        setup:
        queryUtils.setRootStatement("")
        queryUtils.addParam(p1, v1)
        queryUtils.addWhereParam(p2, v2)

        expect:
        queryUtils.getSqlArguments() == result

        where:
            p1          | v1                | p2        | v2                | result
            "PARAM_1"   | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | Arrays.asList("PARAM_1_VALUE", "PARAM_2_VALUE")
            "PARAM_1"   | null              | "PARAM_2" | "PARAM_2_VALUE"   | Arrays.asList("PARAM_2_VALUE")
            null        | "PARAM_1_VALUE"   | "PARAM_2" | "PARAM_2_VALUE"   | Arrays.asList("PARAM_2_VALUE")
            null        | null              | "PARAM_2" | "PARAM_2_VALUE"   | Arrays.asList("PARAM_2_VALUE")
            null        | null              | null      | "PARAM_2_VALUE"   | new ArrayList<>()
    }

}
