public class Monitor extends Thread {
    private FilaSemaforo fila;
    private Estatisticas estatisticas;
    
    public Monitor(FilaSemaforo fila, Estatisticas estatisticas) {
        this.fila = fila;
        this.estatisticas = estatisticas;
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
            	
            	// simular o tempo de atendimento
                Thread.sleep(Config.TEMPO_MONITORAMENTO);
                
                // Chama o método filaEspera() da FilaSemaforo para obter o número de clientes esperando na fila na fila
                int clientesNaFila = fila.filaEspera();
       
                System.out.println();
                System.out.println("Clientes na fila: " + clientesNaFila);
                System.out.println();
                System.out.println(estatisticas);             
                System.out.println();
     
            }
        } catch (InterruptedException e) {
            System.out.println("Monitor interrompido.");
        }
        System.out.println("Monitor finalizado.");
    }
}
