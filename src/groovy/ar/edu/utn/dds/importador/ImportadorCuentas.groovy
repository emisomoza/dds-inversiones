package ar.edu.utn.dds.importador

import ar.edu.utn.dds.exceptions.InversionesException
import ar.edu.utn.dds.model.Cuenta
import ar.edu.utn.dds.model.Empresa
import ar.edu.utn.dds.model.Periodo
import ar.edu.utn.dds.model.TipoCuenta
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.util.stream.Collectors

import static com.xlson.groovycsv.CsvParser.parseCsv

class ImportadorCuentas {
    
    protected static final String EMPRESA_TAG = "empresa"
    protected static final String FECHA_DESDE_TAG = "fecha_desde"
    protected static final String FECHA_HASTA_TAG = "fecha_hasta"
    protected static final String TIPO_TAG = "tipo"
    protected static final String VALOR_TAG = "valor"

    def empresaService
    def periodoService
    def tipoCuentaService
    def cuentaService

    @Transactional
    def importar(String cuentasCSV) {
        List<Map<String, String>> mapasImportar = this.parsearImport(cuentasCSV)

        this.importarEmpresas(mapasImportar)
        this.importarPeriodos(mapasImportar)
        this.importarTipoCuentas(mapasImportar)
        this.importarCuentas(mapasImportar)
    }

    def importarEmpresas(List<Map<String, String>> mapasImportar) {
        List<Empresa> empresas = this.construirEmpresas(mapasImportar)
        this.empresaService.importarEmpresas(empresas, true)
    }

    def construirEmpresas(List<Map<String, String>> mapasImportar) {
        return mapasImportar.stream()
            .map({mapa ->
                Empresa empresa = new Empresa()
                empresa.setNombre(mapa.get(EMPRESA_TAG))
                return empresa
            })
            .collect(Collectors.toList())
    }

    def importarPeriodos(List<Map<String, String>> mapasImportar) {
        List<Periodo> periodos = this.construirPeriodos(mapasImportar)
        this.periodoService.importarPeriodos(periodos, true)
    }

    def construirPeriodos(List<Map<String, String>> mapasImportar) {
        return mapasImportar.stream()
                .map({mapa ->
                    Periodo periodo = new Periodo()
                    periodo.setFechaInicio(LocalDate.parse(mapa.get(FECHA_DESDE_TAG)))
                    periodo.setFechaFin(LocalDate.parse(mapa.get(FECHA_HASTA_TAG)))
                    return periodo
                })
                .collect(Collectors.toList())
    }

    def importarTipoCuentas(List<Map<String, String>> mapasImportar) {
        List<TipoCuenta> tipOCuentas = this.construirTipoCuentas(mapasImportar)
        this.tipoCuentaService.importarTipoCuentas(tipOCuentas, true)
    }

    def construirTipoCuentas(List<Map<String, String>> mapasImportar) {
        return mapasImportar.stream()
                .map({mapa ->
                    TipoCuenta tipoCuenta = new TipoCuenta()
                    tipoCuenta.setDescripcion(mapa.get(TIPO_TAG))
                    return tipoCuenta
                })
                .collect(Collectors.toList())
    }

    def importarCuentas(List<Map<String, String>> mapasImportar) {
        List<Cuenta> cuentas = this.construirCuentas(mapasImportar)
        this.cuentaService.importarCuentas(cuentas)
    }

    def construirCuentas(List<Map<String, String>> mapasImportar) {
        List<Empresa> empresas = this.empresaService.listar()
        List<Periodo> periodos = this.periodoService.listar()
        List<TipoCuenta> tipoCuenta = this.tipoCuentaService.listar()

        return mapasImportar.stream()
                .map({mapa ->
                    Cuenta cuenta = new Cuenta()
                    cuenta.setTipo(tipoCuenta.find {tipo -> tipo.getDescripcion() == mapa.get(TIPO_TAG)})
                    cuenta.setEmpresa(empresas.find {emp -> emp.getNombre() == mapa.get(EMPRESA_TAG)}.getId())
                    cuenta.setPeriodo(periodos.find {periodo ->
                        periodo.getFechaInicio() == LocalDate.parse(mapa.get(FECHA_DESDE_TAG)) &&
                        periodo.getFechaFin() == LocalDate.parse(mapa.get(FECHA_HASTA_TAG))
                    }.getId())
                    cuenta.setValor(Double.parseDouble(mapa.get(VALOR_TAG)))
                    return cuenta
                })
                .collect(Collectors.toList())
    }

    def parsearImport(File archivo) {
        try {
            return this.parsearImport(archivo.text.toString())
        } catch (Exception e) {
            throw new InversionesException("Error importando cuentas desde archivo", e)
        }
    }

    def parsearImport(String cuentasCSV) {
        List<Map<String, String>> mapasCuentas = new ArrayList<>()

        try {
            Iterator iteradorCuentas = parseCsv(cuentasCSV)

            for(linea in iteradorCuentas) {
                Map<String, String> mapaCuentas = new HashMap<>()
                mapaCuentas.put(EMPRESA_TAG, linea.Empresa)
                mapaCuentas.put(FECHA_DESDE_TAG, linea.Fecha_Desde)
                mapaCuentas.put(FECHA_HASTA_TAG, linea.Fecha_Hasta)
                mapaCuentas.put(TIPO_TAG, linea.Cuenta)
                mapaCuentas.put(VALOR_TAG, linea.Valor)
                mapasCuentas.add(mapaCuentas)
                this.validarMapa(mapaCuentas)
            }

            return mapasCuentas
        } catch(InversionesException e) {
            throw e
        } catch(Exception e) {
            throw new InversionesException("Error parceando cuentas para importar", e)
        }
    }

    def validarMapa(Map<String, String> mapaImportar) {
        if(mapaImportar.values().any {value -> value == null || value.size() == 0})
            throw new InversionesException("El archivo posee una o más filas con columnas vacias")

        try {
            Double.parseDouble(mapaImportar.get(VALOR_TAG))
        } catch(NumberFormatException e) {
            throw new InversionesException("El archivo posee una o más filas que tienen un valor no numero en el la columna \"Valor\"", e)
        }

        try {
            LocalDate.parse(mapaImportar.get(FECHA_DESDE_TAG))
            LocalDate.parse(mapaImportar.get(FECHA_HASTA_TAG))
        } catch(DateTimeParseException e) {
            throw new InversionesException("El archivo posee una o más filas que tienen un formato de " +
                    "fecha no valido en la columna \"Fecha_Desde\" o \"Fechca_Hasta\". Solo se aceptan formatos ISO (ej: yyyy-mm-dd).", e)
        }
    }
}
