package com.example.migrateFx.controller;

import com.example.migrateFx.Impactable;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BrickController extends SpriteController {

    public BrickController(SpriteModel model,
                           SpriteView view) {
        super(model, view);
    }



    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getView().getView().visibleProperty()
                 .bind(((BrickModel) getModel()).brokenProperty().not());
        getModel().boundsProperty()
                  .bind(getView().getView().boundsInParentProperty());
        ((BrickModel) getModel()).strengthProperty().addListener(
                (observableValue, number, t1) -> {
                    if (t1.intValue() > 0 && getModel().getName().compareTo("Steel") == 0){
                        createCrack();
                    }
                });
        getView().getView().visibleProperty().addListener(
                (observableValue, aBoolean, t1) -> {

                });
    }

    public boolean isBroken() {
        return ((BrickModel)getModel()).isBroken();
    }
    private void createCrack() {
//        WritableImage writableImage;
//        writableImage = new WritableImage(
//                getView().getView().getImage().getPixelReader(),
//                (int) getView().getView().getImage().getWidth(),
//                (int) getView().getView().getImage().getHeight());
//        PixelWriter writer = writableImage.getPixelWriter();
//        getView().getView().setEffect(new InnerShadow(5, 2, 2, Color.WHITE));
    }
}
