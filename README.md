# Inversiones


# Configuración MySql para sistemas basados en Unix

Instalar docker community edition según sistema operativo. Como se explica en el instructivo de docker, para testear la correcta instalación se puede usar la imagen “hello-world”.


Crear container de MySql
Crear un directorio par guardar la base (ej: ~/dev/utn/dds/mysql/db). Este directorio será el que contenga la base de datos en sí.
	
Ejecutar el comando que se encuentra a continuación (en rojo se encuentra la parte dependiente del paso “2.a”). Si la creación se ejecutó correctamente docker devuelve el id del contenedor creado. En este caso particular como estamos nombrando el container como “mysql” no nos interesa el id.

Comando:
.$ docker run --name mysql -v ~/dev/utn/dds/mysql/db:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql

Checkear si el contenedor está levantado ejecutando .$ docker ps -a



Para levantar el container si estuviera bajo usamos docker start <nombre del container>, para bajarlo docker stop <nombre del container>, en nuestro caso el nombre del container es “mysql”.

Inicializar base
Descargar script mysqlScript.sql.
Ejecutar el comando que se encuentra a continuación (en rojo se encuentra la parte dependiente del paso “3.a”). Con esto estamos creando la base de datos de la aplicación y las tablas para guardar el dominio existentes hasta hoy.

Comando:
.$ cat ./mysqlScript.sql | docker exec -i mysql mysql -uroot -ppassword


Por defecto, si no dio ningún error podemos asumir que salió todo bien, pero para estar seguro se puede hacer lo siguiente.

.$ docker exec -it mysql mysql -uroot -ppassword
mysql> use INVERSIONES
mysql> show tables;


# Instalación de redis en docker

docker run --name some-redis -p6379:6379 -d redis
