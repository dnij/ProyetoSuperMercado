public class StructuresArbolAVL {
    private StructuresNodoAVL nodoRaiz;
    
    // Obtener altura del nodo
    private int obtenerAltura(StructuresNodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.nivelAltura;
    }
    
    // Calcular factor de balance
    private int calcularBalance(StructuresNodoAVL nodo) {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.hijoIzquierdo) - obtenerAltura(nodo.hijoDerecho);
    }
    
    // Rotación a la derecha
    private StructuresNodoAVL girarDerecha(StructuresNodoAVL nodoY) {
        StructuresNodoAVL nodoX = nodoY.hijoIzquierdo;
        StructuresNodoAVL subArbolT2 = nodoX.hijoDerecho;
        
        nodoX.hijoDerecho = nodoY;
        nodoY.hijoIzquierdo = subArbolT2;
        
        nodoY.nivelAltura = Math.max(obtenerAltura(nodoY.hijoIzquierdo), 
                                     obtenerAltura(nodoY.hijoDerecho)) + 1;
        nodoX.nivelAltura = Math.max(obtenerAltura(nodoX.hijoIzquierdo), 
                                     obtenerAltura(nodoX.hijoDerecho)) + 1;
        return nodoX;
    }
    
    // Rotación a la izquierda
    private StructuresNodoAVL girarIzquierda(StructuresNodoAVL nodoX) {
        StructuresNodoAVL nodoY = nodoX.hijoDerecho;
        StructuresNodoAVL subArbolT2 = nodoY.hijoIzquierdo;
        
        nodoY.hijoIzquierdo = nodoX;
        nodoX.hijoDerecho = subArbolT2;
        
        nodoX.nivelAltura = Math.max(obtenerAltura(nodoX.hijoIzquierdo), 
                                     obtenerAltura(nodoX.hijoDerecho)) + 1;
        nodoY.nivelAltura = Math.max(obtenerAltura(nodoY.hijoIzquierdo), 
                                     obtenerAltura(nodoY.hijoDerecho)) + 1;
        return nodoY;
    }
    
    // Insertar producto
    public boolean insertar(ModlProducto producto) {
        if (buscar(producto.getCodigo()) != null) return false;
        nodoRaiz = insertarRecursivo(nodoRaiz, producto);
        return true;
    }
    
    private StructuresNodoAVL insertarRecursivo(StructuresNodoAVL nodo, ModlProducto producto) {
        if (nodo == null) return new StructuresNodoAVL(producto);
        
        int comparacion = producto.getCodigo().compareTo(nodo.datosProducto.getCodigo());
        
        if (comparacion < 0)
            nodo.hijoIzquierdo = insertarRecursivo(nodo.hijoIzquierdo, producto);
        else if (comparacion > 0)
            nodo.hijoDerecho = insertarRecursivo(nodo.hijoDerecho, producto);
        else
            return nodo;
        
        nodo.nivelAltura = 1 + Math.max(obtenerAltura(nodo.hijoIzquierdo), 
                                        obtenerAltura(nodo.hijoDerecho));
        return rebalancear(nodo, producto.getCodigo());
    }
    
    // Rebalancear árbol
    private StructuresNodoAVL rebalancear(StructuresNodoAVL nodo, String codigoInsertado) {
        int factorBalance = calcularBalance(nodo);
        
        // Caso Izquierda-Izquierda
        if (factorBalance > 1 && codigoInsertado.compareTo(nodo.hijoIzquierdo.datosProducto.getCodigo()) < 0)
            return girarDerecha(nodo);
        
        // Caso Derecha-Derecha
        if (factorBalance < -1 && codigoInsertado.compareTo(nodo.hijoDerecho.datosProducto.getCodigo()) > 0)
            return girarIzquierda(nodo);
        
        // Caso Izquierda-Derecha
        if (factorBalance > 1 && codigoInsertado.compareTo(nodo.hijoIzquierdo.datosProducto.getCodigo()) > 0) {
            nodo.hijoIzquierdo = girarIzquierda(nodo.hijoIzquierdo);
            return girarDerecha(nodo);
        }
        
        // Caso Derecha-Izquierda
        if (factorBalance < -1 && codigoInsertado.compareTo(nodo.hijoDerecho.datosProducto.getCodigo()) < 0) {
            nodo.hijoDerecho = girarDerecha(nodo.hijoDerecho);
            return girarIzquierda(nodo);
        }
        
        return nodo;
    }
    
    // Eliminar producto
    public boolean eliminar(String codigo) {
        if (buscar(codigo) == null) return false;
        nodoRaiz = eliminarRecursivo(nodoRaiz, codigo);
        return true;
    }
    
    private StructuresNodoAVL eliminarRecursivo(StructuresNodoAVL raiz, String codigo) {
        if (raiz == null) return raiz;
        
        int comparacion = codigo.compareTo(raiz.datosProducto.getCodigo());
        
        if (comparacion < 0)
            raiz.hijoIzquierdo = eliminarRecursivo(raiz.hijoIzquierdo, codigo);
        else if (comparacion > 0)
            raiz.hijoDerecho = eliminarRecursivo(raiz.hijoDerecho, codigo);
        else {
            if ((raiz.hijoIzquierdo == null) || (raiz.hijoDerecho == null)) {
                StructuresNodoAVL temporal = (raiz.hijoIzquierdo != null) ? 
                                             raiz.hijoIzquierdo : raiz.hijoDerecho;
                if (temporal == null) {
                    temporal = raiz;
                    raiz = null;
                } else {
                    raiz = temporal;
                }
            } else {
                StructuresNodoAVL sucesor = encontrarMinimo(raiz.hijoDerecho);
                raiz.datosProducto = sucesor.datosProducto;
                raiz.hijoDerecho = eliminarRecursivo(raiz.hijoDerecho, 
                                                     sucesor.datosProducto.getCodigo());
            }
        }
        
        if (raiz == null) return raiz;
        
        raiz.nivelAltura = Math.max(obtenerAltura(raiz.hijoIzquierdo), 
                                    obtenerAltura(raiz.hijoDerecho)) + 1;
        int balance = calcularBalance(raiz);
        
        if (balance > 1 && calcularBalance(raiz.hijoIzquierdo) >= 0)
            return girarDerecha(raiz);
        if (balance > 1 && calcularBalance(raiz.hijoIzquierdo) < 0) {
            raiz.hijoIzquierdo = girarIzquierda(raiz.hijoIzquierdo);
            return girarDerecha(raiz);
        }
        if (balance < -1 && calcularBalance(raiz.hijoDerecho) <= 0)
            return girarIzquierda(raiz);
        if (balance < -1 && calcularBalance(raiz.hijoDerecho) > 0) {
            raiz.hijoDerecho = girarDerecha(raiz.hijoDerecho);
            return girarIzquierda(raiz);
        }
        
        return raiz;
    }
    
    private StructuresNodoAVL encontrarMinimo(StructuresNodoAVL nodo) {
        StructuresNodoAVL actual = nodo;
        while (actual.hijoIzquierdo != null) 
            actual = actual.hijoIzquierdo;
        return actual;
    }
    
    // Buscar producto
    public ModlProducto buscar(String codigo) {
        StructuresNodoAVL resultado = buscarRecursivo(nodoRaiz, codigo);
        return resultado == null ? null : resultado.datosProducto;
    }
    
    private StructuresNodoAVL buscarRecursivo(StructuresNodoAVL nodo, String codigo) {
        if (nodo == null || nodo.datosProducto.getCodigo().equals(codigo)) 
            return nodo;
        
        if (codigo.compareTo(nodo.datosProducto.getCodigo()) < 0) 
            return buscarRecursivo(nodo.hijoIzquierdo, codigo);
        
        return buscarRecursivo(nodo.hijoDerecho, codigo);
    }
    
    // Imprimir inventario
    public void imprimirInventario() {
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-9s | %-5s |\n", 
                         "CÓDIGO", "NOMBRE", "CATEGORÍA", "PRECIO", "STOCK");
        System.out.println("---------------------------------------------------------------------");
        recorridoEnOrden(nodoRaiz);
        System.out.println("---------------------------------------------------------------------");
    }
    
    private void recorridoEnOrden(StructuresNodoAVL nodo) {
        if (nodo != null) {
            recorridoEnOrden(nodo.hijoIzquierdo);
            System.out.println(nodo.datosProducto);
            recorridoEnOrden(nodo.hijoDerecho);
        }
    }
}