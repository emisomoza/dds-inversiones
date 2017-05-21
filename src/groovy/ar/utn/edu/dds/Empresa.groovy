package ar.utn.edu.dds

import ar.utn.edu.dds.exceptions.CuentaNoExisteException
import ar.utn.edu.dds.exceptions.PeriodoNoExisteException

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by esomoza on 5/13/17.
 */

public class Empresa {

    private String nombre
    private ArrayList<Periodo> periodos

    public Empresa(String nombre){
        this.nombre = nombre;
        this.periodos = new ArrayList<Periodo>()
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void agregarPeriodo(Periodo periodo) {
        periodos.add(periodo);
    }

    public Periodo obtenerPeriodo(Date fechaInicio, Date fechaFin) {
        return this.periodos.find{unPeriodo -> unPeriodo.getFechaInicio().equals(fechaInicio) && unPeriodo.getFechaFin().equals(fechaFin)}
    }

    public void eliminarPeriodo(Date fechaInicio, Date fechaFin) {
        eliminarPeriodo(obtenerPeriodo(fechaInicio, fechaFin));
    }

    public void eliminarPeriodo(Periodo periodo) {
        periodos.remove(periodo);
    }

    public void importarCuenta(String fechaInicioString, String fechaFinString, String cuentaString, String valorCuentaString) throws ParseException {
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio;
        Date fechaFin;

        fechaInicio = formatoDeFecha.parse(fechaInicioString);
        fechaFin = formatoDeFecha.parse(fechaFinString);

        Periodo periodo = obtenerPeriodo(fechaInicio, fechaFin);

        if(periodo == null) {
            periodo = new Periodo(fechaInicio, fechaFin);
            agregarPeriodo(periodo);
        }

        periodo.importarCuenta(cuentaString, valorCuentaString);
    }

    public float consultarCuenta(String nombreCuenta, Date fechaDesde, Date fechaHasta) throws PeriodoNoExisteException, CuentaNoExisteException {
        Periodo periodo = obtenerPeriodo(fechaDesde, fechaHasta);

        if(periodo == null) {
            throw new PeriodoNoExisteException();
        }

        return periodo.consultarCuenta(nombreCuenta);
    }
}
