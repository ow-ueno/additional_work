import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//引いたカードを山札に戻さないトランプクラス
public class Card {

	//スート、数字の数値、文字列JQKA含みの数値を格納した連想配列
	private Map<Integer, String> cardSuit = new HashMap<>();
	private Map<Integer, Integer> cardFigNum = new HashMap<>();
	private Map<Integer, String> cardFigStr = new HashMap<>();

	//キュー（ID）
	private List<Integer> queueCard = new ArrayList<>();
	//既に引いたカード
	private List<Integer> drawnCard = new ArrayList<>();

	//手札(2人分)
	private List<Integer> hand1 = new ArrayList<>();
	private List<Integer> hand2 = new ArrayList<>();

	//初期化
	public Card() {

		//連想配列とキューにカードをセット
		String suit[] = { "クラブ", "ダイヤ", "ハート", "スペード" };
		String number[] = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {

				//idを計算
				int id = i * 13 + j;
				//連想配列にidとカード呼称をセット
				cardSuit.put(id, suit[i]);
				cardFigNum.put(id, j+1);
				cardFigStr.put(id, number[j]);
				//キューにidをセット
				queueCard.add(id);

			}
		}

	}

	//シャッフル
	public void shuffle() {

		Collections.shuffle(queueCard);

	}

	public int drawCard(int pNum) {

		//引いたカードの枚数をチェック
		int drawnCardNum = drawnCard.size();
		//引けるかチェック
		if(drawnCardNum < 52) {
			//引ける
			int cardID = queueCard.get(drawnCardNum);
			drawnCard.add(cardID);
			//受け取ったpNumに応じてカードを入れる
			if(pNum == 1) {
				hand1.add(cardID);
			}else {
				hand2.add(cardID);
			}

			//一応返す
			return cardID;

		}else {
			//引けない
			System.out.println("これ以上カードを引くことはできません：デッキにカードがありません");
			return -1;
		}


	}

	//下駄
	public Map<Integer, String> getCardSuit() {
		return cardSuit;
	}

	public Map<Integer, Integer> getCardFigNum() {
		return cardFigNum;
	}

	public Map<Integer, String> getCardFigStr() {
		return cardFigStr;
	}

	public List<Integer> getQueueCard() {
		return queueCard;
	}

	public List<Integer> getDrawnCard() {
		return drawnCard;
	}

	public List<Integer> getHand1() {
		return hand1;
	}

	public List<Integer> getHand2() {
		return hand2;
	}



}
