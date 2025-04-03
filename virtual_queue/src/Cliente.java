
/*
	Cliente: Representa um cliente a ser atendido.
	- id identifica o cliente; id = -1 indica a "poison pill" (sinal de término).
	- tempoChegada registra o instante de criação do cliente.
*/

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
    
    // Retorna "CondicaoParada" se o cliente for uma poison pill; caso contrário, retorna "Cliente " seguido do id.
    @Override
    public String toString() {
        if (isPoison()) {
            return "CondicaoParada";
        }
        return "Cliente " + id;
    }
}
