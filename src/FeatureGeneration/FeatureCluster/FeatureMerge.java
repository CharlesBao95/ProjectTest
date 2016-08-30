package FeatureGeneration.FeatureCluster;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class FeatureMerge {
	private static HashSet<String> uselessNoun;
	private static HashSet<String> features;

	private static final String ldaResult = "resource/feature/LDAFeature.txt";
	private static final String fpResult = "resource/feature/fpResult.txt";
	private static final String clothResult = "resource/feature/feature.txt";
	static{
		uselessNoun = new HashSet<String>();
		features 	= new HashSet<String>();

		uselessNoun.add("卖家");
		uselessNoun.add("宝贝");
		uselessNoun.add("东西");
		uselessNoun.add("感觉");
		uselessNoun.add("图片");
		uselessNoun.add("东西");
		uselessNoun.add("衣服");
		uselessNoun.add("店家");
		uselessNoun.add("老公");
		uselessNoun.add("朋友");
		uselessNoun.add("老婆");
		uselessNoun.add("儿子");
		uselessNoun.add("同事");
		uselessNoun.add("照片");
		uselessNoun.add("孩子");
		uselessNoun.add("店主");
		uselessNoun.add("老板");
		uselessNoun.add("女儿");
		uselessNoun.add("眼睛");
		uselessNoun.add("正品");
		uselessNoun.add("好评");
		uselessNoun.add("发票");
		uselessNoun.add("卡其");
		uselessNoun.add("满分");
		uselessNoun.add("魔术");
		uselessNoun.add("运动");
		uselessNoun.add("买家");
		uselessNoun.add("黑色");
		uselessNoun.add("红色");
		uselessNoun.add("个人");
		uselessNoun.add("号码");
		uselessNoun.add("时尚");
		uselessNoun.add("星期");
		uselessNoun.add("时间");
		uselessNoun.add("爸爸");


		


	}

	public static void read(String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp;
		while((temp=br.readLine())!=null){
			if(!uselessNoun.contains(temp)){
				getFeatures().add(temp);
			}
		}
	}
	
	public static void write(String out) throws IOException{
		FileWriter fw = new FileWriter(out);
		fw.write(getFeatures().size()+"\n");
		for(String feature:getFeatures()){
			fw.write(feature+"\n");
		}
		fw.close();
	}
	
	public static void featureMerge() throws IOException{
		read(ldaResult);
		read(fpResult);
		write(getClothresult());
	}
	
	

	public static void main(String[] args) throws IOException {
		featureMerge();
	}

	public static String getClothresult() {
		return clothResult;
	}

	public static HashSet<String> getFeatures() {
		return features;
	}

	

}
