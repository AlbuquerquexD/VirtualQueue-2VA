public class Config {
    // Número de clientes a serem gerados
    public static int NUM_CLIENTES = 5;
    // Número de atendentes que processarão os clientes
    public static final int NUM_ATENDENTES = 2;
    // Intervalo (em milissegundos) entre a chegada dos clientes
    public static final int INTERVALO_CHEGADA = 0;
    // Tempo (em milissegundos) que o atendente leva para processar um cliente
    public static final int TEMPO_ATENDIMENTO = 4000; // 4 segundos
    // Intervalo (em milissegundos) para a atualização do monitor
    public static final int TEMPO_MONITORAMENTO = 3000; // 3 segundos
}
