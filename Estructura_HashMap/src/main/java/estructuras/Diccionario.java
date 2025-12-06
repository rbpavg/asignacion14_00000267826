package estructuras;

/**
 * Interfaz Diccionario para implementar en nuestra TablaHash.
 * @param <K> Tipo de las llaves
 * @param <V> Tipo de los valores
 * 
 * @author 267826
 * @version 1.0
 */
public interface Diccionario<K, V> {
    
    /**
     * Procedimiento de insercion.
     * 
     * @param key llave a la que conectar el valor
     * @param value valor a insertar
     */
    void put(K key, V value);
    
    /**
     * Funcion de retornar valor en la llave indicada.
     * 
     * @param key la llave que se busca
     * @return valor de la llave
     */
    V get(K key);
    
    /**
     * Funcion de retornar y quitar valor y llave indicada.
     * 
     * @param key la llave que se busca
     * @return valor eliminado correspondiente a la llave
     */
    V remove(K key);
    
    /**
     * Funcion que retorna si contiene la llave indicada.
     * 
     * @param key la llave que se busca
     * @return true si existe, false en caso contrario
     */
    boolean containsKey(K key);
    
    /**
     * Funcion que retorna la cantidad de elementos almacenados.
     * 
     * @return int cantidad
     */
    int size();
    
}
