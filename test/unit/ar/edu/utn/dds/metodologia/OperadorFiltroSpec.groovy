package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import spock.lang.Specification

import java.time.LocalDate

class OperadorFiltroSpec extends Specification {
    Indicador indicador
    IndicadorService indicadorServ
    Empresa empresa

    def setup() {
        empresa = new Empresa("Empresa1");

        indicador = new Indicador("indicador", "4*5+ind(indicador1)" )
        def indicador1 = new Indicador("indicador1", "cue(Cuenta1) * ind(indicador2)")
        def indicador2 = new Indicador("indicador2", "4^2-5" )

        indicadorServ = Spy(IndicadorService) {
            obtener("indicador") >> indicador
            obtener("indicador1") >> indicador1
            obtener("indicador2") >> indicador2
        }
    }

    def 'Test filtrado creciente false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 26D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroCreciente operadorFiltro = new OperadorFiltroCreciente();
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        ! operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado creciente true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 10D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroCreciente operadorFiltro = new OperadorFiltroCreciente();
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado decreciente true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 7D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroDecreciente operadorFiltro = new OperadorFiltroDecreciente();
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado igual true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroIgual operadorFiltro = new OperadorFiltroIgual();
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado mayor a 10 true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 911D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 88D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMayor operadorFiltro = new OperadorFiltroMayor(10D);
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado mayor a 2000 false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 911D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMayor operadorFiltro = new OperadorFiltroMayor(2000D);
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        !operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado menor a 2000 true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 6D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMenor operadorFiltro = new OperadorFiltroMenor(2000D);
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa, indicadorServ)
    }

    def 'Test filtrado menor a 10 false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMenor operadorFiltro = new OperadorFiltroMenor(10D);
        operadorFiltro.setIndicador("indicador");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        !operadorFiltro.filtrar(empresa, indicadorServ)
    }
}
