# **MVI Posts App - Ejemplo de Arquitectura MVI en Android**

Este proyecto es una aplicación de ejemplo para Android, desarrollada en Kotlin, que demuestra una implementación limpia y didáctica del patrón de arquitectura MVI (Model-View-Intent). La aplicación obtiene una lista de "posts" desde la API pública <ins>JSONPlaceholder</ins> y la muestra en un RecyclerView.

El objetivo principal es servir como una guía de aprendizaje paso a paso sobre cómo estructurar una aplicación moderna, escalable y fácil de mantener utilizando las mejores prácticas actuales.

## ✨ **Principios Fundamentales**

Este proyecto se adhiere estrictamente a los principios del patrón MVI:

1. **Estado Inmutable (Model)**: La UI se representa mediante un único objeto de estado (State) que es inmutable. Cada cambio genera un nuevo objeto de estado. Esto garantiza que la UI sea siempre un reflejo predecible de los datos.
2. **Flujo de Datos Unidireccional**: Los datos fluyen en un ciclo cerrado y en una sola dirección, lo que hace que la lógica sea fácil de seguir y depurar.
3. **Intenciones (Intent)**: Todas las acciones del usuario o eventos del sistema que pueden modificar el estado se modelan como objetos de Intent. La vista no modifica el estado directamente, solo emite intenciones.

## **🔄 Diagrama de Flujo de Datos**

El flujo de datos de la aplicación sigue este ciclo unidireccional:

    graph TD
    subgraph "View (Activity/Fragment)"
        A(1. El usuario interactúa o la vista se carga) --> B(2. Envía un `Intent`)
    end

    subgraph "ViewModel"
        B --> C(3. Procesa el `Intent`)
        C --> D{4. Llama a la capa de datos?}
        D -- Sí --> E(5. PostRepository)
        E --> C
        C --> F(7. Emite un nuevo `State`)
    end

    subgraph "State"
       F --> G(8. MainState)
    end

    subgraph "View (Activity/Fragment)"
        G --> H(9. Observa el `State` y se renderiza)
    end

1 y 2: La MainActivity envía un MainIntent (p. ej., LoadPosts).

3 a 6: El MainViewModel recibe el Intent, llama al PostRepository para obtener los datos y realiza la lógica de negocio.

7: El ViewModel crea un nuevo objeto MainState que representa el nuevo estado de la UI (p. ej., con los datos cargados o con un estado de error).

8 y 9: La MainActivity está suscrita a los cambios de State. Al recibir el nuevo estado, actualiza la UI para reflejarlo (muestra los datos, una barra de progreso o un mensaje de error).

## 🛠️ **Tecnologías y Librerías Utilizadas**
- <ins>Kotlin</ins>: Lenguaje de programación principal.
- <ins>Coroutines</ins>: Para manejar operaciones asíncronas de forma concurrente y sencilla, como las llamadas a la red.
- <ins>Flow</ins>: Utilizado para el flujo de datos reactivo. StateFlow es el núcleo para emitir los estados desde el ViewModel.
- <ins>Arquitectura MVI</ins>: Patrón de diseño que estructura la capa de presentación.
- <ins>Componentes de Android Jetpack</ins>:
    - <ins>ViewModel</ins>: Para almacenar y gestionar los datos relacionados con la UI, sobreviviendo a cambios de configuración.
    - <ins>ViewBinding</ins>: Para interactuar con las vistas XML de forma segura.
    - <ins>RecyclerView</ins>: Para mostrar listas de datos de manera eficiente.
- <ins>Retrofit</ins>: Cliente HTTP para realizar las peticiones a la API REST.
- <ins>Koin</ins>: Framework de inyección de dependencias pragmático y ligero para Kotlin.

## 📁 **Estructura del Proyecto**

El proyecto está organizado en paquetes por capas y funcionalidades para mantener el código desacoplado y fácil de navegar.com.example.mviposts
```text
com.sairgarces.mvipostsapp
│
│
├── data                # Capa de datos (agnóstica a la UI)
│   ├── model           # Clases de datos (Post.kt)
│   ├── network         # Configuración de red (ApiService.kt)
│   └── repository      # Repositorio (PostRepository.kt)
│
├── di                  # Inyección de dependencias (AppModule.kt)
│
├── ui                  # Capa de presentación (todo lo relacionado con la UI)
│   └── main            # Paquete para la feature/pantalla principal
│       ├── adapter     # Adapter para el RecyclerView (PostAdapter.kt)
│       ├── MainIntent.kt # Define las acciones del usuario
│       ├── MainState.kt  # Define el estado de la UI
│       ├── MainViewModel.kt # Orquesta el flujo de datos
│       └── MainActivity.kt  # La Vista que renderiza el estado
│
└── MyApplication.kt    # Clase Application para inicializar Koin
```

## 🚀 **Cómo Empezar**
1. Clona el repositorio:
    ```text
   git clone https://github.com/SairGarces/MVIPostApp.git
    ```
2. Abre el proyecto en Android Studio (versión Narwhal o superior recomendada).
3. Sincroniza Gradle para que descargue todas las dependencias.
4. Ejecuta la aplicación en un emulador o dispositivo físico. La app debería compilar y mostrar la lista de posts.
