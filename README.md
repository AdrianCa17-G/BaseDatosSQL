# Sistema de Gestión de Estudiantes - CRUD con Java y SQLite

Aplicación de consola desarrollada en Java que implementa un sistema CRUD (Crear, Leer, Actualizar, Eliminar) para la gestión de estudiantes matriculados, utilizando SQLite como motor de base de datos y aplicando los principios de Programación Orientada a Objetos.

## Objetivo

Desarrollar una aplicación en Java que implemente los principios de Programación Orientada a Objetos y permita gestionar información utilizando una base de datos SQLite mediante operaciones CRUD.

## Caso de estudio

Una institución educativa necesita administrar los estudiantes matriculados. Cada estudiante posee la siguiente información:

- Código (ID autogenerado)
- Cédula
- Nombres
- Apellidos
- Correo electrónico
- Carrera
- Semestre

## Tecnologías utilizadas

- **Lenguaje:** Java
- **Base de datos:** SQLite
- **Driver JDBC:** sqlite-jdbc
- **IDE:** NetBeans

## Estructura del proyecto

El proyecto está organizado en 4 paquetes, siguiendo el patrón de separación por capas:
| Paquete | Responsabilidad |
|---|---|
| `modelo` | Contiene la clase `Estudiante`, representa el objeto de negocio (atributos, constructores, getters/setters). |
| `conexion` | Contiene la clase `ConexionSQLite`, encargada exclusivamente de establecer la conexión JDBC con la base de datos. |
| `dao` | Contiene la clase `EstudianteDAO`, implementa el patrón DAO (Data Access Object) con toda la lógica SQL de las operaciones CRUD. |
| `main` | Contiene la clase `Main`, gestiona el menú interactivo y la comunicación con el usuario. |

## Diseño del proyecto

El diseño sigue una arquitectura en capas para separar responsabilidades:

1. **Capa de modelo:** representa la entidad `Estudiante` como un objeto con encapsulamiento total (atributos privados y acceso mediante getters/setters).
2. **Capa de conexión:** aísla la lógica de conexión a la base de datos, de modo que si cambia el motor de base de datos, solo se modifica esta clase.
3. **Capa DAO:** centraliza todas las operaciones de acceso a datos (INSERT, SELECT, UPDATE, DELETE), evitando que la lógica SQL esté mezclada con la interfaz de usuario.
4. **Capa de presentación (main):** gestiona el menú y la interacción con el usuario, delegando toda la persistencia al DAO.

Esta separación permite que cada clase tenga una única responsabilidad y facilita el mantenimiento y las pruebas del sistema.

## Clases implementadas

### `Estudiante` (modelo)

Representa a un estudiante matriculado. Contiene:

- Atributos privados: `id`, `cedula`, `nombres`, `apellidos`, `correo`, `carrera`, `semestre`.
- Constructor vacío.
- Constructor sobrecargado sin `id` (para registrar estudiantes nuevos).
- Constructor sobrecargado con `id` (para reconstruir estudiantes desde la base de datos).
- Getters y setters para todos los atributos.
- Método `toString()` sobrescrito para mostrar la información de forma legible.

### `ConexionSQLite` (conexion)

Clase encargada de establecer la conexión JDBC con la base de datos `universidad.db` mediante el método estático `conectar()`.

### `EstudianteDAO` (dao)

Implementa el patrón DAO con los siguientes métodos:

| Método | Descripción |
|---|---|
| `crearTabla()` | Crea la tabla `estudiante` si no existe. |
| `guardar(Estudiante e)` | Inserta un nuevo estudiante en la base de datos. |
| `listar()` | Retorna un `ArrayList<Estudiante>` con todos los estudiantes registrados. |
| `buscarPorId(int id)` | Retorna un estudiante según su ID, o `null` si no existe. |
| `actualizar(Estudiante e)` | Actualiza los datos de un estudiante existente. |
| `eliminar(int id)` | Elimina un estudiante según su ID. |

### `Main` (main)

Contiene el menú principal del sistema y los métodos privados para registrar, listar, buscar, actualizar y eliminar estudiantes, comunicándose con `EstudianteDAO`.

## Base de datos

Motor: **SQLite**
Nombre de la base de datos: `universidad.db`

```sql
CREATE TABLE estudiante (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    cedula TEXT NOT NULL,
    nombres TEXT NOT NULL,
    apellidos TEXT NOT NULL,
    correo TEXT,
    carrera TEXT,
    semestre INTEGER
);
```

La tabla se crea automáticamente al ejecutar el programa mediante el método `crearTabla()` del DAO.

## Principios POO aplicados

-  **Encapsulamiento:** atributos privados en `Estudiante`, accesibles solo mediante getters y setters.
-  **Objetos y clases:** cada estudiante es una instancia de la clase `Estudiante`.
-  **Constructores:** constructor vacío y constructores con parámetros en `Estudiante`.
-  **Sobrecarga de constructores:** dos constructores con parámetros (con y sin `id`).
-  **Métodos:** operaciones bien definidas en `EstudianteDAO` y en `Main`.
-  **Colecciones:** uso de `ArrayList<Estudiante>` en el método `listar()`.
-  **Patrón DAO:** separación de la lógica de acceso a datos en `EstudianteDAO`.
-  **Separación por paquetes:** `modelo`, `conexion`, `dao` y `main`.

## Menú y ejecución del sistema
