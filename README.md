# Nombre del Proyecto: GaviotaParking

## Autores

- Marcos Pérez Fernández
- Alejandro Sánchez Castillo

## Descripción Breve

GaviotaParking es una API desarrollada en Java utilizando Maven como herramienta de gestión de dependencias y construcción. Esta API está diseñada para proporcionar servicios web RESTful usando Spring Framework parcialmente, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre un sistema de almacenamiento, ayudando así a la gestión de un sistema de parking real.

## Instrucciones de Instalación

### Requisitos Previos

- Java JDK 8 o superior
- Maven 4.0.0 o superior
- Spring Framework 3.2.5 o superior

### Clonar el Repositorio
`git clone https://github.com/Ascastillo98/dss2023-2024GaviotaPk`

### Construir el Proyecto
`mvn clean install`

### Configuración de la Base de Datos
`src/main/resources/application.properties`

### Ejecutar Aplicación
Para arrancar los servicios de la API, puedes utilizar el siguiente comando:
`mvn spring-boot:run`

Para utilizar las consolas de comandos con las que hacer un uso fundamental de la API, tenemos que situarnos en el directorio de la consola y ejecutar los comandos

Para la consola de admin y de barrera:

```sh
./mvnw clean install -DskipTests
java -jar target/Gaviotapk-0.0.1-SNAPSHOT.jar
```
Para la consola de los clientes:

```sh
./mvnw clean install -DskipTests
java -jar target/Gaviotapk-0.0.1-SNAPSHOT.jar
```

La documentación esta disponible con javadoc, se crea con el comando

```sh
mvn javadoc:javadoc
```


## Instrucciones para Contribuir al Proyecto
1. Haz un fork del repositorio.
2. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
3. Realiza tus cambios y haz commit (git commit -am 'Añadir nueva funcionalidad').
4. Sube tus cambios a la rama (git push origin feature/nueva-funcionalidad).
5. Abre un Pull Request.

## Información de Contacto
Para cualquier duda o sugerencia, puedes contactarnos en:
- Marcos Pérez Fernández: marcos.perezfernandez@alum.uca.es
- Alejandro Sánchez Castillo: alejandro.sanchezcastillo@alum.uca.es

También puedes abrir un issue en el repositorio de GitHub.

¡Gracias por usar GaviotaParking! Esperamos que encuentres útil nuestra API y estamos ansiosos por recibir tus comentarios y contribuciones.
