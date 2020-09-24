package com.velasteguicorps.analizador_lexico.Herramientas;

import com.velasteguicorps.analizador_lexico.VO.Token;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author luis
 */
public final class KeyWordsRules extends TokensRules{
    
    private List<Token> listKeywordsRules;
   
    
    public KeyWordsRules(){}

    @Override
    public void createRules() {
        List<Token> keyWordsRules = new ArrayList<>(37);
        keyWordsRules.add(new Token("^auto", "Palabra reservada"));
        keyWordsRules.add(new Token("^bool", "Macro"));
        keyWordsRules.add(new Token("^break", "Palabra reservada"));
        keyWordsRules.add(new Token("^case", "Palabra reservada"));
        keyWordsRules.add(new Token("^char", "Palabra reservada"));
        keyWordsRules.add(new Token("^const", "Palabra reservada"));
        keyWordsRules.add(new Token("^continue", "Palabra reservada"));
        keyWordsRules.add(new Token("^default", "Palabra reservada"));
        keyWordsRules.add(new Token("^do", "Palabra reservada"));
        keyWordsRules.add(new Token("^double", "Palabra reservada"));
        keyWordsRules.add(new Token("^else", "Palabra reservada"));
        keyWordsRules.add(new Token("^enum", "Palabra reservada"));
        keyWordsRules.add(new Token("^extern", "Palabra reservada"));
        keyWordsRules.add(new Token("^float", "Palabra reservada"));
        keyWordsRules.add(new Token("^for", "Palabra reservada"));
        keyWordsRules.add(new Token("^goto", "Palabra reservada"));
        keyWordsRules.add(new Token("^if", "Palabra reservada"));
        keyWordsRules.add(new Token("^int", "Palabra reservada"));
        keyWordsRules.add(new Token("^long", "Palabra reservada"));
        keyWordsRules.add(new Token("^register", "Palabra reservada"));
        keyWordsRules.add(new Token("^return", "Palabra reservada"));
        keyWordsRules.add(new Token("^short", "Palabra reservada"));
        keyWordsRules.add(new Token("^signed", "Palabra reservada"));
        keyWordsRules.add(new Token("^sizeof", "Palabra reservada"));
        keyWordsRules.add(new Token("^static", "Palabra reservada"));
        keyWordsRules.add(new Token("^struct", "Palabra reservada"));
        keyWordsRules.add(new Token("^switch", "Palabra reservada"));
        keyWordsRules.add(new Token("^typedef", "Palabra reservada"));
        keyWordsRules.add(new Token("^union", "Palabra reservada"));
        keyWordsRules.add(new Token("^unsigned", "Palabra reservada"));
        keyWordsRules.add(new Token("^void", "Palabra reservada"));
        keyWordsRules.add(new Token("^volatile", "Palabra reservada"));
        keyWordsRules.add(new Token("^while", "Palabra reservada")); 
        keyWordsRules.add(new Token("^main", "Principal"));
        keyWordsRules.add(new Token("^false", "Booleano 0"));
        keyWordsRules.add(new Token("^true", "Booleano 1"));
        keyWordsRules.add(new Token("^([_a-zA-Z]+[_a-zA-Z0-9]*)", "ID"));
        listKeywordsRules = Collections.unmodifiableList(keyWordsRules);
    }
    
      
    public String isLetterOrID(){
        return "^([_a-zA-Z0-9])";
    }
    
    public String secondSpecification(){
        return "(\\s|\\(|\\.|\\=|\\;|\\+|\\-|\\*|\\/|\\!|\\%|\\)|\\<|\\>|\\,|\\{|\\}|"
                + "\\^|\\~|\\[|\\]|\\#|\\&|\\|)$";
    }
    
            
    @Override
    public Token get(int i){
        return listKeywordsRules.get(i);
    }
    
    @Override
    public int size(){
        return listKeywordsRules.size();
    }
    
    @Override
    public boolean contains(Object arg0) {
        return listKeywordsRules.contains(arg0);
    }

    @Override
    public Iterator<Token> iterator() {
        return listKeywordsRules.iterator();
    }

    @Override
    public Object[] toArray() {
        return listKeywordsRules.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return listKeywordsRules.toArray(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return listKeywordsRules.containsAll(arg0);
    }

    @Override
    public int indexOf(Object arg0) {
        return listKeywordsRules.indexOf(arg0);
    }

    @Override
    public int lastIndexOf(Object arg0) {
        return listKeywordsRules.lastIndexOf(arg0);
    }

    @Override
    public ListIterator<Token> listIterator() {
        return listKeywordsRules.listIterator();
    }

    @Override
    public ListIterator<Token> listIterator(int arg0) {
        return listKeywordsRules.listIterator(arg0);
    }

    @Override
    public List<Token> subList(int arg0, int arg1) {
        return listKeywordsRules.subList(arg0, arg1);
    }   
}
