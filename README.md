# Solución de Sergio del Rey Álvarez

## Datos
- [Repositorio de referencia](https://github.com/capCCA/capgemini-test/)
- [Perfil de github](https://github.com/sergiodelrey25)

## Descripción de la solución
La solución se basa en un backend hecho en Java, con Spring. Cuenta con dos endpoints (los pedidos según el enunciado). Una vez lanzada la aplicación, se puede consultar la documentación de la API Rest desde la ruta raíz. Para ver cómo lanzar la aplicación ver [Guía](#cómo-lanzar-la-aplicación).
Aparte del código propio del backend, también se pueden encontrar distintas pruebas:
- [Tests unitarios](https://github.com/sergiodelrey25/testOpenBank/tree/master/src/test/java/com/capgemini/test/code/entities)
- [Tests de integración](https://github.com/sergiodelrey25/testOpenBank/tree/master/src/test/java/com/capgemini/test/code/services)
- [Tests de aceptación](https://github.com/sergiodelrey25/testOpenBank/tree/master/src/test/java/com/capgemini/test/code/acceptance)

## Cómo lanzar la aplicación
En esta guía se describirá todo lo necesario para poder ejecutar la aplicación.
### Prerrequisitos
- Java 17
- Maven
- Docker (instalado y ejecutándose)
### Proceso de ejecución
- Entrar a la carpeta /docker y ejecutar `$docker-compose up` 
- Una vez desplegados los contenedores, ejecutar la aplicación .java desde CodeApplication.java
### Comprobación
Para comprobar que la aplicación se esté ejecutando sin problemas, acceder a [Documentación](http://localhost:8080), y comprobar que se muestra una interfaz de Swagger.