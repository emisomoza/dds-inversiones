package ar.edu.utn.dds.metodologia

import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo
import spock.lang.Specification

import java.time.LocalDate

class OperadorOrdenSpec extends Specification {
    Empresa empresa1
    Empresa empresa2
    Empresa empresa3

    List<Empresa> empresas = new ArrayList<>();

    def setup() {
        empresa1 = new Empresa("Empresa1");
        empresa2 = new Empresa("Empresa2");
        empresa3 = new Empresa("Empresa3");

        Periodo periodo;

        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 1D));
        empresa1.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 2D));
        empresa1.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 3D));
        empresa1.agregarPeriodo(periodo);



        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 10D));
        empresa2.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 11D));
        empresa2.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 12D));
        empresa2.agregarPeriodo(periodo);



        periodo = new Periodo(LocalDate.of(2017, 1, 1), LocalDate.of(2017, 1, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 20D));
        empresa3.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 2, 1), LocalDate.of(2017, 2, 28));
        periodo.agregarCuenta(new Cuenta("Indicador1", 21D));
        empresa3.agregarPeriodo(periodo);

        periodo = new Periodo(LocalDate.of(2017, 3, 1), LocalDate.of(2017, 3, 31));
        periodo.agregarCuenta(new Cuenta("Indicador1", 22D));
        empresa3.agregarPeriodo(periodo);
    }

    def 'Test ordenado menor'() {
        when:
        OperadorOrdenadorMenor operadorOrdenador = new OperadorOrdenadorMenor();
        operadorOrdenador.setIndicador("Indicador1");
        operadorOrdenador.setModificador(new ModificadorOrdenadorSum())

        then:
        List<Empresa> empresasOrdenadas =  operadorOrdenador.ordenar([empresa1, empresa2, empresa3]);
        empresasOrdenadas == [empresa3, empresa2, empresa1]
    }

    def 'Test ordenado mayor'() {
        when:
        OperadorOrdenadorMayor operadorOrdenador = new OperadorOrdenadorMayor();
        operadorOrdenador.setIndicador("Indicador1");
        operadorOrdenador.setModificador(new ModificadorOrdenadorSum())

        then:
        List<Empresa> empresasOrdenadas =  operadorOrdenador.ordenar([empresa3, empresa2, empresa1]);
        empresasOrdenadas == [empresa1, empresa2, empresa3]
    }
}
