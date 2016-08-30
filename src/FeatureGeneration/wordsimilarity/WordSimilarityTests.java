package FeatureGeneration.wordsimilarity;

public class WordSimilarityTests {
	public static void main(String[] args) {
		// test_loadGlossary();
		test_disPrimitive();
		test_simPrimitive();
		test_simWord();
	}

    public static void test_loadGlossary(){
        WordSimilarity.loadGlossary();
    }
    /**
     * test the method {@link WordSimilarity#disPrimitive(String, String)}.
     */
    public static void test_disPrimitive(){
        int dis = WordSimilarity.disPrimitive("雇佣", "争斗");
        System.out.println("雇佣 and 争斗 dis : "+ dis);
    }
    
    public static void test_simPrimitive(){
        double simP = WordSimilarity.simPrimitive("雇佣", "争斗");
        System.out.println("雇佣 and 争斗 sim : "+ simP);
    }
    public static void test_simWord(){
        String word1 = "帽子";
        String word2 = "衣服";
        double sim = WordSimilarity.simWord(word2, word1);
        System.out.println(sim);
    }
    
}
