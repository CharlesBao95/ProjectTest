package FeatureGeneration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import Util.AnsjUtil;

public class Filter {
	public static void ExplicitSentencesGeneration(String corpus,String featureFile){
		ArrayList<String> features = new ArrayList<String>();
		String des = corpus.substring(0, corpus.length()-4)+"_Exp.txt";
		System.out.println(des);
		
		try {
			FileReader fr1 = new FileReader(featureFile);
			BufferedReader br1 = new BufferedReader(fr1);
			String feature;
			while((feature=br1.readLine())!=null){
				features.add(feature);
				}
//			for(String feature1:features){
//				System.out.println(feature1);
//			}
//			System.out.println("");
			System.out.println("Feature loaded!");
			br1.close();
			fr1.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		try {
			FileReader fr2 = new FileReader(corpus);
			FileWriter fw = new FileWriter(des);
			BufferedReader br2 = new BufferedReader(fr2);
			int count =1;
			String sentence;
			while((sentence = br2.readLine())!=null){
				String[] words =AnsjUtil.toSeggedStringList(AnsjUtil.segmentation(sentence,AnsjUtil.STRINGSEGMENT));
//				for(String word:words){
//					System.out.print(word+" ");
//				}
//				System.out.println("");

				
				for(String feature:features){
					for(String word:words){
						if(word.equals(feature)){
							//System.out.println("Aha exp No."+count+" !"+sentence);
							count++;
							fw.write(sentence+"\n");
						}
					}
				}
			}
			fw.close();
			br2.close();
			fr2.close();
			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		}
		
	public static void duplicateReduction(String corpus){
		FileReader fr1;
		FileWriter fw;
		try {
			fr1 = new FileReader(corpus);
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
			
			fw = new FileWriter(corpus.substring(0,corpus.length()-4)+"Reduced.txt");
			for(String comment :lines){
				fw.write(comment+"\n");
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//public static void 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String src = "resource/segged/LDAImplicitSentencesSegged.txt";
		String featurePath = "resource/cloth/clothFeature.txt";
		//ExplicitSentencesGeneration(src,featurePath);
		duplicateReduction(src);
	}

}
