package CentralVectorGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Util.AnsjUtil;
import Util.UnicodeInputStream;

public class NGenerator {
	public String corpus;
	private ArrayList<String> words;
	private HashSet<String> dict;
	private int wordNum;
	private HashMap<String,Double> unigram;
	private HashMap<String,Double> bigram;
	private HashMap<String,Double> trigram;
	
	
	public NGenerator(){
		this.corpus="resource/LDAModelDir/allSentences.dat";
		this.wordNum=0;
		
	}
	public NGenerator(String filePath){
		this.corpus=filePath;
		this.wordNum=0;
	}
	
	public void uniCalculation() throws IOException{
		this.words=AnsjUtil.segmentation(this.corpus, AnsjUtil.FILESEGMENT);
		for(String word:this.words){
			this.getDict().add(word);
		}
		this.wordNum = this.words.size();
		for(String word: this.getDict()){
			int times = 0;
			for(String k:this.words){
				if(word.equals(k))
					times++;
			}
			double p = times/this.wordNum;
			this.getUnigram().put(word, p);
			
		}
		
		
		
	}
	
	public void biCalculation() throws UnsupportedEncodingException, FileNotFoundException{
		String enc = "GBK";
		File src = new File(this.corpus);
		FileInputStream fis = new FileInputStream(src);
		UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
		enc = uin.getEncoding(); // check and skip
		InputStreamReader in;
		if (enc == null) 
			in = new InputStreamReader(uin);
		else 
			in = new InputStreamReader(uin, enc);	
		BufferedReader reader = new BufferedReader(in);
		
	}
	
	public void triCalculation() throws FileNotFoundException, UnsupportedEncodingException{
		String enc = "GBK";
		File src = new File(this.corpus);
		FileInputStream fis = new FileInputStream(src);
		UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
		enc = uin.getEncoding(); // check and skip
		InputStreamReader in;
		if (enc == null) 
			in = new InputStreamReader(uin);
		else 
			in = new InputStreamReader(uin, enc);	
		BufferedReader reader = new BufferedReader(in);
		
	}
	
	public HashSet<String> getDict() {
		return dict;
	}
	public void setDict(HashSet<String> dict) {
		this.dict = dict;
	}
	public Map<String,Double> getUnigram() {
		return unigram;
	}
	public void setUnigram(HashMap<String,Double> unigram) {
		this.unigram = unigram;
	}	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
