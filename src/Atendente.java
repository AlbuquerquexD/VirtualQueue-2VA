public class Atendente extends Thread {
    private int id;
    private FilaSemaforo fila;
    private Estatisticas estatisticas;
    
    public Atendente(int id, FilaSemaforo fila, Estatisticas estatisticas) {
        this.id = id;
        this.fila = fila;
        this.estatisticas = estatisticas;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
            	
                // Retira o cliente da fila; se a fila estiver vazia, take() bloqueia até que um cliente seja inserido.
                Cliente cliente = fila.take();
                System.out.println();
                // Verifica se recebeu a "poison pill" para finalizar o atendimento.
                if (cliente.isPoison()) {
                    System.out.println("Atendente " + id + " recebeu sinal de término.");
                    System.out.println();
                    // Reinsere a poison pill para que outros atendentes também possam terminar.
                    fila.put(cliente);
                    break;
                }
                
                long tempoInicioAtendimento = System.currentTimeMillis();
                long tempoEspera = tempoInicioAtendimento - cliente.getTempoChegada();
                System.out.println("Atendente " + id + " atendendo " + cliente + " (esperou " + tempoEspera + " ms).");
                System.out.println();
                // Simula o tempo de atendimento.
                Thread.sleep(Config.TEMPO_ATENDIMENTO);
                
                // Atualiza as estatísticas.
                estatisticas.clienteAtendido(tempoEspera);
                System.out.println();
                System.out.println("Atendente " + id + " finalizou atendimento de " + cliente + ".");
                System.out.println();
            }
        } catch (InterruptedException e) {
            System.out.println("Atendente " + id + " interrompido.");
        }
        System.out.println("Atendente " + id + " finalizado.");
    }
}
