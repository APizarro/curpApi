# curpApi Secretaria de Gobernacion
Permite obtener datos de https://consultas.curp.gob.mx/CurpSP/ utilizando
la Clave Unica de Registro de Poblacion.




# Metodos

### Inicializacion


```java
    downloadCaptcha()

```
 Descarga una imagen captcha del servidor y obtiene una cookie de sesion
correspondiente a la captcha, debe ejecutars eeste comando para inicializar

```java
    getData(String captcha,String curp)

```
Obtiene los datos personales del servidor y los asigna a las variables, devuelve

* true: Si la consulta fue exitosa
* throws Exception: Si ha ocurrido un error


### Recuperar datos

```java
  getCurp()
```
Devuelve en un string la CURP.


```java
  getNombre()
```
Devuelve el nombre.

```java
  getaPaterno()
```
Devuelve el apellido paterno.


```java
  getaMaterno()
```
Devuelve el apellido materno.

```java
  getSexo()d
```
Devuelve el sexo en un string en el formato.
* HOMBRE
* MUJER

```java
getfNacimiento()
```
Devuelve la fecha de nacimiento en el formato.
* DD/MM/AAAA

```java
geteNacimiento()
```
Devuelve la entidad de nacimiento.

```java
 getCodeEntidad()
```
Devuelve el codigo y la entidad de nacimiento en el formato

* XX ENTIDAD

Donde XX es el codigo numerico de la entidad.

```java
 getCodeMunicipio()
```
Devuelve el codigo numerico del municipio seguido del nombre de este en el
formato
* XXX municipio
Donde XXX es el codigo numerico de 3 digitos del municipio siempre con ceros a
la izquierda por ejemplo 001
