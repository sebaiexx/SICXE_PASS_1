package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void AppendF1Format()
    {
        F1.add("FIX");
        F1.add("FLOAT");
        F1.add("HIO");
        F1.add("NORM");
        F1.add("SIO");
        F1.add("TIO");
    }

    static void AppendF2Format()
    {
        F2.add("ADDR");
        F2.add("CLEAR");
        F2.add("COMPR");
        F2.add("DIVR");
        F2.add("MULR");
        F2.add("RMO");
        F2.add("SHIFTL");
        F2.add("SHIFTR");
        F2.add("SUBR");
        F2.add("SVC");
        F2.add("TIXR");
    }

    static void AppendF3Format()
    {

        F3.add("LDB");
        F3.add("LDCH");
        F3.add("LDF");
        F3.add("LDL");
        F3.add("LDS");
        F3.add("LDT");
        F3.add("LDX");
        F3.add("LPS");
        F3.add("MUL");
        F3.add("MULF");
        F3.add("OR");
        F3.add("RD");
        F3.add("ADD");
        F3.add("ADDF");
        F3.add("AND");
        F3.add("COMP");
        F3.add("COMPF");
        F3.add("DIV");
        F3.add("DIVF");
        F3.add("J");
        F3.add("JEQ");
        F3.add("JGT");
        F3.add("JLT");
        F3.add("JSUB");
        F3.add("LDA");
        F3.add("RSUB");
        F3.add("SSK");
        F3.add("STA");
        F3.add("STB");
        F3.add("STCH");
        F3.add("STF");
        F3.add("STI");
        F3.add("STL");
        F3.add("STS");
        F3.add("STSW");
        F3.add("STT");
        F3.add("STX");
        F3.add("SUB");
        F3.add("SUBF");
        F3.add("TD");
        F3.add("TIX");
    }
    static void emptyLiterals()
    {
        int i = 0;
        while(!literalSize.isEmpty())
        {
            literalAddress.add(address);
            address+=literalSize.get(i);
            literalSize.remove(i);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        AppendF1Format();
        AppendF2Format();
        AppendF3Format();
        File codeFile = new File("C:\\Users\\Sebaiexx\\IdeaProjects\\SICXE PASS 1 MOHAMED TAREK 18102610 ABDELRAHMAN MOHAMED 18107192\\IN.txt");
        Scanner code = new Scanner(codeFile);
        File location = new File("output.txt");
        FileWriter addLocationTable = new FileWriter(location);
        AppendF1Format();
        AppendF2Format();
        AppendF3Format();
        while (code.hasNext()) {
            String line = code.nextLine();
            String[] lineArray = line.split("\\t");


            addLocationTable.append(String.format("%04X", address) + "	" + line+"\n");


            System.out.println(String.format("%04X", address) + "	" + line);
            if (lineArray[0].equals("LTORG")) {
                emptyLiterals();
            } else if (lineArray[0].equals("BASE")) {
                address = address;
            } else if (lineArray[1].equals("START")) {
                ProgramName = lineArray[0];
                address = Integer.parseInt(lineArray[2]);
                startAddress = address;
            } else if (F1.contains(lineArray[0])) {
                addressLocations.add(address);
                address = address + 1;
            } else if (F2.contains(lineArray[0])) {
                addressLocations.add(address);
                address = address + 2;
            } else if (F3.contains(lineArray[0]))
            {
                addressLocations.add(address);
                if (lineArray[1].charAt(0) == '=') {
                    if (!literals.contains(lineArray[1])) {
                        String[] str2 = lineArray[1].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                        literals.add(lineArray[1]);
                    }
                }
                address = address + 3;
            }
            else if (lineArray[0].charAt(0) == '&') {
                addressLocations.add(address);
                if (lineArray[1].charAt(0) == '=') {
                    if (!literals.contains(lineArray[1])) {
                        String[] str2 = lineArray[1].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                        literals.add(lineArray[1]);
                    }
                }
                address = address + 3;
            }
            else if (lineArray[0].charAt(0) == '$') {
                addressLocations.add(address);
                if (lineArray[1].charAt(0) == '=') {
                    if (!literals.contains(lineArray[1])) {
                        String[] str2 = lineArray[1].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                        literals.add(lineArray[1]);
                    }
                }
                address = address + 4;
            }
            else if (lineArray[0].charAt(0) == '+') {
                addressLocations.add(address);
                if (lineArray[1].charAt(0) == '=') {
                    if (!literals.contains(lineArray[1])) {
                        String[] str2 = lineArray[1].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                        literals.add(lineArray[1]);
                    }
                }
                address = address + 4;
            }
            else if (F1.contains(lineArray[1])) {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                address = address + 1;
            } else if (F2.contains(lineArray[1])) {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                address = address + 2;
            } else if (F3.contains(lineArray[1])) {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                if (lineArray[2].charAt(0) == '=') {
                    if (!literals.contains(lineArray[2])) {
                        literals.add(lineArray[2]);
                        literalAddress.add(address);
                        String[] str2 = lineArray[2].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                    }
                }
                address = address + 3;
            } else if (lineArray[1].charAt(0) == '+') {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                if (lineArray[2].charAt(0) == '=') {
                    if (!literals.contains(lineArray[2])) {
                        literals.add(lineArray[2]);
                        String[] str2 = lineArray[2].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                    }
                }
                address = address + 4;
            }
            else if (lineArray[1].charAt(0) == '&') {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                if (lineArray[2].charAt(0) == '=') {
                    if (!literals.contains(lineArray[2])) {
                        literals.add(lineArray[2]);
                        String[] str2 = lineArray[2].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                    }
                }
                address = address + 3;
            }
            else if (lineArray[1].charAt(0) == '$') {
                variables.add(lineArray[0]);
                variableLocations.add(address);
                addressLocations.add(address);
                if (lineArray[2].charAt(0) == '=') {
                    if (!literals.contains(lineArray[2])) {
                        literals.add(lineArray[2]);
                        String[] str2 = lineArray[2].split("'");
                        if (str2[0].equals("=C")) {
                            literalSize.add(str2[1].length());
                        }
                        if (str2[0].equals("=X")) {
                            literalSize.add((str2[1].length() + 1) / 2);
                        }
                    }
                }
                address = address + 4;
            }
            else if (lineArray[1].equals("RESW")) {
                variables.add(lineArray[0]);
                variableLocations.add(address);

                address = address + (Integer.parseInt(lineArray[2]) * 3);
            } else if (lineArray[1].equals("RESB")) {
                variables.add(lineArray[0]);
                variableLocations.add(address);

                address = address + (Integer.parseInt(lineArray[2]));
            } else if (lineArray[1].equals("WORD")) {
                variables.add(lineArray[0]);
                int value = 0;
                int variable;
                int type = 0;
                String[] str2 = lineArray[2].split(" ");
                if (variables.contains(str2[0])) {
                    type++;
                    int temp = variables.indexOf(str2[0]);
                    value = variableLocations.get(temp);
                } else {
                    value = Integer.parseInt(str2[0]);
                }
                for (int i = 1; i < str2.length; i = i + 2) {
                    if (str2[i].equals("+")) {
                        if (variables.contains(str2[i + 1])) {
                            type++;
                            variable = variables.indexOf(str2[i + 1]);
                            value = value + variableLocations.get(variable);
                        } else {
                            value = value + Integer.parseInt(str2[i + 1]);
                        }
                    } else if (str2[i].equals("-")) {
                        if (variables.contains(str2[i + 1])) {
                            type++;
                            variable = variables.indexOf(str2[i + 1]);
                            value = value - variableLocations.get(variable);
                        } else {
                            value = value - Integer.parseInt(str2[i + 1]);
                        }
                    }
                }
                variableLocations.add(value);

            } else if (lineArray[1].equals("BYTE")) {
                variables.add(lineArray[0]);
                variableLocations.add(address);


                String[] str2 = lineArray[2].split("'");
                if (str2[0].equals("C")) {
                    address += str2[1].length();
                }
                if (str2[0].equals("X")) {
                    address += ((str2[1].length() + 1) / 2);
                } else {
                    address += 3;
                }
            } else if (lineArray[0].equals("END")) {
                endAddress = address;
                emptyLiterals();
            }
            else if(lineArray[1].equals("EQU"))
            {
                variables.add(lineArray[0]);
                if (lineArray[2].equals("*"))
                {
                    address = address;
                    variableLocations.add(address);
                }
                else
                {
                    int value = 0;
                    int variable;
                    int type = 0;
                    String [] str2 = lineArray[2].split(" ");
                    if(variables.contains(str2[0]))
                    {
                        type++;
                        int temp = variables.indexOf(str2[0]);
                        value = variableLocations.get(temp);
                    }
                    else
                    {
                        value = Integer.parseInt(str2[0]);
                    }
                    for(int i = 1; i < str2.length; i = i+2)
                    {
                        if(str2[i].equals("+"))
                        {
                            if(variables.contains(str2[i+1]))
                            {
                                type++;
                                variable = variables.indexOf(str2[i+1]);
                                value = value + variableLocations.get(variable);
                            }
                            else
                            {
                                value = value + Integer.parseInt(str2[i+1]);
                            }
                        }
                        else if(str2[i].equals("-"))
                        {
                            if(variables.contains(str2[i+1]))
                            {
                                type++;
                                variable = variables.indexOf(str2[i+1]);
                                value = value - variableLocations.get(variable);
                            }
                            else
                            {
                                value = value - Integer.parseInt(str2[i+1]);
                            }
                        }
                    }
                    variableLocations.add(value);
                }
            }
        }
        addLocationTable.close();
        code.close();
        File symbolTable = new File("symbolTable.txt");
        FileWriter addSymbolTable = new FileWriter(symbolTable);
        addSymbolTable.append("Symbol\t\tAddress\n\n");
        for (int k = 0; k < variables.size(); k++) {
            addSymbolTable.append(variables.get(k) + "\t" +"\t" + String.format("%04X", variableLocations.get(k)) + "\n");
        }
        addSymbolTable.close();

        File literalTable = new File("literalTable.txt");
        FileWriter addLiteralTable = new FileWriter(literalTable);
        addLiteralTable.append("Literal\t\tAddress\n\n");
        for (int m = 0; m < literals.size(); m++) {
            addLiteralTable.append(literals.get(m) + "\t\t" + (Integer.toHexString(literalAddress.get(m)).toUpperCase()) + "\n");
        }
        addSymbolTable.close();
        addLiteralTable.close();

    }
    static ArrayList<String> F1 = new ArrayList<String>();
    static ArrayList<String> F2 = new ArrayList<String>();
    static ArrayList<String> F3 = new ArrayList<String>();
    static ArrayList<String> variables = new ArrayList<String>();
    static ArrayList<Integer> variableLocations = new ArrayList<Integer>();
    static ArrayList<Integer> addressLocations = new ArrayList<Integer>();
    static ArrayList<String> literals = new ArrayList<String>();
    static ArrayList<Integer> literalAddress = new ArrayList<Integer>();
    static ArrayList<Integer> literalSize = new ArrayList<Integer>();




    static String ProgramName;
    static int address = 0;
    static int startAddress = 0;
    static int endAddress = 0;
    static String base;
    static int baseAddress = 0;
    static int literalCounter = 0;
}