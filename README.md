# Virtual Queue

Virtual Queue simula um sistema de atendimento no modelo produtor-consumidor. Clientes são gerados periodicamente, inseridos em uma fila compartilhada e processados por threads atendentes, enquanto uma classe monitor faz o monitoramento e exibe o status do sistema via console. A implementação utiliza semáforos para sincronização e é facilmente configurável.

## Link para o slide do projeto no Canvas
[Clique para visualizar ](https://www.canva.com/design/DAGhkT8IhoM/EwYIfwPPuHAu2qwZ0txnaA/edit?utm_content=DAGhkT8IhoM&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

## Requisitos
- JDK 23 ou superior

## Instruções de Uso
1. Clone o repositório.
2. Configure os parâmetros desejados na classe `Config`.
3. Compile e execute a classe `AtendimentoManager`.

## Sobre o Projeto
- **Linguagem:** Java
- **IDE:** Eclipse
- **Arquitetura:**  
  - **Clientes (Produtor):** São gerados periodicamente.
  - **Fila (Recurso Compartilhado):** Gerenciada com semáforos para controle de acesso.
  - **Atendentes (Consumidores):** Threads que processam clientes e atualizam estatísticas.
- **Controle de Concorrência:**  
  - Uso de semáforos (mutex para exclusão mútua e contador para itens disponíveis).
