import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	// Tests for Card.java

	@Test
	void testCardConstructor() {
		Card c = new Card("diamond", 5);
		assertEquals("diamond", c.getSuite(), "Oops, Card construtor did not set the the correct suite!!");
		assertEquals(5, c.getValue(), "Oops, Card construtor did not set the the correct value!!");
	}
	
	@Test
	void testSetSuite() {
		Card c = new Card("diamond", 5);
		c.setSuite("club");
		assertEquals("club", c.getSuite(), "Oops, Card setSuit() did not set the the correct suite!!");
		
		Card c2 = new Card("club", 5);
		c2.setSuite("heart");
		assertEquals("heart", c2.getSuite(), "Oops, Card setSuit() did not set the the correct suite!!");
	}
	
	@Test
	void testGetSuite() {
		Card c = new Card("spade", 4);
		assertEquals("spade", c.getSuite(), "Oops, Card getSuit() did not get the the correct suite!!");
		
		Card c2 = new Card("club", 7);
		assertEquals("club", c2.getSuite(), "Oops, Card getSuit() did not get the the correct suite!!");
	}
	
	@Test
	void testGetValue() {
		Card c = new Card("spade", 4);
		assertEquals(4, c.getValue(), "Oops, Card getValue() did not get the the correct value!!");
		
		Card c2 = new Card("club", 7);
		assertEquals(7, c2.getValue(), "Oops, Card getValue() did not get the the correct value!!");
	}
	
	@Test
	void testSetValue() {
		Card c = new Card("spade", 4);
		c.setValue(6);
		assertEquals(6, c.getValue(), "Oops, Card setValue() did not get the the correct value!!");
		
		Card c2 = new Card("club", 7);
		c2.setValue(9);
		assertEquals(9, c2.getValue(), "Oops, Card setValue() did not get the the correct value!!");
	}
 	
	@Test
	void testGetBaccaratValue() {
		Card c = new Card("spade", 4);
		assertEquals(4, c.getBaccaratValue(), "Oops, Card getBaccaratValue() did not get the the correct Baccarat value!!");
		
		Card c2 = new Card("spade", 13);
		assertEquals(0, c2.getBaccaratValue(), "Oops, Card getBaccaratValue() did not get the the correct Baccarat value!!");
	}
	
	@Test
    void testToString() {
		Card c = new Card("diamond", 5);
		assertEquals("diamond5.jpg", c.toString(), "Oops, Card toString() did not make the correct file name!!");
		
		Card c2 = new Card("spade", 13);
		assertEquals("spade13.jpg", c2.toString(), "Oops, Card toString() did not make the the correct file name!!");
	}
	
	
	// Tests for BaccaratDealer.java
	
	//@Test
	//void testBaccaratDealerConstructor() {
    //}
	
	@Test
	void testGenerateDeck() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.printDeck();
		
		assertEquals(52, dealer.deckSize(), "Opps, dealer returned the wrong deck size!!");
		
		dealer.dealHand();
		assertEquals(50, dealer.deckSize(), "Opps, dealer returned the wrong deck size after dealing a hand!!");
		
		dealer.drawOne();
		assertEquals(49, dealer.deckSize(), "Opps, dealer returned the wrong deck size after drawing one!!");
		
	}
	
	@Test 
	void testDealHand() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		
		assertNotNull(dealer.dealHand(), "Oops, the DealHand functionality returns a null hand!!" );
		
		BaccaratDealer dealer2 = new BaccaratDealer();
		dealer2.generateDeck();
		
		assertNotNull(dealer2.dealHand(), "Oops, the DealHand functionality returns a null hand!!" );

	}
	
	@Test 
	void testDrawOne() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		
		assertNotNull(dealer.drawOne(), "Oops, the drawOne functionality returns a null Card!!" );
		
		BaccaratDealer dealer2 = new BaccaratDealer();
		dealer2.generateDeck();
		
		assertNotNull(dealer2.drawOne(), "Oops, the DealHand functionality returns a null Card!!" );

	}
	
	@Test
	void testShuffleDeck() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.shuffleDeck();
		dealer.printDeck();
		
		assertEquals(52, dealer.deckSize(), "Opps, dealer returned the wrong deck size!!");
		
		dealer.dealHand();
		assertEquals(50, dealer.deckSize(), "Opps, dealer returned the wrong deck size after dealing a hand!!");
		
		dealer.drawOne();
		assertEquals(49, dealer.deckSize(), "Opps, dealer returned the wrong deck size after drawing one!!");
		
	}
	
	
	@Test
	void testdeckSize() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.printDeck();
		
		assertEquals(52, dealer.deckSize(), "Opps, dealer returned the wrong deck size!!");
		
		dealer.dealHand();
		assertEquals(50, dealer.deckSize(), "Opps, dealer returned the wrong deck size after dealing a hand!!");
		
		dealer.drawOne();
		assertEquals(49, dealer.deckSize(), "Opps, dealer returned the wrong deck size after drawing one!!");
		
	}
	
	
	// Tests for BaccaratGameLogic.java
	
	
	@Test
	void testWhoWon() {
		
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("diamond", 3));
		hand1.add(new Card("spade", 8));
		
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("heart", 2));
		hand2.add(new Card("club", 1));

		
		assertEquals("Banker", BaccaratGameLogic.whoWon(hand1, hand2),  "Opps, whowWon did not return the right person who won!!");
		
		
		ArrayList<Card> hand1a = new ArrayList<Card>();
		hand1a.add(new Card("diamond", 3));
		hand1a.add(new Card("spade", 8));
		
		ArrayList<Card> hand2a = new ArrayList<Card>();
		hand2a.add(new Card("heart", 3));
		hand2a.add(new Card("club", 8));

		
		assertEquals("Draw", BaccaratGameLogic.whoWon(hand1a, hand2a),  "Opps, whowWon did not return the right person who won!!");
		
		
		ArrayList<Card> hand1b = new ArrayList<Card>();
		hand1b.add(new Card("diamond", 1));
		hand1b.add(new Card("spade", 2));
		
		ArrayList<Card> hand2b = new ArrayList<Card>();
		hand2b.add(new Card("heart", 3));
		hand2b.add(new Card("club", 8));

		
		assertEquals("Player", BaccaratGameLogic.whoWon(hand1b, hand2b),  "Opps, whowWon did not return the right person who won!!");
		
		
	}
	
	@Test
	void testHandTotal() {
		
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("diamond", 3));
		hand1.add(new Card("spade", 8));
		
		assertEquals(1, BaccaratGameLogic.handTotal(hand1),  "Opps, handTotal did not return the right total of the hand!!");
		
		
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("heart", 5));
		hand2.add(new Card("club", 1));
		
		assertEquals(6, BaccaratGameLogic.handTotal(hand2),  "Opps, handTotal did not return the right total of the hand!!");
		
	}
	
	@Test
	void testEvaluateBankerDraw() {
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("diamond", 6));
		hand1.add(new Card("spade", 8));
		
		Card playerCard = new Card("heart", 1);
		
		assertEquals(false, BaccaratGameLogic.evaluateBankerDraw(hand1, playerCard),  "Opps, evaluateBankerDraw did not return the right draw strategy!!");
		
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("heart", 5));
		hand2.add(new Card("club", 1));
		
		Card playerCard2 = new Card("heart", 6);
		
		assertEquals(true, BaccaratGameLogic.evaluateBankerDraw(hand2, playerCard2),  "Opps, evaluateBankerDraw did not return the right draw strategy!!");
		
		
	}
	
	@Test
	void testEvaluatePlayerDraw() {
		
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("diamond", 6));
		hand1.add(new Card("spade", 8));
		
		assertEquals(true, BaccaratGameLogic.evaluatePlayerDraw(hand1),  "Opps, evaluatePlayerDraw did not return the right draw strategy!!");
		
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("heart", 9));
		hand2.add(new Card("club", 8));
		
		assertEquals(false, BaccaratGameLogic.evaluatePlayerDraw(hand2),  "Opps, evaluatePlayerDraw did not return the right draw strategy!!");

		
	}

	
	// Tests for BaccaratGame.java

	@Test
	void testBaccaratGameConstructor() {
		
		BaccaratGame game = new BaccaratGame(400, "Player");
		
		assertEquals("Player", game.bettingType,  "Opps, BaccaratGame Constructor did not set the betting type!!");
		assertEquals(400, game.currentBet,  "Opps, BaccaratGame Constructor did not set the betting value!!");
		
		assertNotNull(game.bankerHand, "Oops, BaccaratGame constructor creates a null banker hand!!" );
		assertNotNull(game.playerHand, "Oops, BaccaratGame constructor creates a null player hand!" );
		
		assertEquals(48,  game.theDealer.deckSize(),  "Opps, BaccaratGame Constructor did not create a deck!");
	
		
	}
	
	@Test
	void testDrawCards() {
		
		BaccaratGame game = new BaccaratGame(500, "Draw");
		
		ArrayList<Card> hand1 = new ArrayList<Card>();
		hand1.add(new Card("diamond", 6));
		hand1.add(new Card("spade", 8));
		
		ArrayList<Card> hand2 = new ArrayList<Card>();
		hand2.add(new Card("heart", 9));
		hand2.add(new Card("club", 10));
		
		game.drawCards(hand1, hand2);
		
		assertEquals(true, game.naturalWin,  "Opps, BaccaratGame Constructor did not set the betting type!!");
		
		
		ArrayList<Card> hand1a = new ArrayList<Card>();
		hand1a.add(new Card("diamond", 9));
		hand1a.add(new Card("spade", 8));
		
		ArrayList<Card> hand2a = new ArrayList<Card>();
		hand2a.add(new Card("heart", 10));
		hand2a.add(new Card("club", 1));
		
		game.drawCards(hand1a, hand2a);
		
		assertEquals(true, game.bankerDraw,  "Opps, BaccaratGame draw card did not give the right draw strategy!!");
		
		
	}
	
	@Test
	void testEvaluateWinnings() {
		BaccaratGame game = new BaccaratGame(500, "Draw");
		
		game.evaluateWinnings();
		
		assertNotEquals(0.0, game.evaluateWinnings(), "Oops, evaluateWinnings is not updating the currentWinning!!");
		
		
		BaccaratGame game2 = new BaccaratGame(300, "Player");
		
		game2.evaluateWinnings();
		
		assertNotEquals(0.0, game2.evaluateWinnings(), "Oops, evaluateWinnings is not updating the currentWinning!!");
		
		
	}
	
	

}
