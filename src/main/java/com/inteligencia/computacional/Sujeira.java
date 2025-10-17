package com.inteligencia.computacional;

public class Sujeira extends ObjetoCenario {

    public Sujeira(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
    }

    protected float gerarOrdemVisualizacao() {
        return -0.5f;
    }

    protected String gerarCaminho() {
        return "/trash.png";
    }
}
