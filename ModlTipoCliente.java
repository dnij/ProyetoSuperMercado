public enum ModlTipoCliente {
    VIP(1),
    ADULTO_MAYOR(2),
    REGULAR(3);
    
    private final int nivelPrioridad;
    
    ModlTipoCliente(int nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }
    
    public int getPrioridad() {
        return nivelPrioridad;
    }
}