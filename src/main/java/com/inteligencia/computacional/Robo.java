package com.inteligencia.computacional;

import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Robo extends ObjetoCenario {
    protected int largura = Main.getLargura(), altura = Main.getAltura();
    protected int x = 0, y = 0, novo_x = 0, novo_y = 0, pontos = 0;
    protected final Random random = new Random();

    public Robo(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
    }

    public void mover(Sujeira[] sujeiras) {
        definirPosicoes();
        ajustarPosicoes();
        limparSujeira(sujeiras);
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

    public void limparSujeira(Sujeira[] sujeiras) {
        boolean limpou = false;

        for (int i = 0; i < sujeiras.length; i++) {
            if (sujeiras[i] != null) {
                if (sujeiras[i].getX() == x_atual && sujeiras[i].getY() == y_atual) {
                    int indice = i;
                    PauseTransition pausa = new PauseTransition(Duration.seconds(0.375));

                    pausa.setOnFinished(_ -> {
                        sujeiras[indice].getImagem().setVisible(false);
                        sujeiras[indice] = null;
                    });

                    pausa.play();

                    limpou = true;
                    ++pontos;
                }
            }
        }

        if (!limpou)
            --pontos;
    }

    protected float gerarOrdemVisualizacao() {
        return -1f;
    }

    protected String gerarCaminho() {
        return "/robot.png";
    }
}
