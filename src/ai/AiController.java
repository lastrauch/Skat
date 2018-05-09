package ai;

import interfaces.InGameInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import logic.Card;
import logic.ClientLogic;
import logic.GameSettings;
import logic.Number;
import logic.PlayMode;
import logic.PlayState;
import logic.Player;
import logic.Position;
import network.Settings;

public class AiController implements InGameInterface {

  // This class is the main class of the AI.
  // It inherits the InGameInterface methods to call different actions on the AI.
  // Available methods are:

  // startPlay(List<Card>, Position) : void
  // Sets the hand and the position

  // askForBet(int, Player) : boolean
  // Returns true, if the AI wants to place the bet

  // receivedNewBet(int, Player) : void
  // Some other player bet, the AI was / is not yet involved in the auction

  // asktToTakeUpSkat() : void
  // Returns whether the AI wants to take up the Skat

  // switchSkat(PlayState) : List<Card>
  // If the AI did pick up the Skat, returns two Cards to the Skat

  // askToSetPlayState(PlayState) : PlayState
  // If the AI won the auction, the AI will set a PlayState

  // askToRekontra() : boolean
  // Returns whether the AI wants to declare Rekontra

  // askToPlayCard() : int
  // Returns the index of the Card the AI wants to play

  // receivedNewCard(Card, Player) : void
  // A player played a card

  // setPlaySettingsAfterAuction(PlayState) : void
  // Sets the PlayState, if the AI did not win the auction

  // updateHand(List<Card>) : void
  // Updates the hand

  // showScore(List<Player> player) : void
  // For the AI it resets the Play information

  // setCardProbability(double, int, int, player) : void
  // Sets the probability for a single card for a specific player

  private Bot bot;
  private ClientLogic logic; // Logic on which the interface will be called on
  private GameSettings gameSettings;
  private PlayState playState;
  private List<Player> player; // These are the other players
  private List<Player> opponents; // Opponents in this play
  private Player partner; // Partner in this play; If bot is declarer: null
  private int[] bets; // Vector of the last bets by player i.
  private int maxBet; // Set to -1, if bot doesn't want to play single
  private SinglePlay singlePlay; // PlayMode and colour the bot wants to play, if so; otherwise null
  private Card[][] playedCards; // Matrix of played Cards. Columns = players, rows = Cards

  private double[][] cardProbability; // Matrix of probabilities, player i has card j;
                                      // Player are the columns, probabilities are the rows
                                      // Spades(0), Clubs(1), Hearts(2), Diamonds(3)
                                      // Ace(0), Ten(1), King(2), Queen(3), Jack(4), Nine(5),
                                      // Eight(6), Seven(7)
  private boolean[][] hasColour; // Columns are the players, rows are the colours
  private boolean[] hasTrump; // Index is the player
  private int existingTrumps; // Trumps left in the play, including own cards
  private List<Card> currentTrick;

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Constructor
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Constructs an instance of the AIController and initializes parameters, sets up Lists
   * 
   * @author fkleinoe
   * @param name
   * @param difficulty
   * @param gameSettings
   */
  public AiController(ClientLogic logic, String name, BotDifficulty difficulty,
      GameSettings gameSettings) {
    this.logic = logic;
    this.bot = new Bot(name, difficulty);
    this.gameSettings = gameSettings;
    this.player = new ArrayList<Player>();
    this.opponents = new ArrayList<Player>();
    this.bets = new int[3];
    this.maxBet = 0;
    this.playedCards = new Card[1][3];
    this.cardProbability = new double[32][3];
    this.hasColour = new boolean[4][3];
    for (int i = 0; i < this.hasColour.length; i++) {
      for (int j = 0; j < this.hasColour[0].length; j++) {
        this.hasColour[i][j] = true;
      }
    }
    this.hasTrump = new boolean[3];
    for (int i = 0; i < this.hasTrump.length; i++) {
      this.hasTrump[i] = true;
    }
    this.existingTrumps = 0;
    this.currentTrick = new ArrayList<Card>();
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Interface Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  /**
   * Starts the play with the given hand on the given position. The PlayState will be introduced
   * later.
   * 
   * @author fkleinoe
   * @param hand
   * @param position
   */
  public void startPlay(List<Card> hand, Position position) {
    this.bot.setHand(hand);
    this.bot.setPosition(position);
    this.cardProbability = General.initializeProbabilities(hand);
    for (int i = 0; i < hand.size(); i++) {
      this.hasColour[3 - hand.get(i).getColour().ordinal()][0] = true;
    }
  }

  @Override
  /**
   * Asks the bot if he wants to place a bet. The passed bet is the height that is asked for. The
   * passed player is the last player, that placed a bet. Returns a boolean representing the
   * decision.
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
    if (player != null) {
      if (this.player.size() < 2) {
        boolean existing = false;
        for (int i = 0; i < this.player.size(); i++) {
          if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
            existing = true;
            this.bets[this.player.get(i).getId()] = bet;
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
   * Passes the last bet that was set by a player. This will update setBet so the AI holds track of
   * the bets.
   * 
   * @author fkleinoe
   * @param bet
   * @param player
   */
  public void receivedNewBet(int bet, Player player) {
    if (this.player.size() < 2) {
      boolean existing = false;
      for (int i = 0; i < this.player.size(); i++) {
        if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
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
      if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
        this.bets[this.player.get(i).getId()] = bet;
      }
    }
  }

  @Override
  /**
   * Asks the bot wether it wants to take up the Skat. Returns a boolean representing the decision.
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
      default:
        return false;
    }
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
      default:
        return Arrays.asList(playState.getSkat());
    }
  }

  @Override
  /**
   * If the bot won the auction, it now needs to set the PlayState. It recieves the current
   * PlayState, because it is not able to construct one itself.
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
        playState = Easy.askToSetPlayState(this);
        break;
      case MEDIUM:
        playState = Medium.askToSetPlayState(this);
        break;
      case HARD:
        playState = Hard.askToSetPlayState(this);
        break;
      default:
    }
    // Update hasTrump
    if (playState.getPlayMode() != PlayMode.NULL) {
      int j = 0;
      while (!hasTrump[0] && j < this.bot.getHand().size()) {
        if (this.bot.getHand().get(j).getNumber() == Number.JACK
            || (playState.getPlayMode() == PlayMode.SUIT
                && this.bot.getHand().get(j).getColour() == playState.getTrump())) {
          this.hasTrump[0] = true;
        }
      }
    }
    this.hasTrump[1] = this.hasTrump[2] = true;
    // Update partner
    this.setPartner(null);
    // Update opponents
    List<Player> opponents = new ArrayList<Player>();
    for (int i = 0; i < this.getPlayer().size(); i++) {
      opponents.add(this.getPlayer().get(i));
    }
    this.setOpponents(opponents);
    // Update existingTrumps
    switch (playState.getPlayMode()) {
      case GRAND:
        this.setExistingTrumps(4);
        break;
      case SUIT:
        this.setExistingTrumps(11);
        break;
      case NULL:
        this.setExistingTrumps(0);
        break;
      default:
    }

    this.playState = playState;

    return playState;
  }

  @Override
  /**
   * Asks the bot if he wants de declare Rekontra. Returns a boolean representing this decision.
   * 
   * @author fkleinoe
   * @return boolean
   */
  public void askToRekontra() {
    try {
      Thread.sleep(Settings.DELAY);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    boolean announce = false;
    switch (this.bot.getDifficulty()) {
      case EASY:
        announce = Easy.askToRekontra(this);
        break;
      case MEDIUM:
        announce = Medium.askToRekontra(this);
        break;
      case HARD:
        announce = Hard.askToRekontra(this);
        break;
      default:
        announce = false;
    }
    if (announce) {
      logic.announceRekontra();
    }
  }

  @Override
  /**
   * Asks the bot to play a card. Returns the index of the card within the bots hand.
   * 
   * @author fkleinoe
   * @return int
   */
  public int askToPlayCard(int timeToPlay, PlayState playState) {
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
   * Adds a new Card to the trick, played by player. Therefore updates currentTrick, playedCards,
   * cardProbabilities, hasColour, existingTrumps hasTrump.
   * 
   * @author fkleinoe
   * @param card
   * @param player
   */
  public void receivedNewCard(Card card, Player player) {
    // Update currentTrick
    if (this.currentTrick.size() == 3) {
      this.currentTrick.clear();

    }
    this.currentTrick.add(card);

    // Update playedCards
    for (int i = 0; i < this.player.size(); i++) {
      if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
        if (this.playedCards[this.playedCards.length - 1][this.player.get(i).getId()] == null) {
          this.playedCards[this.playedCards.length - 1][this.player.get(i).getId()] = card;
          return;
        } else {
          Card[][] playedCards = new Card[this.playedCards.length + 1][3];
          for (int row = 0; row < this.playedCards.length; row++) {
            for (int column = 0; column < this.playedCards[0].length; column++) {
              if (this.playedCards[row][column] != null) {
                playedCards[row][column] = this.playedCards[row][column];
              }
            }
          }
          playedCards[playedCards.length - 1][this.player.get(i).getId()] = card;
          this.playedCards = playedCards;
          return;
        }
      }
    }
    // Update probability for single card
    int colour = 3 - card.getColour().ordinal();
    int number = 7 - card.getNumber().ordinal();
    for (int playerIndex = 0; playerIndex < this.cardProbability[0].length; playerIndex++) {
      this.cardProbability[colour * 8 + number][playerIndex] = 0;
    }
    // Update hasColour
    if (this.currentTrick.get(0).getNumber() != Number.JACK
        && (this.playState.getPlayMode() == PlayMode.SUIT
            && this.currentTrick.get(0).getColour() != this.playState.getTrump())) {
      if (card.getColour() != this.currentTrick.get(0).getColour()) {
        for (int i = 0; i < this.player.size(); i++) {
          if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
            this.hasColour[3 - this.currentTrick.get(0).getColour().ordinal()][this.player.get(i)
                .getId()] = false;
            // Update colour probabilities
            for (int value = 0; value < 7; value++) {
              this.cardProbability[colour * 8 + value][i] = 0;
            }
          }
        }
      }
    }
    // Update existingTrumps
    if (this.playState.getPlayMode() != PlayMode.NULL) {
      if (card.getNumber() == Number.JACK || (this.playState.getPlayMode() == PlayMode.SUIT
          && card.getColour() == this.playState.getTrump())) {
        this.existingTrumps--;
        int ownTrumps = 0;
        for (int i = 0; i < this.bot.getHand().size(); i++) {
          if (this.bot.getHand().get(i).getNumber() == Number.JACK
              || (this.playState.getPlayMode() == PlayMode.SUIT
                  && this.bot.getHand().get(i).getColour() == this.playState.getTrump())) {
            ownTrumps++;
          }
        }
        if (this.existingTrumps - ownTrumps == 0) {
          this.hasTrump = new boolean[3];
        }
      }
    }
    // Update hasTrump
    if (this.existingTrumps != 0 && (this.currentTrick.get(0).getNumber() == Number.JACK
        || (this.playState.getPlayMode() == PlayMode.SUIT
            && this.getCurrentTrick().get(0).getColour() == this.playState.getTrump()))) {
      if (card.getNumber() != Number.JACK
          && card.getColour() != this.currentTrick.get(0).getColour()) {
        for (int i = 0; i < this.player.size(); i++) {
          if (this.player.get(i).getName().equals(Integer.toString(player.getId()))) {
            this.hasTrump[this.player.get(i).getId()] = false;
            // Update trump probabilities
            int value = 7 - Number.JACK.ordinal();
            for (colour = 0; colour < 4; colour++) {
              this.cardProbability[colour * 8 + value][i] = 0;
            }
            if (this.playState.getPlayMode() == PlayMode.SUIT) {
              colour = 3 - this.playState.getTrump().ordinal();
              for (number = 0; number < 7; number++) {
                this.cardProbability[colour * 8 + number][i] = 0;
              }
            }
          }
        }
      }
    }
  }

  @Override
  /**
   * This method sets the PlayState, if the AI is not declarer. These parameters will be updated:
   * partner, opponents, hasTrump, existingTrumps. Also the if the AI difficulty is hard, the AI is
   * asked if it wants to declare kontra.
   * 
   * @author fkleinoe
   * @param playState
   */
  public void setPlaySettingsAfterAuction(PlayState playState) {
    this.playState = playState;
    // Update partner
    for (int i = 0; i < playState.getGroup().length; i++) {
      if (!playState.getGroup()[i].isDeclarer()
          && !playState.getGroup()[i].getName().equals(this.bot.getName())) {
        for (int j = 0; j < this.player.size(); j++) {
          if (this.player.get(j).getName()
              .equals(Integer.toString(playState.getGroup()[i].getId()))) {
            this.partner = this.player.get(j);
          }
        }
      }
    }
    // Update opponents
    for (int i = 0; i < playState.getGroup().length; i++) {
      if (playState.getGroup()[i].isDeclarer()) {
        for (int j = 0; j < this.player.size(); j++) {
          if (this.player.get(j).getName()
              .equals(Integer.toString(playState.getGroup()[i].getId()))) {
            this.opponents.add(this.player.get(j));
          }
        }
      }
    }
    // Update hasTrump
    if (playState.getPlayMode() != PlayMode.NULL) {
      for (int i = 0; i < this.bot.getHand().size(); i++) {
        if (this.bot.getHand().get(i).getNumber() == Number.JACK
            || (playState.getPlayMode() == PlayMode.SUIT
                && this.bot.getHand().get(i).getColour() == playState.getTrump())) {
          this.hasTrump[0] = true;
        }
      }
    }
    this.hasTrump[1] = this.hasTrump[2] = true;
    // Update existingTrumps
    switch (playState.getPlayMode()) {
      case GRAND:
        this.existingTrumps = 4;
        break;
      case SUIT:
        this.existingTrumps = 11;
        break;
      case NULL:
        this.existingTrumps = 0;
        break;
      default:
    }

    boolean announce = false;
    switch (this.bot.getDifficulty()) {
      case EASY:
        break;
      case MEDIUM:
        break;
      case HARD:
        announce = Hard.decideToPlayKontra(this);
      default:
    }

    if (announce) {
      logic.announceKontra();
    }
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
  public void showScore(List<Player> player) {
    this.playState = null;
    this.singlePlay = null;
    this.maxBet = 0;
    this.bets = new int[3];
    this.playedCards = new Card[1][3];
    this.cardProbability = new double[32][3];
    this.partner = null;
    this.opponents = new ArrayList<Player>();
    this.hasColour = new boolean[4][3];
    this.hasTrump = new boolean[3];
    for (int i = 0; i < hasTrump.length; i++) {
      hasTrump[i] = true;
    }
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
   * Passes the seconds that the bot has left, to play a card. Only important for a human player.
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

  @Override
  /**
   * Only important for the ui.
   * 
   * @author fkleinoe
   */
  public void showPossibleCards(List<Card> cards) {
    // Do nothing
  }

  @Override
  /**
   * Only important for the ui.
   * 
   * @author fkleinoe
   */
  public void showOpen(Player player) {
    // Do nothing.
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Internal Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * This method updated the probability of a single card on a single player.
   * 
   * @author fkleinoe
   * @param probability
   * @param colour
   * @param number
   * @param player
   */
  public void setCardProbability(double probability, int colour, int number, int player) {
    this.cardProbability[colour * 8 + number][player] = probability;
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Setter- and Getter-Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////

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
