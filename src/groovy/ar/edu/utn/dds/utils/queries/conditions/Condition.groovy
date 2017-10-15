package ar.edu.utn.dds.utils.queries.conditions

interface Condition {

    public static String DEFAULT_CONDITION_RIGHT_PREDICATE = " = ?"

    String toSqlString()
}