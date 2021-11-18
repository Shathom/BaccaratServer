import java.util.ArrayList;

public class BaccaratGameLogic {
	

	// The method whoWon will evaluate two hands at the end of the game and return a string depending on the winner: “Player”, “Banker”, “Draw”. 
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		// hand1 is player and hand2 is banker
		if (handTotal(hand1) < handTotal(hand2)) {
			return "Banker";
		}
		if (handTotal(hand1) > handTotal(hand2)) {
			return "Player";
		}
		return "Draw";
	}
	// The method handTotal will take a hand and return how many points that hand is worth.
	public static int handTotal(ArrayList<Card> hand) {
		int total = 0;
		for (Card c : hand) {
			total += c.getBaccaratValue();
		}
		return total%10;
	}
	
	// The methods evaluateBankerDraw and evaluatePlayerDraw will return true if either one should be dealt a third card, otherwise return false.
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		if (playerCard == null) {
			return handTotal(hand) < 6;
		}
		if (handTotal(hand) >= 7) {
			return false;
		}
		if (handTotal(hand) < 3) {
			return true;
		}
		if (handTotal(hand) == 3) {
			return playerCard.getValue() != 8;
		}
		if (handTotal(hand) == 4) {
			return (playerCard.getValue() != 0 
					&& playerCard.getValue() != 1
					&& playerCard.getValue() != 8 
					&& playerCard.getValue() != 9);
		}
		if (handTotal(hand) == 5) {
			return (playerCard == null ||
					playerCard.getValue() >= 4 &&
					playerCard.getValue() <= 7);
		}
		if (handTotal(hand) == 6) {
			return (playerCard.getValue() == 6 ||
					playerCard.getValue() == 7);
		}
		System.out.println("Exception: Discovered new Integer");
		return false;
	}
	
	//The methods evaluateBankerDraw and evaluatePlayerDraw will return true if either one should be dealt a third card, otherwise return false.
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		return handTotal(hand) < 6;
	}
}
