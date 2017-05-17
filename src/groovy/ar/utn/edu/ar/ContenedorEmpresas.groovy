package ar.utn.edu.ar

import ar.utn.edu.ar.exceptions.CuentaNoExisteException
import ar.utn.edu.ar.exceptions.PeriodoNoExisteException

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created by esomoza on 5/13/17.
 */

//Utilizamos el patrón singleton
public class ContenedorEmpresas {
    private ArrayList<Empresa> empresas
    private static ContenedorEmpresas instance = null

    private ContenedorEmpresas() {
        empresas = new ArrayList<ContenedorEmpresas>()
    }

    public static ContenedorEmpresas getInstance() {
        if(instance == null) {
            instance = new ContenedorEmpresas()
        }
        return instance
    }

    public List<ContenedorEmpresas> getEmpresas() {
        return empresas;
    }

    public List<Periodo> getAllPeriodos() {
        ArrayList<Periodo> periodos = new ArrayList<Periodo>();
        this.empresas.each { Empresa empresa ->
            empresa.periodos.each{ Periodo periodo ->
                periodos.add(periodo)
            }
        }
        return periodos;
    }

    public Empresa obtenerEmpresaConNombre(String nombre) {
        return this.empresas.find{Empresa unaEmpresa -> nombre.equals(unaEmpresa.nombre)}
    }

    public ArrayList<Empresa> obtenerEmpresasConNombre(List<String> nombres) {
        return empresas.findAll{Empresa unaEmpresa -> nombres.contains(unaEmpresa.nombre)};
    }

    public void eliminarEmpresaConNombre(String nombre) {
        Empresa empresaAEliminar = obtenerEmpresaConNombre(nombre);
        if(empresaAEliminar != null) {
            this.eliminarEmpresa(empresaAEliminar);
        }
    }

    public void eliminarEmpresasConNombre(List<String> nombres) {
        this.eliminarEmpresas(obtenerEmpresasConNombre(nombres));
    }

    public void eliminarEmpresa(Empresa empresa) {
        empresas.remove(empresa);
    }

    public void eliminarEmpresas(ArrayList<Empresa> unasEmpresas) {
        empresas.removeAll(unasEmpresas);
    }

    public void importarCuentasDesdeArchivo (String pathArchivo) {

        /*
         * Levantar archivo y cargar cuentas
         * Formato: nombreEmpresa;fechaInicio;fechaFin;cuenta;valor
         */

        FileReader fr = null;
        BufferedReader br;
        String[] arraySubstrings;

        try {
            new File(pathArchivo).eachLine { linea ->
                arraySubstrings = linea.split(";");
                Empresa empresa = this.obtenerEmpresaConNombre(arraySubstrings[0]);
                if(empresa == null) {
                    empresa = new Empresa(arraySubstrings[0]);
                    this.empresas.add(empresa)
                }
                empresa.importarCuenta(arraySubstrings[1], arraySubstrings[2], arraySubstrings[3], arraySubstrings[4]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fr != null)
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
