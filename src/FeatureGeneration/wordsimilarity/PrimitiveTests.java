package FeatureGeneration.wordsimilarity;

import java.util.List;

public class PrimitiveTests {
    // public void main(String args[]) {
    	// test_getParents();
    // }
    /**
     * test the method {@link Primitive#getParents(String)}.
     */
    public void test_getParents(){
        String primitive = "攻打";
        List<Integer> list = Primitive.getParents(primitive);
        for(Integer i : list){
            System.out.println(i);
        }
    }
}
