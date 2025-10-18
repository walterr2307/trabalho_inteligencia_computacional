package com.inteligencia.computacional;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Robo extends ObjetoCenario {
    protected int largura = Main.getLargura(), altura = Main.getAltura();
    protected int x = 0, y = 0, novo_x = 0, novo_y = 0, pontos = 0, indice_robo;
    protected static int indice_geral = 1;
    protected final Random random = new Random();
    protected String tipo_robo = definirTipoRobo();
    protected Sujeira sujeira_registrada;

    public Robo(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
        indice_robo = indice_geral;
        ++indice_geral;
    }

    public void mover(ArrayList<Sujeira> sujeiras) {
        definirPosicoes();
        ajustarPosicoes();
        limparSujeira(sujeiras);
    }

    public String imprimirInformacoes() {
        return "Robô " + indice_robo + " (" + tipo_robo + ") na posição (" +
                x_atual + ", " + y_atual + ")" + " | Pontos: " + pontos;
    }

    protected String definirTipoRobo() {
        return "Comum";
    }

    protected void definirPosicoes() {
        x = random.nextInt(3) - 1;
        y = random.nextInt(3) - 1;
        novo_x = x_atual + x;
        novo_y = y_atual + y;
    }

    protected void ajustarPosicoes() {
        TranslateTransition mover = new TranslateTransition(Duration.seconds(0.25f), img);

        if (novo_x < 0 || novo_x >= largura) {
            if (novo_x < 0)
                novo_x = 0;
            else
                novo_x = largura - 1;

            x = 0;
        }
        if (novo_y < 0 || novo_y >= altura) {
            if (novo_y < 0)
                novo_y = 0;
            else
                novo_y = altura - 1;

            y = 0;
        }

        mover.setByX(60f * x);
        mover.setByY(60f * y);
        mover.setCycleCount(1);
        mover.play();

        x_atual = novo_x;
        y_atual = novo_y;
    }

    protected void limparSujeira(ArrayList<Sujeira> sujeiras) {
        sujeira_registrada = null;

        for (Sujeira sujeira : sujeiras) {
            if (sujeira.getX() == x_atual && sujeira.getY() == y_atual) {
                PauseTransition pausa = new PauseTransition(Duration.seconds(0.375));

                pausa.setOnFinished(_ -> sujeira.getImagem().setVisible(false));
                pausa.play();

                sujeira_registrada = sujeira;
                sujeiras.remove(sujeira);
                ++pontos;
                break;
            }
        }

        if (sujeira_registrada == null)
            --pontos;
    }

    protected float gerarOrdemVisualizacao() {
        return -1f;
    }

    protected String gerarCaminho() {
        return "/robot.png";
    }
}
