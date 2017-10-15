package ar.edu.utn.dds.utils.queries.builders

import ar.edu.utn.dds.exceptions.RootStatementBuilderException

import java.util.stream.Collectors

class RootStatementBuilder {

    static String buildSelectRootStatement(List<String> columns, String table) {

        if(columns == null)
            throw new RootStatementBuilderException("Se necesitan columnas para armar una declaracion raiz de select")

        return buildSelectRootStatement(
                columns.stream().collect(Collectors.joining(", ")),
                table
        )
    }

    static String buildSelectRootStatement(String columns, String table) {

        if(columns == null || columns.length() == 0 || table == null || table.length() == 0)
            throw new RootStatementBuilderException("Se necesitan columnas y una tabla para armar una declaracion raiz de select")

        StringBuilder rs = new StringBuilder()

        rs.append(" SELECT ")
        rs.append(columns)
        rs.append(" FROM ")
        rs.append(table)

        return rs
    }

    static String buildInsertRootStatement(String table) {

        if(table == null || table.length() == 0) {
            throw new RootStatementBuilderException("Se necesita una tabla para armar una declaracion raiz de insert")
        }

        StringBuilder rs = new StringBuilder()

        rs.append("INSERT INTO ")
        rs.append(table)
        rs.append(" ")

        return rs
    }

    static String buildUpdateRootStatement(String table) {

        if(table == null || table.length() == 0) {
            throw new RootStatementBuilderException("Se necesita una tabla para armar una declaracion raiz de update")
        }

        StringBuilder rs = new StringBuilder()

        rs.append(" UPDATE  ")
        rs.append(table)
        rs.append(" SET ")

        return rs
    }
}
