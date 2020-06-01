import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//csvファイルのデータをもとに出題を行う英単語学習プログラム
public class Kadai01 {

	public static void main(String[] args) throws IOException {

		//問題データを格納
		String fileName = "C:\\pleiades-2019-12-java-win-64bit-jre_20200213\\pleiades\\workspace\\Gips_Java3\\src\\サンプルcsv\\English.csv";
		List<String[]> data = InputData.engLearning(fileName);
		if (data == null) {
			System.out.println("問題を読み込むことができませんでした");
			return;
		}

		//問題数をListの長さから読み込む
		final int EXAM_MAX = data.size();

		//メッセージを出力
		System.out.println("英単語学習アプリ「Grape」へようこそ！");

		//Scannerの使用
		Scanner scan = new Scanner(System.in);
		//Scannerから取り出した文字列を格納する
		String input = "null";
		//最初の問題番号を設定
		Random random = new Random();
		int examNum = random.nextInt(EXAM_MAX);
		//前の問題番号を格納する
		int beforeExamNum = -1;

		while (true) {

			//出題する
			System.out.println(data.get(examNum)[1] + "、を英語で入力してください。（半角、大文字小文字は問わない）");

			//入力結果を受け取る
			input = scanInput(scan);

			//正解が入力されていれば正解を、されていなければ不正解を出力する
			//200408 equalsではなくequalsIgnoreCaseを用いると大文字/小文字の区別をしなくなる
			if (input.equalsIgnoreCase(data.get(examNum)[0])) {

				System.out.println("正解です！");

			} else {

				System.out.println("不正解です");

			}

			System.out.println("続行する場合はEnterを、終了する場合は文字列を入力してください");
			//続行判定
			//入力結果を受け取る
			input = scanInput(scan);
			//正解が入力されていれば正解を、されていなければ不正解を出力する
			if (!input.equals("")) {
				break;
			}

			//次の出題をRandomに決定
			//200408 1つ前と同じ出題にしない※Randomを再度回さない書き方
			beforeExamNum = examNum;
			examNum = random.nextInt(EXAM_MAX - 1);
			if (examNum >= beforeExamNum) {
				examNum++;
			}

		}

		//Scannerの終了処理
		scan.close();

	}

	//テキストのスキャン
	//200408 問題解答時と続行確認時の両方で使うので名前をAnswerからInputに
	public static String scanInput(Scanner scan) {

		//入力されたメッセージを格納する変数をString型で格納
		String inputText = scan.nextLine();
		//返す
		return inputText;

	}

}
