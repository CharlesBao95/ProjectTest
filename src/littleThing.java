import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class littleThing {

	public static void main(String[] args) throws IOException {
		String file = "resource/Reduced/clothes.txt";
		String featureFile = "resource/feature/clothFeature.txt";
		HashSet<String> features = new HashSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(featureFile));
		String f;
		while((f=br.readLine())!=null){
			features.add(f);
		}
		
		br = new BufferedReader(new FileReader(file));
		int i = 0;
		while((f=br.readLine())!=null){
			String[] splitted = f.split(" ");
			for(String s :splitted){
				if(features.contains(s)){
					i++;
					break;
				}
			}
		}
		System.out.println(i);
		
		
	}

}
