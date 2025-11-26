import java.util.ArrayList;

public class StructuresColaPrioridad {
    private final ArrayList<ModlCliente> monticulo;
    
    public StructuresColaPrioridad() {
        this.monticulo = new ArrayList<>();
    }
    
    public boolean esVacia() {
        return monticulo.isEmpty();
    }
    
    // Agregar cliente
    public void agregar(ModlCliente cliente) {
        monticulo.add(cliente);
        flotarHaciaArriba(monticulo.size() - 1);
    }
    
    // Extraer cliente prioritario
    public ModlCliente extraer() {
        if (esVacia()) return null;
        
        ModlCliente clientePrioritario = monticulo.get(0);
        ModlCliente ultimoCliente = monticulo.remove(monticulo.size() - 1);
        
        if (!esVacia()) {
            monticulo.set(0, ultimoCliente);
            hundirHaciaAbajo(0);
        }
        
        return clientePrioritario;
    }
    
    // Flotar elemento hacia arriba
    private void flotarHaciaArriba(int posicion) {
        int indicePadre = (posicion - 1) / 2;
        
        while (posicion > 0 && monticulo.get(posicion).compareTo(monticulo.get(indicePadre)) < 0) {
            intercambiarPosiciones(posicion, indicePadre);
            posicion = indicePadre;
            indicePadre = (posicion - 1) / 2;
        }
    }
    
    // Hundir elemento hacia abajo
    private void hundirHaciaAbajo(int posicion) {
        int indiceMenor = posicion;
        int indiceIzquierdo = 2 * posicion + 1;
        int indiceDerecho = 2 * posicion + 2;
        
        if (indiceIzquierdo < monticulo.size() && 
            monticulo.get(indiceIzquierdo).compareTo(monticulo.get(indiceMenor)) < 0) {
            indiceMenor = indiceIzquierdo;
        }
        
        if (indiceDerecho < monticulo.size() && 
            monticulo.get(indiceDerecho).compareTo(monticulo.get(indiceMenor)) < 0) {
            indiceMenor = indiceDerecho;
        }
        
        if (indiceMenor != posicion) {
            intercambiarPosiciones(posicion, indiceMenor);
            hundirHaciaAbajo(indiceMenor);
        }
    }
    
    // Intercambiar posiciones
    private void intercambiarPosiciones(int i, int j) {
        ModlCliente temporal = monticulo.get(i);
        monticulo.set(i, monticulo.get(j));
        monticulo.set(j, temporal);
    }
    
    // Mostrar cola
    public void mostrarCola() {
        System.out.println("=== CLIENTES EN ESPERA ===");
        System.out.printf("| %-5s | %-20s | %-15s | %-10s |\n", 
                         "Pos", "Nombre", "Tipo", "Productos");
        System.out.println("-------------------------------------------------------------");
        
        ArrayList<ModlCliente> copiaOrdenada = new ArrayList<>(monticulo);
        copiaOrdenada.sort(ModlCliente::compareTo);
        
        for (int i = 0; i < copiaOrdenada.size(); i++) {
            ModlCliente cliente = copiaOrdenada.get(i);
            System.out.printf("| %-5d %s\n", (i + 1), cliente.toString());
        }
        System.out.println("-------------------------------------------------------------");
    }
    
    public int tamano() { 
        return monticulo.size(); 
    }
}