# Modelo de Decisión Arquitectónica (MADR)
## 1. Contexto del Proyecto
### Descripción
Gaviota Parking es una API de software libre para la gestión de parkings, que controla la entrada y salida de vehículos mediante una barrera y consola, la generación de informes y administración del sistema con una consola para el administrador del parking, y por último, la compra de abonos y pago de tickets mediante una consola para los clientes del parking.

### Objetivos
- Control de entradas: Identificación de matrículas, apertura de barreras automáticas y emisión de tickets.
- Control de pagos: Pagos y validaciones de tickets y abonos.
- Control de salidas: Identificación de matrículas y tickets.
- Gestión de tarifas y bonos: Políticas de tarificación y bonos.
- Generación de informes: Ingresos y movimientos de vehículos.

### Alcance del Proyecto
Incluye todas las funcionalidades mencionadas, interacción con hardware y software externo, y configurabilidad para diferentes empresas.

## 2. Problema a Resolver
### Descripción del Problema
- Identificación de los requisitos funcionales y no funcionales.
- Elección de herramientas y lenguajes para la creación de diagramas de modelado.
- Elección de herramientas de gestión (control de versiones, comunicación, tareas, etc).
- Elección de herramientas de desarrollo (IDE, framework, etc).
- Decidir cómo de limpia será y qué estructura tendrá la arquitectura del proyecto.
- Elección de tecnologías para el desarrollo de las consolas.

 ## 3. Opciones Arquitectónicas Consideradas
 ### Herramienta de Control de Versiones
 - Opción *GitHub* -> Pros: Acceso gratuito, funcionalidades robustas, integración con otras herramientas.
   Contras: Ninguno relevante.
- Opción *Gitkraken* -> Pros: Mismas funcionalidades que Github pero con una interfaz más visual. Contras: Para disfrutar de ciertas ventajas extras hay que pagar.

### IDE de Desarrollo
- Opción *Eclipse* -> Pros: Gratuito, extensible, comunidad amplia. Contras: Interfaz no tan moderna, puede ser pesado.
- Opción *IntelliJ* -> Pros: Interfaz moderna, características avanzadas, soporte excelente. Contras: Versión completa de pago.
- Opción *Visual Studio Code* -> Pros: Gratuito, ligero, extensible, y sobre todo, estamos familiarizados con él. Contras: Integración con Java menos nativa que IntelliJ.

### Canal de Comunicación y Tareas
- Opción *Trello* -> Pros: Fácil de usar, visualmente intuitivo, gratuito para la mayoría de las funcionalidades. Contras: Limitado en características avanzadas sin suscripción.
-  Opción *GitHub Projects* -> Pros: Integración directa con repositorios, fácil seguimiento de issues y proyectos. Contras: Menos funcionalidad que herramientas dedicadas como Trello.
- Opción *Jira* -> Pros: Extremadamente potente, altamente configurable. Contras: Es de pago y curva de aprendizaje alta.

### Herramientas de Diseño y Modelado
- Opción *Mermaid* -> Pros: Gratuito, fácil de integrar con Markdown. Contras: Menos poderoso que PlantUML en algunos aspectos.
- Opción *PlantUML* ->  Pros: Amplia funcionalidad, soporta diversos diagramas. Contras: La sintaxis puede llegar a ser compleja.
- Opción *IcePanel* -> Pros: Ideal para modelos C4, interfaz amigable. Contras: La prueba gratuita dura poco tiempo, luego se vuelve de pago.
- Opción *Draw.io* -> Pros: Gratuito, fácil de usar, tenemos experiencia y plantillas hechas de otros proyectos con esta herramienta, integración con Google Drive y otras plataformas. Contras: Menos especializado para UML que PlantUML, puede ser menos eficiente para diagramas complejos.

### Gestor de Base de Datos
- Opción *MySQL* -> Pros: Estructura relacional, transacciones ACID, madurez y estabilidad, amplia comunidad y soporte.
Contras: Esquema fijo, escalabilidad vertical limitada, rendimiento inferior en consultas complejas.
- Opción *MongoDB* -> Pros: Base de datos NoSQL flexible, escalabilidad horizontal, alto rendimiento, replicación y tolerancia a fallos, índices flexibles.
Contras: Consistencia eventual, menor soporte para transacciones complejas, curva de aprendizaje.

### Tipo de Arquitectura
- Opción *Arquitectura Limpia* -> Pros: Independencia de frameworks, alta mantenibilidad, pruebas unitarias fáciles. Contras: Curva de aprendizaje, estructura compleja al inicio.
- Opción *Arquitectura en Capas* -> Pros: Simplicidad, fácil de entender y aplicar, ampliamente utilizada. Contras: Menor flexibilidad, puede llevar a acoplamiento fuerte entre capas.
- Opción *Arquitectura Basada en Microservicios* -> Pros: Escalabilidad, despliegue independiente, alineación con DevOps. Contras: Complejidad en la gestión, sobrecarga en comunicación entre servicios.
- Opción *Arquitectura Monolítica* -> Pros: Simplicidad en el desarrollo inicial, despliegue sencillo. Contras: Dificultad para escalar, mantenimiento complicado a largo plazo.

### Herramientas para la Creación de Consolas
- Opción *Spring Shell* -> Pros: Integración con Spring Framework, facilidad para crear aplicaciones CLI. Contras: Curva de aprendizaje para desarrolladores no familiarizados con Spring.
- Opción *Picocli* -> Pros: Ligero, fácil de usar, soporte para subcomandos, documentación extensa. Contras: Menos integración con otros frameworks en comparación con Spring Shell.
- Opción *JLine* -> Pros: Poderosa biblioteca para crear aplicaciones CLI interactivas, soporte para compleción de comandos, historial y edición en línea. Contras: Mayor complejidad en la configuración inicial, más bajo nivel que Spring Shell o Picocli.
- Opción *Bash* ->  Pros: Nativo en sistemas Unix, ampliamente utilizado, potente para scripts y automatización. Contras: Dependencia de entorno Unix, sintaxis menos intuitiva para desarrolladores no familiarizados.
- Opción *cmd2 (Python)* -> Pros: Fácil de usar, extensible, proporciona funcionalidades avanzadas de CLI. Contras: Dependencia de Python, no es nativo para entornos Java.


## 4. Decisiones Arquitectónicas
### Lenguaje de Programación
- Opción Seleccionada: *Java*.
- Justificación: Es un requisito impuesto por la asignatura para la que se desarrolla este proyecto.

###  Herramienta de software para la Gestión y Construcción
- Opción Seleccionada: *Maven*.
- Justificación: Es un requisito impuesto por la asignatura para la que se desarrolla este proyecto.

###  Framework de Desarrollo 
- Opción Seleccionada: *Spring Framework*.
- Justificación: Es un requisito impuesto por la asignatura para la que se desarrolla este proyecto.

### Herramienta de Control de Versiones
- Opción Seleccionada: *GitHub*.
- Justificación: Familiaridad del equipo, robustez y facilidad de uso.

### IDE de Desarrollo
- Opción Seleccionada: *Visual Studio Code*.
- Justificación: Familiaridad del equipo y facilidad de añadir diferentes plugins para este proyecto.

### Canal de Comunicación y Tareas
- Opción Seleccionada: *GitHub Projects*.
- Justificación: Por su integración directa con el repositorio.

### Herramientas de Diseño y Modelado
- Opción Seleccionada: *Draw.io* y *IcePanel*.
- Justificación: Por un lado, Draw.io porque es fácil de usar y contamos con plantillas de otros proyectos. Por otro lado, IcePanel por recomendación del profesor y por su interfaz intuitiva.

### Gestor de Base de Datos
- Opción Seleccionada: *MySQL*.
- Justificación: Familiaridad del equipo de trabajo y al ser una base de datos relacional, encaja mejor en la estructura de este proyecto.
### Tipo de Arquitectura
- Opción Seleccionada: *Arquitectura parcialmente limpia, por capas y monolítica*.
- Justificación:
Nuestro enfoque se basa en la flexibilidad y la modularidad desde su concepción. Si bien aprovechamos parcialmente el Framework Spring para la gestión de consolas y la API, hemos diseñado nuestro sistema para que los clientes futuros puedan cambiar de Framework con facilidad, sin comprometer la integridad del código base.
En cuanto al almacenamiento de datos, hemos adoptado una postura neutral, sin compromisos con sistemas específicos. Sin embargo, en el caso de que se requiera una base de datos en el futuro, nos orientamos hacia una solución relacional. No obstante, mantenemos la flexibilidad necesaria para adaptarnos a las preferencias de los clientes en cuanto a sistemas de gestión de bases de datos.
Por otro lado, nuestra arquitectura se organiza en diferentes capas que cumplen funciones específicas dentro de la aplicación. Contamos con la capa de presentación (Controller), encargada de gestionar las interacciones con el usuario; la capa de lógica de negocio (Service), donde se procesan las operaciones y reglas del negocio; y la capa de acceso a datos (Repository), responsable de interactuar con la persistencia de los datos. Cada una de estas capas está claramente definida y separada, lo que facilita la comprensión del sistema y su mantenimiento a lo largo del tiempo.
Finalmente, nuestra aplicación se despliega como un artefacto monolítico, lo que simplifica su implementación y gestión. Sin embargo, mantenemos la flexibilidad necesaria para evolucionar hacia una arquitectura más distribuida si las necesidades del proyecto así lo requieren en el futuro.

### Herramientas para la Creación de Consolas
- Opción Seleccionada: *Spring Shell*.
- Justificación: Recomendación del profesor, integración con Spring Framework y facilidad para crear aplicaciones CLI.

## 5. Impacto y Riesgo de las Decisiones Tomadas
- Impacto a Corto Plazo: Configuración más lenta, curva de aprendizaje muy alta.
- Impacto a Largo Plazo: Facilidad de mantenimiento y escalabilidad del proyecto, mejora en la colaboración y documentación.
- Riesgos y Mitigaciones: Riesgo: Dependencia de herramientas externas. Mitigación: Formación continua del equipo y evaluación periódica de las herramientas.

## 6. Plan de Implementación
La implementación se ha llevado a cabo en base a una serie de hitos:

### Hito 1 : Core
- Elicitación de requisitos funcionales y no funcionales del enunciado del proyecto.
- Creación de la documentación (Creación de diagramas UML/C4, MADR, Readme, etc).
- Creación del repositorio en Github para el proyecto.
- Asignación de tareas entre los desarrolladores.
- Creación del POM (Project Object Model) con Maven.
- Creación del core de la API usando Java y Maven (operaciones, clases de servicio, entidades
del dominio, repositorios, etc).
- Creación de batería de test para las operaciones de la API.

### Hito 2 : API HTTP con persistencia
- Creación del API HTTP usando Spring Framework.
- Implementación de persistencia sobre base de datos Mysql.
- Documentación continua.

### Hito 3 : Aplicaciones
- Creación de consolas para la gestión de clientes, administradores y la barrera, usando Spring Shell.
- Documentación continua.

## 7. Convenciones y Estilo de Programación
- Convención de Nomenclatura: Se seguirá el estándar de nomenclatura de Java para clases, métodos y variables, utilizando CamelCase para nombres de clases y métodos, y camelCase para variables.

- Indentación y Espaciado: Se utilizará una indentación de 4 espacios para mejorar la legibilidad del código. Se evitarán líneas de código con longitud excesiva, dividiéndolas cuando sea necesario para mantener un máximo de 100 caracteres por línea.

- Comentarios: Se fomentará el uso de comentarios descriptivos para explicar secciones complicadas de código, especialmente donde la intención del código no sea inmediatamente clara.

- Documentación de Código: Se empleará JavaDoc para documentar clases y métodos públicos, proporcionando descripciones claras de su propósito, parámetros y valores de retorno.

- Manejo de Excepciones: Se seguirán las mejores prácticas para el manejo de excepciones, evitando el uso excesivo de bloques try-catch y capturando excepciones específicas en lugar de excepciones genéricas.

- Pruebas Unitarias: Se escribirán pruebas unitarias exhaustivas utilizando JUnit para garantizar la robustez y la calidad del código. Las pruebas unitarias cubrirán tanto los casos de uso normales como los casos de borde.

## 8. Documentación Relacionada
-  Wiki Github: allí podrás encontrar la elicitación de requisitos, el diagrama de clases, los diagramas de secuencia, el diagrama de contexto, el diagrama de contenedores y el diagrama de componentes.
