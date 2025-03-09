public class AtendimentoManager {
    public static void main(String[] args) {
        // Cria a fila de atendimento utilizando nossa implementação com semáforos.
        FilaSemaforo fila = new FilaSemaforo();
        // Cria o objeto Estatisticas para registrar dados de atendimento.
        Estatisticas estatisticas = new Estatisticas();
        
        // Inicializa e inicia as threads dos atendentes.
        Atendente[] atendentes = new Atendente[Config.NUM_ATENDENTES];
        for (int i = 0; i < Config.NUM_ATENDENTES; i++) {
            atendentes[i] = new Atendente(i + 1 , fila, estatisticas);
            atendentes[i].start();
        }
        
        
        // Inicia a thread de monitoramento para exibir o status do sistema periodicamente.
        Monitor monitor = new Monitor(fila, estatisticas);
        monitor.start();
        
        // Gera clientes periodicamente e os adiciona na fila.
        for (int i = 1; i <= Config.NUM_CLIENTES; i++) {
            try {
                Thread.sleep(Config.INTERVALO_CHEGADA);
                Cliente cliente = new Cliente(i);
                fila.put(cliente);
                System.out.println();  
                System.out.println(cliente + " entrou na fila.");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
        	 
        	fila.put(new Cliente()); 

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
        
        // Aguarda que todos os atendentes finalizem.
        for (Atendente atendente : atendentes) {
            try {
                atendente.join();
               
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Interrompe a thread do monitor, já que os atendimentos foram concluídos.
        monitor.interrupt();
//        try {
//            monitor.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        
        System.out.println("Todos os clientes foram atendidos. Sistema encerrado.");
    }
}
