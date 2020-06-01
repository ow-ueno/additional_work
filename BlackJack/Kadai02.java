import java.util.List;
import java.util.Scanner;

//ブラックジャック
public class Kadai02 {

	public static void main(String[] args) {

		//ゲーム開始メッセージの出力
		startMessage();

		//デッキの用意とシャッフル
		Card deck = new Card();
		deck.shuffle();

		//カードを2枚ずつ引く
		//プレイヤーのカードは逐次コンソール上に出力する
		for (int i = 0; i < 2; i++) {
			deck.drawCard(1);
			drawCardMessage(deck, 1);
		}
		for (int i = 0; i < 2; i++) {
			deck.drawCard(2);
			drawCardMessage(deck, 2);
		}

		//スキャナの宣言
		Scanner scan = new Scanner(System.in);
		//バーストの数値の指定
		final int BURST_NUM = 21;
		//ディーラーのヤメ判断数値
		final int STOP_NUM = 17;

		//プレイヤー：3枚目以降をバーストを超えない範囲で任意の枚数引く
		playerDrawCards(scan, deck, BURST_NUM);

		//スキャナを閉じる
		scan.close();

		//ディーラー：3枚目以降をヤメ判断値以上になるまで引く
		dealerDrawCards(deck,STOP_NUM);

		//判定対象の得点を格納
		int playerScore = nowScore(deck, deck.getHand1());
		int dealerScore = nowScore(deck, deck.getHand2());
		//ディーラーと自分の得点を渡して判定結果をisWinに格納
		int isWin = lsWinJudge(playerScore, dealerScore);
		//isWinの結果と終了メッセージの出力
		endMessage(isWin, playerScore, dealerScore);

	}

	//スタート時のメッセージ
	public static void startMessage() {

		System.out.println("☆★☆★☆★☆★☆★　ブラックジャックへようこそ！　☆★☆★☆★☆★☆★");
		System.out.println("ゲームを開始します。");

	}

	//カードを引いた際のメッセージ出力
	public static void drawCardMessage(Card deck, int pNum) {

		if (pNum == 1) {

			//プレイヤーはすべて開示
			System.out.println("あなたの引いたカードは" + deck.getCardSuit().get(deck.getHand1().get(deck.getHand1().size() - 1))
					+ "の" + deck.getCardFigStr().get(deck.getHand1().get(deck.getHand1().size() - 1)) + "です。");

		} else {

			if (deck.getHand2().size() == 2) {

				//ディーラーの2枚目は非開示
				System.out.println("ディーラーの2枚目のカードは分かりません。");

			} else {

				//ディーラーの2枚目以外は開示
				System.out.println(
						"ディーラーの引いたカードは" + deck.getCardSuit().get(deck.getHand2().get(deck.getHand2().size() - 1)) + "の"
								+ deck.getCardFigStr().get(deck.getHand2().get(deck.getHand2().size() - 1)) + "です。");

			}
		}

	}

	//終了時メッセージ
	public static void endMessage(int isW, int pScore, int eScore) {

		//表示メッセージ
		System.out.println("あなたの得点は" + pScore + "です。");
		System.out.println("ディーラーの得点は" + eScore + "です。");
		switch (isW) {
		case 1:
			System.out.println("あなたの勝ちです！");
			break;
		case 0:
			System.out.println("引き分けです");
			break;
		case -1:
			System.out.println("あなたの負けです");
		}
		System.out.println("ブラックジャック終了！また遊んでね★");

	}

	public static void playerDrawCards(Scanner scan, Card deck, final int BURST_NUM) {

		while (true) {

			//得点の計算
			int playerScore = nowScore(deck, deck.getHand1());
			System.out.println("あなたの現在の得点は" + playerScore + "です。");
			//バーストまたは上限
			if (playerScore >= BURST_NUM) {
				break;
			}

			//プレイヤーの選択受け取り
			System.out.println("カードを引きますか？引く場合はYを、引かない場合はNを入力してください");
			String nextCard = scanInput(scan);

			//引かない ※Y以外
			if (!nextCard.equalsIgnoreCase("y")) {
				break;
			}

			//引く
			deck.drawCard(1);
			drawCardMessage(deck, 1);

		}
	}

	public static void dealerDrawCards(Card deck, final int STOP_NUM) {

		//2枚目の開示
		System.out.println("ディーラーの2枚目のカードは" + deck.getCardSuit().get(deck.getHand2().get(deck.getHand2().size() - 1)) + "の"
		+ deck.getCardFigStr().get(deck.getHand2().get(deck.getHand2().size() - 1)) + "でした。");

		while (true) {
			//得点の計算
			int Score = nowScore(deck, deck.getHand2());
			//閾値を越えたら終了
			if (Score >= STOP_NUM) {
				break;
			}
			System.out.println("ディーラーの現在の得点は" + Score + "です。");

			//引く
			deck.drawCard(2);
			drawCardMessage(deck, 2);

		}

	}

	//現在のスコア計算
	public static int nowScore(Card deck, List<Integer> Hand) {

		int score = 0;
		for (int i : Hand) {
			int iScore = deck.getCardFigNum().get(i);
			if (iScore > 10) {
				iScore = 10;
			}
			score += iScore;
		}
		return score;
	}

	//勝敗判定
	public static int lsWinJudge(int playerScore, int dealerScore) {

		//判定用の変数 1=Win 0=Draw -1=Lose
		int isWin = 1;
		if (playerScore > 21) {
			//プレイヤーバーストにより負け
			isWin = -1;
		} else if (dealerScore > 21) {
			//プレイヤー21以下、ディーラーバーストにより勝ち
		} else if (playerScore < dealerScore) {
			//双方非バースト、プレイヤー<ディーラーにより負け
			isWin = -1;
		} else if (playerScore == dealerScore) {
			//双方非バースト、プレイヤー=ディーラーにより引き分け
			isWin = 0;
		} //else:双方非バースト、プレイヤー>ディーラーにより勝ち

		//isWinを戻す
		return isWin;

	}

	//テキストのスキャン
	public static String scanInput(Scanner scan) {

		//入力されたメッセージを格納する変数をString型で格納
		String inputText = scan.nextLine();
		//返す
		return inputText;

	}

}
