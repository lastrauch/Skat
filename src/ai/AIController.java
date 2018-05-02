package ai;

import java.util.ArrayList;
import java.util.List;
import interfaces.InGameInterface;
import logic.Card;
import logic.ClientLogic;
import logic.GameSettings;
import logic.PlayState;
import logic.Player;
import logic.Position;

public class AIController implements InGameInterface {
  private Bot bot;
  private GameSettings gs;
  private PlayState ps;
  private List<Player> player; // These are the other players
  private List<Player> opponents;
  private Player partner;
  private int[] bets; // Vector of bets by player i
  private int maxBet = 0; // Set to -1, if bot doesn't want to play single
  private SinglePlay singlePlay;
  private Card[][] playedCards; // Matrix of played Cards. Columns are the players, rows are the
                                // Cards
  private double[][] cardProbability; // Matrix of probabilities, player i has card j;
                                      // Player are the columns, probabilities are the rows
                                      // Spades, Clubs, Hearts, Diamonds
                                      // Ace, Ten, King, Queen, Jack, Nine, Eight, Seven
  private boolean[][] hasColour; // Columns are the players, rows are the colours: Spades, Clubs,
                                 // Hearts, Diamonds
  private boolean[] hasTrump; // Index is the player
  private int existingTrumps; // Trumps left in the whole game, including own cards
  private List<Card> currentTrick;

  public AIController(String name, BotDifficulty difficulty, GameSettings gs) {
    this.bot = new Bot(name, difficulty);
    this.gs = gs;
    this.player = new ArrayList<Player>();
    this.opponents = new ArrayList<Player>();
  }

  public void startPlay(ArrayList<Card> hand, Position position) {
    this.bot.setHand(hand);
    this.bot.setPosition(position);
  }

  public int askToPlayCard() {
    switch (this.bot.getDifficulty()) {
      case EASY:
        return Easy.playCard(this);
      case MEDIUM:
        return Medium.playCard(this);
      case HARD:
        return Hard.playCard(this);
      default:
        return -1;
    }
  }


  public void showSecoundsLeftToPlayCard(int seconds) {
    // Do nothing and expect the AI to react in sufficient time.
  }


  public PlayState askToTakeUpSkat(PlayState ps) {
    this.ps = ps;
    switch (this.bot.getDifficulty()) {
      case EASY:
        return Easy.setPlayState(this);
      case MEDIUM:
        return Medium.setPlayState(this);
      case HARD:
        return Hard.setPlayState(this);
    }
    return null;
  }


  public boolean askForBet(int bet, Player player) {
    // TODO Player is the one who put the last bet; Null if one is the first one to bet
    if (this.player.size() < 2) {
      for (int i = 0; i < this.player.size(); i++) {
        if (this.player.get(i).getName() != Integer.toString(player.getId())) {
          Player p = new Player(Integer.toString(player.getId()));
          p.setId(this.player.size() + 1);
          this.player.add(p);
        }
      }
    }

    switch (this.bot.getDifficulty()) {
      case EASY:
        return Easy.setBet(this, bet);
      case MEDIUM:
        return Medium.setBet(this, bet);
      case HARD:
        return Hard.setBet(this, bet);
      default:
        return false;
    }
  }


  public void updateHand(List<Card> hand) {
    if (hand.size() == 10) {
      this.cardProbability = General.initializeProbabilities(hand);
    }
    this.bot.setHand(hand);
  }


  public void setPlaySettings(PlayState ps) {
    this.partner = null;
    this.opponents = new ArrayList<Player>();
    for (int i = 0; i < ps.getGroup().length; i++) {
      if (ps.getGroup()[i].IsDeclarer()) {
        for (int j = 0; j < this.player.size(); i++) {
          if (this.player.get(j).getName() == Integer.toString(ps.getGroup()[i].getId())) {
            this.opponents.add(this.player.get(j));
          }
        }
      } else if (ps.getGroup()[i].getPosition() != Position.DEALER
          && this.bot.getId() != ps.getGroup()[i].getId()) {
        for (int j = 0; j < this.player.size(); i++) {
          if (this.player.get(j).getName() == Integer.toString(ps.getGroup()[i].getId())) {
            this.partner = this.player.get(j);
          }
        }
      }
    }

    switch (ps.getPlayMode()) {
      case GRAND:
        this.existingTrumps = 4;
        break;
      case SUIT:
        this.existingTrumps = 11;
        break;
      case NULL:
        this.existingTrumps = 0;
        break;
    }
    this.ps = ps;

    // Check if AI wants to play Kontra
    if (this.bot.getDifficulty() == BotDifficulty.HARD) {
      Hard.playKontra(this);
    }
  }


  public void updateTrick(List<Card> currentTrick) {
    //TODO update card played, hasTrump, hasColour, cardProbabilities
    this.currentTrick = currentTrick;
  }


  public void setGameSettings(GameSettings gs) {
    this.gs = gs;
  }


  public void stopGame(String reason) {
    // Do nothing
  }

  public void showWinnerTrick(Player player) {
    // Do nothing
  }

  public void showWinnerPlay(Player player1, Player player2) {
    // Reset play informations
    this.ps = null;
    this.singlePlay = null;
    this.bets = new int[0];
    this.cardProbability = new double[32][3];
    this.partner = null;
    this.opponents = new ArrayList<Player>();
    this.hasColour = new boolean[4][3];
    this.hasTrump = new boolean[3];
  }

  public void showWinnerGame(Player player) {
    // Do nothing
  }

  public void startPlay(List<Card> hand, Position position) {
    this.cardProbability = General.initializeProbabilities(hand);
    this.bot.setHand(hand);
    this.bot.setPosition(position);
  }

  public boolean askToRekontra() {
    switch (this.bot.getDifficulty()) {
      case EASY:
        return false;
      case MEDIUM:
        return Medium.askToRekontra(this);
      case HARD:
        return Hard.askToRekontra(this);
    }
    return false;
  }

  public GameSettings getGameSettings() {
    return this.gs;
  }

  public Bot getBot() {
    return this.bot;
  }

  public void setBot(Bot bot) {
    this.bot = bot;
  }

  public PlayState getPlayState() {
    return this.getPlayState();
  }

  public List<Card> getCurrentTrick() {
    return this.currentTrick;
  }

  public void setCardProbabilities(double[][] cardProbabilities) {
    this.cardProbability = cardProbabilities;
  }

  public double[][] getCardProbabilities() {
    return this.cardProbability;
  }

  public void setCardProbability(double probability, int colour, int number, int player) {
    this.cardProbability[colour * 8 + number][player] = probability;
  }

  public void setMaxBet(int maxBet) {
    this.maxBet = maxBet;
  }

  public int getMaxBet() {
    return this.maxBet;
  }

  public void setSinglePlay(SinglePlay singlePlay) {
    this.singlePlay = singlePlay;
  }

  public SinglePlay getSinglePlay() {
    return this.singlePlay;
  }

  public void setAllPlayer(List<Player> player) {
    this.player = player;
  }

  public List<Player> getAllPlayer() {
    return this.player;
  }

  public void setOpponents(List<Player> opponents) {
    this.opponents = opponents;
  }

  public List<Player> getOpponents() {
    return this.opponents;
  }

  public void setPartner(Player partner) {
    this.partner = partner;
  }

  public Player getPartner() {
    return this.partner;
  }

  public void setBets(int[] bets) {
    this.bets = bets;
  }

  public int[] getBets() {
    return this.bets;
  }

  public void setPlayedCards(Card[][] playedCards) {
    this.playedCards = playedCards;
  }

  public Card[][] getPlayedCards() {
    return this.playedCards;
  }

  public void setHasColour(boolean[][] hasColour) {
    this.hasColour = hasColour;
  }

  public boolean[][] getHasColour() {
    return this.hasColour;
  }

  public void setHasTrump(boolean[] hasTrump) {
    this.hasTrump = hasTrump;
  }

  public boolean[] getHasTrump() {
    return this.hasTrump;
  }

  public void setExistingTrumps(int existingTrumps) {
    this.existingTrumps = existingTrumps;
  }

  public int getExistingTrumps() {
    return this.existingTrumps;
  }

}
