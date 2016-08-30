package FeatureGeneration.LTPAnalyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

import org.ansj.splitWord.analysis.ToAnalysis;

import Util.UnicodeInputStream;

public class Parser {
	private static final String api_key = "s4V0e9X8SQESPAYKANNEaZNExiKEFNUEZPGdmy5A";
	private static final String format = "plain";
	private static final String pattern = "dp";
	private static ArrayList<String> keyRelation= new ArrayList<String>();
	
	static{
		keyRelation.add("SBV");
		keyRelation.add("VOB");
		keyRelation.add("IOB");
		keyRelation.add("FOB");
	//	keyRelation.add("COO");
		keyRelation.add("LAD");
		keyRelation.add("RAD");
		keyRelation.add("HED");
	}

 	public static HashSet<String> getWanttedResult(String pattern, String format, String text) throws IOException{
		String key = api_key;

	    text = URLEncoder.encode(text, "utf-8");

	    URL url     = new URL("http://ltpapi.voicecloud.cn/analysis/?"
	                              + "api_key=" + key + "&"
	                              + "text="    + text    + "&"
	                              + "format="  + format  + "&"
	                              + "pattern=" + pattern);
	    URLConnection conn = url.openConnection();
	    conn.connect();

	    BufferedReader innet = new BufferedReader(new InputStreamReader(
	                                conn.getInputStream(),
	                                "utf-8"));
	    HashSet<String> lines = new HashSet<String>();
	    String line;
	    while ((line = innet.readLine())!= null) {
	       // System.out.println(line);
	        int length = line.length();
	        String dpRelation = line.substring(length-3, length);
	        if(keyRelation.contains(dpRelation)){
	        	line = ToAnalysis.parse(line).toString();
	        	String[] words = line.split(",");
	        	for(String wordInfo:words){
	        		if(wordInfo.length()>2&&wordInfo.substring(wordInfo.length()-2,wordInfo.length()).equals("/n")){
	    		        lines.add(wordInfo.substring(0, wordInfo.length()-2));
	    		      //  System.out.println(wordInfo.substring(0, wordInfo.length()-2));
	        		}
	        	}
	        	
	        	
	        	
	        	
	        	

	        }
	       
	    }
	    innet.close();
	    return lines;
	}
	
	public static void dp(String src,String des) throws IOException{
		String enc = "UTF-8";
		File source = new File(src);
		FileInputStream fis = new FileInputStream(source);
		UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
		enc = uin.getEncoding(); // check and skip
		InputStreamReader in;
		if (enc == null) 
			in = new InputStreamReader(uin);
		else 
			in = new InputStreamReader(uin, enc);	
		BufferedReader br = new BufferedReader(in);
		
		String s =br.readLine();
		int fileSize = Integer.parseInt(s);
		System.out.println(fileSize);
			
		String temp;
		HashSet<String> allFeature = new HashSet<String>();
		FileWriter fw = new FileWriter(des);
		while((temp = br.readLine())!=null){
			HashSet<String> result = getWanttedResult(pattern,format,temp);
			allFeature.addAll(result);
		}
		for(String feature:allFeature){
			fw.write(feature+"\n");
			System.out.println(feature);
		}
			
			
		fw.close();
		br.close();
		
		
		
	}	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//	    String pattern = "dp";
//	    String format  = "plain";
//	    String text    = "屏幕也没那么清晰。";
//		getWanttedResult(pattern,format,text);	
		String src = "resource/LDAExplicitSentences.txt";
		dp(src,"resource/LTPFeatures.txt");
	
	
	}



}
