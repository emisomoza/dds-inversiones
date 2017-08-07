package ar.edu.utn.dds

import ar.edu.utn.dds.exceptions.CuentaNoExisteException

import java.time.LocalDate

/**
 * Created by esomoza on 5/13/17.
 */
public class Periodo {

    private List<Cuenta> cuentas
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Periodo(LocalDate fechaComienzo, LocalDate fechaFin) {
        this.fechaInicio = fechaComienzo;
        this.fechaFin = fechaFin;
        this.cuentas = new ArrayList<Cuenta>()
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void getFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void importarCuenta(String nombreCuenta, String valorCuenta) {
        Cuenta cuenta = obtenerCuentaConNombre(nombreCuenta);

        if(cuenta == null) {
            cuenta = new Cuenta(nombreCuenta, Float.parseFloat(valorCuenta));
            agregarCuenta(cuenta);
        } else {
            cuenta.setValor(Float.parseFloat(valorCuenta));
        }
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

        public Cuenta obtenerCuentaConNombre(String nombreCuenta) {
        return this.cuentas.find{unaCuenta -> unaCuenta.getNombre().equals(nombreCuenta)}
    }

    public List<Cuenta> obtenerCuentasConNombre(List<String> nombres) {
            return this.cuentas.find{unaCuenta -> nombres.contains(unaCuenta.getNombre())}
    }

    public void eliminarCuentaConNombre(String nombre) {
        Cuenta cuentaAEliminar = obtenerCuentaConNombre(nombre);
        if(cuentaAEliminar != null) {
            eliminarCuenta(cuentaAEliminar);
        }
    }

    public void eliminarCuentasConNombre(List<String> nombres) {
        eliminarCuentas(obtenerCuentasConNombre(nombres));
    }

    public void eliminarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public void eliminarCuentas(List<Cuenta> unasCuentas) {
        cuentas.removeAll(unasCuentas);
    }

    public float consultarCuenta(String nombreCuenta) throws CuentaNoExisteException {
        Cuenta cuenta = obtenerCuentaConNombre(nombreCuenta);

        if(cuenta == null) {
            throw new CuentaNoExisteException();
        }

        return cuenta.getValor();
    }

}