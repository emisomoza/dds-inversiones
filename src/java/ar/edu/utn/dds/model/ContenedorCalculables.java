package ar.edu.utn.dds.model;

import java.util.ArrayList;
import java.util.List;

public class ContenedorCalculables {
	private static ContenedorCalculables instance;
	private List<Calculable> calculabes;

	public static ContenedorCalculables getInstance() {
		if (instance == null) {
			instance = new ContenedorCalculables();
		}
		return instance;
	}

	private ContenedorCalculables() {
		calculabes = new ArrayList<Calculable>();
	}

	public List<Calculable> getCalculabes() {
		return calculabes;
	}

	public void setCalculabes(List<Calculable> calculabes) {
		this.calculabes = calculabes;
	}

	public Calculable getCalculablePorNombre(String name) {
		return calculabes.stream().filter(aCalculable -> aCalculable.getNombre().equals(name)).findAny().get();
	}
}
