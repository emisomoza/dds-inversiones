package ar.edu.utn.dds.utils.queries.operations

enum Operator {

    GT ( " > " ),
    GTE ( " >= " ),
    LT ( " < " ),
    LTE ( " <= " ),
    EQ ( " = " ),
    LIKE ( " LIKE " ),
    IN ( " IN " ),
    BETWEEN ( " BETWEEN " ),
    IS ( " IS " ),
    IS_NOT ( " IS NOT " ),
    IS_NULL (" IS NULL ")

    private String sqlString

    Operator(String sql){
        this.sqlString = sql
    }

    String toSqlString(){
        return this.sqlString.toUpperCase()
    }
}