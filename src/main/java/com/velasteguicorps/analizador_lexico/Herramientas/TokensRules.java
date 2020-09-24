package com.velasteguicorps.analizador_lexico.Herramientas;

import com.velasteguicorps.analizador_lexico.VO.Token;
import java.util.Collection;
import java.util.List;

 /**
 *
 * @author luis
 */
public abstract class TokensRules implements List<Token>{  
    
    public abstract void createRules();
    
    public static String[] getSpecialRules(){
        String [] specialRules = new String [6];
        specialRules[0] = "^/\\*(\\p{ASCII})*\\*/$";       
        specialRules[1] = "^\\s*/\\*(\\p{ASCII})*$";
        specialRules[2] = "^(\\p{ASCII})+/\\*(\\p{ASCII})*$"; 
        specialRules[3] = "^(\\p{ASCII})*\\*/$";        
        specialRules[4] = "^\\s*//(\\p{ASCII})*$";
        specialRules[5] = "^(\\p{ASCII})+//(\\p{ASCII})*$"; 
        return specialRules;
    }
     
    public static String regexStartForQuotationMarks(){
        return "^(\"|\')";
    }
   
    public static String regexForString(){
        return "^\"(?:[^\\\\\"]|\\\\.)*\"$";
    } 
    
    public static String regexForCharacter(){
        return "^\'([^\']|\\\\.)?\'$";
    }
    
    public static String regexForNumbers(){
        return "^[0-9]+(\\.[0-9]+)?(\\s|\\(|\\=|\\;|\\+|\\-|\\*|\\/|\\!|\\%|\\)|\\<|\\>|"
                + "\\,|\\{|\\}|\\^|\\~|\\[|\\]|\\#|\\&|\\|)$";
    }   
    

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(Token arg0) {
        return false;
    }

    @Override
    public boolean remove(Object arg0) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Token> arg0) {
        return false;
    }

    @Override
    public boolean addAll(int arg0, Collection<? extends Token> arg1) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        return false;
    }

    @Override
    public void clear() {
        //Do nothing
    }

    @Override
    public Token set(int arg0, Token arg1) {
        return null;
    }

    @Override
    public void add(int arg0, Token arg1) {
        //Do nothing
    }

    @Override
    public Token remove(int arg0) {
        return null;
    }
}
