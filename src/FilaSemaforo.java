import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class FilaSemaforo {
	
	
    /*
    FilaSemaforo: Classe responsável por gerenciar uma fila de clientes com controle de sincronização.
    
    - mutex: Semáforo binário (inicializado com 1 permissão) que garante exclusão mútua, 
    permitindo que apenas uma thread acesse a região crítica (adição ou remoção de clientes) de cada vez.
    
    - items: Semáforo de contagem (inicializado com 0) que controla quantos itens estão disponíveis na fila.
    Isso evita que uma thread tente remover um cliente quando a fila está vazia, pois a thread ficará bloqueada 
    até que um cliente seja inserido (quando items.release() é chamado).
    
    */
	
    private final LinkedList<Cliente> fila = new LinkedList<>();
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore items = new Semaphore(0);
    
    // o produtor vai inserir os clientes na fila 
    public void colocarNaFila(Cliente cliente) throws InterruptedException {
        mutex.acquire();
        try {
            fila.add(cliente); // <- SEÇÃO CRÍTICA (acessando recurso compartilhado)
        } finally {
            mutex.release();
        }
        items.release();
    }
    
    //consumidor vai retirar da fila caso exist algum cliente na fila, caso não exista ele fica bloqueado
    public Cliente retirarDaFila() throws InterruptedException {
        items.acquire();
        mutex.acquire();
        try {
            return fila.removeFirst(); // <- SEÇÃO CRÍTICA (removendo recurso compartilhado)
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

}
