

/*
	AtendimentoManager coordena todo o fluxo de atendimento: cria fila, estatísticas, atendentes e monitor.
	Os Clientes são gerados periodicamente e uma poison pill sinaliza o término dos atendimentos.
	Aguardam-se os atendentes (join) e o monitor é interrompido antes de encerrar o sistema.
	Exibe mensagem final indicando que todos os clientes foram atendidos.
	
*/

public class GerenciadorDeAtendimentos {
    public static void main(String[] args) {
    	
        
        FilaSemaforo fila = new FilaSemaforo();
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
            	
            	 //simula o tempo de chegada dos clientes
                Thread.sleep(Config.INTERVALO_CHEGADA);
                Cliente cliente = new Cliente(i);
                fila.colocarNaFila(cliente);
                System.out.println();  
                System.out.println(cliente + " entrou na fila.");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
        	 
        	fila.colocarNaFila(new Cliente()); 

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
        
        // Interrompe o monitor e espera sua finalização com join() para que todas as atualizações sejam exibidas antes do encerramento.
        monitor.interrupt();
        
        try {
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Todos os clientes foram atendidos. Sistema encerrado.");
    }
}
