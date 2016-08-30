package FeatureGeneration.FeatureCluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import FeatureGeneration.wordsimilarity.WordSimilarity;

public class SimilarityCluster {
	private static ArrayList<String> features ;
	private static HashMap<String,Integer> cluster;
	private static ArrayList<Integer> centerPosition;
	
	static{
		try {
			features = new ArrayList<String>();
			centerPosition = new ArrayList<Integer>();
			cluster = new HashMap<String, Integer>();
			loadFeatures();
			int i = 0;
			for(String feature:features){
				cluster.put(feature,i++);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}

	
	private static void loadFeatures() throws IOException{
		String src = "resource/feature/fpResult-1.txt";
		BufferedReader br = new BufferedReader(new FileReader(src));
		String temp;
		while((temp=br.readLine())!=null){
			features.add(temp);
			}
		br.close();	
		}
 
// public double simCalculation(int i ,int j){
//  return WordSimilarity.simWord(features.get(i), features.get(j));
//   
// }
	public void goThroughCluster(){
		boolean flag = true;
		ArrayList<Integer> pool = new ArrayList<Integer>();
		ArrayList<Integer> walkPool =new ArrayList<Integer>();
		for(int i = 0;i<features.size();i++){
			pool.add(i);
			walkPool.add(i);
			}
		
		while(flag){   
			//select a word from the remaining ones 
			int len = pool.size();
			Random ran = new Random();
			int index = ran.nextInt(len-1);
			centerPosition.add(index);
			
			//test 
			System.out.println("The position of the new center is "+index);
			if(index<0) break;
			pool.remove(index);
			
			//update walkPool
			walkPool.remove(index) ;
			
   
			for(int q:walkPool){
				String word = features.get(q);
				String newCenter = features.get(index);
				
				if(word.equals(newCenter)) continue;
				double sim =WordSimilarity.simWord(newCenter, word);
				  
            int newType = cluster.get(newCenter);
            int oldType = cluster.get(word);
            // System.out.println("Old type is "+oldType + " and new type is "+newType);
            double oldSim =0.0;
            
            //System.out.println(oldType);
            if(oldType!=0){
            	String oldCenter = getCenter(oldType);
            	
            	//test
            	System.out.println(" BREAK 1:OLD Center is "+oldCenter);
            	System.out.println(" BREAK 1:word is "+word);            	

            	oldSim = WordSimilarity.simWord(oldCenter, word);
            	}
            
            if(sim>0.5&&sim>oldSim){
            	cluster.put(word, newType);
            	pool.remove(q);
            	}
            
            System.out.println("-----------------------------------------------------------");
            
			}
			


			if(pool.size()==0)
				flag =false;
			
			//test
			break;
   
		}  
		}

	
	public void iterateCluster(int k){
		int[] center = centerGenerate(k);
//		System.out.println("Original center is ");
//		for(int i :center){
//			System.out.print(features.get(i)+" ");
//		}
//		System.out.println("");
		iterate(center);
		
		
	}

	private void iterate(int[] center){
		boolean flag = true;
		int[] tempCenter =center;
		while(flag){
			flag=false;
			ArrayList<String> cenFeature = new  ArrayList<String>();
			for(int pos:tempCenter){
				cenFeature.add(features.get(pos));
				}
			//test
			System.out.println("Updating!");
			for(String feature:features){
				
				if(cenFeature.contains(feature)) continue;
				int oldType = cluster.get(feature);
				int newType = belongTo(feature,tempCenter);
				if(oldType!=newType) flag =true;
				cluster.put(feature, newType);
				}
			
			
			tempCenter = centerUpdate(tempCenter);

			//test
			break;
		 
		 
		 }
	 
 }
 
	private int[] centerUpdate(int[] center){
		System.out.print("Now the center is ");
		for(int i:center){
			System.out.print(i+" ");
		}
		System.out.println("");
		int[] newCenter = new int[center.length];
		for(int i = 0 ;i<newCenter.length;i++){
			int oldType = cluster.get(features.get(center[i]));
			//System.out.println(oldType);
			ArrayList<Integer> littlePool = new ArrayList<Integer>();
			for(int index = 0;index<features.size();index++){
				if(cluster.get(features.get(index))==oldType){
					littlePool.add(index);
				}
			}
			//System.out.println("There are "+littlePool.size()+" feature in this cluster");
			int newPos = littlePool.get(getRandom(littlePool.size()));	
			newCenter[i] = newPos;	
			
			
			
			
		}
		
		return newCenter;
		
	}
	
	private int[] centerGenerate(int k){
		int[] center = new int[k];
		ArrayList<Integer> pool= new  ArrayList<Integer>();
		for(int i = 0;i<features.size();i++){
			pool.add(i);
			}
		for(int j =0;j<k;j++){
			Random ran = new Random();
			int index =  ran.nextInt(pool.size()-1);
			center[j]=pool.get(index);
			
			pool.remove(index);
			}
		return center;
 }
 
	private int getRandom(int max){
		Random ran = new Random();
		int random = ran.nextInt(max);
		return random;
		
	}
	
	private int belongTo(String word,int[] list){
		int type = cluster.get(word);
		double maxSim = 0.0;
		for(int i:list){
			double sim = WordSimilarity.simWord(word, features.get(i));
			if(sim>maxSim&&sim>0.8){
				maxSim = sim;
				type = cluster.get(features.get(i));
				}
			}
		//if(maxSim<0.03) features.remove(word);
		return type;
 }
 
 
	public String getCenter(int wordType){
		for(int pos : centerPosition){
			String feature = features.get(pos);
			int cenType = cluster.get(feature);
			
			if(cenType == wordType){
				System.out.println("FOUND IT!");
				return feature;
				}
			}
		return features.get(wordType);
		}

	
	public HashMap<String,Integer> getCluster(){
		return cluster;
		}
	
 
 

	public static void main(String[] args) {
		SimilarityCluster sc = new SimilarityCluster();
		/// sc.cluster();
		 
		sc.iterateCluster(9);
		Iterator itor = sc.getCluster().keySet().iterator();  
		 while(itor.hasNext()){  
		  String key = (String)itor.next();  
		  int value = sc.getCluster().get(key);  
		  System.out.println(key+" "+value);
		 }  

		
  
  }
  
  
  
  }

  
