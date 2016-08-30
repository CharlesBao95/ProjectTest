package FeatureGeneration.FeatureCluster.Kmeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;

import FeatureGeneration.Word2Vec.ModelFormation;

public class Test {

	public static void main(String[] args) throws IOException {
		ModelFormation m = new ModelFormation();
		Process.loadData("resource/feature/Vector.txt");
		Entry<Integer[], Double> result = Process.cluster(10);
		ArrayList<String> features = m.getFeature("resource/feature/featureVector.txt");
		String[] f = new String[features.size()];
		int num =0;
		for(String feature:features){
			f[num++]=feature;
			//System.out.println(feature+"!");
		}
		
		Integer[] keys = result.getKey();
		int index = 0;
		for(int i :keys){
			System.out.print(i+" ");
			System.out.println(f[index++]);
		}
		
	}

}
