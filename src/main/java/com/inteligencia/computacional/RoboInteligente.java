package com.inteligencia.computacional;

import java.util.ArrayList;

public class RoboInteligente extends Robo {
    private final ArrayList<Sujeira> sujeiras_registradas = new ArrayList<>();

    public RoboInteligente(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
    }

    protected String gerarCaminho() {
        return "/smart_robot.png";
    }

    protected String definirTipoRobo() {
        return "Inteligente";
    }

    public void mover(ArrayList<Sujeira> sujeiras) {
        super.mover(sujeiras);
        registrarSujeira(sujeiras);
        removerRegistroSujeira();
    }

    protected void definirPosicoes() {
        if (sujeiras_registradas.isEmpty())
            super.definirPosicoes();
        else
            seguirSujeira(sujeiras_registradas.getFirst());
    }

    private void removerRegistroSujeira() {
        for (Sujeira sujeira : sujeiras_registradas) {
            if (sujeira.getX() == x_atual && sujeira.getY() == y_atual) {
                sujeiras_registradas.remove(sujeira);
                break;
            }
        }
    }

    private void registrarSujeira(ArrayList<Sujeira> sujeiras) {
        for (int i = -3; i <= 3; i++) {
            for (int j = -3; j <= 3; j++) {
                int soma_x = x_atual + i, soma_y = y_atual + j;

                if (i == 0 && j == 0 || soma_x < 0 || soma_y < 0 || soma_x >= largura || soma_y >= altura)
                    continue;

                for (Sujeira sujeira : sujeiras) {
                    if (sujeira.getX() == soma_x && sujeira.getY() == soma_y && !sujeiras_registradas.contains(sujeira))
                        sujeiras_registradas.add(sujeira);
                }
            }
        }
    }

    private void seguirSujeira(Sujeira sujeira) {
        x = sujeira.getX() - x_atual;
        y = sujeira.getY() - y_atual;

        if (x < -1)
            x = -1;
        if (x > 1)
            x = 1;
        if (y < -1)
            y = -1;
        if (y > 1)
            y = 1;

        novo_x = x_atual + x;
        novo_y = y_atual + y;
    }
}
