# Yogurt-Batch

Este proyecto es una API hecha con Java y Spring Boot para manejar el proceso de elaboracion de yogurt. Permite trabajar con recetas, ingredientes, lotes de produccion y registros de temperatura.

La idea del sistema es poder crear recetas de yogurt, iniciar lotes usando esas recetas, cambiar el estado del lote durante el proceso y consultar informacion de monitoreo.

## Tecnologias

- Java 21
- Spring Boot
- Maven
- Spring Data JPA
- H2 Database
- Lombok
- Swagger / OpenAPI

## Funciones principales

- Crear, buscar, actualizar, activar y desactivar recetas.
- Guardar ingredientes asociados a cada receta.
- Manejar lotes de yogurt y sus estados.
- Consultar registros y resumenes de temperatura.
- Revisar la API desde Swagger.

## Como ejecutar

Desde la carpeta del proyecto:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
.\mvnw.cmd spring-boot:run
```

La aplicacion corre en:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

H2 Console:

```text
http://localhost:8080/h2-console
```

## Nota

La parte de recetas es la mas completa. Algunas funciones de lotes y temperatura todavia estan en desarrollo.
