/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;
import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import WordProcessing.WordTransformations;

/**
 *
 * @author M Luisa Díez Platas
 */
public class ProperNamesThisTable {
    static public Hashtable<String,ProperName> tPR=new Hashtable<String,ProperName>();
    private static String path;
    public static void createTable(String p) throws java.io.FileNotFoundException,java.io.IOException{
    	path=p;
        tPR=new Hashtable<String,ProperName>();
       // File archivo = new File ("dataFiles/proper-names.txt");
        File archivo=new File(path+"dataFiles/proper-names.txt");
        //System.out.println(""+path);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line=br.readLine())!=null){
            String[] arr=line.split(";");
            
            tPR.put(arr[0], new ProperName(arr[0],arr[1],arr[2],arr[3]));
        }
        br.close();
}
static public boolean contains(String word){
    return tPR.containsKey(word);
}

static public void newName(String word,String document){
    ProperName newn=new ProperName(word,document,"validar");
    tPR.put(word.toLowerCase(),newn);
}


static public void updateFile(){
    try{
       File newfile=new File(path+"dataFiles/proper-names.txt");
    
        BufferedWriter file = new BufferedWriter(new FileWriter(newfile)); 
        Hashtable<String,ProperName> t=tPR;
        Vector v = new Vector(tPR.keySet());
        
        Collections.sort(v);
        Iterator it = v.iterator();
        while (it.hasNext()) {
            String element =  (String)it.next();
            
            ProperName md=t.get(element);
           
            file.write(md.name+';'+md.document+';'+md.provenance+';'+md.type);
            file.write("\n");
        }
        
         file.close();
            
        
        
    }catch (Exception e){;}
}

public static void putNewName(String name, String document, String provenance){
    try{
    tPR.put(name,new ProperName(name,document,provenance));
    
      }catch(Exception e){;}
}
public static void putNewName(String name, String document, String provenance,String type){
    try{
    tPR.put(name,new ProperName(name,document,provenance));
    
      }catch(Exception e){;}
}

public static String nameType(String name){
    
    ProperName infoName=tPR.get(name);
    if (infoName!=null) return infoName.type; else return null;
}
}
