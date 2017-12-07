import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class MonteCristoDriver {

	public static void main(String[] args) throws IOException {
		Tokenizer token = new Tokenizer();
		File f1 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/p2-input-part-B.txt");
		File f2 = new File("/Users/rohantandon/Desktop/CS_446_2/P2/inquery.txt");
		ArrayList<String> l1 = new ArrayList<String>();
		l1 = token.tokens(f1, l1);
		ArrayList<String> l2 = token.stopper(l1, f2);
		ArrayList<String> l3 = token.porter1a(l2);
		ArrayList<String> l4 = token.porter1b(l3);

		MonteCristo monte = new MonteCristo();
		TreeMap<String, Integer> map = monte.toTreeMap(l4);
		TreeMap<String, Integer> treemap = monte.sortTreeMap(map);

		File file = new File("/Users/rohantandon/Desktop/CS_446_2/P2/terms.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		int count =0;
		for (Entry<String, Integer> entry : treemap.entrySet()) {
			if(count<200){
				writer.write("Key: " + entry.getKey() + " Value: " + entry.getValue() + "\n");
			}
			count++;
		}

		writer.close();

	}

}
