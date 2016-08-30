package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class AnsjUtil {
	public static final int FILESEGMENT =1;
	public static final int STRINGSEGMENT=2;


	public static ArrayList<String> segmentation(String str,int type) throws IOException{
		ArrayList<String> result = new ArrayList<String>();
		
		//file segmentation
		if (type ==1){
			String enc = "GBK";
			File src = new File(str);
			FileInputStream fis = new FileInputStream(src);
			UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
			enc = uin.getEncoding(); // check and skip
			InputStreamReader in;
			if (enc == null) 
				in = new InputStreamReader(uin);
			else 
				in = new InputStreamReader(uin, enc);	
			BufferedReader reader = new BufferedReader(in);
		
			String line;
			while((line = reader.readLine())!=null){
				line=line.replaceAll("[\\pP‘’“”]", "");
				List<Term> terms = ToAnalysis.parse(line).getTerms();
				String templine= "";
				for(Term term:terms){
					templine+=term.getName()+" ";
				}
				result.add(templine);
			}
		return result;
		}
		
		//String segmentation
		else if (type == 2){
			List<Term> terms = ToAnalysis.parse(str).getTerms();
			for(Term term:terms){
				result.add(term.getName());
			}
			return result;
		}
		
		System.out.println("Error, plz select what to do with the string!");
		return null;
		
		
	}
	
	//public static ArrayList<String> segmentation(String )
	public static String toSeggedString(ArrayList<String> list){
		String result = "";
		for(String l:list){
			result+=l+" ";
		}
		
		return result;
	}
	
	public static String[] toSeggedStringList(ArrayList<String> list){
		String[] result = new String[list.size()];
		int index = 0;
		for(String word:list){
			result[index]=word;
			index++;
		}
		
		return result;
	}
	
	
	
	
	public static void segmentation(String src,String des) throws IOException{
		String enc = "UTF-8";
		File source = new File(src);
		FileInputStream fis = new FileInputStream(source);
		UnicodeInputStream uin = new UnicodeInputStream(fis, enc); 
		enc = uin.getEncoding(); // check and skip
		InputStreamReader in;
		if (enc == null) 
			in = new InputStreamReader(uin);
		else 
			in = new InputStreamReader(uin, enc);	
		BufferedReader reader = new BufferedReader(in);
	
		String line;
		FileWriter fw = new FileWriter(des);
		while((line = reader.readLine())!=null){
			line=line.replaceAll("[\\pP‘’“”]", "");
			
			//test
			//System.out.print(line);
			
			List<Term> terms = ToAnalysis.parse(line).getTerms();
			String templine= "";
			for(Term term:terms){
				templine+=term.getName()+" ";
			}
			//test
			//System.out.println(templine);
			fw.write(templine+"\n");
	}
	}
	
	public static void main(String[] args) throws IOException {
		String suffix = ".segged.txt";
		String text = "衣服跟裤子的尺码都很不错，很合适。防水功能也是真的有哦。我买的是冬季加绒，这个天穿西藏就不怕啦。另外还一起买了条速干裤也不错吧。总的来说 还不错 值得购买。";
		text=text.replaceAll("[\\pP‘’“”]", "");
		
		String result = ToAnalysis.parse(text).toString();
		System.out.println(result);
		
		String srcFilePath;
		Scanner sc = new Scanner(System.in);
		srcFilePath = sc.nextLine();
		sc.close();
		String desFilePath= srcFilePath.substring(0,srcFilePath.length()-4)+suffix;
		
//		FileWriter fw = new FileWriter(desFilePath);
//		ArrayList<String> res = segmentation(srcFilePath,FILESEGMENT);
//		System.out.println(desFilePath);
//		for(String temp:res){
//			System.out.println(temp);
//			fw.write(temp+"\n");
//		}
		//System.out.println(result);
		segmentation(srcFilePath,desFilePath);
		
		
	}

}
