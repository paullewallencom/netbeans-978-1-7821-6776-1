/*
 * Copyright 2014 David Salter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.davidsalter.cookbook.fxcomponent.custom;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author David Salter <david@developinjava.com>
 */
public class CustomInput extends HBox {

    @FXML private Label label;
    @FXML private TextField text;
    
    public CustomInput() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomInput.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(CustomInput.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    
    public String getLabel() {
        return labelProperty().get();
    }
    public void setLabel(String value) {
        labelProperty().set(value);
    }
    
    public StringProperty labelProperty() {
        return label.textProperty();
    }

    public String getText() {
        return textProperty().get();
    }
    public void setText(String value) {
        textProperty().set(value);
    }
    
    public StringProperty textProperty() {
        return text.textProperty();
    }
}
