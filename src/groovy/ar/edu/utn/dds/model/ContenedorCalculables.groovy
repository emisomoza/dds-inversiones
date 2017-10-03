package ar.edu.utn.dds.model

class ContenedorCalculables {
    private static ContenedorCalculables instance;
    private List<Calculable> calculabes;

    static ContenedorCalculables getInstance() {
        if (instance == null) {
            instance = new ContenedorCalculables();
        }
        return instance;
    }

    private ContenedorCalculables() {
        calculabes = new ArrayList<Calculable>();
    }

    List<Calculable> getCalculabes() {
        return calculabes;
    }

    void setCalculabes(List<Calculable> calculabes) {
        this.calculabes = calculabes;
    }

    Calculable getCalculablePorNombre(String name) {
        return calculabes.find { it.getNombre().equals(name) }
    }
}
