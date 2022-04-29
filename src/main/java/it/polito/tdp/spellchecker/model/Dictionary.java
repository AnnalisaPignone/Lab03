package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	int i; 
	int j; 
	List <String> DizionarioInglese;
	List <String> DizionarioItaliano;
	boolean inglese=false; 
	boolean italiano=false; 
	List <RichWord> result2;
	
	
	//LETTURA FILE INGLESE
	public void letturaFileInglese () {
	try {
		DizionarioInglese= new ArrayList <String>(); 
		inglese=true; 
		FileReader fr= new FileReader("src/main/resources/English.txt"); //src/main/resources/" + language + ".txt"
		BufferedReader br=new BufferedReader(fr); 
		String word;
		while ((word=br.readLine())!=null) {
			DizionarioInglese.add(word);
		}
		
		br.close();
	} 
	catch(IOException e) {
		System.out.println("Errore nella lettura del file 1"); 
	}
	}
	
	
	
	//LETTURA FILE ITALIANO
	public void letturaFileItaliano () {
	try {
		DizionarioItaliano= new ArrayList <String>();
		italiano=true;
		FileReader fr= new FileReader("src/main/resources/Italian.txt");
		BufferedReader br=new BufferedReader(fr); 
		String word;
		while ((word=br.readLine())!=null) {
			DizionarioItaliano.add(word);
		}
		
		br.close();
	} 
	catch(IOException e) {
		System.out.println("Errore nella lettura del file 2"); 
	}
	}
	
	
	//METODO SPELLCHECKTEXT
	public List <RichWord> spellCheckText (List <String> inputTextList){
		List <RichWord> result= new ArrayList <RichWord>();
		for (i=0; i<inputTextList.size(); i++)
		{
		RichWord r= new RichWord (inputTextList.get(i), false);
		result.add(r);
		}

		//se la parola e inglese 
		if(inglese==true) {
		for (RichWord r: result)
		{
			for (String d:DizionarioInglese)
			{
				if(r.getParola().compareToIgnoreCase(d)==0)
					r.setCorretta(true);
			}
		}
		}
		
		//se la parole e italiana
		if (italiano==true) {
		for (RichWord r: result)
		{
			for (String d:DizionarioItaliano)
			{
				if(r.getParola().compareToIgnoreCase(d)==0)
					r.setCorretta(true);
			}
		}
		}
		
		//restituisco quelle false 
		result2= new ArrayList <RichWord>();
		for (i=0; i<result.size(); i++)
		{
			if(result.get(i).isCorretta()==false)
				result2.add(result.get(i));
		}
		
		return result2; 
	}
	
	
	
	//METODO SPELLCHECKTESTLINEAR
	public List <RichWord> spellCheckTextLinear (List <String> inputTextList){
		List <RichWord> result= new ArrayList <RichWord>();
		for (i=0; i<inputTextList.size(); i++)
		{
		RichWord r= new RichWord (inputTextList.get(i), false);
		result.add(r);
		}

		//se la parola e inglese 
		if(inglese==true) {
		for (RichWord r: result)
		{
			for (String d:DizionarioInglese)
			{
				if(r.getParola().compareToIgnoreCase(d)==0) {
					r.setCorretta(true);
					break;
				}
			}
		}
		}
		
		//se la parole e italiana
		if (italiano==true) {
		for (RichWord r: result)
		{
			for (String d:DizionarioItaliano)
			{
				if(r.getParola().compareToIgnoreCase(d)==0) {
					r.setCorretta(true);
					break;
				}
			}
		}
		}
		
		//restituisco quelle false 
		result2= new ArrayList <RichWord>();
		for (i=0; i<result.size(); i++)
		{
			if(result.get(i).isCorretta()==false)
				result2.add(result.get(i));
		}
		
		return result2; 
	}
	
	
	//METODO SPELLCHECKTESTDICHOTOMIC
	public List <RichWord> spellCheckTextDichotomic (List <String> inputTextList){
		
		List <RichWord> result= new ArrayList <RichWord>();
		for (i=0; i<inputTextList.size(); i++)
		{
		RichWord r= new RichWord (inputTextList.get(i), false);
		result.add(r);
		}
		
		//se la parola e inglese 
		if(inglese==true) {
 
		int pc=DizionarioInglese.size()/2;
			for (RichWord r: result)
			{
				int inizio=0; 
				int fine=DizionarioInglese.size();
				while((r.isCorretta()==false) && (fine-inizio>1)) {
					String d= DizionarioInglese.get(pc);
					if (r.getParola().compareToIgnoreCase(d)==0) {
						r.setCorretta(true);
					}
					if (r.getParola().compareToIgnoreCase(d)>0) {
						inizio=pc;
						pc=(fine+inizio)/2;
					}
					if (r.getParola().compareToIgnoreCase(d)<0) {
						fine=pc; 
						pc=(fine+inizio)/2;
					}
			}

		}
	}
		
		
		//se la parola e italiana
		
		if(italiano==true) {
			 
		int pc=DizionarioItaliano.size()/2;
			for (RichWord r: result)
			{
				int inizio=0; 
				int fine=DizionarioItaliano.size();
				while((r.isCorretta()==false) && (fine-inizio>1)) {
					String d= DizionarioItaliano.get(pc);
					if (r.getParola().compareToIgnoreCase(d)==0) {
						r.setCorretta(true);
					}
					if (r.getParola().compareToIgnoreCase(d)>0) {
						inizio=pc;
						pc=(fine+inizio)/2;
					}
					if (r.getParola().compareToIgnoreCase(d)<0) {
						fine=pc; 
						pc=(fine+inizio)/2;
					}
			}

		}
	}
		
	//restituisco quelle false 
	result2= new ArrayList <RichWord>();
	for (i=0; i<result.size(); i++)
	{
		if(result.get(i).isCorretta()==false)
			result2.add(result.get(i));
	}
	
	return result2; 
}
		

	public int numeroErrori() {
		return result2.size();
	}
}





