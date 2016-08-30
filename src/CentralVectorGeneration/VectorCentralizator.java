package CentralVectorGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

import Util.UnicodeInputStream;

public class VectorCentralizator {
	private NGenerator ngram;
	private String pairPath;
	private Hashtable<Double,Double> vectorPair;
	private Hashtable<String,Double> featureVetor;
	
	
	public VectorCentralizator(){
		this.pairPath = "resource/NGram/transdoc";
		ngram = new NGenerator();

	}
	
	public VectorCentralizator(String pairPath){
		this.pairPath = pairPath;
		ngram = new NGenerator();
	}

	public void word2VectorTransformation() throws IOException{
		
		this.ngram.uniCalculation();
		
		String enc = "GBK";
		File src = new File(this.pairPath);
		FileInputStream fis = new FileInputStream(src);
		UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
		enc = uin.getEncoding(); // check and skip
		InputStreamReader in;
		if (enc == null) 
			in = new InputStreamReader(uin);
		else 
			in = new InputStreamReader(uin, enc);	
		BufferedReader reader = new BufferedReader(in);
		
		String line;
		while((line = reader.readLine())!=null){
			String[] pair = line.split("/t/b/n");
			if(this.ngram.getUnigram().get(pair[0])!=null&&this.ngram.getUnigram().get(pair[1])!=null) {
				double featureVector = this.ngram.getUnigram().get(pair[0]);
				double commentVector = this.ngram.getUnigram().get(pair[1]);
				this.vectorPair.put(featureVector, commentVector);
			}
			else {
				System.out.println("comment does not exist!!!");
				return ;
			}
			
		
		
		}
		
	}

	public void VectorCentralization(){
		
	}

	public static void main(String[] args) {
	}

}
