package com.gluonhq.strange.ui;

import com.gluonhq.strange.Model;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.stream.Collectors;

public class Circuit extends Control {

    private CircuitOutput output = new CircuitOutput();
    private int idx; // the number of the qubit
    private ObservableList<GateSymbol> gates = FXCollections.observableArrayList();
    
    private final Model model = Model.getInstance();

    public int getIndex() {
        return this.idx;
    }
    
    public Circuit(int idx) {
        this.idx = idx;
        setPrefHeight(70);
        getStyleClass().add("circuit");
        model.getEndStates().addListener((Observable o)-> {
            double mv = model.getEndStates().get(idx);
            output.setMeasuredChance(mv);
        });
        gates.addListener( (Observable o) -> {
            model.setGatesForQubit(
                    idx, gates.stream().map(GateSymbol::getGate).collect(Collectors.toList()));
        });

    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CircuitSkin(this);
    }

    public ObservableList<GateSymbol> getGateSymbols() {
        return this.gates;
    }
    
    public CircuitOutput getOutput() {
        return output;
    }
    
    
}