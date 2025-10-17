package com.inteligencia.computacional;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public abstract class ObjetoCenario {
    protected int x_atual, y_atual;
    protected float ordem_visualizacao = gerarOrdemVisualizacao();
    protected String caminho = gerarCaminho();
    protected ImageView img;
    protected Pane root = Main.getRoot();

    protected abstract float gerarOrdemVisualizacao();

    protected abstract String gerarCaminho();

    public ObjetoCenario(int x_inicial, int y_inicial) {
        this.x_atual = x_inicial;
        this.y_atual = y_inicial;
    }

    public ImageView gerarImagem() {
        ImageView img = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(caminho)).toExternalForm()));

        img.setFitWidth(60f);
        img.setFitHeight(60f);
        img.setLayoutX(x_atual * 60f);
        img.setLayoutY(y_atual * 60f);
        img.setViewOrder(ordem_visualizacao);

        root.getChildren().add(img);
        return img;
    }

    public void setImagem(ImageView img) {
        this.img = img;
    }

    public int getX() {
        return x_atual;
    }

    public int getY() {
        return y_atual;
    }

    public ImageView getImagem() {
        return img;
    }
}
