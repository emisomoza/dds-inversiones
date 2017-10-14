package ar.edu.utn.dds.utils.queries.builders

import ar.edu.utn.dds.exceptions.RootStatementBuilderException
import spock.lang.Specification

class RootStatementBuilderSpec extends Specification {

    void "constuir declaracion raiz select con lista de columnas"(List<String> columns, String table, String result) {
        expect:
            RootStatementBuilder.buildSelectRootStatement(columns, table) == result

        where:
            columns                                 | table     | result
            Arrays.asList("COL_1", "COL_2", "COL_3")| "TABLE_1" | " SELECT COL_1, COL_2, COL_3 FROM TABLE_1"
            Arrays.asList("COL_4", "COL_5", "COL_6")| "TABLE_2" | " SELECT COL_4, COL_5, COL_6 FROM TABLE_2"
    }

    void "constuir declaracion raiz select con string columnas"(String columns, String table, String result) {
        expect:
        RootStatementBuilder.buildSelectRootStatement(columns, table) == result

        where:
            columns              | table     | result
            "COL_1, COL_2, COL_3"| "TABLE_1" | " SELECT COL_1, COL_2, COL_3 FROM TABLE_1"
            "COL_4, COL_5, COL_6"| "TABLE_2" | " SELECT COL_4, COL_5, COL_6 FROM TABLE_2"
    }

    void "constuir declaracion raiz select excepcion"() {
        when:
            RootStatementBuilder.buildSelectRootStatement((List) null, "TEST")

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildSelectRootStatement(new ArrayList<String>(), "TEST")

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildSelectRootStatement((String) null, "TEST")

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildSelectRootStatement("", "TEST")

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildSelectRootStatement("TEST", "")

        then:
            thrown RootStatementBuilderException
    }

    void "constuir declaracion raiz insert"(String table, String result) {
        expect:
            RootStatementBuilder.buildInsertRootStatement(table) == result

        where:
            table     | result
            "TABLE_1" | " INSERT INTO TABLE_1"
            "TABLE_2" | " INSERT INTO TABLE_2"
    }

    void "constuir declaracion raiz insert excepcion"() {
        when:
            RootStatementBuilder.buildInsertRootStatement(null)

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildInsertRootStatement("")

        then:
            thrown RootStatementBuilderException
    }

    void "constuir declaracion raiz update"(String table, String result) {
        expect:
            RootStatementBuilder.buildUpdateRootStatement(table) == result

        where:
            table     | result
            "TABLE_1" | " UPDATE  TABLE_1 SET "
            "TABLE_2" | " UPDATE  TABLE_2 SET "
    }

    void "constuir declaracion raiz update excepcion"() {
        when:
            RootStatementBuilder.buildUpdateRootStatement(null)

        then:
            thrown RootStatementBuilderException

        when:
            RootStatementBuilder.buildUpdateRootStatement("")

        then:
            thrown RootStatementBuilderException
    }
}
