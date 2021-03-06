package ar.edu.utn.dds.model

import ar.edu.utn.dds.antlr.IndicadorLexer
import ar.edu.utn.dds.antlr.IndicadorParser
import ar.edu.utn.dds.exceptions.IndicadorInvalidoException
import ar.edu.utn.dds.expresion.Expresion
import ar.edu.utn.dds.listener.IndicadorCustomListener
import ar.edu.utn.dds.visitor.IndicadorCustomVisitor
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.antlr.v4.runtime.BailErrorStrategy
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.tree.ParseTreeWalker
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
    @JsonProperty("expresionString")
    private String expresionString
    @JsonProperty("dependenciasIndicador")
    private List<String> dependenciasIndicador
    @JsonProperty("dependenciasCuenta")
    private List<String> dependenciasCuenta

    Indicador() {
    }

    static Indicador from(String nombre, String expresion, String visibilidad) {
        Indicador indicador = new Indicador()
        indicador.conExpresionString(expresion)

        indicador.setNombre(nombre)
        indicador.setExpresionString(expresion)
        indicador.setVisibilidad(visibilidad)

        return indicador
    }

    Indicador(String nombre, String expresion, String visibilidad) {
        this.nombre = nombre
        this.expresionString = expresion
        this.visibilidad = visibilidad

        IndicadorLexer lexer = new IndicadorLexer(CharStreams.fromString(expresion))
        CommonTokenStream tokens = new CommonTokenStream(lexer)
        IndicadorParser parser = new IndicadorParser(tokens)
        parser.removeErrorListeners()
        parser.setErrorHandler(new BailErrorStrategy())
        IndicadorParser.IndicadorContext context = parser.indicador()

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

    void conExpresionString(String expresionString) {
        IndicadorLexer lexer = new IndicadorLexer(CharStreams.fromString(expresionString))
        CommonTokenStream tokens = new CommonTokenStream(lexer)
        IndicadorParser parser = new IndicadorParser(tokens)
        parser.removeErrorListeners()
        parser.setErrorHandler(new BailErrorStrategy())

        if(parser.getNumberOfSyntaxErrors() > 0) {
            throw new IndicadorInvalidoException("La expresion " + expresionString + " es invalida")
        }

        try {
            IndicadorParser.IndicadorContext context = parser.indicador()
            IndicadorCustomVisitor visitor = new IndicadorCustomVisitor()
            IndicadorCustomListener listener = new IndicadorCustomListener()

            ParseTreeWalker walker = new ParseTreeWalker()
            walker.walk(listener, context)

            this.expresion = visitor.visit(context)
            this.dependenciasCuenta = new ArrayList<>(listener.getDependenciasCuenta())
            this.dependenciasIndicador = new ArrayList<>(listener.getDependenciasIndicador())
        } catch(ParseCancellationException e) {
            throw new IndicadorInvalidoException("La expresion " + expresionString + " es invalida")
        }
    }

    String getExpresionString() {
        return expresionString
    }

    void setExpresionString(String expresionString) {
        this.expresionString = expresionString
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

    void validarConsistencia() {
        if(this.getNombre() == null || this.getNombre().isEmpty())
            throw new IndicadorInvalidoException("El indicador debe tener nombre")

        if(this.getExpresion() == null)
            throw new IndicadorInvalidoException("El indicador debe tener expresion")

        if(this.getOwner() == null)
            throw new IndicadorInvalidoException("El indicador debe tener dueño")

        if(this.getVisibilidad() == null || this.getVisibilidad().isEmpty())
            throw new IndicadorInvalidoException("El indicador debe tener visibilidad")
    }
}