import java.time.Instant;

public class ModlCliente implements Comparable<ModlCliente> {
    
    private final String nombreCompleto;
    private final ModlTipoCliente categoriaCliente;
    private final int totalArticulos;
    private final long marcaTiempoRegistro;
    
    public ModlCliente(String nombreCompleto, ModlTipoCliente categoriaCliente, int totalArticulos) {
        this.nombreCompleto = nombreCompleto;
        this.categoriaCliente = categoriaCliente;
        this.totalArticulos = totalArticulos;
        this.marcaTiempoRegistro = Instant.now().toEpochMilli();
    }
    
    @Override
    public int compareTo(ModlCliente otraPersona) {
        // Comparar por prioridad
        int resultadoComparacion = Integer.compare(
            this.categoriaCliente.getPrioridad(), 
            otraPersona.categoriaCliente.getPrioridad()
        );
        
        if (resultadoComparacion != 0) {
            return resultadoComparacion;
        }
        
        // Desempate por orden de llegada
        return Long.compare(this.marcaTiempoRegistro, otraPersona.marcaTiempoRegistro);
    }
    
    public String getNombre() { return nombreCompleto; }
    public ModlTipoCliente getTipo() { return categoriaCliente; }
    public int getCantidadProductos() { return totalArticulos; }
    
    @Override
    public String toString() {
        return String.format("| %-20s | %-15s | %-10d |", 
                           nombreCompleto, categoriaCliente, totalArticulos);
    }
}
