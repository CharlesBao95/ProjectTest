import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ansj.splitWord.analysis.ToAnalysis;

import Util.AnsjUtil;

public class ansjTest {

	public static void main(String[] args) throws IOException {
		String str = "朋友" ;
		str=str.replaceAll("[\\pP‘’“”]", "");

		
		String result =AnsjUtil.toSeggedString(AnsjUtil.segmentation(str,AnsjUtil.STRINGSEGMENT)) ;
		String test = ToAnalysis.parse(str).toString().replace(",", " ");
		System.out.println(ToAnalysis.parse(str));
		System.out.println(result);
		System.out.println(test);
			
		///String src = "resource/LDAImplicitSentences.txt";
		//String des = src.substring(0,src.length()-4)+"Segged.txt";
		//AnsjUtil.segmentation(src, des);
		
		

	}

}
