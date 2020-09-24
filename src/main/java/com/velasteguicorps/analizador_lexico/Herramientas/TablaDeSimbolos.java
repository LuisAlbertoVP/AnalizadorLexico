package com.velasteguicorps.analizador_lexico.Herramientas;

import com.velasteguicorps.analizador_lexico.VO.Token;
import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 *
 * @author luis
 */
public class TablaDeSimbolos extends HashMap<Integer, Token>{
    
    private final HashMap<Integer, Token> tablaSimbolos;
    
    public TablaDeSimbolos(){
        tablaSimbolos = new HashMap<>();
    }
    
    @Override
    public Token put(Integer key, Token token){
        return tablaSimbolos.put(key, token);
    }
    
    @Override
    public Token get(Object Key){
        return tablaSimbolos.get(Key);
    }
    
    @Override
    public int size(){
        return tablaSimbolos.size();
    }
    
    @Override
    public void forEach(BiConsumer<? super Integer, ? super Token> action){
        tablaSimbolos.forEach(action);
    }
}
