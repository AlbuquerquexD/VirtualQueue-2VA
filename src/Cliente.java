public class Cliente {
    private int id;
    private long tempoChegada;
    
    // Construtor normal para clientes
    public Cliente(int id) {
        this.id = id;
        this.tempoChegada = System.currentTimeMillis();
    }
    
    // Construtor para "poison pill": um cliente com id = -1 sinaliza término.
    public Cliente() {
        this.id = -1;
        this.tempoChegada = 0;
    }
    
    public int getId() {
        return id;
    }
    
    public long getTempoChegada() {
        return tempoChegada;
    }
    
    // Retorna true se este cliente for a "poison pill"
    public boolean isPoison() {
        return id == -1;
    }
    
    @Override
    public String toString() {
        if (isPoison()) {
            return "CondicaoParada";
        }
        return "Cliente " + id;
    }
}
