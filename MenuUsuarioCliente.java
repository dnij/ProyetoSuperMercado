import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUsuarioCliente {
    private final logicSuper sistemaGestion;
    private final Scanner entradaDatos;
    
    // Paleta de colores personalizada
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_TITULO = "\u001B[95m";      // Magenta brillante
    private static final String COLOR_EXITO = "\u001B[92m";       // Verde brillante
    private static final String COLOR_ERROR = "\u001B[91m";       // Rojo brillante
    private static final String COLOR_INFO = "\u001B[96m";        // Cian brillante
    private static final String COLOR_ADVERTENCIA = "\u001B[93m";  // Amarillo brillante
    private static final String COLOR_OPCION = "\u001B[94m";      // Azul brillante
    private static final String ESTILO_NEGRITA = "\u001B[1m";
    private static final String ESTILO_SUBRAYADO = "\u001B[4m";
    
    public MenuUsuarioCliente() {
        sistemaGestion = new logicSuper();
        entradaDatos = new Scanner(System.in);
    }
    
    
    public void iniciar() {
        int opcionSeleccionada = 0;
        do {
            borrarPantalla();
            dibujarEncabezadoPrincipal();
            presentarOpcionesMenu();
            
            try {
                opcionSeleccionada = Integer.parseInt(entradaDatos.nextLine());
                procesarSeleccion(opcionSeleccionada);
            } catch (NumberFormatException excepcion) {
                mostrarError(" Entrada inválida. Por favor ingrese solo números.");
                esperarUsuario();
            } catch (InputMismatchException excepcion) {
                mostrarError(" Formato incorrecto detectado.");
                entradaDatos.nextLine();
                esperarUsuario();
            }
        } while (opcionSeleccionada != 0);
        
        despedida();
    }
    
    private void presentarOpcionesMenu() {
        System.out.println(COLOR_ADVERTENCIA + "\n   ┌─────────────────────────────────────────────────────────┐");
        System.out.println("   │  Seleccione el número de la operación que desea realizar │");
        System.out.println("   └─────────────────────────────────────────────────────────┘" + COLOR_RESET);
        
        System.out.println(ESTILO_NEGRITA + COLOR_TITULO + "\n   ╔══════════════════════════════════════════════════╗");
        System.out.println("   ║            MÓDULO DE PRODUCTOS DE SUPER                ║");
        System.out.println("   ╚══════════════════════════════════════════════════╝" + COLOR_RESET);
        
        System.out.println(COLOR_OPCION + "      [1] ->  Registrar nuevo artículo en inventario");
        System.out.println("      [2] ->  Consultar información de producto");
        System.out.println("      [3] ->  Visualizar catálogo completo");
        System.out.println("      [4] ->   Modificar datos de producto existente");
        System.out.println("      [5] ->  Remover artículo del sistema" + COLOR_RESET);
        
        System.out.println(ESTILO_NEGRITA + COLOR_TITULO + "\n   ╔══════════════════════════════════════════════════╗");
        System.out.println("   ║            MÓDULO DE ATENCIÓN AL CLIENTES      ║");
        System.out.println("   ╚══════════════════════════════════════════════════╝" + COLOR_RESET);
        
        System.out.println(COLOR_OPCION + "      [6] ->  Registrar nuevo cliente en fila");
        System.out.println("      [7] ->  Mostrar lista de espera actual");
        System.out.println("      [8] ->  Procesar siguiente cliente en cola");
        System.out.println("      [9] -> Ejecutar atención automática completa" + COLOR_RESET);
        
        System.out.println(ESTILO_NEGRITA + COLOR_TITULO + "\n   ╔══════════════════════════════════════════════════╗");
        System.out.println("   ║            FINALIZAR SESIÓN DEL SISTEMA                   ║");
        System.out.println("   ╚══════════════════════════════════════════════════╝" + COLOR_RESET);
        
        System.out.println(COLOR_OPCION + "      [0] -> Cerrar aplicación" + COLOR_RESET);
        
        System.out.print(COLOR_ADVERTENCIA + "\n   ► Tu elección: " + COLOR_RESET);
    }
    
    private void procesarSeleccion(int numeroOpcion) {
        switch (numeroOpcion) {
            case 1 -> operacionRegistrarProducto();
            case 2 -> operacionConsultarProducto();
            case 3 -> operacionMostrarCatalogo();
            case 4 -> operacionActualizarProducto();
            case 5 -> operacionEliminarProducto();
            case 6 -> operacionAgregarCliente();
            case 7 -> operacionVisualizarCola();
            case 8 -> operacionAtenderUnCliente();
            case 9 -> operacionSimulacionCompleta();
            case 0 -> { /* Salir - no hace nada */ }
            default -> mostrarError(" La opción ingresada no existe en el menú.");
        }
        
        if (numeroOpcion != 0) esperarUsuario();
    }
    
    // ═══════════════════ OPERACIONES DE PRODUCTOS ═══════════════════
    
    private void operacionRegistrarProducto() {
        imprimirTituloSeccion("REGISTRO DE NUEVO PRODUCTO");
        
        System.out.print(COLOR_INFO + "   • Código único del producto: " + COLOR_RESET);
        String codigoProducto = entradaDatos.nextLine();
        
        System.out.print(COLOR_INFO + "   • Nombre descriptivo: " + COLOR_RESET);
        String nombreProducto = entradaDatos.nextLine();
        
        System.out.print(COLOR_INFO + "   • Categoría o departamento: " + COLOR_RESET);
        String categoriaProducto = entradaDatos.nextLine();
        
        System.out.print(COLOR_INFO + "   • Precio de venta (Q): " + COLOR_RESET);
        double precioProducto = Double.parseDouble(entradaDatos.nextLine());
        
        System.out.print(COLOR_INFO + "   • Cantidad en existencia: " + COLOR_RESET);
        int stockProducto = Integer.parseInt(entradaDatos.nextLine());
        
        String respuesta = sistemaGestion.agregarProducto(codigoProducto, nombreProducto, 
                                                          categoriaProducto, precioProducto, stockProducto);
        
        if (respuesta.contains("Error")) {
            mostrarError(respuesta);
        } else {
            mostrarExito(respuesta);
        }
    }
    
    private void operacionConsultarProducto() {
        imprimirTituloSeccion("BÚSQUEDA DE PRODUCTO");
        
        System.out.print(COLOR_INFO + "   • Ingrese el código a buscar: " + COLOR_RESET);
        String codigoBusqueda = entradaDatos.nextLine();
        
        ModlProducto productoEncontrado = sistemaGestion.buscarProducto(codigoBusqueda);
        
        if (productoEncontrado != null) {
            System.out.println(COLOR_EXITO + "\n PRODUCTO LOCALIZADO EN EL SISTEMA" + COLOR_RESET);
            System.out.println(COLOR_INFO + "   ─────────────────────────────────────────────" + COLOR_RESET);
            System.out.println(productoEncontrado);
            System.out.println(COLOR_INFO + "   ─────────────────────────────────────────────" + COLOR_RESET);
        } else {
            mostrarError(" No se encontró ningún producto con ese código.");
        }
    }
    
    private void operacionMostrarCatalogo() {
        imprimirTituloSeccion("CATÁLOGO COMPLETO DE PRODUCTOS");
        sistemaGestion.mostrarInventario();
    }
    
    private void operacionActualizarProducto() {
        imprimirTituloSeccion("ACTUALIZACIÓN DE PRODUCTO");
        
        System.out.print(COLOR_INFO + "   • Código del producto a modificar: " + COLOR_RESET);
        String codigoModificar = entradaDatos.nextLine();
        
        if (sistemaGestion.buscarProducto(codigoModificar) == null) {
            mostrarError(" El producto especificado no existe en el inventario.");
            return;
        }
        
        System.out.print(COLOR_INFO + "   • Nombre actualizado: " + COLOR_RESET);
        String nombreNuevo = entradaDatos.nextLine();
        
        System.out.print(COLOR_INFO + "   • Categoría actualizada: " + COLOR_RESET);
        String categoriaNueva = entradaDatos.nextLine();
        
        System.out.print(COLOR_INFO + "   • Precio actualizado (Q): " + COLOR_RESET);
        double precioNuevo = Double.parseDouble(entradaDatos.nextLine());
        
        System.out.print(COLOR_INFO + "   • Stock actualizado: " + COLOR_RESET);
        int stockNuevo = Integer.parseInt(entradaDatos.nextLine());
        
        String resultado = sistemaGestion.actualizarProducto(codigoModificar, nombreNuevo, 
                                                             categoriaNueva, precioNuevo, stockNuevo);
        mostrarExito(resultado);
    }
    
    private void operacionEliminarProducto() {
        imprimirTituloSeccion("ELIMINACIÓN DE PRODUCTO");
        
        System.out.print(COLOR_INFO + "   • Código del producto a eliminar: " + COLOR_RESET);
        String codigoEliminar = entradaDatos.nextLine();
        
        System.out.print(COLOR_ADVERTENCIA + "   ¿Confirma la eliminación? (S/N): " + COLOR_RESET);
        String confirmacion = entradaDatos.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            String resultado = sistemaGestion.eliminarProducto(codigoEliminar);
            if (resultado.contains("no encontrado")) {
                mostrarError(resultado);
            } else {
                mostrarExito(resultado);
            }
        } else {
            System.out.println(COLOR_INFO + "   ✓ Operación cancelada." + COLOR_RESET);
        }
    }
    
    // ═══════════════════ OPERACIONES DE CLIENTES ═══════════════════
    
    private void operacionAgregarCliente() {
        imprimirTituloSeccion("REGISTRO DE CLIENTE EN COLA");
        
        System.out.print(COLOR_INFO + "   • Nombre completo del cliente: " + COLOR_RESET);
        String nombreCliente = entradaDatos.nextLine();
        
        System.out.println(COLOR_ADVERTENCIA + "\n   Categorías disponibles:");
        System.out.println("      [1]  VIP (Prioridad Alta)");
        System.out.println("      [2]  Adulto Mayor (Prioridad Media)");
        System.out.println("      [3]  Regular (Prioridad Normal)" + COLOR_RESET);
        
        System.out.print(COLOR_INFO + "   • Seleccione categoría [1-3]: " + COLOR_RESET);
        int categoriaNumero = Integer.parseInt(entradaDatos.nextLine());
        
        ModlTipoCliente categoriaCliente = ModlTipoCliente.REGULAR;
        if (categoriaNumero == 1) categoriaCliente = ModlTipoCliente.VIP;
        else if (categoriaNumero == 2) categoriaCliente = ModlTipoCliente.ADULTO_MAYOR;
        
        System.out.print(COLOR_INFO + "   • Total de productos a procesar: " + COLOR_RESET);
        int cantidadArticulos = Integer.parseInt(entradaDatos.nextLine());
        
        sistemaGestion.agregarCliente(new ModlCliente(nombreCliente, categoriaCliente, cantidadArticulos));
        mostrarExito("✓ Cliente registrado en la fila de atención.");
    }
    
    private void operacionVisualizarCola() {
        imprimirTituloSeccion("LISTA DE CLIENTES EN ESPERA");
        sistemaGestion.verCola();
    }
    
    private void operacionAtenderUnCliente() {
        imprimirTituloSeccion("ATENCIÓN AL SIGUIENTE CLIENTE");
        sistemaGestion.atenderSiguiente();
    }
    
    private void operacionSimulacionCompleta() {
        imprimirTituloSeccion("SIMULACIÓN DE ATENCIÓN AUTOMÁTICA");
        
        System.out.print(COLOR_ADVERTENCIA + "   Esto procesará TODOS los clientes en cola. ¿Continuar? (S/N): " + COLOR_RESET);
        String confirmacion = entradaDatos.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            sistemaGestion.simularTodo();
            mostrarExito("✓ Simulación completada exitosamente.");
        } else {
            System.out.println(COLOR_INFO + "   ✓ Simulación cancelada." + COLOR_RESET);
        }
    }
    
    // ═══════════════════ MÉTODOS AUXILIARES ═══════════════════
    
    private void dibujarEncabezadoPrincipal() {
        System.out.println(ESTILO_NEGRITA + COLOR_TITULO);
        System.out.println("\n   ███████╗██╗   ██╗██████╗ ███████╗██████╗ ");
        System.out.println("   ██╔════╝██║   ██║██╔══██╗██╔════╝██╔══██╗");
        System.out.println("   ███████╗██║   ██║██████╔╝█████╗  ██████╔╝");
        System.out.println("   ╚════██║██║   ██║██╔═══╝ ██╔══╝  ██╔══██╗");
        System.out.println("   ███████║╚██████╔╝██║     ███████╗██║  ██║");
        System.out.println("   ╚══════╝ ╚═════╝ ╚═╝     ╚══════╝╚═╝  ╚═╝");
        System.out.println(COLOR_RESET);
        System.out.println(COLOR_INFO + "   ═══════════════════════════════════════════════════════════" + COLOR_RESET);
        System.out.println(ESTILO_SUBRAYADO + COLOR_EXITO + "            Sistema Integral de Gestión Comercial v2.0" + COLOR_RESET);
        System.out.println(COLOR_INFO + "   ═══════════════════════════════════════════════════════════" + COLOR_RESET);
    }
    
    private void imprimirTituloSeccion(String titulo) {
        System.out.println(COLOR_TITULO + ESTILO_NEGRITA + "\n   ╔════════════════════════════════════════════════╗");
        System.out.printf("   ║  %-46s║\n", titulo);
        System.out.println("   ╚════════════════════════════════════════════════╝" + COLOR_RESET);
    }
    
    private void mostrarExito(String mensaje) {
        System.out.println(COLOR_EXITO + "\n   ✓ " + mensaje + COLOR_RESET);
    }
    
    private void mostrarError(String mensaje) {
        System.out.println(COLOR_ERROR + "\n   ✗ " + mensaje + COLOR_RESET);
    }
    
    private void esperarUsuario() {
        System.out.print(COLOR_ADVERTENCIA + "\n   ⏸ Presione [ENTER] para regresar al menú principal..." + COLOR_RESET);
        entradaDatos.nextLine();
    }
    
    private void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private void despedida() {
        borrarPantalla();
        System.out.println(COLOR_TITULO + ESTILO_NEGRITA);
        System.out.println("\n   ╔═══════════════════════════════════════════════════╗");
        System.out.println("   ║                                                   ║");
        System.out.println("   ║             GRACIAS POR USAR EL SISTEMA           ║");
        System.out.println("   ║                                                   ║");
        System.out.println("   ║          ¡Que tenga un excelente día!             ║");
        System.out.println("   ║                                                   ║");
        System.out.println("   ╚═══════════════════════════════════════════════════╝");
        System.out.println(COLOR_RESET);
    }
    
    public static void main(String[] args) {
        new MenuUsuarioCliente().iniciar();
    }
}

