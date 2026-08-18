# Sistema de Gestión Hospitalaria (Microservicios)

Una implementación de referencia de una arquitectura de microservicios para la gestión hospitalaria. Está pensada para demostrar comunicación síncrona (OpenFeign) y asíncrona (Apache Kafka), descubrimiento de servicios (Eureka), un API Gateway y varios microservicios independientes con sus propias bases de datos. El código principal está en Java 17 y usa Spring Boot / Spring Cloud.

> Nota: el README incorpora el diagrama de arquitectura que compartiste (gateway, Eureka, Kafka, servicios, bases de datos). Revisa los `application.properties` / `application.yml` de cada módulo para puertos y configuraciones concretas — aquí se incluyen valores indicativos que aparecen en el diagrama.

Características principales
- Arquitectura de microservicios con Spring Cloud (Eureka, Gateway).
- Comunicación síncrona entre servicios con OpenFeign.
- Comunicación asíncrona con Apache Kafka (eventos como `appointment-created`, `payment-completed`).
- Servicios independientes con persistencia (MariaDB / JDBC).
- Modular: proyecto Maven multi-módulo.

Stack
- Lenguaje: Java 17
- Framework / runtime: Spring Boot + Spring Cloud (Spring Cloud Version: 2025.1.2 según parent POM)
- Dependencias notables: Spring Cloud Gateway, Eureka Client, OpenFeign, spring-kafka, Spring Data JPA, MariaDB JDBC, Lombok, Jackson

Módulos (estructura de alto nivel)
- discovery-server/      - Servidor de descubrimiento (Eureka)
- api-gateway/           - API Gateway (Spring Cloud Gateway)
- patient-service/       - Servicio de gestión de pacientes
- doctor-service/        - Servicio de gestión de médicos
- appointment-service/   - Servicio de citas (publica eventos a Kafka)
- payment-service/       - Servicio de pagos (consume/produce eventos Kafka)
- common-dtos/           - DTOs compartidos entre servicios (artefacto common)

Cómo está organizado (resumen)
- Cada microservicio es una aplicación Spring Boot independiente (módulos Maven).
- El parent `pom.xml` define el multi-módulo.
- `common-dtos` contiene los modelos/DTOs compartidos para serialización entre servicios y Kafka.
- `docker-compose.yml` provee un contenedor Kafka (KRaft) usado por los microservicios para mensajería.

Preparación (requisitos)
- Java 17 (JDK)
- Maven (o usar el wrapper incluido: `./mvnw`)
- Docker & Docker Compose (para levantar Kafka rápidamente)
- Opcional: MariaDB / una base de datos compatible si quieres persistencia real (los servicios tienen dependencias MariaDB/ojdbc según módulo)

Inicio rápido (entorno de desarrollo)
1. Clonar el repositorio
   git clone https://github.com/victordaniel123rt-lang/Hospital-Management-System.git
   cd Hospital-Management-System

2. Levantar Kafka (docker-compose)
   docker-compose up -d
   Esto empieza el broker Kafka definido en `docker-compose.yml`.

3. Construir el proyecto (módulos Maven)
   ./mvnw clean package -DskipTests

4. Ejecutar los microservicios (en terminales separados)
   - Primero el discovery-server (Eureka):
     ./mvnw -pl discovery-server spring-boot:run
   - Luego el api-gateway:
     ./mvnw -pl api-gateway spring-boot:run
   - Servicios (ejemplo, en terminales separados):
     ./mvnw -pl patient-service spring-boot:run
     ./mvnw -pl doctor-service spring-boot:run
     ./mvnw -pl appointment-service spring-boot:run
     ./mvnw -pl payment-service spring-boot:run

   Nota: puedes importar todo como proyecto Maven en tu IDE y ejecutar cada clase `@SpringBootApplication` desde allí.

Puertos (valores indicativos del diagrama compartido)
- API Gateway: 8080
- Patient Service: 8081
- Doctor Service: 8082
- Appointment Service: 8083
- Payment Service: 8084
- Eureka Server: 8761
- Config Server (en el diagrama): 8888

Comprueba los `application.properties` / `application.yml` dentro de cada módulo para los puertos y otras propiedades reales.

Temas de mensajería (Kafka)
- Topics (según diagrama / código que integra Kafka):
  - appointment-created
  - payment-completed
  - (opcional) appointment-cancelled
- Flujo típico:
  1. Cliente crea una cita vía API Gateway -> Appointment Service (síncrono).
  2. Appointment Service publica `appointment-created` en Kafka.
  3. Payment Service consume/produce eventos (p. ej. procesa pago y publica `payment-completed`).
  4. Appointment Service puede escuchar `payment-completed` para confirmar estado.

Bases de datos
- El proyecto usa drivers para MariaDB (y en patient-service aparece `ojdbc11` si se quisiera conectar a Oracle).
- Cada servicio tiene su propia persistencia (bases de datos separadas según arquitectura de microservicios).

Construir y pruebas
- Compilar todo:
  ./mvnw clean package
- Ejecutar tests por módulo:
  ./mvnw -pl appointment-service test

Puntos importantes / recomendaciones
- Revisa las propiedades de conexión a Kafka y a las bases de datos en cada `src/main/resources/application*.yml|properties` antes de ejecutar.
- Si usas Docker para MariaDB, crea contenedores con volúmenes y credenciales coherentes con la configuración de los servicios.
- Para desarrollo local, levanta Kafka con `docker-compose` y ejecuta los servicios desde tu IDE para debug.
- Verifica temas de CORS/seguridad si vas a probar desde un front.

Estructura de directorios (resumen)
```
discovery-server/     # Servidor Eureka
api-gateway/          # Gateway (rutas hacia microservicios)
patient-service/      # Servicio paciente (JPA, controllers, repos)
doctor-service/       # Servicio doctor
appointment-service/  # Servicio citas (Kafka producer/consumer)
payment-service/      # Servicio pagos (consume/produce eventos)
common-dtos/          # DTOs compartidos entre servicios
docker-compose.yml    # Compose para Kafka (KRaft)
pom.xml               # Parent POM (módulos)
```

Comprobación rápida (health)
- Comprueba Eureka en: http://localhost:8761 (si discovery-server está corriendo).
- Comprueba gateway en: http://localhost:8080 (rutas /api/...)
- Revisa los logs de cada microservicio para confirmar conexiones a Kafka y registros de temas.

Preguntas frecuentes y troubleshooting
- Si los servicios no se registran en Eureka: confirma `spring.application.name` y `eureka.client.service-url.defaultZone`.
- Si Kafka no recibe/produce mensajes: confirma `spring.kafka.bootstrap-servers` y que el contenedor kafka esté accesible desde la red (puerto 9092).
- Problemas con drivers DB (MariaDB / Oracle): asegúrate de tener las credenciales y la URL correctas en `application.properties`.

Contribuciones
- Abre issues con ideas o bugs y envía pull requests. Mantén los cambios por módulo y actualiza `common-dtos` cuando cambien los contratos.

Licencia
- Añade el archivo LICENSE si quieres publicar con una licencia concreta. (No incluido en este repo por defecto.)

---

Si quieres, puedo:
- Crear el archivo README.md en el repo (si lo deseas, puedo preparar el commit).
- Generar ejemplos concretos de `application.yml` para cada servicio (puertos, Kafka, datasource).
- Añadir instrucciones para levantar MariaDB con Docker Compose y scripts de inicialización.
