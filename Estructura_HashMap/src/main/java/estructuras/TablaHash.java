package estructuras;

import java.util.LinkedList;

/**
 * Construccion robusta desde cero de estructura de datos tipo HashMap.
 * Utilizando una lista enlazada de elementos.
 * Cuando dos claves diferentes producen el mismo indice hash (colision),
 * ambas se almacenan en la misma lista.
 * 
 * @param <K> Tipo de las llaves
 * @param <V> Tipo de los valores
 * 
 * @author 267826
 * @version 1.0
 */
public class TablaHash<K, V> implements Diccionario<K, V> {
    
    // Clase interna: Almacena un par clave-valor
    
    private class Nodo {

        K key;
        V value;

        public Nodo(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // Atributos principales
    
    private LinkedList<Nodo>[] tabla; // Arreglo de listas (buckets)
    private int size; // Cantidad de elementos almacenados (N)
    private int capacidad; // Tamano del arreglo (M)
    private static final double FACTOR_CARGA_MAX = 0.75;
    
    /**
     * Constructor por defecto
     * Inicializa la tabla con capacidad 11 (numero primo)
     */
    public TablaHash() {
        this.capacidad = 11;
        this.tabla = new LinkedList[capacidad];
        this.size = 0;
        
        // IMPORTANTE: Inicializar cada posicion del arreglo
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }
    }
    
    // Operaciones Basicas
    
    /**
     * Calcula el indice hash para una clave.
     * Maneja correctamente hashCodes negativos.
     * 
     * @param key La clave a procesar
     * @return Indice valido en [0, capacidad-1]
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacidad; 
    }
    
    /**
     * Inserta un par clave-valor en la tabla hash.
     * Si la clave ya existe, actualiza su valor.
     * Verifica automaticamente el factor de carga y redimensiona si es necesario.
     * 
     * @param key La clave unica del elemento (no puede ser null)
     * @param value El valor asociado a la clave
     */
    @Override
    public void put(K key, V value) {
        
        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];
        
        for (Nodo nodo : lista) {
            if (nodo.key.equals(key)) {
                nodo.value = value;
                return;
            }
        }

        lista.add(new Nodo(key, value));
        size++;

        if ((double) size / capacidad >= FACTOR_CARGA_MAX) {
            resize();
        }
    }
    
    /**
     * Recupera el valor asociado a una clave.
     * 
     * @param key la clave a buscar
     * @return El valor asociado, o null si no existe
     */
    @Override
    public V get(K key) {
        
        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];

        for (Nodo nodo : lista) {
            if (nodo.key.equals(key)) {
                return nodo.value;
            }
        }
        return null;
    }

    /**
     * Elimina un par clave-valor de la tabla.
     * 
     * @param key la clave del elemento a eliminar
     * @return El valor eliminado, o null si no existe
     */
    @Override
    public V remove(K key) {
        
        int indice = hash(key);
        LinkedList<Nodo> lista = tabla[indice];

        for (int i = 0; i < lista.size(); i++) {
            Nodo nodo = lista.get(i);
            if (nodo.key.equals(key)) {
                V valorEliminado = nodo.value;
                lista.remove(i);
                size--;
                return valorEliminado;
            }
        }
        return null;
    }
    
    /**
     * Verifica si una clave existe en la tabla.
     * 
     * @param key la clave a verificar
     * @return true si existe, false en caso contrario
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
    /**
     * Retorna la cantidad de elementos almacenados.
     * @return El tamano actual de la tabla
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Implementacion del Metodo resize()
    
    /**
     * Redimensiona la tabla hash cuando el factor de carga supera el umbral.
     * Duplica la capacidad y reubica todos los elementos.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Nodo>[] tablaVieja = tabla;
        capacidad = capacidad * 2;
        tabla = new LinkedList[capacidad];

        // Inicializar cada posicion del nuevo arreglo
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new LinkedList<>();
        }

        // Reinsertar todos los elementos
        size = 0; // Sera incrementado por put()
        for (LinkedList<Nodo> bucket : tablaVieja) {
            for (Nodo nodo : bucket) {
                put(nodo.key, nodo.value); // Recalcula indices automaticamente
            }
        }

    }
    
}
