import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TokenizerDriver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Tokenizer token = new Tokenizer();
		File f1 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/input.txt");
		File f2 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/inquery.txt");
		ArrayList<String> l1 = new ArrayList<String>();
		 l1 = token.tokens(f1, l1);
		ArrayList<String> l2 = token.stopper(l1, f2);
		ArrayList<String> l3 = token.porter1a(l2);
		ArrayList<String> l4 = token.porter1b(l3);
		
		File file = new File("/Users/rohantandon/Desktop/CS_446_2/P2/tokenized.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int i = 0;i<l4.size();i++){
			writer.write(l4.get(i)+"\n");
		}
		writer.close();
	}
}
