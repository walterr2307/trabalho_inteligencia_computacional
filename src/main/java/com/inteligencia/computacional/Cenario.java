package com.inteligencia.computacional;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class Cenario extends Application {

    private void gerarRobos(ObjetoCenario[] objs) {
        int i = 0, qtd = 0;

        for (ObjetoCenario obj : objs) {
            if (obj instanceof Robo)
                ++qtd;
        }

        Robo[] robos = new Robo[qtd];
        ArrayList<Sujeira> sujeiras = new ArrayList<>();

        for (ObjetoCenario obj : objs) {
            if (obj instanceof Robo) {
                robos[i] = (Robo) obj;
                ++i;
            } else {
                sujeiras.add((Sujeira) obj);
            }
        }

        iniciarLoop(robos, sujeiras);
    }

    private void iniciarLoop(Robo[] robos, ArrayList<Sujeira> sujeiras) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5f), _ -> {
            for (Robo robo : robos)
                robo.mover(sujeiras);

            if (sujeiras.isEmpty()) {
                for (Robo robo : robos)
                    System.out.println(robo.imprimirInformacoes());

                System.exit(0);
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void start(Stage stage) {
        int largura = Main.getLargura(), altura = Main.getAltura();
        Pane root = Main.getRoot();
        ObjetoCenario[] objs = Main.getObjetosCenario();
        ImageView img = new ImageView(new Image(Objects.requireNonNull(getClass().
                getResource("/wallpaper.jpg")).toExternalForm()));

        img.setFitWidth(largura * 60f);
        img.setFitHeight(altura * 60f);
        root.getChildren().add(img);

        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                Rectangle casa = new Rectangle(60f, 60f);
                casa.setStrokeWidth(4f);
                casa.setStroke(Color.WHITE);
                casa.setFill(Color.TRANSPARENT);
                casa.setWidth(i * 60f + 60f);
                casa.setHeight(j * 60f + 60f);
                root.getChildren().add(casa);
            }
        }

        for (ObjetoCenario obj : objs)
            obj.setImagem(obj.gerarImagem());

        Scene scene = new Scene(root, img.getFitWidth(), img.getFitHeight());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        gerarRobos(objs);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
