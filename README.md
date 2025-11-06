# ActividadNucleo3LPPN
# CajeroApp – Proyecto de Microservicio (Spring Boot)

Este proyecto simula el funcionamiento básico de un **cajero automático**, desarrollado en **Spring Boot** con conexión a **MySQL**.
Incluye operaciones de **login, retiro, consignación y transferencia** entre clientes.

---

## ⚙️ Requisitos previos

* **Java 17 o superior**
* **Maven 3.9+**
* **MySQL 8.0 o superior**
* Un IDE como *Eclipse*, *IntelliJ IDEA* o *VS Code*

---

##  Configuración de base de datos

1. **Crear la base de datos**

   ```sql
   CREATE DATABASE cajero_bd;
   USE cajero_bd;
   ```

2. **Crear el usuario**

   ```sql
   CREATE USER 'cajero_user'@'localhost' IDENTIFIED BY '1234';
   GRANT ALL PRIVILEGES ON cajero_bd.* TO 'cajero_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Importar el archivo SQL incluido en el proyecto**

   ```sql
   SOURCE cajero_bd_clientes.sql;
   ```

   Este archivo contiene 3 clientes iniciales

##  Configuración del proyecto

El archivo `src/main/resources/application.properties` ya está configurado con los siguientes valores:

```properties
spring.application.name=LengApp
server.port=8810
spring.datasource.url=jdbc:mysql://localhost:3306/cajero_bd
spring.datasource.username=cajero_user
spring.datasource.password=1234
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

---

## 🚀 Ejecución

Para ejecutar el proyecto:

```bash
mvn spring-boot:run
```

O desde tu IDE, ejecuta la clase principal con la anotación

```java
@SpringBootApplication
```

Una vez iniciado el servidor, abre en el navegador:

 **[http://localhost:8810/login](http://localhost:8810/login)**

---

##  Funcionalidades

* **Inicio de sesión:** con documento y clave (máx. 3 intentos)
* **Bloqueo automático:** el usuario se bloquea 10 minutos tras 3 intentos fallidos
* **Retiro:** descuenta del saldo actual
* **Consignación:** incrementa el saldo
* **Transferencia:** mueve dinero entre dos clientes existentes
* **Cierre automático:** por inactividad o al cerrar sesión

---



## 👨‍🏫 Autor

**JAIR NEUTA.**
Proyecto desarrollado como parte de la *Actividad Núcleo 3* .

---
