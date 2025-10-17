package com.inteligencia.computacional;

public abstract class ObjetoCenario {
    protected int x_atual, y_atual;

    public ObjetoCenario(int x_inicial, int y_inicial) {
        this.x_atual = x_inicial;
        this.y_atual = y_inicial;
    }

    protected void mover() {
        int x = (int) (Math.random() * 3 - 1), y = (int) (Math.random() * 3 - 1);

        x_atual += x;
        y_atual += y;
    }
}
