public class ModlProducto {
    private final String codigoIdentificador;
    private String nombreArticulo;
    private String clasificacion;
    private double valorUnitario;
    private int unidadesDisponibles;
    
    public ModlProducto(String codigoIdentificador, String nombreArticulo, 
                        String clasificacion, double valorUnitario, int unidadesDisponibles) {
        this.codigoIdentificador = codigoIdentificador;
        this.nombreArticulo = nombreArticulo;
        this.clasificacion = clasificacion;
        this.valorUnitario = valorUnitario;
        this.unidadesDisponibles = unidadesDisponibles;
    }
    
    // Getters y Setters
    public String getCodigo() { return codigoIdentificador; }
    public String getNombre() { return nombreArticulo; }
    public void setNombre(String nombreArticulo) { this.nombreArticulo = nombreArticulo; }
    public String getCategoria() { return clasificacion; }
    public void setCategoria(String clasificacion) { this.clasificacion = clasificacion; }
    public double getPrecio() { return valorUnitario; }
    public void setPrecio(double valorUnitario) { this.valorUnitario = valorUnitario; }
    public int getStock() { return unidadesDisponibles; }
    public void setStock(int unidadesDisponibles) { this.unidadesDisponibles = unidadesDisponibles; }
    
    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-15s | Q%-8.2f | %-5d |", 
                codigoIdentificador, nombreArticulo, clasificacion, valorUnitario, unidadesDisponibles);
    }
}