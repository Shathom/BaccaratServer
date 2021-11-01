import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
	
	public ArrayList<Card> deck;
	public static final String suits[] = {"club", "diamond", "heart", "spade"};
	public static final int ranks[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	// generateDeck will generate a new standard 52 card deck where each card is an instance of the Card class in the ArrayList<Card> deck.
	public void generateDeck() {
		deck = new ArrayList<Card>();
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				this.deck.add(new Card(suits[i], ranks[j]));
			}
		}
		Collections.shuffle(deck);
	}
	// dealHand will deal two cards and return them in an ArrayList<Card>
	public ArrayList<Card> dealHand() {
		ArrayList<Card> cardsToDeal = new ArrayList<Card>();
		if (deck.isEmpty() || deck.size() == 1) {
			System.out.println("No more cards left to deal!");
		} else {
			cardsToDeal.add(deck.remove(0));
			cardsToDeal.add(deck.remove(0));
		}
		return cardsToDeal;
	}
	// drawOne will deal a single card and return it.
	public Card drawOne() {
		Card cardToDeal = null;
		if (deck.isEmpty()) {
			System.out.println("No more cards left to deal!");
		} else {
			cardToDeal = new Card(deck.remove(0).getSuite(), deck.remove(0).getValue());
		}
		return cardToDeal;
	}
	
	// shuffleDeck will create a new deck of 52 cards and “shuffle”; randomize the cards in that ArrayList<Card>. 
	public void shuffleDeck() {
		generateDeck();
	}
	// deckSize will just return how many cards are in this deck at any given time.
	public int deckSize() {
		return deck.size();
	}
	
}
