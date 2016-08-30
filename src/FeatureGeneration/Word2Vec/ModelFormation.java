package FeatureGeneration.Word2Vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.ansj.vec.Learn;

public class ModelFormation {
    private static final File clothCorpus = new File("resource/corpus/corpus.txt");
    private static final File featureFile = new File("resource/feature/clothfeature.txt");
    private static final String modelFile = "resource/corpus/vector.txt";
    private static final String featureVectorFile = "resource/feature/featureVector.txt";
    private static final String VectorFile = "resource/feature/Vector.txt";
    private HashSet<String> feature = new HashSet<String>();
    
    
    public void saveModel() throws IOException, InterruptedException{
    	Learn lean = new Learn() ;

        lean.learnFile(clothCorpus) ;

        //lean.saveModel(new File("model/vector.txt")) ;
        lean.saveModel(modelFile) ;

    }
    
    public void getFeature() throws IOException{
    	BufferedReader br = new BufferedReader(new FileReader(featureFile));
    	String temp;
    	while((temp = br.readLine())!=null){
    		feature.add(temp);
    	}
    	br.close();
    	
    }
    
    public ArrayList<String> getFeature(String feature) throws IOException{
    	BufferedReader br = new BufferedReader(new FileReader(feature));
    	ArrayList<String> features = new ArrayList<String>();
    	String temp;
    	while((temp = br.readLine())!=null){
    		String[] words = temp.split(" ");
    		//test
    		//System.out.println(words[0]+"000");
    		features.add(words[0]);
    	}
    	br.close();
    	return features;
    }
    
    public void getFeatureVector() throws IOException{
    	getFeature();
    	File fvf = new File(featureVectorFile);
    	File v = new File(VectorFile);
    	FileWriter fw1 = new FileWriter(fvf);
    	FileWriter fw2 = new FileWriter(v);
    	BufferedReader br = new BufferedReader(new FileReader(modelFile));
    	String temp ="";
    	while((temp = br.readLine())!=null){
    		//temp = temp.replaceAll("\\d+", "");
    		String[] wordWithVector = temp.split(" ");
    		wordWithVector[0] = wordWithVector[0].replaceAll("\\d+", "");

    		if(feature.contains(wordWithVector[0])){
    			fw1.write(temp+"\n");
    			String tempVector = temp.substring(wordWithVector[0].length()+1);
    			fw2.write(tempVector+"\n");
    		}
    	}
    	fw2.close();
    	fw1.close();
    	br.close();
    }

	public static void main(String[] args) throws IOException, InterruptedException {
		ModelFormation mf = new ModelFormation();
		mf.saveModel();
		mf.getFeatureVector();
	}

}
