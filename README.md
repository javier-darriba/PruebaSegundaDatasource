# PruebaSegundaDatasource
Este es un proyecto Java basado en la gestión de productos en un inventario. Incluye funcionalidades para agregar, eliminar, modificar, buscar y listar productos, además de una interfaz web implementada mediante Servlets y JSP y una base de datos que hace uso de Datasource.

## 1. Instalación

Para ejecutar este proyecto, es necesario:

1. Un servidor de aplicaciones compatible con Java EE, como Apache Tomcat.
2. Un entorno de desarrollo, como Eclipse.
3. Ejecuta el script.sql situado en /resource en un servidor sql
4. Clona el repositorio e importa el proyecto en tu IDE. Configura el servidor de aplicaciones para desplegar la aplicación.

## 2. Descripción

El proyecto proporciona un sistema para gestionar productos. Cada producto tiene un nombre, categoría, precio y stock. Se pueden realizar operaciones como:

- Agregar nuevos productos.
- Eliminar productos existentes.
- Modificar las propiedades de un producto.
- Buscar un producto indicando su nombre.
- Listar todos los productos disponibles.

## 3. Funcionalidades

### Gestión de Productos

- **Agregar Producto**: Permite crear un nuevo producto proporcionando los datos requeridos.
- **Eliminar Producto**: Elimina un producto del inventario seleccionándolo de una lista.
- **Modificar Producto**: Modifica las propiedades de un producto existente.
- **Buscar Productos**: Muestra el producto indicado por el nombre.
- **Listar Productos**: Muestra todos los productos disponibles.

### Lógica de Negocio

1. **Interfaz IGestionProducto**:
   Define los métodos básicos para la gestión:
   - `agregarProducto(Producto producto)`
   - `eliminarProducto(String nombre)`
   - `modificarProducto(Producto producto)`
   - `buscarProducto(String nombre)`
   - `listarProductos()`

2. **Implementación GestionProductoImpl**:
   La implementación de `IGestionProducto` utiliza una lista en memoria para gestionar los productos.

3. **Servlets**:
   - `AgregarProducto`: Permite agregar productos a la aplicación.
   - `EliminarProducto`: Gestiona la eliminación de un producto.
   - `ModificarProducto`: Modifica los atributos de un producto existente.
   - `BuscarProducto`: Busca un producto por su nombre.
   - `ListarProductos`: Despliega la lista de todos los productos.

## 4. Uso

### Ejemplo de uso de las funcionalidades

1. **Agregar Producto**:
   - Accede al formulario de agregar producto.
   - Proporciona los datos (nombre, categoría, precio y stock) y envía el formulario.

2. **Eliminar Producto**:
   - Selecciona un producto de la lista desplegable en la página de eliminación.
   - Haz clic en el botón para confirmar la eliminación.

3. **Modificar Producto**:
   - Selecciona un producto para modificar.
   - Completa el formulario cambiando los datos que consideres y envíalo.
  
4. **Buscar Producto**:
   - Introduce el nombre en el buscador y dale al botón.

5. **Listar Productos**:
   - Seleccion este enlace enla pagina principal y obserba la tabla de productos.
  
## 5. Contribuir

1. Haz un fork del repositorio.
2. Crea una rama para tu nueva funcionalidad: `git checkout -b mi-nueva-funcionalidad`.
3. Realiza tus cambios y haz commit: `git commit -am 'Añadida una nueva funcionalidad'`.
4. Haz push a tu rama: `git push origin mi-nueva-funcionalidad`.
5. Envía un Pull Request para revisión.

## 6. Historia

- **Versión 1.0 (17-12-2024)**: 
  - Lanzamiento inicial

## 7. Créditos y atribuciones

**Desarrollador principal**: Javier Darriba González

## 8. License

GNU GENERAL PUBLIC LICENSE
 Version 3, 29 June 2007

