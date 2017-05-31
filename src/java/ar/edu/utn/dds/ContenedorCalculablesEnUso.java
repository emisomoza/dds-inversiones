package ar.edu.utn.dds;

import java.util.ArrayList;
import java.util.List;

public class ContenedorCalculablesEnUso {
	private static ContenedorCalculablesEnUso instance;
	private List<Calculable> calculables;

	public static ContenedorCalculablesEnUso getInstance() {
		if (instance == null) {
			instance = new ContenedorCalculablesEnUso();
		}
		return instance;
	}

	private ContenedorCalculablesEnUso() {
		calculables = new ArrayList<Calculable>();
	}

	public List<Calculable> getCalculables() {
		return calculables;
	}

	public void setCalculables(List<Calculable> calculables) {
		this.calculables = calculables;
	}

	public Calculable getCalculable(String name) {
		return calculables.stream().filter(aCalculable -> aCalculable.getNombre().equals(name)).findAny().get();
	}
}
