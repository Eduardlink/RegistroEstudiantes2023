
# Registro de estudiantes 🎓

En este repositorio se presenta un sistema donde se registra los estudiantes matriculados en una institución educativa.


## Caracteríticas

### Usuario administrador/a
- Inicio de sesión con huella digital
- Gestión de estudiantes (CRUD)
- Gestión de usuarios (CRUD)

### Usuario secretaria/o
- Inicio de sesión con huella digital
- Gestión de estudiantes (CRUD)
- Visualización de reportes en formato pdf

## Requerimientos de software

### Tecnologias
- Java

### Sistema Operativo

El sistema operativo principal utilizado será Windows, tanto para el servidor como para los dispositivos clientes. Aunque al ser una aplicación java, se garantiza que funciones en otros sistemas operativos como MacOS o distribuciones Linux, aunque para que funcione el sensor de huellas se deberá obtener los drivers correspondientes para cada sistema operativo.

### Controladores y complementos

El sistema necesita drivers para conectarse con el sensor de huella, el driver que se usa actualmente es DPFingerPrintDriver que es compatible con sistemas Windows.

El sistema requiere que tenga instalado el Java Development Kit en su versión 8, para el correcto funcionamiento del mismo.

El controlador del lector de huellas se encuentra en la carpeta Driver

## Requerimientos de hardware

Sensor de huellas Digital Persona B100E16647, que será útil para la verificación de credenciales del usuario durante el inicio de sesión

## Authors

- [@ChristianChico](https://github.com/ChristianCLop)
- [@AnthonySolis](https://github.com/Anthony6887)
- [@EstefaníaMora](https://github.com/Yachitzu)
