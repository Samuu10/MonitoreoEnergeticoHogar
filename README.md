# GESTIÓN ENERGÉTICA DEL HOGAR

## Objetivo

El objetivo de este proyecto es desarrollar una aplicación de Android para monitorear el consumo de energía en el hogar.
Se busca informar al usuario del consumo de sus electrodomesticos o habitaciones para ayudarle a reducir el consumo energético de su hogar.
La recopilación de datos se gestiona en segundo plano para no afectar al rendimiento del dispositivo ni al consumo de bateria.

## Clases Java

### `EstadisticasActivity.java`
Clase para mostrar las estadísticas de consumo de energía de las habitaciones y electrodomésticos. Utiliza gráficos circulares y de barras para visualizar estos datos, y se podrían modificarcestos gráficos dependiendo de los electrodomésticos y habitaciones del usuario

### `HistorialActivity.java`
Clase para mostrar el historial de consumo de energía del hogar. Utiliza un `RecyclerView` para listar los registros históricos. Se muestra una lista que simula las facturas de energía del usuario de los últimos 12 meses

### `MainActivity.java`
Actividad principal de la aplicación. Muestra el consumo actual, mensual y anual de energía y permite navegar a otras actividades como estadísticas, recomendaciones e historial.

### `RecomendacionesActivity.java`
Clase para mostrar recomendaciones para optimizar el consumo de energía. Utiliza un `RecyclerView` para listar las recomendaciones. Se podrían mostrar recomendaciones personalizadas dependiendo del consumo de cada usuario; pero en este proyecto se ha implementado una lógica más general.

### `CustomValueFormatter.java`
Clase para personalizar el formato de los valores de las barras en el gráfico de barras.

### `EstadisticasViewModel.java`
Clase ViewModel para gestionar los datos de consumo de electrodomésticos y habitaciones. Se comunica con Firebase para cargar los datos.

### `FirebaseAsyncTask.java`
Clase para cargar los datos de Firebase en segundo plano utilizando `AsyncTask`.

### `Historial.java`
Clase que define un registro de historial de consumo con atributos como mes, consumo y precio.

### `HistorialAdapter.java`
Adaptador para el `RecyclerView` del historial de consumo.

### `Recomendacion.java`
Clase que define una recomendación con atributos como título y descripción.

### `RecomendacionAdapter.java`
Adaptador para el `RecyclerView` de las recomendaciones.

## Archivos XML

### `activity_estadisticas.xml`
Diseño de la actividad de estadísticas. Contiene un `PieChart` y un `BarChart` para mostrar las estadísticas de consumo de energía.

### `activity_historial.xml`
Diseño de la actividad de historial. Contiene un `RecyclerView` para listar los registros históricos de consumo de energía.

### `activity_main.xml`
Diseño de la actividad principal. Contiene tarjetas para mostrar el consumo actual, mensual y anual, y botones para navegar a otras actividades.

### `activity_recomendaciones.xml`
Diseño de la actividad de recomendaciones. Contiene un `RecyclerView` para listar las recomendaciones de optimización del consumo de energía.

### `item_historial.xml`
Diseño de un elemento del `RecyclerView` en la actividad de historial. Muestra el mes, consumo y precio de un registro histórico.

### `item_recomendacion.xml`
Diseño de un elemento del `RecyclerView` en la actividad de recomendaciones. Muestra el título y la descripción de una recomendación.

## Links

* Link al repositorio: https://github.com/Samuu10/MonitoreoEnergeticoHogar.git
* Link al database de Firebase: https://console.firebase.google.com/project/monitoreoenergeticohogar/database/monitoreoenergeticohogar-default-rtdb/data/~2F?hl=es
