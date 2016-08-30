package FeatureGeneration.Fpgrowth;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;



public class FrequentItemTest {

	public static void main(String[] args) throws IOException {
		String filePath = "resource/Reduced/clothes.txt";
		String output = "resource/feature/fpResult.txt";
		RawFeatureGenerator rfg = new RawFeatureGenerator();
		HashSet<String> features = rfg.featureExtraction(filePath,0.01);
	
		FileWriter fw = new FileWriter(output);
		for(String word:features){
			System.out.println(word);
			fw.write(word+"\n");
		}
		fw.flush();
		fw.close();
		
		
		
		
	}

}
