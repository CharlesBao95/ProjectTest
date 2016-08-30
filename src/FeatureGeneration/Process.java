package FeatureGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.ansj.vec.Learn;

import FeatureGeneration.FeatureCluster.FeatureMerge;
import FeatureGeneration.Fpgrowth.RawFeatureGenerator;
import Util.PreProcessUtility;

public class Process {
	//预处理后的文件
	private static final String processedFile = "";
	//去重后的文件
	private static final String reducedFile = "";
	//分词后的文件
	private static final String seggedFile = "input.txt";
	//频繁项算法提取的特征词
	private static final String fpFeature = "resource/feature/fpResult.txt";
	//LDA模型提取的特征词
	private static final String ldaFeature= "resource/feature/ldaFeature.txt";
	//合并后的特征词
	private static final String feature = "resource/feature/clothFeature";
	//显式句集合
	private static final String explicit = "";
	//隐式句集合
	private static final String implicit = "";
	//语料向量模型
	private static final String word2VecModel = "resource/corpus/vector.txt";
	//提取的特征向量
	private static final String featureVec ="resource/corpus/featureVector.txt";

	

	

	public String preProcess(String inputFile) throws FileNotFoundException{
		String temp;
		try {
			//Preprocess
			System.out.println("Preprocessing!");
			FileWriter fw = new FileWriter(inputFile.substring(0,inputFile.length()-4)+"_pre.txt");
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			while((temp = br.readLine())!=null){
				temp= PreProcessUtility.preProcess(temp);
				fw.write(temp+"\n");
			}
			return inputFile.substring(0,inputFile.length()-4)+"_pre.txt";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in writing preprocessed file");
			e1.printStackTrace();
		}
		System.out.println("Error in preprocessing!");
		return null;
	}
	
	public String duplicateReduction(String seggedFile){
		FileReader fr1;
		FileWriter fw;
		String temp;
		try {
			System.out.println("Reducting!");
			fr1 = new FileReader(seggedFile);
			BufferedReader br1 = new BufferedReader(fr1);
			ArrayList<String> lines = new ArrayList<String>();
			String line;
			while((line= br1.readLine())!=null){
				//System.out.println("raw comment: "+line);
				if(!lines.contains(line))	{lines.add(line);}
				else continue;
			}
			br1.close();
			fr1.close();
			
			fw = new FileWriter(seggedFile.substring(0,seggedFile.length()-4)+"Reduced.txt");
			return seggedFile.substring(0,seggedFile.length()-4)+"_reduced.txt";
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Error in writing reduced file");
			e1.printStackTrace();
		}
		System.out.println("Error in reduction!");
		return null;
		
	}
	
	public void fpFeatureGeneration(String reducedFile) throws IOException{
		RawFeatureGenerator rfg = new RawFeatureGenerator();
		HashSet<String> features = rfg.featureExtraction(reducedFile,0.01);
		String output = fpFeature;
		FileWriter fw = new FileWriter(output);
		for(String word:features){
			System.out.println(word);
			fw.write(word+"\n");
		}
		fw.flush();
		fw.close();
	}
	
	public void ldaResultParse(String resultDir) throws IOException{
		LDAResultParse p = new LDAResultParse();
		p.walkTheDir(resultDir);
		p.write(ldaFeature);		
	}
	
	public void featureMerge() throws IOException{
		FeatureMerge.featureMerge();
	}
	
	public void sentenceSplit() throws IOException{
		HashSet<String> features = FeatureMerge.getFeatures();
		FileWriter fwi = new FileWriter(implicit);
		FileWriter fwe = new FileWriter(explicit);
		BufferedReader br = new BufferedReader(new FileReader(seggedFile));
		String temp;
		while((temp=br.readLine())!=null){
			String[] tempWords = temp.split(" ");
			//store a line of comment
			ArrayList<String> words = new ArrayList<String>();
			for(String word:tempWords){
				words.add(word);
			}
			//if the comment contains one of the features, add it in the explicit sentences set
			boolean isExplicit = false;
			for(String feature:features){
				if(words.contains(feature)){
					fwe.write(temp+"\n");
					isExplicit = true;
					break;
				}
			}
			if(!isExplicit){
				fwi.write(temp+"\n");
			}
			
			
		}

	}
	
	public void getFeatureVector() throws IOException{
		HashSet<String> features = getFeature();
    	File fv = new File(featureVec);
    	FileWriter fw = new FileWriter(fv);
    	BufferedReader br = new BufferedReader(new FileReader(word2VecModel));
    	String temp ="";
    	while((temp = br.readLine())!=null){
    		String[] wordWithVector = temp.split(" ");
    		if(feature.contains(wordWithVector[0])){
    			fw.write(temp+"\n");
    		}
    	}
		
	}

	public void word2VecModelFormation() throws IOException, InterruptedException{
		Learn lean = new Learn() ;
		File corpus = new File(seggedFile);
        lean.learnFile(corpus) ;
        lean.saveModel(word2VecModel) ;
	}
	
	public HashSet<String> getFeature() throws IOException{
    	BufferedReader br = new BufferedReader(new FileReader(feature));
    	HashSet<String> features = new HashSet<String>();
    	String temp;
    	while((temp = br.readLine())!=null){
    		features.add(temp);
    	}
    	br.close();
    	return features;
    }
	
	public void featureClsuter(){
		
	}
	
}
