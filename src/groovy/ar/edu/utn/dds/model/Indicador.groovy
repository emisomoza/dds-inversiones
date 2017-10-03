package ar.edu.utn.dds.model

import ar.edu.utn.dds.antlr.IndicadorLexer
import ar.edu.utn.dds.antlr.IndicadorParser
import ar.edu.utn.dds.expresion.Expresion
import ar.edu.utn.dds.listener.IndicadorCustomListener
import ar.edu.utn.dds.visitor.IndicadorCustomVisitor
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "indicadores")
@JsonIgnoreProperties(ignoreUnknown=true)
class Indicador implements Serializable, Calculable {

    @Id
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("expresion")
    private Expresion expresion;
    @JsonProperty("dependenciasIndicador")
    private List<String> dependenciasIndicador;
    @JsonProperty("dependenciasCuenta")
    private List<String> dependenciasCuenta;

    Indicador() {
    }

    Indicador(String nombre, String expresion) {
        this.nombre = nombre;

        IndicadorLexer lexer = new IndicadorLexer(CharStreams.fromString(expresion));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        IndicadorParser parser = new IndicadorParser(tokens);
        IndicadorParser.ExpressionContext context = parser.expression();

        IndicadorCustomVisitor visitor = new IndicadorCustomVisitor();
        IndicadorCustomListener listener = new IndicadorCustomListener();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, context);

        this.expresion = visitor.visit(context);
        this.dependenciasCuenta = new ArrayList<>(listener.getDependenciasCuenta());
        this.dependenciasIndicador = new ArrayList<>(listener.getDependenciasIndicador());
    }

    @Override
    String getNombre() {
        return nombre;
    }

    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    Expresion getExpresion() {
        return expresion;
    }

    void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    List<String> getDependenciasIndicador() {
        return dependenciasIndicador;
    }

    void setDependenciasIndicador(List<String> dependenciasIndicador) {
        this.dependenciasIndicador = dependenciasIndicador;
    }

    List<String> getDependenciasCuenta() {
        return dependenciasCuenta;
    }

    void setDependenciasCuenta(List<String> dependenciasCuenta) {
        this.dependenciasCuenta = dependenciasCuenta;
    }

    Double aplicar() {
        return expresion.getValor();
    }

    @Override
    @JsonIgnore
    Double getValor() {
        return this.aplicar();
    }
}