package FeatureGeneration.Fpgrowth;

import java.io.PrintWriter;
import java.util.ArrayList;

import org.ansj.splitWord.analysis.ToAnalysis;


public class SplitWords {
private static ArrayList<String> stopWords;
	
	static{
		stopWords = new ArrayList<String>();
		getStopWords().add("卖家");
		getStopWords().add("宝贝");
		getStopWords().add("东西");
		getStopWords().add("感觉");
		getStopWords().add("图片");
		getStopWords().add("东西");
		getStopWords().add("衣服");
		getStopWords().add("店家");
		getStopWords().add("老公");
		getStopWords().add("朋友");
		getStopWords().add("老婆");
		getStopWords().add("儿子");
		stopWords.add("同事");
		stopWords.add("照片");
		stopWords.add("孩子");
		stopWords.add("店主");
		stopWords.add("老板");
	//	stopWords.add("");
	//	stopWords.add("");
	//	stopWords.add("");
	//	stopWords.add("");

		
	}
	
	
	
	
	
	
	
	/*
	 * hasSeg: 表示当前传入的String数组是否已经分词
	 */
	public static void split(String [] data, Boolean hasSeg)
	{
		try
		{
			PrintWriter pw = new PrintWriter ("input.txt");

			for(int i = 0;i< data.length; i++)
			{
				String nativeStr;
				
				if (hasSeg)
					nativeStr = data[i];
				else
				{
					nativeStr = ToAnalysis.parse(data[i]).toString().replace(",", " ");
				//	System.out.println(nativeStr);
				}
				String [] tempstr = nativeStr.split(" ");
				for(int q = 0 ; q<tempstr.length;q++){
					if(tempstr[q].contains("/")&&getStopWords().contains(tempstr[q].substring(0, tempstr[q].indexOf("/"))))
						tempstr[q] = "";
					
				}
				
					
					
				nativeStr = "";
				for(int j = 0 ;j < tempstr.length ;j++)
				{
					if (!tempstr[j].contains("/"))
						continue;
					else if(POSConfig.frequentItemPOSList.contains(tempstr[j].substring(tempstr[j].indexOf("/")+1)))
					{
						nativeStr +=tempstr[j].substring(0, tempstr[j].indexOf("/")) + " ";
					}
				}
				pw.println(nativeStr.trim());
			}
			
			pw.flush();
			pw.close();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}







	public static ArrayList<String> getStopWords() {
		return stopWords;
	}






	
	
	/*
	 * 抽取当前feature的显式评论中的二维频繁项（不含能表示该Feature的词语）
	 */
//	public static void split(Feature tempFeature)
//	{
//		try
//		{
//			PrintWriter pw = new PrintWriter ("input.txt");
//
//			for(int i = 0;i< tempFeature.getExplicitSentenceList().size(); i++)
//			{
//				if (tempFeature.getExplicitSentenceList().get(i).getExplicitFeatureList().size() != 1)
//					continue;
//				String nativeStr = "";				
//				for (int j=0; j < tempFeature.getExplicitSentenceList().get(i).getSegWordList().size(); j++){
//					if(POSConfig.frequentItemPOSList.contains(tempFeature.getExplicitSentenceList().get(i).getSegWordPOSList().get(j))) {
//						if (!tempFeature.getConceptList().contains(tempFeature.getExplicitSentenceList().get(i).getSegWordList().get(j)))
//						{
//							nativeStr += tempFeature.getExplicitSentenceList().get(i).getSegWordList().get(j) + " ";
//						}
//					}
//				}
//				if (nativeStr.length() > 0)
//					pw.println(nativeStr.trim());
//			}
//			
//			pw.flush();
//			pw.close();			
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}		
//	}
}
