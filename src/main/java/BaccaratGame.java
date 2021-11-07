import java.util.ArrayList;

public class BaccaratGame {
	
	private ArrayList<Card> playerHand;
	private ArrayList<Card> bankerHand;
	private String bettingType;
	private BaccaratDealer theDealer;
	private double currentBet;
	private double totalWinnings;
	
	
	BaccaratGame(double currentBet, String whoToBetOn) {
		this.currentBet = currentBet;
		this.bettingType = whoToBetOn;
		theDealer.generateDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
	}
	
	
	public void drawCards(ArrayList<Card> playerHand, ArrayList<Card> bankerHand) {
		Card drawCard = theDealer.drawOne();
		Card noDraw = null;
		if (BaccaratGameLogic.handTotal(bankerHand) == 9 ||
			BaccaratGameLogic.handTotal(bankerHand) == 8 ||
			BaccaratGameLogic.handTotal(playerHand) == 9 ||
			BaccaratGameLogic.handTotal(playerHand) == 8 ) {
			return;
		} else {
			if (BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
				playerHand.add(drawCard);
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, drawCard)) {
					bankerHand.add(theDealer.drawOne());
					return;
				} else {
					return;
				}
			} else {
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, noDraw)) {
					bankerHand.add(theDealer.drawOne());
					return;
				} else {
					return;
				}
			}
	    }	
   }
	
	// This method will determine if the user won or lost their bet and return the amount won or lost based on the value in currentBet.
	public double evaluateWinnings() {
		drawCards(playerHand, bankerHand);
			if (BaccaratGameLogic.whoWon(playerHand, bankerHand) == bettingType) {
				if (bettingType == "Player" || bettingType == "Banker") {
					totalWinnings = currentBet * 2;
				} else {
					totalWinnings = (currentBet * 8) + currentBet;
				}
			} else {
				totalWinnings = -currentBet;
			}
		return totalWinnings;
	}
}
