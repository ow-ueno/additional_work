import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//問題データを二次元String配列に格納する
//ref : https://uxmilk.jp/48018
public class InputData {

	public static List<String[]> engLearning(String fileName) throws IOException {

		//filenameは引数をそのまま利用する
		File f = new File(fileName);

		try {

			//String[]のListにcsvの内容を格納
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "Shift-JIS"));
			List<String[]> data = new ArrayList<>();

			//最初の一行を読む※ここはラベル
			String line = br.readLine();
			//もう一度readLineを呼ぶ＝1行目はラベルなので捨てる
			line = br.readLine();

			//readLineしたデータをカンマ区切りでString[]としてdataに格納
			while (line != null) {
				data.add(line.split(",", 0));
				line = br.readLine();
			}

			br.close();

			// dataを返す
			return data;

		} catch (IOException e) {

			System.out.println(e);
			//nullを返す
			return null;

		}

	}

}
