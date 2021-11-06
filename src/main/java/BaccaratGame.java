import java.util.ArrayList;

public class BaccaratGame {
	
	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
	public String whoToBetOn;
	public BaccaratDealer theDealer;
	public double currentBet;
	public double totalWinnings;
	
	
	BaccaratGame(double currentBet, String whoToBetOn) {
		this.currentBet = currentBet;
		this.whoToBetOn = whoToBetOn;
		theDealer.generateDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
	}
	
	public boolean winOrLoss(ArrayList<Card> playerHand, ArrayList<Card> bankerHand) {
		Card drawCard = theDealer.drawOne();
		Card noDraw = null;
		if (BaccaratGameLogic.handTotal(bankerHand) == 9 ||
			BaccaratGameLogic.handTotal(bankerHand) == 8 ||
			BaccaratGameLogic.handTotal(playerHand) == 9 ||
			BaccaratGameLogic.handTotal(playerHand) == 8 ) {
			
			return BaccaratGameLogic.whoWon(playerHand, bankerHand) == whoToBetOn;
				
		} else { 
			if (BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
				playerHand.add(drawCard);
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, drawCard)) {
					bankerHand.add(theDealer.drawOne());
					return BaccaratGameLogic.whoWon(playerHand, bankerHand) == whoToBetOn;
				} else {
					return BaccaratGameLogic.whoWon(playerHand, bankerHand) == whoToBetOn;
				}
			} else {
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, noDraw)) {
					bankerHand.add(theDealer.drawOne());
					return BaccaratGameLogic.whoWon(playerHand, bankerHand) == whoToBetOn;
				} else {
					return BaccaratGameLogic.whoWon(playerHand, bankerHand) == whoToBetOn;
				}
			}
		}	
	}
	
	
	// This method will determine if the user won or lost their bet and return the amount won or lost based on the value in currentBet.
	public double evaluateWinnings() {
		if (winOrLoss(playerHand, bankerHand)) {
			if (BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Player" ||
			    BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Banker") {
				totalWinnings = currentBet * 2;
			} else {
				totalWinnings = (currentBet * 8) + currentBet;
			}
		}
		return totalWinnings;
	}
	
}
