package com.example.migrateFx;

import com.example.util.Sprite;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Test extends Application {
    SpriteView m_spriteView;
    SpriteController m_spriteController;
    SpriteModel m_model;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        Pane pane = new Pane();
        pane.setPrefSize(300, 300);
        Button button = new Button();
        button.setOnAction(actionEvent -> {m_model.setLocation(
                m_model.getLocation().add(new Point2D(10, 10)
                ));
            System.out.println(m_spriteView.getView().xProperty().get());
            System.out.println(m_model.getXLocationProperty().get());
        });
        pane.getChildren().add(button);
        borderPane.setCenter(pane);
        initiaize();
        m_spriteView.addView(pane);
        stage.setScene(scene);
        stage.show();

    }
    private void initiaize() {
         m_spriteView = new SpriteView(
                "D:\\Data\\Praket\\Nottingham\\Y2\\DMS\\Breakout_Clone\\src\\main\\resources\\com\\example\\breakout_clone_javafx\\sprite\\blue\\tile000\\tile000.png");
        m_model = new SpriteModel();
        m_model.setLocation(new Point2D(50, 50));
        m_spriteController = new SpriteController(m_model, m_spriteView);
        m_spriteController.initalize();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
