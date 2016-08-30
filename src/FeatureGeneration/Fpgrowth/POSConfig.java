package FeatureGeneration.Fpgrowth;

import java.util.ArrayList;

/*
 * 定义不同步骤中抽取的词语词性
 */
public class POSConfig {
	public static ArrayList<String> hotWordPOSList = new ArrayList<String>(); //隐含特征抽取——热点词词性列表
	public static ArrayList<String> frequentItemPOSList = new ArrayList<String>(); //隐含特征抽取——频繁项词性列表
	public static ArrayList<String> featureWordPOSList = new ArrayList<String>(); //隐含特征抽取——机器学习特征词性列表
	public static ArrayList<String> similarWordPosList = new ArrayList<String>(); //计算相似度所寻找的词性
	
	static {
		similarWordPosList.add("a");//形容词
		similarWordPosList.add("n");
		similarWordPosList.add("v");
		
		hotWordPOSList.add("a"); //形容词
		hotWordPOSList.add("v"); //动词
		hotWordPOSList.add("n"); //名词
//		hotWordPOSList.add("x");
//		hotWordPOSList.add("m");
		
	
		frequentItemPOSList.add("n"); //名词
		frequentItemPOSList.add("t"); //时间
		frequentItemPOSList.add("s"); //处所
		frequentItemPOSList.add("f"); //方位
		frequentItemPOSList.add("v"); //动词
		frequentItemPOSList.add("a"); //形容词
		frequentItemPOSList.add("b"); //区别词
		frequentItemPOSList.add("z"); //状态词
		frequentItemPOSList.add("r"); //代词
		frequentItemPOSList.add("m"); //数词
		frequentItemPOSList.add("q"); //量词
		//frequentItemPOSList.add("d"); //副词

		featureWordPOSList.add("n"); //名词
//		featureWordPOSList.add("t"); //时间
//		featureWordPOSList.add("s"); //处所
//		featureWordPOSList.add("f"); //方位
		featureWordPOSList.add("v"); //动词
		featureWordPOSList.add("a"); //形容词
//		featureWordPOSList.add("b"); //区别词
//		featureWordPOSList.add("z"); //状态词
//		featureWordPOSList.add("r"); //代词
		featureWordPOSList.add("m"); //数词
		featureWordPOSList.add("q"); //量词
		featureWordPOSList.add("x"); //外来词
		//featureWordPOSList.add("d"); //副词
	}
}
