package com.inteligencia.computacional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class Cenario extends Application {
    private final int largura = 3, altura = 2;
    private final Pane root = new Pane();

    public void start(Stage stage) {
        ImageView img = new ImageView(new Image(Objects.requireNonNull(getClass().
                getResource("/com/inteligencia/computacional/walpaper.jpg")).toExternalForm()));

        img.setFitWidth(largura * 50f);
        img.setFitHeight(altura * 50f);

        root.getChildren().add(img);
        Scene scene = new Scene(root, img.getFitWidth(), img.getFitHeight());
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
