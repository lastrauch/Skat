package ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import interfaces.InGameInterface;
import logic.Card;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;
import network.Settings;

public class AIController implements InGameInterface {
	
	// TODO
	// Available methods are:
	// setCardProbability(probability, colour, number, player) : void
	
	
	private Bot bot;
	private GameSettings gameSettings;
	private PlayState playState;
	private List<Player> player; // These are the other players
	private List<Player> opponents; // Opponents in this play
	private Player partner; // Partner in this play; If bot is declarer: null
	private int[] bets; // Vector of the last bets by player i.
	private int maxBet = 0; // Set to -1, if bot doesn't want to play single
	private SinglePlay singlePlay; // PlayMode and colour the bot wants to play, if so; otherwise null
	private Card[][] playedCards; // Matrix of played Cards. Columns are the players, rows are the Cards
	private double[][] cardProbability; // Matrix of probabilities, player i has card j;
										// Player are the columns, probabilities are the rows
										// Spades(0), Clubs(1), Hearts(2), Diamonds(3)
										// Ace(0), Ten(1), King(2), Queen(3), Jack(4), Nine(5), Eight(6), Seven(7)
	private boolean[][] hasColour; // Columns are the players, rows are the colours
	private boolean[] hasTrump; // Index is the player
	private int existingTrumps; // Trumps left in the play, including own cards
	private List<Card> currentTrick;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Constructor
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Constructs an instance of the AIController and initializes parameters, sets
	 * up Lists
	 * 
	 * @author fkleinoe
	 * @param name
	 * @param difficulty
	 * @param gameSettings
	 */
	public AIController(String name, BotDifficulty difficulty, GameSettings gameSettings) {
		this.bot = new Bot(name, difficulty);
		this.gameSettings = gameSettings;
		this.player = new ArrayList<Player>();
		this.opponents = new ArrayList<Player>();
		this.currentTrick = new ArrayList<Card>();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Interface Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	/**
	 * Starts the play with the given hand on the given position. The PlayState was
	 * introduced earlier.
	 * 
	 * @author fkleinoe
	 * @param hand
	 * @param position
	 */
	public void startPlay(List<Card> hand, Position position) {
		this.bot.setHand(hand);
		this.bot.setPosition(position);
	}

	@Override
	/**
	 * Asks the bot if he wants to place a bet. The passed bet is the height that is
	 * asked for. The passed player is the last player, that placed a bet. Returns a
	 * boolean representing the decision.
	 * 
	 * @author fkleinoe
	 * @param bet
	 * @param player
	 * @return boolean
	 */
	public boolean askForBet(int bet, Player player) {
		try {
			Thread.sleep(Settings.DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// TODO Player is the one who put the last bet;
		// TODO Does the Player has a proper id??
		if (player != null) {
			if (this.player.size() < 2) {
				boolean existing = false;
				for (int i = 0; i < this.player.size(); i++) {
					if (this.player.get(i).getName() == Integer.toString(player.getId())) {
						existing = true;
					}
				}
				if (!existing) {
					Player p = new Player(Integer.toString(player.getId()));
					p.setId(this.player.size());
					this.player.add(p);
				}
			}
		}

		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.askForBet(this, bet);
		case MEDIUM:
			return Medium.askForBet(this, bet);
		case HARD:
			return Hard.askForBet(this, bet);
		default:
			return false;
		}
	}

	@Override
	/**
	 * Asks the bot wether it wants to take up the Skat. Returns a boolean
	 * representing the decision.
	 * 
	 * @author fkleinoe
	 * @return boolean
	 */
	public boolean askToTakeUpSkat() {
		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.askToTakeUpSkat(this);
		case MEDIUM:
			return Medium.askToTakeUpSkat(this);
		case HARD:
			return Hard.askToTakeUpSkat(this);
		}
		return false;
	}

	@Override
	/**
	 * After the Bot decided to pick up the Skat, it needs to return two Cards.
	 * 
	 * @author fkleinoe
	 * @param playState
	 * @return List(Card)
	 */
	public List<Card> switchSkat(PlayState playState) {
		this.playState = playState;
		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.switchSkat(this);
		case MEDIUM:
			return Medium.switchSkat(this);
		case HARD:
			return Hard.switchSkat(this);
		}
		return Arrays.asList(playState.getSkat());
	}

	@Override
	/**
	 * If the bot won the auction, it now needs to set the PlayState. It recieves
	 * the current PlayState, because it is not able to construct one itself.
	 * 
	 * @author fkleinoe
	 * @param playState
	 * @return PlayState
	 */
	public PlayState askToSetPlayState(PlayState playState) {
		try {
			Thread.sleep(Settings.DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.playState = playState;
		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.askToSetPlayState(this);
		case MEDIUM:
			return Medium.askToSetPlayState(this);
		case HARD:
			return Hard.askToSetPlayState(this);
		}
		return playState;
	}

	@Override
	/**
	 * Asks the bot if he wants de declare Rekontra. Returns a boolean representing
	 * this decision.
	 * 
	 * @author fkleinoe
	 * @return boolean
	 */
	public boolean askToRekontra() {
		try {
			Thread.sleep(Settings.DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.askToRekontra(this);
		case MEDIUM:
			return Medium.askToRekontra(this);
		case HARD:
			return Hard.askToRekontra(this);
		}
		return false;
	}

	@Override
	/**
	 * Asks the bot to play a card. Returns the index of the card within the bots
	 * hand.
	 * 
	 * @author fkleinoe
	 * @return int
	 */
	public int askToPlayCard() {
		try {
			Thread.sleep(Settings.DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (this.bot.getDifficulty()) {
		case EASY:
			return Easy.askToPlayCard(this);
		case MEDIUM:
			return Medium.askToPlayCard(this);
		case HARD:
			return Hard.askToPlayCard(this);
		default:
			return -1;
		}
	}

	@Override
	/**
	 * Passes the last bet that was set by a player. This will update setBet so the
	 * AI holds track of the bets.
	 * 
	 * @author fkleinoe
	 * @param bet
	 * @param player
	 */
	public void receivedNewBet(int bet, Player player) {
		if (this.player.size() < 2) {
			boolean existing = false;
			for (int i = 0; i < this.player.size(); i++) {
				if (this.player.get(i).getName() == Integer.toString(player.getId())) {
					existing = true;
				}
			}
			if (!existing) {
				Player p = new Player(Integer.toString(player.getId()));
				p.setId(this.player.size());
				this.player.add(p);
			}
		}

		for (int i = 0; i < this.player.size(); i++) {
			if (this.player.get(i).getName() == Integer.toString(player.getId())) {
				this.bets[this.player.get(i).getId()] = bet;
			}
		}
	}

	@Override
	public void setPlaySettingsAfterAuction(PlayState playState) {
		this.playState = playState;
		Hard.decideToPlayKontra(this);
	}

	@Override
	/**
	 * Updates the hand of the bot.
	 * 
	 * @author fkleinoe
	 * @param hand
	 */
	public void updateHand(List<Card> hand) {
		if (hand.size() == 10) {
			this.cardProbability = General.initializeProbabilities(hand);
		}
		this.bot.setHand(hand);
	}

	@Override
	/**
	 * Reset play informations.
	 * 
	 * @author fkleinoe
	 * @param player1
	 * @param player2
	 */
	public void showWinnerPlay(Player player1, Player player2) {
		this.playState = null;
		this.singlePlay = null;
		this.maxBet = 0;
		this.bets = new int[3];
		this.playedCards = new Card[0][3];
		this.cardProbability = new double[32][3];
		this.partner = null;
		this.opponents = new ArrayList<Player>();
		this.hasColour = new boolean[4][3];
		this.hasTrump = new boolean[3];
		this.currentTrick = new ArrayList<Card>();
	}

	@Override
	/**
	 * Sets the GameSettings.
	 * 
	 * @author fkleinoe
	 * @param gameSettings
	 */
	public void setGameSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	@Override
	/**
	 * Adds a new Card to the trick, played by player. Therefore updates
	 * playedCards, cardProbabilities, hasColour, hasTrump, existingTrumps,
	 * currentTrick.
	 * 
	 * @author fkleinoe
	 * @param card
	 * @param player
	 */
	public void receivedNewCard(Card card, Player player) {
		// TODO

	}

	@Override
	/**
	 * Passes the seconds that the bot has left, to play a card. Only important for
	 * a human player.
	 * 
	 * @author fkleinoe
	 * @param seconds
	 */
	public void showSecondsLeftToPlayCard(int seconds) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @param bet
	 */
	public void updateBet(int bet) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @param reason
	 */
	public void stopGame(String reason) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @param player
	 */
	public void showWinnerTrick(Player player) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @param player
	 */
	public void showWinnerGame(Player player) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @author bet
	 */
	public void openAskForBet(int bet) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 */
	public void openTakeUpSkat() {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 */
	public void openAuctionWinnerScreen() {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 * @param playState
	 */
	public void openSwitchSkat(PlayState playState) {
		// Do nothing
	}

	@Override
	/**
	 * Only important for the ui.
	 * 
	 * @author fkleinoe
	 */
	public void itsYourTurn() {
		// Do nothing.
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Internal Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setCardProbability(double probability, int colour, int number, int player) {
		this.cardProbability[colour * 8 + number][player] = probability;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Setter- and Getter-Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Set bot.
	 * 
	 * @author fkleinoe
	 * @param bot
	 */
	public void setBot(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Get bot.
	 * 
	 * @author fkleinoe
	 * @return Bot
	 */
	public Bot getBot() {
		return this.bot;
	}

	// public void setGameSettings(GameSettings gameSettings); is already
	// implemented as
	// interface method

	/**
	 * Get GameSettings.
	 * 
	 * @author fkleinoe
	 * @return GameSettings
	 */
	public GameSettings getGameSettings() {
		return this.gameSettings;
	}

	/**
	 * Set playState.
	 * 
	 * @author fkleinoe
	 * @param playState
	 */
	public void setPlayState(PlayState playState) {
		this.playState = playState;
	}

	/**
	 * Get PlayState.
	 * 
	 * @author fkleinoe
	 * @return PlayState
	 */
	public PlayState getPlayState() {
		return this.playState;
	}

	/**
	 * Set player.
	 * 
	 * @author fkleinoe
	 * @param player
	 */
	public void setPlayer(List<Player> player) {
		this.player = player;
	}

	/**
	 * Get player.
	 * 
	 * @author fkleinoe
	 * @return List(Player)
	 */
	public List<Player> getPlayer() {
		return this.player;
	}

	/**
	 * Set opponents.
	 * 
	 * @author fkleinoe
	 * @param opponents
	 */
	public void setOpponents(List<Player> opponents) {
		this.opponents = opponents;
	}

	/**
	 * Get opponents.
	 * 
	 * @author fkleinoe
	 * @return List(Player)
	 */
	public List<Player> getOpponents() {
		return this.opponents;
	}

	/**
	 * Set partner.
	 * 
	 * @author fkleinoe
	 * @param partner
	 */
	public void setPartner(Player partner) {
		this.partner = partner;
	}

	/**
	 * Get partner.
	 * 
	 * @author fkleinoe
	 * @return Player
	 */
	public Player getPartner() {
		return this.partner;
	}

	/**
	 * Set bets.
	 * 
	 * @author fkleinoe
	 * @param bets
	 */
	public void setBets(int[] bets) {
		this.bets = bets;
	}

	/**
	 * Get bets.
	 * 
	 * @author fkleinoe
	 * @return int[]
	 */
	public int[] getBets() {
		return this.bets;
	}

	/**
	 * Set maxBet.
	 * 
	 * @author fkleinoe
	 * @param maxBet
	 */
	public void setMaxBet(int maxBet) {
		this.maxBet = maxBet;
	}

	/**
	 * Get maxBet.
	 * 
	 * @author fkleinoe
	 * @return int
	 */
	public int getMaxBet() {
		return this.maxBet;
	}

	/**
	 * Set singlePlay.
	 * 
	 * @author fkleinoe
	 * @param singlePlay
	 */
	public void setSinglePlay(SinglePlay singlePlay) {
		this.singlePlay = singlePlay;
	}

	/**
	 * Get singlePlay.
	 * 
	 * @author fkleinoe
	 * @return SinglePlay
	 */
	public SinglePlay getSinglePlay() {
		return this.singlePlay;
	}

	/**
	 * Set playedCards.
	 * 
	 * @author fkleinoe
	 * @param playedCards
	 */
	public void setPlayedCards(Card[][] playedCards) {
		this.playedCards = playedCards;
	}

	/**
	 * Get playedCards.
	 * 
	 * @author fkleinoe
	 * @return Card[][]
	 */
	public Card[][] getPlayedCards() {
		return this.playedCards;
	}

	/**
	 * Set cardProbabilities.
	 * 
	 * @author fkleinoe
	 * @param cardProbabilities
	 */
	public void setCardProbabilities(double[][] cardProbabilities) {
		this.cardProbability = cardProbabilities;
	}

	/**
	 * Get cardProbability.
	 * 
	 * @author fkleinoe
	 * @return double[][]
	 */
	public double[][] getCardProbabilities() {
		return this.cardProbability;
	}

	/**
	 * Set hasColour.
	 * 
	 * @author fkleinoe
	 * @param hasColour
	 */
	public void setHasColour(boolean[][] hasColour) {
		this.hasColour = hasColour;
	}

	/**
	 * Get hasColour.
	 * 
	 * @author fkleinoe
	 * @return boolean[][]
	 */
	public boolean[][] getHasColour() {
		return this.hasColour;
	}

	/**
	 * Set hasTrump.
	 * 
	 * @author fkleinoe
	 * @param hasTrump
	 */
	public void setHasTrump(boolean[] hasTrump) {
		this.hasTrump = hasTrump;
	}

	/**
	 * Get hasTrump.
	 * 
	 * @author fkleinoe
	 * @return boolean[]
	 */
	public boolean[] getHasTrump() {
		return this.hasTrump;
	}

	/**
	 * Set existingTrumps.
	 * 
	 * @author fkleinoe
	 * @param existingTrumps
	 */
	public void setExistingTrumps(int existingTrumps) {
		this.existingTrumps = existingTrumps;
	}

	/**
	 * Get existingTrumps.
	 * 
	 * @author fkleinoe
	 * @return int
	 */
	public int getExistingTrumps() {
		return this.existingTrumps;
	}

	/**
	 * Set currentTrick.
	 * 
	 * @author fkleinoe
	 * @param currentTrick
	 */
	public void setCurrentTrick(List<Card> currentTrick) {
		this.currentTrick = currentTrick;
	}

	/**
	 * Get currentTrick.
	 * 
	 * @author fkleinoe
	 * @return List(Card)
	 */
	public List<Card> getCurrentTrick() {
		return this.currentTrick;
	}

}
