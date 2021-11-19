import java.util.ArrayList;

public class BaccaratGame {
	
	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
	public String bettingType;
	public String gameResult;
	public BaccaratDealer theDealer;
	public double currentBet;
	public double currentWinning = 0.0;
	public boolean naturalWin = false;
	public boolean playerDraw = false;
	public boolean bankerDraw = false;
	public int playerHandTotal;
	public int bankerHandTotal;
	
	
	
	BaccaratGame(double currentBet, String whoToBetOn) {
		this.currentBet = currentBet;
		this.bettingType = whoToBetOn;
		theDealer = new BaccaratDealer();
		bankerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		theDealer.generateDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
		playerHandTotal = BaccaratGameLogic.handTotal(playerHand);
		bankerHandTotal = BaccaratGameLogic.handTotal(bankerHand);
	}
	
	
	public void drawCards(ArrayList<Card> playerHand, ArrayList<Card> bankerHand) {
		Card drawCard = theDealer.drawOne();
		Card noDraw = null;
		if (BaccaratGameLogic.handTotal(bankerHand) == 9 ||
			BaccaratGameLogic.handTotal(bankerHand) == 8 ||
			BaccaratGameLogic.handTotal(playerHand) == 9 ||
			BaccaratGameLogic.handTotal(playerHand) == 8 ) {
			naturalWin = true;
			return;
		} else {
			if (BaccaratGameLogic.evaluatePlayerDraw(playerHand)) {
				playerHand.add(drawCard);
				playerHandTotal = BaccaratGameLogic.handTotal(playerHand);
				playerDraw = true;
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, drawCard)) {
					bankerHand.add(theDealer.drawOne());
					bankerHandTotal = BaccaratGameLogic.handTotal(bankerHand);
					bankerDraw = true;
					return;
				} else {
					return;
				}
			} else {
				if (BaccaratGameLogic.evaluateBankerDraw(bankerHand, noDraw)) {
					bankerHand.add(theDealer.drawOne());
					bankerHandTotal = BaccaratGameLogic.handTotal(bankerHand);
					bankerDraw = true;
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
		gameResult = BaccaratGameLogic.whoWon(playerHand, bankerHand);
			if (gameResult == bettingType) {
				if (bettingType == "Player" || bettingType == "Banker") {
					currentWinning = currentBet * 2;
				} else {
					currentWinning = (currentBet * 8) + currentBet;
				}
			} else {
				currentWinning = -currentBet;
			}
		return currentWinning;
	}
}
