import ar.edu.utn.dds.ContenedorEmpresas
import ar.edu.utn.dds.Empresa
import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.PeriodoNoExisteException
import net.sf.cglib.core.Local
import spock.lang.Specification

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter;

class CargarDatosSpec extends Specification{

    void "insertar 2 empresas nuevas"(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")

        expect:
        contenedor.getEmpresas().size() == 2

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "insertar registros misma empresa"(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");

        expect:
        contenedor.getEmpresas().size() == 1

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "validar creacion periodos misma empresa"(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");

        expect:
        contenedor.getAllPeriodos().size() == 3

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "eliminar varias empresas y evaluar cantidad"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>()
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        nombres.add("Apple")
        nombres.add("Microsoft")
        nombres.add("Samsung")

        for(String unNombre : nombres) {
            Empresa empresa = new Empresa(unNombre);
            contenedor.empresas.add(empresa)
        }

        nombres.remove("Apple");
        contenedor.eliminarEmpresasConNombre(nombres);

        expect:
        contenedor.getEmpresas().size() == 1

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "eliminar varias empresas y evaluar restante"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>(3);
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        nombres.add("Apple");
        nombres.add("Microsoft");
        nombres.add("Samsung");

        for(String unNombre : nombres) {
            Empresa empresa = new Empresa(unNombre);
            contenedor.empresas.add(empresa)
        }

        nombres.remove("Apple");
        contenedor.eliminarEmpresasConNombre(nombres);

        expect:
        contenedor.getEmpresas().get(0).getNombre() == "Apple"

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "obtener varias empresas"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>()
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        nombres.add("Apple")
        nombres.add("Microsoft")
        nombres.add("Samsung")

        nombres.each { unNombre ->
            contenedor.empresas.add(new Empresa(unNombre))
        }

        expect:
        contenedor.each{
            it.obtenerEmpresasConNombre(nombres).size() == 3
        }

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "Consultar cuenta"(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");

        float valor = empresa.consultarCuenta("mac", LocalDate.parse("01/01/2015", dateFormat), LocalDate.parse("01/01/2016", dateFormat));

        expect:
        1000.0f == valor

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void "consultar cuenta con periodo inexistente"(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        when:
        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");
        empresa.consultarCuenta("mac", LocalDate.parse("02/01/2015", dateFormat), LocalDate.parse("01/12/2016", dateFormat));

        then:
        thrown PeriodoNoExisteException

        cleanup:
        contenedor.eliminarEmpresas()
    }

    void consultarCuentaConCuentaInexistente(){
        setup:
        ContenedorEmpresas contenedor = ContenedorEmpresas.getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        when:
        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");
        empresa.consultarCuenta("estaCuentaNoExiste", LocalDate.parse("01/01/2015", dateFormat), LocalDate.parse("01/01/2016", dateFormat));

        then:
        thrown CuentaNoExisteException

        cleanup:
        contenedor.eliminarEmpresas()
    }

}
