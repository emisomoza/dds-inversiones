package ar.edu.utn.dds.utilidades.mapper;

/**
 * Created by andres on 29/05/17.
 */
public interface Mapeable<K, V> {

    public abstract K getKey();
    public abstract V getValue();
}
