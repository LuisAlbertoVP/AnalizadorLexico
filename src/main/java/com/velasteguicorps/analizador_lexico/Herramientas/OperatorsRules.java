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
public final class OperatorsRules extends TokensRules{
    
    private List<Token> listOperatorsRules;
    

    public OperatorsRules(){}
    
    @Override
    public void createRules() {
        List<Token> operatorsRules = new ArrayList<>(38);
        operatorsRules.add(new Token("^=", "Operador asignacion"));
        operatorsRules.add(new Token("^\\=\\=", "Operador igual que"));
        operatorsRules.add(new Token("^\\+", "Operador suma"));
        operatorsRules.add(new Token("^\\+\\+", "Operador incremento"));
        operatorsRules.add(new Token("^\\-", "Operador resta"));
        operatorsRules.add(new Token("^\\-\\-", "Operador decremento"));
        operatorsRules.add(new Token("^\\-\\>", "Operador miembro de puntero"));
        operatorsRules.add(new Token("^\\*", "Operador multiplicacion"));
        operatorsRules.add(new Token("^\\/", "Operador division"));
        operatorsRules.add(new Token("^\\%", "Operador modulo"));
        operatorsRules.add(new Token("^\\<", "Operador menor"));
        operatorsRules.add(new Token("^\\<\\=", "Operador menor o igual"));
        operatorsRules.add(new Token("^\\<\\<", "Operador desplazamiento a la izquierda"));
        operatorsRules.add(new Token("^\\<\\<\\=", "Operador asignacion desplazamiento a la izquierda"));
        operatorsRules.add(new Token("^\\>", "Operador mayor"));
        operatorsRules.add(new Token("^\\>\\=", "Operador mayor o igual"));
        operatorsRules.add(new Token("^\\>\\>", "Operador desplazamiento a la derecha"));
        operatorsRules.add(new Token("^\\>\\>\\=", "Operador asignacion desplazamiento a la derecha"));
        operatorsRules.add(new Token("^\\!", "Operador negacion"));
        operatorsRules.add(new Token("^\\!\\=", "Operador diferente de"));
        operatorsRules.add(new Token("^\\.", "Operador miembro")); 
        operatorsRules.add(new Token("^\\&", "Operador AND binario"));
        operatorsRules.add(new Token("^\\&\\=", "Operador asignacion AND binario"));
        operatorsRules.add(new Token("^\\&\\&", "Operador AND logico"));
        operatorsRules.add(new Token("^\\|", "Operador OR binario"));
        operatorsRules.add(new Token("^\\|\\=", "Operador asignacion OR binario"));
        operatorsRules.add(new Token("^\\|\\|", "Operador OR logico"));
        operatorsRules.add(new Token("^\\^", "Operador XOR binario"));
        operatorsRules.add(new Token("^\\^\\=", "Operador asignacion XOR binario"));
        operatorsRules.add(new Token("^\\~", "Operador complemento a uno")); 
        operatorsRules.add(new Token("^\\,", "Coma"));
        operatorsRules.add(new Token("^\\;", "Punto y coma")); 
        operatorsRules.add(new Token("^\\{", "Llave abierta"));
        operatorsRules.add(new Token("^\\}", "Llave cerrada"));
        operatorsRules.add(new Token("^\\(", "Abre parentesis"));
        operatorsRules.add(new Token("^\\)", "Cierre parentesis"));
        operatorsRules.add(new Token("^\\[", "Abre corchete"));
        operatorsRules.add(new Token("^\\]", "Cierre corchete"));
        listOperatorsRules = Collections.unmodifiableList(operatorsRules);
    }
    
    public boolean isOperatorX(String op){
        return (op.equals("+") || op.equals("-") || op.equals("<") || op.equals(">")
                || op.equals("!") || op.equals("=") || op.equals("|") || op.equals("&")
                || op.equals("<<") || op.equals(">>") || op.equals("^"));
    }
    
    public String regexForMoreOperators(){
        return "(\\s|;|\'|\"|\\(|\\)|[_a-zA-Z0-9])$";
    }
    
    @Override
    public Token get(int i){
        return listOperatorsRules.get(i);
    }
    
    @Override
    public int size(){
        return listOperatorsRules.size();
    }

    @Override
    public boolean contains(Object arg0) {
        return listOperatorsRules.contains(arg0);
    }

    @Override
    public Iterator<Token> iterator() {
        return listOperatorsRules.iterator();
    }

    @Override
    public Object[] toArray() {
        return listOperatorsRules.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {
        return listOperatorsRules.toArray(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        return listOperatorsRules.containsAll(arg0);
    }

    @Override
    public int indexOf(Object arg0) {
        return listOperatorsRules.indexOf(arg0);
    }

    @Override
    public int lastIndexOf(Object arg0) {
        return listOperatorsRules.lastIndexOf(arg0);
    }

    @Override
    public ListIterator<Token> listIterator() {
        return listOperatorsRules.listIterator();
    }

    @Override
    public ListIterator<Token> listIterator(int arg0) {
        return listOperatorsRules.listIterator(arg0);
    }

    @Override
    public List<Token> subList(int arg0, int arg1) {
        return listOperatorsRules.subList(arg0, arg1);
    }
}
