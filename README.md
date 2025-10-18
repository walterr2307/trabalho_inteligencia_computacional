# Trabalho de Inteligência Computacional — Simulador de Robôs

Descrição
- Projeto em Java (JavaFX) que simula um cenário com robôs e sujeiras.
- Robôs se movimentam em uma grade; há robôs comuns e robôs inteligentes que detectam sujeiras próximas.
- A interface usa JavaFX para desenhar o cenário e animar os robôs.

Pré-requisitos
- Java 21 instalado.
- JavaFX (versão 21) disponível — o projeto já referencia JavaFX no `pom.xml`.
- Maven (para compilar e executar com os plugins configurados).

Como compilar e executar
1. Abra um terminal na pasta do projeto (onde está o `pom.xml`).
2. Compilar:
   - mvn clean package
3. Executar com o plugin exec (ou ajuste do module-path conforme seu ambiente):
   - mvn -q exec:exec
   - Ou use o `javafx:run` se preferir:
     - mvn javafx:run

Observação: o `pom.xml` contém configurações com paths locais para o JavaFX SDK — ajuste o caminho (`--module-path`) conforme sua instalação do JavaFX, se necessário.

Uso do programa
- Ao executar o programa, ele pede entradas pelo console:
  1. Largura do cenário (entre 3 e 10).
  2. Altura do cenário (entre 3 e 10).
  3. Quantidade de objetos a inserir (entre 1 e largura*altura).
  4. Para cada objeto: tipo (1=Robô, 2=Robô Inteligente, 3=Sujeira), posição X e posição Y.
- Validações: o programa não permite posições repetidas nem números fora dos limites.
- Após configurar os objetos, o cenário JavaFX abre e a simulação começa. Mensagens de debug e pontuação são exibidas no console.

Estrutura do projeto (resumida)
- src/main/java/com/inteligencia/computacional
  - Main.java — entrada do programa e leitura das entradas.
  - Cenario.java — cria a interface JavaFX e controla o loop de simulação.
  - ObjetoCenario.java — classe base para objetos do cenário.
  - Robo.java — comportamento do robô comum.
  - RoboInteligente.java — comportamento do robô inteligente.
  - Sujeira.java — representa sujeiras no cenário.
  - Excecoes.java — mensagens e validações de entrada.
- resources (imagens esperadas no classpath):
  - /robot.png
  - /smart_robot.png
  - /trash.png
  - /wallpaper.jpg

Boas práticas / notas
- Ajuste o caminho do JavaFX no `pom.xml` se estiver usando outro diretório do SDK.
- Se as imagens não aparecerem, verifique se estão no diretório `resources` e são incluídas no classpath do build.
- O código já imprime informações de debug no console (útil para entender o comportamento dos robôs).

