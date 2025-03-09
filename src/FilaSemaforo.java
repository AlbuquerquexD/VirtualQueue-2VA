import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class FilaSemaforo {
    private final LinkedList<Cliente> fila = new LinkedList<>();
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore items = new Semaphore(0);
    
    public void put(Cliente cliente) throws InterruptedException {
        mutex.acquire();
        try {
            fila.add(cliente);
        } finally {
            mutex.release();
        }
        items.release();
    }
    
    public Cliente take() throws InterruptedException {
        items.acquire();
        mutex.acquire();
        try {
            return fila.removeFirst();
        } finally {
            mutex.release();
        }
    }
    
    
    public int filaEspera() {
        int count = 0;
        try {
            mutex.acquire();
            for (Cliente c : fila) {
                if (!c.isPoison()) {
                    count++;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            mutex.release();
        }
        return count;
    }
    
//    public void printQueue() {
//        try {
//            mutex.acquire();
//            System.out.println("Conteúdo da Fila: " + fila.toString());
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        } finally {
//            mutex.release();
//        }
//    }


}
