package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.indicador.service.IndicadorService
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Indicador
import ar.edu.utn.dds.model.Periodo
import spock.lang.Specification

import java.time.LocalDate

class OperadorOrdenSpec extends Specification {
    Indicador indicador1
    Indicador indicador2
    Indicador indicador3

    IndicadorService indicadorServ

    Empresa empresa1
    Empresa empresa2
    Empresa empresa3

    def setup() {
        setupIndicadores()

        setupIndicadorService()

        setupEmpresas()
    }

    def setupIndicadores() {
        indicador1 = Indicador.from("indicador1", "cue(Cuenta1) * ind(indicador2)", "")
        indicador2 = Indicador.from("indicador2", "4^2-5", "" )
        indicador3 = Indicador.from("indicador3", "4*5+ind(indicador1)", "" )
    }

    def setupIndicadorService() {
        indicadorServ = Spy(IndicadorService) {
            obtener("indicador1") >> indicador1
            obtener("indicador2") >> indicador2
            obtener("indicador3") >> indicador3
        }
    }

    def setupEmpresas() {
        empresa1 = new Empresa("Empresa1");
        empresa2 = new Empresa("Empresa2");
        empresa3 = new Empresa("Empresa3");

        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 1D));
        empresa1.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 2D));
        empresa1.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 3D));
        empresa1.agregarPeriodo(periodo);



        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 10D));
        empresa2.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 11D));
        empresa2.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 12D));
        empresa2.agregarPeriodo(periodo);



        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 20D));
        empresa3.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 21D));
        empresa3.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Cuenta1", 22D));
        empresa3.agregarPeriodo(periodo);
    }

    def 'Test ordenado menor'() {
        when:
        OperadorOrdenadorMenor operadorOrdenador = new OperadorOrdenadorMenor()
        operadorOrdenador.setIndicador("indicador3")
        operadorOrdenador.setModificador(new ModificadorOrdenadorSum())

        then:
        List<Empresa> empresasOrdenadas =  operadorOrdenador.ordenar([empresa1, empresa2, empresa3], indicadorServ);
        empresasOrdenadas == [empresa3, empresa2, empresa1]
    }

    def 'Test ordenado mayor'() {
        when:
        OperadorOrdenadorMayor operadorOrdenador = new OperadorOrdenadorMayor();
        operadorOrdenador.setIndicador("indicador1");
        operadorOrdenador.setModificador(new ModificadorOrdenadorSum())

        then:
        List<Empresa> empresasOrdenadas =  operadorOrdenador.ordenar([empresa3, empresa2, empresa1], indicadorServ);
        empresasOrdenadas == [empresa1, empresa2, empresa3]
    }
}
