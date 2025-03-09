import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Logger {
    // Fila para armazenar as mensagens de log
    private static final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();

    // Método para registrar uma mensagem
    public static void log(String message) {
        logQueue.add(message);
    }

    // Inicia uma thread que consome as mensagens de log e as imprime
    public static void startLogger() {
        Thread loggerThread = new Thread(() -> {
            try {
                while (true) {
                    String msg = logQueue.take();
                    System.out.println(msg);
                }
            } catch (InterruptedException e) {
                System.out.println("Logger interrompido.");
            }
        });
        loggerThread.setDaemon(true); // Permite que o programa finalize mesmo se o logger estiver rodando
        loggerThread.start();
    }
}
