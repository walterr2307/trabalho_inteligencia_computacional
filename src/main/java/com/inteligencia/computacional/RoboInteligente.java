package com.inteligencia.computacional;

public class RoboInteligente extends Robo {

    public RoboInteligente(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
    }

    protected String gerarCaminho() {
        return "/smart_robot.png";
    }
}
