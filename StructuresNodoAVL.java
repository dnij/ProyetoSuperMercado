public class StructuresNodoAVL {
    ModlProducto datosProducto;
    StructuresNodoAVL hijoIzquierdo;
    StructuresNodoAVL hijoDerecho;
    int nivelAltura;
    
    public StructuresNodoAVL(ModlProducto datosProducto) {
        this.datosProducto = datosProducto;
        this.nivelAltura = 1;
    }
}