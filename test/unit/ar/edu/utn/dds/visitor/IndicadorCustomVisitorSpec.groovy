package ar.edu.utn.dds.visitor

import ar.edu.utn.dds.antlr.IndicadorLexer
import ar.edu.utn.dds.antlr.IndicadorParser
import ar.edu.utn.dds.expresion.Expresion
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import spock.lang.Specification

class IndicadorCustomVisitorSpec extends Specification {
    def "Test gramatica"() {
        expect:
        IndicadorLexer lexer = new IndicadorLexer(CharStreams.fromString(ecuacion))
        CommonTokenStream tokens = new CommonTokenStream( lexer )
        IndicadorParser parser = new IndicadorParser( tokens )
        IndicadorParser.ExpressionContext context = parser.expression()
        IndicadorCustomVisitor visitor = new IndicadorCustomVisitor()
        Expresion expression = visitor.visit(context)
        expression.getValor() == resultado

        where:
        ecuacion                       | resultado
        "root(3, 8)"                   | 2D
        "2*3+4*5"                      | 26D
        "sqrt(2^3*8)"                  | 8D
        "root(2, 2+2^4-(-4)+3)"        | 5D
        "-1-1-1"                       | -3D
        "1+3*-4"                       | -11D
        "2^2^3"                        | 256D
        "1+-2^2^3/256"                 | 0D
    }
}
