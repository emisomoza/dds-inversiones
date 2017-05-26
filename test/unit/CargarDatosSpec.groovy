import ar.edu.utn.dds.ContenedorEmpresas
import ar.edu.utn.dds.Empresa
import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.PeriodoNoExisteException
import spock.lang.Specification

import java.text.SimpleDateFormat;

/**
 * Created by esomoza on 5/14/17.
 */
class CargarDatosSpec extends Specification{

    void "insertar 2 empresas nuevas"(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt")

        expect:
        contenedor.getEmpresas().size() == 2

        cleanup:
        contenedor.empresas = []
    }

    void "insertar registros misma empresa"(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");

        expect:
        contenedor.getEmpresas().size() == 1

        cleanup:
        contenedor.empresas = []
    }

    void "validar creacion periodos misma empresa"(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_misma_empresa.txt");

        expect:
        contenedor.getAllPeriodos().size() == 3

        cleanup:
        contenedor.empresas = []
    }

    void "eliminar varias empresas y evaluar cantidad"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>()
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
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
        contenedor.empresas = []
    }

    void "eliminar varias empresas y evaluar restante"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>(3);
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
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
        contenedor.empresas = []
    }

    void "obtener varias empresas"() {
        setup:
        ArrayList<String> nombres = new ArrayList<String>()
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
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
        contenedor.empresas = []
    }

    void "Consultar cuenta"(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");

        float valor = empresa.consultarCuenta("mac", dateFormat.parse("01/01/2015"), dateFormat.parse("01/01/2016"));

        expect:
        1000.0f == valor

        cleanup:
        contenedor.empresas = []
    }

    void "consultar cuenta con periodo inexistente"(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        when:
        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");
        empresa.consultarCuenta("mac", dateFormat.parse("02/01/2015"), dateFormat.parse("01/12/2016"));

        then:
        thrown PeriodoNoExisteException

        cleanup:
        contenedor.empresas = []
    }

    void consultarCuentaConCuentaInexistente(){
        setup:
        ContenedorEmpresas contenedor = new ContenedorEmpresas().getInstance()
        contenedor.importarCuentasDesdeArchivo("./archivo_2_empresas.txt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        when:
        Empresa empresa = contenedor.obtenerEmpresaConNombre("Apple");
        empresa.consultarCuenta("estaCuentaNoExiste", dateFormat.parse("01/01/2015"), dateFormat.parse("01/01/2016"));

        then:
        thrown CuentaNoExisteException

        cleanup:
        contenedor.empresas = []
    }

}
