package com.inteligencia.computacional;

import javafx.scene.layout.Pane;

import java.util.Scanner;

public class Main {
    private static int largura, altura;
    private static final Pane root = new Pane();
    private static final Scanner scanner = new Scanner(System.in);
    private static ObjetoCenario[] objs;

    private static int escanearValor(int min, int max, String msg) {
        int valor;

        while (true) {
            try {
                System.out.print("Digite um valor inteiro para " + msg + ": ");

                if (!scanner.hasNextInt())
                    throw new Excecoes();

                valor = scanner.nextInt();

                if (valor < min || valor > max)
                    throw new Excecoes();

                scanner.nextLine();
                break;
            } catch (Excecoes e) {
                switch (msg) {
                    case "o respectivo objeto do cenário" -> e.indiceObjetoException();
                    case "a posição x", "a posição y" -> e.posicaoException();
                    case "a largura", "a altura" -> e.medidaException();
                    default -> e.numeroObjetosExpection();
                }

                scanner.nextLine();
            }
        }

        return valor;
    }

    private static void comecarLoopObjetos(int largura, int altura) {
        int x, y, tipo_obj;
        boolean[][] posicoes_registradas = new boolean[largura][altura];

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++)
                posicoes_registradas[i][j] = false;
        }

        for (int i = 0; i < objs.length; i++) {
            System.out.println("\nlargura(x), altura(y) = " + largura + ", " + altura);
            System.out.println("1 - Robô\n2 - Robô Inteligente\n3 - Sujeira\n");

            tipo_obj = escanearValor(1, 3, "o respectivo objeto do cenário");
            x = escanearValor(0, largura - 1, "a posição x");
            y = escanearValor(0, altura - 1, "a posição y");

            if (posicoes_registradas[x][y]) {
                System.out.println("\nJá há um objeto nessa posição!\n");
                --i;
                continue;
            }

            posicoes_registradas[x][y] = true;
            objs[i] = gerarObjetoCenario(x, y, tipo_obj);
        }
    }

    private static ObjetoCenario gerarObjetoCenario(int x, int y, int tipo_obj) {
        return switch (tipo_obj) {
            case 1 -> new Robo(x, y);
            case 2 -> new RoboInteligente(x, y);
            default -> new Sujeira(x, y);
        };
    }

    private static boolean verificarObjetosValidos() {
        boolean robo = false, sujeira = false;

        for (ObjetoCenario obj : objs) {
            if (obj instanceof Robo)
                robo = true;
            if (obj instanceof Sujeira)
                sujeira = true;
        }

        if (!robo || !sujeira)
            System.out.println("\n\nDeve haver pelo menos um robô e uma sujeira!\n\n");

        return robo && sujeira;
    }

    public static void main(String[] args) {
        int qtd_objs;

        do {
            largura = escanearValor(3, 10, "a largura");
            altura = escanearValor(3, 10, "a altura");
            qtd_objs = escanearValor(1, largura * altura, "a quantidade de objetos para o jogo");

            objs = new ObjetoCenario[qtd_objs];
            comecarLoopObjetos(largura, altura);
        } while (!verificarObjetosValidos());

        Cenario.main(args);
    }

    public static int getLargura() {
        return largura;
    }

    public static int getAltura() {
        return altura;
    }

    public static Pane getRoot() {
        return root;
    }

    public static ObjetoCenario[] getObjetosCenario() {
        return objs;
    }
}