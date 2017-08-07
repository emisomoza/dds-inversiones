package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.Cuenta
import ar.edu.utn.dds.Empresa
import ar.edu.utn.dds.Periodo
import spock.lang.Specification

import java.time.LocalDate

class OperadorFiltroSpec extends Specification {
    Empresa empresa

    def setup() {
        empresa = new Empresa("Empresa1");
    }

    def 'Test filtrado creciente creciente false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 26D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroCreciente operadorFiltro = new OperadorFiltroCreciente();
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        ! operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado creciente true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 10D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroCreciente operadorFiltro = new OperadorFiltroCreciente();
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado decreciente true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 7D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroDecreciente operadorFiltro = new OperadorFiltroDecreciente();
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado igual true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroIgual operadorFiltro = new OperadorFiltroIgual();
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado mayor a 10 true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 911D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 88D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMayor operadorFiltro = new OperadorFiltroMayor(10D);
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado mayor a 10 false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 911D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMayor operadorFiltro = new OperadorFiltroMayor(10D);
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        !operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado menor a 10 true'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 6D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMenor operadorFiltro = new OperadorFiltroMenor(10D);
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        operadorFiltro.filtrar(empresa)
    }

    def 'Test filtrado menor a 10 false'() {
        given:
        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 11D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 9D));
        empresa.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 8D));
        empresa.agregarPeriodo(periodo);

        when:
        OperadorFiltroMenor operadorFiltro = new OperadorFiltroMenor(10D);
        operadorFiltro.setIndicador("Indicador1");
        operadorFiltro.setModificador(new ModificadorFiltroSiempre())

        then:
        !operadorFiltro.filtrar(empresa)
    }
}
