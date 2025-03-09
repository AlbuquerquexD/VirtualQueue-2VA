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
                Thread.sleep(Config.TEMPO_MONITORAMENTO);
                
                // Chama o método size() da FilaSemaforo para obter de forma segura o número de clientes na fila
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
