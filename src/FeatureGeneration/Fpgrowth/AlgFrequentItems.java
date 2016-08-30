package FeatureGeneration.Fpgrowth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AlgFrequentItems {

	public static FrequentItemResults getFrequentItem(String [] data, int maxItems, double support)
	{		
		SplitWords.split(data, false);
		Gen_File.GenerateFile(maxItems, support);
		Fpt test = new Fpt();
		return test.runFptNew();
	}
	
//	public static FrequentItemResults getFrequentItem(Feature tempFeature, int maxItems, double support)
//	{
//		SplitWords.split(tempFeature);
//		Gen_File.GenerateFile(maxItems, support);
//		Fpt test = new Fpt();
//		return test.runFptNew();
//	}
	
	public static void main(String [] args) throws NumberFormatException, IOException	{
		
		File explicitSentences = new File("resource/LDAModelDir/LDAExplicitSentences.txt");
		BufferedReader br = new BufferedReader(new FileReader(explicitSentences));
		String s =br.readLine();
		int fileSize = Integer.parseInt(s);
		System.out.println(fileSize);

		
		String [] data= new String[fileSize+1];
		String temp;
		int k =0;
		while((temp = br.readLine())!=null){
			data[k]=temp;
		//	System.out.println(k+" "+data[k]);
			k++;
		}
		
		FrequentItemResults test = AlgFrequentItems.getFrequentItem(data, 2, 0.05);
		
		ItemSetNode  aLargeItemset;
		try	{
			for(int i = 0;i<test.realK;i++){				
				System.out.print((test.numLarge[i]+ "个"+(i+1)+"-频繁项目集:\r\n"));
				if(test.numLarge[i] == 0)
					break;
				aLargeItemset = test.largeItemset[i];
				while(aLargeItemset != null){
					for(int j = 0;j<=i;j++){
						System.out.print((test.wordList[aLargeItemset.itemset[j]]+" "));						
					}
					System.out.print(("["+aLargeItemset.support +"]\r\n"));
					aLargeItemset = aLargeItemset.next;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
