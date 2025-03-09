public class Config {
    // Número de clientes a serem gerados
    public static final int NUM_CLIENTES = 7;
    // Número de atendentes que processarão os clientes
    public static final int NUM_ATENDENTES = 3;
    // Intervalo (em milissegundos) entre a chegada dos clientes
    public static final int INTERVALO_CHEGADA = 3000;
    // Tempo (em milissegundos) que o atendente leva para processar um cliente
    public static final int TEMPO_ATENDIMENTO = 5000; // 2 segundos
    // Intervalo (em milissegundos) para a atualização do monitor
    public static final int TEMPO_MONITORAMENTO = 4000; // 3 segundos
}
