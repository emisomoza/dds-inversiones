package ar.edu.utn.dds.listener;

import ar.edu.utn.dds.antlr.IndicadorBaseListener;
import ar.edu.utn.dds.antlr.IndicadorParser;

import java.util.HashSet;
import java.util.Set;

public class IndicadorCustomListener extends IndicadorBaseListener {
	private Set<String> dependenciasCuenta = new HashSet<>();
	private Set<String> dependenciasIndicador = new HashSet<>();

	public Set<String> getDependenciasCuenta() {
		return dependenciasCuenta;
	}

	public Set<String> getDependenciasIndicador() {
		return dependenciasIndicador;
	}

	@Override
	public void enterPrimary(IndicadorParser.PrimaryContext ctx) {
		if(ctx.IND() != null) {
			dependenciasIndicador.add(ctx.VAR().getText());
		}

		if (ctx.CUE() != null) {
			dependenciasCuenta.add(ctx.VAR().getText());
		}
	}
}
