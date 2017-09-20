package ar.edu.utn.dds.model

import ar.edu.utn.dds.exceptions.CuentaNoExisteException
import ar.edu.utn.dds.exceptions.PeriodoNoExisteException

import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter;

public class Empresa implements Serializable {

    private Long id;
    private String nombre
    private ArrayList<Periodo> periodos

    public Empresa(){}

    public Empresa(String nombre){
        this.nombre = nombre;
        this.periodos = new ArrayList<Periodo>()
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    void setPeriodos(ArrayList<Periodo> periodos) {
        this.periodos = periodos
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void savePeriodo(Map period){
        Periodo periodo = new Empresa(LocalDate.parse(period.fechaDesde, formatoDeFecha), LocalDate.parse(period.fechaHasta, formatoDeFecha));
        this.agregarPeriodo(periodo);
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }


    public void agregarPeriodo(Periodo periodo) {
        periodos.add(periodo);
    }

    public Periodo obtenerPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        return this.periodos.find{unPeriodo -> unPeriodo.getFechaInicio().equals(fechaInicio) && unPeriodo.getFechaFin().equals(fechaFin)}
    }

    public void eliminarPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        eliminarPeriodo(obtenerPeriodo(fechaInicio, fechaFin));
    }

    public void eliminarPeriodo(Periodo periodo) {
        periodos.remove(periodo);
    }

    public void importarCuenta(String fechaInicioString, String fechaFinString, String cuentaString, String valorCuentaString) throws ParseException {
        DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio;
        LocalDate fechaFin;

        fechaInicio = LocalDate.parse(fechaInicioString, formatoDeFecha);
        fechaFin = LocalDate.parse(fechaFinString, formatoDeFecha);

        Periodo periodo = obtenerPeriodo(fechaInicio, fechaFin);

        if(periodo == null) {
            periodo = new Periodo(fechaInicio, fechaFin);
            agregarPeriodo(periodo);
        }

        periodo.importarCuenta(cuentaString, valorCuentaString);
    }

    public float consultarCuenta(String nombreCuenta, LocalDate fechaDesde, LocalDate fechaHasta) throws PeriodoNoExisteException, CuentaNoExisteException {
        Periodo periodo = obtenerPeriodo(fechaDesde, fechaHasta);

        if(periodo == null) {
            throw new PeriodoNoExisteException();
        }

        return periodo.consultarCuenta(nombreCuenta);
    }
}
