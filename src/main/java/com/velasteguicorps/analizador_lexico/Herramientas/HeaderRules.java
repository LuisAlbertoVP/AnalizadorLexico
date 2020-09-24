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
public final class HeaderRules extends TokensRules{

    private List<Token> listHeaderRules;

    
    public HeaderRules(){}
    
    @Override
    public void createRules() {
        List<Token> headerRules = new ArrayList<>(12);
        headerRules.add(new Token("^include$", "Directiva"));
        headerRules.add(new Token("^define$", "Directiva"));
        headerRules.add(new Token("^DEFINE$", "Directiva"));
        headerRules.add(new Token("^elif$", "Directiva"));
        headerRules.add(new Token("^else$", "Directiva"));
        headerRules.add(new Token("^endif$", "Directiva"));
        headerRules.add(new Token("^error$", "Directiva"));
        headerRules.add(new Token("^if", "Directiva"));
        headerRules.add(new Token("^ifdef$", "Directiva"));
        headerRules.add(new Token("^ifndef$", "Directiva"));
        headerRules.add(new Token("^message$", "Directiva"));
        headerRules.add(new Token("^undef$", "Directiva"));
        listHeaderRules = Collections.unmodifiableList(headerRules);
    }
    
    public String regexForIfDirective(){
        return "\\s$";
    }
    
    public String regexForLibrary(){
        return "^\\<[a-z]{4,12}\\.(h)\\>$";
    }
    
    public String regexForDefine1(){
        return "^([_a-zA-Z]+[_a-zA-Z0-9]*)(\\s)+$";
    }
    
    public String regexForDefine2(){
        return "^[0-9]+(\\.[0-9]+)?$";
    }
   
    
    @Override
    public Token get(int i){
        return listHeaderRules.get(i);
    }
    
    @Override
    public int size(){
        return listHeaderRules.size();
    }
    
    @Override
    public boolean contains(Object arg0) {
        return listHeaderRules.contains(arg0);
    }

    @Override
    public Iterator<Token> iterator() {
        return listHeaderRules.iterator();
    }

    @Override
    public Object[] toArray() {
        return listHeaderRules.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return listHeaderRules.toArray(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return listHeaderRules.containsAll(arg0);
    }

    @Override
    public int indexOf(Object arg0) {
        return listHeaderRules.indexOf(arg0);
    }

    @Override
    public int lastIndexOf(Object arg0) {
        return listHeaderRules.lastIndexOf(arg0);
    }

    @Override
    public ListIterator<Token> listIterator() {
        return listHeaderRules.listIterator();
    }

    @Override
    public ListIterator<Token> listIterator(int arg0) {
        return listHeaderRules.listIterator(arg0);
    }

    @Override
    public List<Token> subList(int arg0, int arg1) {
        return listHeaderRules.subList(arg0, arg1);
    }    
}
