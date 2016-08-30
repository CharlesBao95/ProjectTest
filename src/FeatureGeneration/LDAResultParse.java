package FeatureGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.ansj.splitWord.analysis.ToAnalysis;


public class LDAResultParse {
	private static HashSet<String> uselessNoun;
	private HashSet<String> features;
	
	static{
		uselessNoun = new HashSet<String>();
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
	
	public void parse(File src) throws IOException{
		features = new HashSet<String>();
		FileReader fr = new FileReader(src);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine())!=null){
			String[] wordWithPOS = ToAnalysis.parse(line).toString().split(",");
			for(String word:wordWithPOS){
				int length = word.length();
				if((length>2)&&word.substring(length-2, length).equals("/n")){
					String temp=word.substring(0,word.length()-2);
					if(temp.length()>1&&!uselessNoun.contains(temp))
						features.add(temp);
					}
				}
		}
	}
	
	public static HashSet<String> parse(String line){
		HashSet<String> feature = new HashSet<String>();
		String[] wordWithPOS = ToAnalysis.parse(line).toString().split(",");
		for(String word:wordWithPOS){
			int length = word.length();
			if((length>2)&&word.substring(length-2, length).equals("/n")){
				feature.add(word);
				}
			}
		return feature;
	}
	
	public void write(String src) throws IOException{
		FileWriter fw = new FileWriter(src);
		for(String f:features){
			fw.write(f+"\n");
		}
		fw.flush();
		fw.close();
	}
	
	public void walkTheDir(String srcDir) throws IOException{
		File dir = new File(srcDir);
		if(dir.isDirectory()){
			File[] ldaResults = dir.listFiles();
//			for(File re:ldaResults){
//				System.out.println(re.getName());
//			}
			for(File re:ldaResults){
				parse(re);
			}
			
		}
		
	}
	
	
	
	
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String line = "Topic 4: 0.052*玩 + 0.050*还 + 0.045*选择 + 0.043*待机 + 0.032*删除 + 0.029*有 + 0.028*够 + 0.027*人 + 0.026*是 + 0.026*像";
//		HashSet<String> features= parse(line);
//		for(String f:features){
//			System.out.println(f);
//		}
		//File src = new File("C:/Users/win/Desktop/Charles Bao/V_Week 08/TPresults/tfidfASLDA15_25.txt");
		String src = "F:/TPresults";
		String output = "resource/feature/LDAFeature.txt";
		
		LDAResultParse p = new LDAResultParse();
		p.walkTheDir(src);
		int i = 0;
		for(String f:p.features){
			i++;
			System.out.println(f);
		}
		System.out.println(i);
		p.write(output);
		
			
		
		
	}

}
