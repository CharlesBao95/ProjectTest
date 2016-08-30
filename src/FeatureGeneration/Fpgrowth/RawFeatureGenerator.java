package FeatureGeneration.Fpgrowth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.ansj.splitWord.analysis.ToAnalysis;

public class RawFeatureGenerator {
	
	
	//从文件生成特征
	public HashSet<String> featureExtraction(String filePath,double support){
		File corpus = new File(filePath);
		FileReader fis;
		try {
			fis = new FileReader (corpus);
			BufferedReader br = new BufferedReader (fis);
			String s;
			try {
				s = br.readLine();
				int fileSize = Integer.parseInt(s);
				System.out.println(fileSize);
				String [] data= new String[fileSize+1];
				String temp;
				int k =0;
				while((temp = br.readLine())!=null){
					data[k]=temp;
					k++;
				}
				FrequentItemResults freq = AlgFrequentItems.getFrequentItem(data, 3,support);
				
				ItemSetNode  aLargeItemset;
				
				ArrayList<String> freqWords = new ArrayList<String>();
				for(int i = 0;i<freq.realK;i++){				
					if(freq.numLarge[i] == 0)
						break;
					aLargeItemset = freq.largeItemset[i];
					while(aLargeItemset != null){
						for(int j = 0;j<=i;j++){
							if(i==1&&freq.wordList[aLargeItemset.itemset[j]].length()>1)
								freqWords.add(freq.wordList[aLargeItemset.itemset[j]]);	
							if(i==2&&freq.wordList[aLargeItemset.itemset[j]].length()>1)
								freqWords.add(freq.wordList[aLargeItemset.itemset[j]]);	

						}
						aLargeItemset = aLargeItemset.next;

					}
				}
				
				HashSet<String> feature = new HashSet<String>();
				for(String word:freqWords){
					String wordWithPOS = ToAnalysis.parse(word).toString();
					int length = wordWithPOS.length();
					if((length>2)&&wordWithPOS.substring(length-2, length).equals("/n")){
						feature.add(word);
					}
				}
				
				return feature;			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Error occurred while extracting features!");
		return null;
		
		
		
		
		
	}
		

	}

