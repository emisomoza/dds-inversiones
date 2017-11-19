package ar.edu.utn.dds.model

import ar.edu.utn.dds.antlr.IndicadorLexer
import ar.edu.utn.dds.antlr.IndicadorParser
import ar.edu.utn.dds.expresion.Expresion
import ar.edu.utn.dds.listener.IndicadorCustomListener
import ar.edu.utn.dds.visitor.IndicadorCustomVisitor
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "indicadores")
@CompoundIndexes([
        @CompoundIndex(name = "id_idx", def = "{'nombre': 1, 'owner': -1}", unique = true)
])
@JsonIgnoreProperties(ignoreUnknown=true)
class Indicador implements Serializable {

    @JsonProperty("nombre")
    private String nombre
    @JsonProperty("owner")
    private Long owner
    @JsonProperty("visibilidad")
    private String visibilidad
    @JsonProperty("expresion")
    private Expresion expresion
    @JsonProperty("dependenciasIndicador")
    private List<String> dependenciasIndicador
    @JsonProperty("dependenciasCuenta")
    private List<String> dependenciasCuenta

    Indicador() {
    }

    Indicador(String nombre, String expresion, String visibilidad) {
        this.nombre = nombre
        this.visibilidad = visibilidad

        IndicadorLexer lexer = new IndicadorLexer(CharStreams.fromString(expresion))
        CommonTokenStream tokens = new CommonTokenStream(lexer)
        IndicadorParser parser = new IndicadorParser(tokens)
        IndicadorParser.ExpressionContext context = parser.expression()

        IndicadorCustomVisitor visitor = new IndicadorCustomVisitor()
        IndicadorCustomListener listener = new IndicadorCustomListener()

        ParseTreeWalker walker = new ParseTreeWalker()
        walker.walk(listener, context)

        this.expresion = visitor.visit(context)
        this.dependenciasCuenta = new ArrayList<>(listener.getDependenciasCuenta())
        this.dependenciasIndicador = new ArrayList<>(listener.getDependenciasIndicador())
    }

    String getNombre() {
        return nombre
    }

    void setNombre(String nombre) {
        this.nombre = nombre
    }

    Long getOwner() {
        return owner
    }

    void setOwner(Long owner) {
        this.owner = owner
    }

    String getVisibilidad() {
        return visibilidad
    }

    void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad
    }

    Expresion getExpresion() {
        return expresion
    }

    void setExpresion(Expresion expresion) {
        this.expresion = expresion
    }

    List<String> getDependenciasIndicador() {
        return dependenciasIndicador
    }

    void setDependenciasIndicador(List<String> dependenciasIndicador) {
        this.dependenciasIndicador = dependenciasIndicador
    }

    List<String> getDependenciasCuenta() {
        return dependenciasCuenta
    }

    void setDependenciasCuenta(List<String> dependenciasCuenta) {
        this.dependenciasCuenta = dependenciasCuenta
    }
}