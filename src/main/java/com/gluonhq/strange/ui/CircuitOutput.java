package com.gluonhq.strange.ui;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class CircuitOutput extends Control {

    public CircuitOutput() {
        getStyleClass().add("circuit-output");
        setPrefWidth(45);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CircuitOutputSkin(this);
    }

    // measuredChanceProperty
    private final DoubleProperty measuredChanceProperty = new SimpleDoubleProperty(this, "measured chance", .5) {
        @Override
        public void set(double newValue) {
            if (newValue >= 0 && newValue <= 1) {
                super.set(newValue);
            }
        }
    };

    public final DoubleProperty measuredChanceProperty() {
        return measuredChanceProperty;
    }

    public final double getMeasuredChance() {
        return measuredChanceProperty.get();
    }

    public final void setMeasuredChance(double value) {
        measuredChanceProperty.set(value);
    }


}