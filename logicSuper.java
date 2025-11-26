public class logicSuper {
    
    private final StructuresArbolAVL almacenProductos;
    private final StructuresColaPrioridad filaAtencion;
    
    public logicSuper() {
        this.almacenProductos = new StructuresArbolAVL();
        this.filaAtencion = new StructuresColaPrioridad();
        cargarDatosIniciales();
    }
    
    private void cargarDatosIniciales() {
        // Datos de prueba
    }
    
    // Registrar producto
    public String agregarProducto(String cod, String nom, String cat, double precio, int stock) {
        if (precio <= 0 || stock < 0) return "Error: Valores inválidos";
        boolean exito = almacenProductos.insertar(new ModlProducto(cod, nom, cat, precio, stock));
        return exito ? "Producto insertado correctamente" : "Error: Código duplicado";
    }
    
    public ModlProducto buscarProducto(String codigo) {
        return almacenProductos.buscar(codigo);
    }
    
    public String actualizarProducto(String cod, String nom, String cat, double precio, int stock) {
        ModlProducto p = almacenProductos.buscar(cod);
        if (p == null) return "Producto no existe";
        p.setNombre(nom);
        p.setCategoria(cat);
        p.setPrecio(precio);
        p.setStock(stock);
        return "Producto actualizado exitosamente";
    }
    
    public String eliminarProducto(String codigo) {
        boolean exito = almacenProductos.eliminar(codigo);
        return exito ? "Producto eliminado" : "Producto no encontrado";
    }
    
    public void mostrarInventario() {
        almacenProductos.imprimirInventario();
    }
    
    // Gestión de clientes
    public void agregarCliente(ModlCliente c) {
        filaAtencion.agregar(c);
        System.out.println(">> Cliente " + c.getNombre() + " agregado a la cola. Prioridad: " + c.getTipo());
    }
    
    public void verCola() {
        filaAtencion.mostrarCola();
    }
    
    public void atenderSiguiente() {
        ModlCliente c = filaAtencion.extraer();
        if (c == null) {
            System.out.println("No hay clientes en espera.");
            return;
        }
        procesarCliente(c);
    }
    
    public void simularTodo() {
        System.out.println("\n=== INICIANDO SIMULACIÓN MASIVA ===");
        while (!filaAtencion.esVacia()) {
            atenderSiguiente();
        }
        System.out.println("=== TODOS LOS CLIENTES HAN SIDO ATENDIDOS ===");
    }
    
    private void procesarCliente(ModlCliente c) {
        int tiempoTotal = c.getCantidadProductos() * 2;
        System.out.println("\n----------------------------------------");
        System.out.printf("Atendiendo a: %s (%s)\n", c.getNombre(), c.getTipo());
        System.out.printf("Productos: %d | Tiempo estimado: %d segundos\n", 
                         c.getCantidadProductos(), tiempoTotal);
        
        try {
            System.out.print("Procesando: [");
            for(int i=0; i<tiempoTotal; i++) {
                Thread.sleep(1000);
                System.out.print("=");
            }
            System.out.println("] 100%");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(">> Cliente atendido correctamente.");
    }
}