package com.inteligencia.computacional;

import java.util.ArrayList;

public class RoboInteligente extends Robo {
    private final ArrayList<Sujeira> locais_sujos = new ArrayList<>();

    public RoboInteligente(int x_inicial, int y_inicial) {
        super(x_inicial, y_inicial);
    }

    protected String gerarCaminho() {
        return "/smart_robot.png";
    }

    protected String definirTipoRobo() {
        return "Inteligente";
    }

    public void mover(Sujeira[] sujeiras) {
        super.mover(sujeiras);
        registrarSujeira(sujeiras);
    }

    protected void definirPosicoes() {
        if (locais_sujos.isEmpty())
            super.definirPosicoes();
        else
            seguirSujeira();
    }

    protected void limparSujeira(Sujeira[] sujeiras) {
        super.limparSujeira(sujeiras);

        if (local_limpo != null)
            locais_sujos.remove(local_limpo);
    }

    private void registrarSujeira(Sujeira[] sujeiras) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int x_soma = x_atual + i, y_soma = y_atual + j;

                if ((i == 0 && j == 0) || x_soma < 0 || x_soma >= largura || y_soma < 0 || y_soma >= altura)
                    continue;

                for (Sujeira sujeira : sujeiras) {
                    if (sujeira != null) {
                        if (sujeira.getX() == x_soma && sujeira.getY() == y_soma) {
                            locais_sujos.add(sujeira);
                            locais_sujos.add(sujeira);
                        }
                    }
                }
            }
        }
    }

    private void seguirSujeira() {
        Sujeira local_sujo = locais_sujos.getFirst();

        x = local_sujo.getX() - x_atual;
        y = local_sujo.getY() - y_atual;

        if (x > 1)
            x = 1;
        if (x < -1)
            x = -1;
        if (y > 1)
            y = 1;
        if (y < -1)
            y = -1;

        novo_x = x_atual + x;
        novo_y = y_atual + y;
        locais_sujos.remove(local_sujo);
    }
}
