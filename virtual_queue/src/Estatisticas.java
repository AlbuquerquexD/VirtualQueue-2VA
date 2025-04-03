public class Estatisticas {
	
	
	/*

	Estatisticas: Gerencia estatísticas de atendimento de clientes de forma thread-safe.

	totalAtendidos: Conta quantos clientes foram atendidos.
	tempoTotalEspera: Soma o tempo de espera de todos os atendimentos.

	Métodos sincronizados:
	- clienteAtendido(long): Atualiza os contadores ao atender um cliente.
	- getTotalAtendidos(): Retorna o total de atendimentos.
	- getTempoMedioEspera(): Calcula a média do tempo de espera.
	- toString(): Exibe as estatísticas formatadas.
	
	*/
	
	
    private int totalAtendidos = 0;
    private long tempoTotalEspera = 0;

    // Atualiza as estatísticas para um cliente atendido de forma sincronizada.
    public synchronized void clienteAtendido(double tempoEspera) {
        totalAtendidos++;
        tempoTotalEspera += tempoEspera;
    }

    // Retorna o total de atendimentos de forma sincronizada.
    public synchronized int getTotalAtendidos() {
        return totalAtendidos;
    }

    // Calcula e retorna o tempo médio de espera de forma sincronizada.
    public synchronized double getTempoMedioEspera() {
        if (totalAtendidos == 0) return 0;
        return (double) tempoTotalEspera / totalAtendidos;
    }

    @Override
    public synchronized String toString() {
        return "Total atendidos: " + totalAtendidos +
               " | Tempo médio de espera: " + String.format("%.2f", getTempoMedioEspera()) + " ms";
    }
}
