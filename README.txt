
 "PARCIAL ALGORITMO SISTEMA DE GESTIÓN DE SUPERMERCADO"
 


DESCRIPCIÓN:
Sistema base con diseño moderno, colores vibrantes y menú con iconos emoji.
Orientado a ser visualmente atractivo y profesional.

ARCHIVOS (8 en total):
  1. ModlProducto.java           - Modelo de datos para productos
  2. ModlTipoCliente.java        - Enum con tipos de cliente (VIP, Adulto Mayor, Regular)
  3. ModlCliente.java            - Modelo de datos para clientes
  4. StructuresNodoAVL.java      - Nodo individual del árbol AVL
  5. StructuresArbolAVL.java     - Estructura AVL para inventario
  6. StructuresColaPrioridad.java - Cola de prioridad para clientes
  7. logicSuper.java             - Lógica de negocio principal
  8. MenuUsuarioCliente.java     - Interfaz de usuario con menú principal

CARACTERÍSTICAS:
  - Diseño visual con arte ASCII "SUPER"
  - Colores brillantes: magenta, verde, amarillo, cian, azul
  - Iconos emoji: 📝🔎📊✏️🗑️👤📋🔔⚡
  - Menú organizado por módulos (Productos / Clientes)
  - Opción 0 para salir
  - Confirmaciones con S/N


ESTRUCTURA DEL MENÚ:
  [1-5]  Módulo de Productos
  [6-9]  Módulo de Clientes  
  [0]    Salir

ESTILO DE MENSAJES:
  ✓ Producto registrado exitosamente
  ✗ Error: Ya existe un producto con ese código
  ⚠ Esto procesará TODOS los clientes en cola

CÓMO EJECUTAR:
  javac *.java
  java MenuUsuarioCliente

PÚBLICO OBJETIVO:
  Usuarios que buscan una experiencia visual moderna y amigable.
