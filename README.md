# Documentación de la Aplicación de Recetas

## Descripción
La **Aplicación de Recetas** tiene como propósito proporcionar información detallada sobre diversas recetas culinarias. Entre los elementos más destacados que se presentan en la aplicación se incluyen:

- **Nombre de la receta**
- **Descripción de la receta**
- **Imagen ilustrativa**
- **Ubicación**

## Patrones de Arquitectura y Diseño
La aplicación ha sido desarrollada siguiendo una serie de patrones de diseño que garantizan su estructura y mantenibilidad:

- **Clean Architecture (Arquitectura Limpia):** Se ha implementado este patrón a nivel macro para garantizar una separación efectiva de las capas de la aplicación.
- **MVVM:** Se ha adoptado el patrón Model-View-ViewModel a nivel del módulo de la aplicación (app) para una gestión eficiente de la interfaz de usuario.

- Se han utilizado varios **patrones de diseño**, como Factory, Adapter, Observer y Prototype, entre otros, para abordar necesidades específicas del desarrollo.

| **Importante:** Los principios SOLID se han tenido en cuenta durante todo el proceso de desarrollo. |
|------------------|

## Componentes Jetpack Utilizados
La aplicación hace uso de componentes de Jetpack de Android para optimizar su rendimiento y experiencia de usuario:


| Componente              | Descripción                                                                      |
|------------------------|----------------------------------------------------------------------------------|
| ViewModel              | Para la gestión de datos y la comunicación entre la interfaz de usuario y la lógica de la aplicación.|
| Hilt                   | Se utiliza para la inyección de dependencias.                                   |
| Compose                | Para la creación de la interfaz de usuario mediante programación declarativa.  |
| Navigation Compose     | Facilita la navegación dentro de la aplicación.                                |
| State                  | Para gestionar el estado de la aplicación.                                     |
| Material Design        | Se ha seguido el diseño de Material Design para una interfaz atractiva y coherente.|
| Catálogo de Versiones  | Se mantiene un catálogo actualizado de las versiones utilizadas.               |

## Librerías Externas
Se han incorporado diversas librerías externas para mejorar la funcionalidad y eficiencia de la aplicación:

| Librería                 | Descripción                                                                                                          | Documentación                                        |
|--------------------------|----------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| Coil                     | Se utiliza para cargar imágenes desde URL.                                                                          | [Documentación de Coil](https://coil-kt.github.io/coil/compose/) |
| Retrofit                 | Para el consumo de servicios web.                                                                                   | [Documentación de Retrofit](https://square.github.io/retrofit/) |
| Moshi                    | Se emplea como convertidor JSON en conjunto con Retrofit.                                                          | [Documentación de Moshi](https://github.com/square/retrofit/blob/master/retrofit-converters/moshi/README.md) |
| Logging-Interceptor      | Para imprimir respuestas de servicios en modo de depuración.                                                        | [Documentación de Logging-Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) |
| MockWebServer            | Utilizado para simular un servidor HTTP durante pruebas.                                                            | [Documentación de MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) |
| MockK                    | Para simular información en pruebas unitarias.                                                                      | [Documentación de MockK](https://mockk.io/)           |
| Maps-Compose             | Utilizado para mostrar mapas en la aplicación.                                                                     | [Documentación de Maps-Compose](https://developers.google.com/maps/documentation/android-sdk/maps-compose) |

## Plugins Externos
Se han empleado plugins externos que mejoran la calidad y mantenimiento del código:

| Plugin                       | Descripción                                                                                                                                                       | Documentación                                        |
|------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| Gradle-Versions-Plugin        | Se utiliza para mantener actualizado el catálogo de versiones.                                                                                            | [Documentación](https://github.com/ben-manes/gradle-versions-plugin) |
| Detekt                       | Para revisión de código limpio.                                                                                                                                  | [Documentación](https://detekt.dev/)                   |
| Spotless                     | Se automatiza la eliminación de importaciones no utilizadas y se agrega una cabecera a los archivos.  | [Documentación](https://github.com/diffplug/spotless)  |
| KtLint                       | Se utiliza para el formateo automático del código.                                                    | [Documentación](https://github.com/JLLeitschuh/ktlint-gradle) |
| Jacoco                       | Se ha implementado para comprobar la cobertura de código y se puede conectar a SonarQube.          | [Documentación](https://docs.gradle.org/current/userguide/jacoco_plugin.html) |

## Notas Importantes
Para garantizar la eficiencia y calidad del desarrollo, se han tomado las siguientes medidas:

- Se ha configurado un módulo específico para personalizar plugins y centralizar la generación de variables de compilación.
- Se ha creado un módulo dedicado a pruebas integrales.
