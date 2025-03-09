import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Estatisticas {
    private AtomicInteger totalAtendidos = new AtomicInteger(0);
    private AtomicLong tempoTotalEspera = new AtomicLong(0);
    
    // Atualiza as estatísticas para um cliente atendido.
    public void clienteAtendido(long tempoEspera) {
        totalAtendidos.incrementAndGet();
        tempoTotalEspera.addAndGet(tempoEspera);
    }
    
    public int getTotalAtendidos() {
        return totalAtendidos.get();
    }
    
    // Calcula o tempo médio de espera.
    public double getTempoMedioEspera() {
        int count = totalAtendidos.get();
        if (count == 0) return 0;
        return (double) tempoTotalEspera.get() / count;
    }
    
    @Override
    public String toString() {
        return "Total atendidos: " + getTotalAtendidos() +
               " | Tempo médio de espera: " + String.format("%.2f", getTempoMedioEspera()) + " ms";
    }
}
