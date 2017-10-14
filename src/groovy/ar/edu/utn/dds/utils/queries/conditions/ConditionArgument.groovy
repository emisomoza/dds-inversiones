package ar.edu.utn.dds.utils.queries.conditions

import ar.edu.utn.dds.utils.queries.operations.Operator

class ConditionArgument implements Condition {

    private String argument
    private String condition

    ConditionArgument(String argument, String condition){
        this.argument = argument
        this.condition = condition
    }

    ConditionArgument(String rightPredicate, Operator operation, String leftPredicate ){
        this.argument = rightPredicate
        this.condition = operation.toSqlString() + "'"+leftPredicate+"'"
    }

    String getArgument() {
        return argument
    }

    void setArgument(String argument) {
        this.argument = argument
    }

    String getCondition() {
        return condition
    }

    void setCondition(String condition) {
        this.condition = condition
    }

    @Override
    String toSqlString() {
        return " ( "+this.argument+" "+this.condition+" ) "
    }
}
