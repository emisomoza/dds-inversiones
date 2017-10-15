package ar.edu.utn.dds.utils.queries

import ar.edu.utn.dds.utils.queries.conditions.Condition
import ar.edu.utn.dds.utils.queries.conditions.ConditionArgument

import java.util.stream.Collectors

class QueryUtils {

    private String rootStatement
    private List<String> params = new ArrayList<>()
    private List<Object> arguments = new ArrayList<>()
    private List<Condition> whereConditions = new ArrayList<>()
    private List<Object> whereArguments = new ArrayList<>()

    void setRootStatement(String rootStatement) {
        this.rootStatement = rootStatement
    }

    String getRootStatement() {
        return rootStatement
    }

    List<String> getParams() {
        return params
    }

    void setParams(List<String> params) {
        this.params = params
    }

    private static Boolean isValid(String key, Object value) {
        return isValid(key) && isValid(value)
    }

    private static Boolean isValid(Object value) {
        return Optional.ofNullable(value).isPresent()
    }

    void addParam(String key, Object value){
        if(isValid(key,value)) {
            this.params.add(key)
            this.arguments.add(value)
        }
    }

    void addParams(Map<String, Object> kvParams) {
        kvParams.each {k, v -> this.addParam(k, v)}
    }

    private void addWhereCondition(Condition condition, Object... values){
        this.whereConditions.add(condition)
        whereArguments.addAll(values)
    }

    void addWhereParam(String key, Object... values) {
        if(isValid(key, values))
            this.addWhereCondition(new ConditionArgument(key, Condition.DEFAULT_CONDITION_RIGHT_PREDICATE), values)
    }

    void addWhereParam(String key, Condition condition, Object... values) {
        if(isValid(key, values))
            this.addWhereCondition(condition, values)
    }

    void addWhereParam(Map<String, Object> whereParams) {
        whereParams.each {k, v -> this.addWhereParam(k, v)}
    }

    private String buildParams() {
        return this.params
            .collect {p -> p + " = ?"}
            .stream()
            .collect(Collectors.joining(", "))
    }

    private String buildWhereStatement() {
        StringBuilder whereStatement = new StringBuilder()

        if(whereArguments.size() > 0) {
            whereStatement.append(" WHERE")
            whereStatement.append(this.buildWhereParams())
        }

        return whereStatement.toString()
    }

    private String buildWhereParams() {
        return this.whereConditions
                .collect {wc -> wc.toSqlString()}
                .stream().collect(Collectors.joining(" AND "))

    }

    String getSqlQuery() {
        StringBuilder query = new StringBuilder()

        query.append(this.rootStatement)
        query.append(this.buildParams())
        query.append(this.buildWhereStatement())

        return query.toString()
    }

    List<Object> getSqlArguments() {
        List<Object> sqlArguments = new ArrayList<>()
        sqlArguments.addAll(arguments)
        sqlArguments.addAll(whereArguments)
        return sqlArguments
    }
}
