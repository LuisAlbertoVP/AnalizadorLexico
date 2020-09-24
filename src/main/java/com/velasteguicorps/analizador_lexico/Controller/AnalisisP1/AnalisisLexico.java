package com.velasteguicorps.analizador_lexico.Controller.AnalisisP1;

import com.velasteguicorps.analizador_lexico.Herramientas.KeyWordsRules;
import com.velasteguicorps.analizador_lexico.Herramientas.HeaderRules;
import com.velasteguicorps.analizador_lexico.Herramientas.OperatorsRules;
import com.velasteguicorps.analizador_lexico.Herramientas.TablaDeSimbolos;
import com.velasteguicorps.analizador_lexico.Herramientas.TokensRules;
import com.velasteguicorps.analizador_lexico.VO.Token;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 *
 * @author luis
 */
public class AnalisisLexico {
    
    HeaderRules headerRules;
    KeyWordsRules keyWordsRules;
    OperatorsRules operatorsRules;
    StringBuilder output;
    TablaDeSimbolos symbolsTable;
    int start, current;
    int id, nLine, nColumna;
    int nErrors;
    boolean band;
    boolean isIfDirective;
    boolean hasMoreOperators;
    boolean waitToFinishHeader;
    boolean WillBeString;
    boolean AreThereErrors;
    
    
    public AnalisisLexico(String programaFuente, HeaderRules headerRules, 
            KeyWordsRules keyWordsRules, OperatorsRules operatorsRules){
        this.headerRules = headerRules;
        this.keyWordsRules = keyWordsRules;
        this.operatorsRules = operatorsRules;
        output = new StringBuilder(); 
        start(programaFuente);
    }
    
    
    private void start(String programaFuente){ 
        long startTime = System.currentTimeMillis();
        symbolsTable = new TablaDeSimbolos();
        StringBuilder finalProgram = new StringBuilder();
        deleteComments(programaFuente, finalProgram);
        long endTime = System.currentTimeMillis() - startTime;
        output.append("Tiempo transcurrido: ").append(endTime).append(" ms");
    }
    
    private void deleteComments(String programaFuente, StringBuilder finalProgram){ 
        nColumna = 1;
        String[] lineas = programaFuente.split("\n");
        for(String line: lineas){
            nLine++;
            if(!isBlank(line)){
                if(!isUnion(line, specialRules()[0])){ 
                    if(band == false){
                        if(!isUnion(line, specialRules()[1])){
                            if(!isUnion(line, specialRules()[2])){
                                if(!isUnion(line, specialRules()[4])){ 
                                    if(!isUnion(line, specialRules()[5])){
                                        finalProgram.append(line); 
                                        deleteWhiteSpaces(finalProgram, nLine);
                                    }else{                    
                                        String[] newLines = line.split("//");
                                        int last = newLines[0].length();
                                        finalProgram.append(line.substring(0, last));
                                        deleteWhiteSpaces(finalProgram, nLine);
                                    }
                                }  
                            }else{
                                String[] newLines = line.split("/\\*");
                                int last = newLines[0].length();
                                finalProgram.append(line.substring(0, last)); 
                                deleteWhiteSpaces(finalProgram, nLine);
                                band = true;
                            }
                        }else
                            band = true;
                    }
                    if(isUnion(line, specialRules()[3]))
                        band = false; 
                } 
                nColumna = 1;
            }
        }
        if(AreThereErrors == true){
            if(nErrors == 1)
                output.append("Se ha generado ").append(nErrors).append(" error");
            else
                output.append("Se han generado ").append(nErrors).append(" errores");
            output.append("\n").append("\n");
        }
        output.append("FINAL").append("\n");
        output.append(finalProgram.toString()).append("\n").append("\n");
    }
    
    private void deleteWhiteSpaces(StringBuilder finalProgram, int line){
        boolean flag = true;
        if(WillBeString != true){
            while(start < finalProgram.length() && flag == true){
                if(current < finalProgram.length()){
                    if(!isBlank(finalProgram.substring(start, current + 1))){
                        if(checkToken(finalProgram, line))
                            start = current + 1;
                        current ++;
                    }else
                        finalProgram.deleteCharAt(current);
                }else{
                    AreThereErrors = true;
                    nErrors++;
                    output.append("Error en linea: ").append(line).append("\n");
                    if(symbolsTable.get(id-1) != null){
                        if(symbolsTable.get(id-1).getLine().equals(line))
                            output.append("Error cerca de: ").append(symbolsTable.get(id-1).getValue()).
                                append("\n");
                        else
                            output.append("Error al inicio de linea").append("\n");
                    }
                    finalProgram.delete(start, current);
                    current = finalProgram.length();
                    start = current;
                }
            }
        }
    }
   
    public boolean checkToken(StringBuilder finalProgram, int line){
        boolean flag = false;
        int i = 0;       
        if(isStartHeader(finalProgram, line) || waitToFinishHeader == true){
            while(i < headerRules.size() && flag == false){
                if(isUnion(finalProgram, headerRules.get(i).getValue())){
                    if(!finalProgram.substring(start, current + 1).equals("if") && isIfDirective == false){
                        generateToken(finalProgram, headerRules.get(i).getType(), line);
                        flag = true;
                    }else{
                        isIfDirective = true;
                        if(checkToken(finalProgram, headerRules.regexForIfDirective(), "Directiva", line)){
                            isIfDirective = false;
                            flag = true;
                        }
                    }
                }           
                i++;
            }
            if(isUnion(finalProgram, headerRules.regexForLibrary())){
                generateToken(finalProgram, "Libreria", line);
                waitToFinishHeader = false;
                flag = true;
            }
            if(isUnion(finalProgram, headerRules.regexForDefine1())){
                generateToken(finalProgram, "Macro", line);
                current--;
                flag = true;
            }
            if(current + 1 == finalProgram.length()){
                if(isUnion(finalProgram, headerRules.regexForDefine2())){
                    generateToken(finalProgram, "Valor", line);
                    waitToFinishHeader = false;
                    flag = true;
                }
            }
        }else{
            if(isUnion(finalProgram, TokensRules.regexStartForQuotationMarks())) 
                WillBeString = true;
            if(WillBeString == false){
                if(isUnion(finalProgram, keyWordsRules.isLetterOrID())){
                    while(i < keyWordsRules.size() && flag == false){            
                        if(checkToken(finalProgram, keyWordsRules.get(i).getValue() + 
                                keyWordsRules.secondSpecification(), keyWordsRules.get(i).getType(), line))
                            flag = true;     
                        i++;
                    }      
                }else{
                    while(i < operatorsRules.size() && flag == false){
                        if(!operatorsRules.isOperatorX(finalProgram.substring(start, current + 1)) && 
                                hasMoreOperators == false){
                            if(isUnion(finalProgram, operatorsRules.get(i).getValue())){
                                generateToken(finalProgram, operatorsRules.get(i).getType(), line);
                                flag = true;
                            }
                        }else{
                            hasMoreOperators = true;
                            if(checkToken(finalProgram, operatorsRules.get(i).getValue() + 
                                    operatorsRules.regexForMoreOperators(), operatorsRules.get(i).getType(),
                                    line)){
                                hasMoreOperators = false;
                                flag = true;
                            }
                        }
                        i++;
                    }          
                }
                if(flag == false){
                    if(checkToken(finalProgram, TokensRules.regexForNumbers(), "Numero", line))
                        flag = true;
                }
            }else{
                flag = isCharacterOrString(finalProgram, TokensRules.regexForCharacter(),"Caracter", line);
                if(flag == false)
                    flag = isCharacterOrString(finalProgram, TokensRules.regexForString(),"Cadena", line);
            }
        }
        return flag;
    }
    
    public boolean isStartHeader(StringBuilder finalProgram, int line){
        boolean flag = false;
        if(finalProgram.substring(start, current + 1).equals("#")){
            generateToken(finalProgram, "Numeral", line);
            flag = true;
            waitToFinishHeader = true;
            current++;
            start = current;
        }
        
        return flag;
    }
    
    public boolean isCharacterOrString(StringBuilder finalProgram, String regex, String type, int line){
        boolean flag = false;
        if(isUnion(finalProgram, regex)){
            generateToken(finalProgram, type, line);
            WillBeString = false;
            flag = true;
        }
        return flag;
    }
    
    public boolean checkToken(StringBuilder finalProgram, String regex, String type, int line){
        boolean flag = false;
        if(isUnion(finalProgram, regex)){
            current--;
            generateToken(finalProgram, type, line);
            flag = true;
        }
        return flag;
    }
    
    public void generateToken(StringBuilder finalProgram, String tipo, int line){
        Token token = new Token();
        token.setValue(finalProgram.substring(start, current + 1));
        token.setType(tipo);
        token.setLine(line);
        token.setColumn(nColumna);
        symbolsTable.put(id, token);
        id++;
        nColumna++;
    }
 
    private boolean isBlank(String line){
        return line.trim().length() == 0;
    }
    
    public boolean isUnion(String string, String regex){
        Pattern p = Pattern.compile(regex);
        return p.matcher(string).find();
    }
    
    public boolean isUnion(StringBuilder finalProgram, String regex){
        Pattern p = Pattern.compile(regex);
        return p.matcher(finalProgram.substring(start, current + 1)).find();
    }
    
    public String getResults(){
        return output.toString();
    }
    
    public HashMap<Integer, Token> getSymbolTable(){
        return symbolsTable;
    }
    
    public String[] specialRules(){
        return TokensRules.getSpecialRules();
    }  
}
