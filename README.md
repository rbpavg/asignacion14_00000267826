Estructura HashMap.
El objetivo principal es construir una estructura de datos TablaHash robusta desde cero,
utilizando Encadenamiento Separado (Separate Chaining) para manejar colisiones.
Para compilar, importar el proyecto dentro de su carpeta de proyectos y simplemente correr.
1. put(K key, V value): Procedimiento de insercion.
2. get(K key): Funcion de retornar valor de la llave indicada.
3. remove(K key): Funcion que elimina el valor y llave indicada.
4. containsKey(K key): Funcion que retorna si se contiene la llave indicada.
5. size(): Funcion que retorna la cantidad de elementos almacenados.
6. hash(K key): Funcion que calcula el indice hash para una llave indicada.
7. resize(): Redimensiona la tabla hash cuando el factor de carga supera el umbral.
