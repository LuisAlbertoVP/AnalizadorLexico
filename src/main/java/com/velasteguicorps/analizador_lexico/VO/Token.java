package com.velasteguicorps.analizador_lexico.VO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author luis
 */
public class Token {
    private final StringProperty value;
    private final StringProperty type;
    private final IntegerProperty line;
    private final IntegerProperty column;
    
    
    public Token(){
        value = new SimpleStringProperty();
        type = new SimpleStringProperty();
        line = new SimpleIntegerProperty();
        column = new SimpleIntegerProperty();
    }

    
    public Token(String v, String t){
        value = new SimpleStringProperty(v);
        type = new SimpleStringProperty(t);
        line = new SimpleIntegerProperty();
        column = new SimpleIntegerProperty();
    }

    
    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public Integer getLine() {
        return line.get();
    }

    public void setLine(Integer linea) {
        this.line.set(linea);
    }
    
    public Integer getColumn() {
        return column.get();
    }

    public void setColumn(Integer column) {
        this.column.set(column);
    }
    
    
    public StringProperty getValueProperty(){
        return value;
    }
    
    public StringProperty getTypeProperty(){
        return type;
    }
    
    public IntegerProperty getLineProperty(){
        return line;
    }
    
    public IntegerProperty getColumnProperty(){
        return column;
    }        
}
