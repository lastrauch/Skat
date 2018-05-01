package logic;

import java.util.ArrayList;
import java.util.List;
import gui.ImplementsLogicGui;
import gui.InGameController;
import interfaces.AILogic;
import interfaces.GuiLogic;
import interfaces.InGameInterface;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import interfaces.NetworkLogic;
import javafx.scene.image.Image;
import network.NetworkController;

public class ClientLogic implements NetworkLogic, AILogic {

  Player player;
  InGameInterface inGameController; // implemented by Gui or Ai
  LogicNetwork netController;
  LogicGui guiController;
  PlayState playState;
  GameSettings gameSettings;
  Game game; // we need this for calcutlating the winner --> maybe in playstate
  List<Card> cards;
  List<Player> group;

  public ClientLogic(Player player) {
    // System.out.println("created ClientLogic for Player " + player.getName());
    this.player = player;
    this.initializeCards();
  }

  public void setLogicGui(LogicGui lg) {
    this.guiController = lg;
  }

  public List<Player> getGroup() {
    return this.group;
  }

  public GameSettings getGameSettings() {
    return this.gameSettings;
  }

  public void initializeCards() {
    cards = new ArrayList<Card>();
    for (int i = 1; i <= 4; i++) {
      Colour col = null;
      switch (i) {
        case 1:
          col = Colour.DIAMONDS;
          break;
        case 2:
          col = Colour.HEARTS;
          break;
        case 3:
          col = Colour.SPADES;
          break;
        case 4:
          col = Colour.CLUBS;
          break;
      }
      for (int j = 1; j <= 8; j++) {
        Number nr = null;
        switch (j) {
          case 1:
            nr = Number.SEVEN;
            break;
          case 2:
            nr = Number.EIGHT;
            break;
          case 3:
            nr = Number.NINE;
            break;
          case 4:
            nr = Number.JACK;
            break;
          case 5:
            nr = Number.QUEEN;
            break;
          case 6:
            nr = Number.KING;
            break;
          case 7:
            nr = Number.TEN;
            break;
          case 8:
            nr = Number.ASS;
            break;
        }
        // cards are generated in the order of their value

        Card c = new Card(col, nr);
        this.cards.add(c);
      }
    }
  }



  /**
   * asks the gui/AI to play a card and checks if it is possible to play it
   * 
   * @param firstCard (it depends on the first played card if it is possible to play the following)
   * @return
   */
  public Card playCard(Card firstCard) {
    Card playedCard = this.player.getHand().get(this.inGameController.askToPlayCard());

    // the first card is null it is allowed to play any card
    if (firstCard.equals(null)) {
      return playedCard;
    }

    // if it is not possible to play the card the gui/AI is asked to play another card
    try {
      if (checkIfCardPossible(playedCard, firstCard, this.playState, this.player)) {
        return playedCard;
      } else {
        return this.playCard(firstCard);
      }
    } catch (LogicException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  /**
   * its is checked if the card can be played by the player depending on his hand, the first Colour
   * of the trick and the PlayMode
   * 
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @return if card can be played
   * @throws LogicException
   * @author sandfisc
   */
  public static boolean checkIfCardPossible(Card card, Card firstCard, PlayState playState,
      Player player) throws LogicException {
    if (playState.getPlayMode() == PlayMode.SUIT) {
      return checkIfCardPossibleColour(card, firstCard, playState, player);
    } else if (playState.getPlayMode() == PlayMode.GRAND) {
      return checkIfCardPossibleGrand(card, firstCard, player, playState);
    } else if (playState.getPlayMode() == PlayMode.NULL) {
      return checkIfCardPossibleNull(card, firstCard, player);
    }
    return false;
  }

  /**
   * submethod of checkIfCardPossible
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Colour
   */
  public static boolean checkIfCardPossibleColour(Card card, Card firstCard, PlayState playState,
      Player player) {

    // check if card serves first played card
    if (checkIfServedColour(card, firstCard, playState)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < player.getHand().size(); i++) {
      if (checkIfServedColour(player.getHand().get(i), firstCard, playState)) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if the serving card serves the served card --> checks is both are trump/jack or have the
   * same color.
   * 
   * @author sandfisc
   * @param servingCard
   * @param servedCard
   * @return
   */
  public static boolean checkIfServedColour(Card servingCard, Card servedCard,
      PlayState playState) {

    if (servedCard.getColour() == playState.getTrump() || servedCard.getNumber() == Number.JACK) {
      // first card is trump
      if (servingCard.getColour() == playState.getTrump()
          || servingCard.getNumber() == Number.JACK) {
        return true;
      }
    } else {
      // first card is not trump
      if (servingCard.getColour() == servedCard.getColour()
          && servingCard.getNumber() != Number.JACK) {
        return true;
      }
    }
    return false;
  }

  /**
   * submethod of checkIfCardPossible.
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Grand
   */
  public static boolean checkIfCardPossibleGrand(Card card, Card firstCard, Player player,
      PlayState playState) {

    // check if card serves first played card
    if (checkIfServedColour(card, firstCard, playState)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < player.getHand().size(); i++) {
      if (checkIfServedGrand(player.getHand().get(i), firstCard)) {
        return false;
      }
    }
    return true;
  }

  /**
   * checks if the serving card serves the served card --> checks is both are jack or have the same
   * color.
   * 
   * @author sandfisc
   * @param servingCard
   * @param servedCard
   * @return
   */
  public static boolean checkIfServedGrand(Card servingCard, Card servedCard) {

    // both cards are jack
    if (servedCard.getNumber() == Number.JACK && servingCard.getNumber() == Number.JACK) {
      return true;
    }

    // both cards are no jack
    if (servedCard.getNumber() != Number.JACK && servingCard.getNumber() != Number.JACK) {
      if (servedCard.getColour() == servingCard.getColour()) {
        return true;
      }
    }
    return false;
  }

  /**
   * submethod of checkIfCardPossible.
   * 
   * @author sandfisc
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card is possible in PlayMode Null or NullOuvert
   */
  public static boolean checkIfCardPossibleNull(Card card, Card firstCard, Player player) {

    if (card.getColour() == firstCard.getColour()) {
      return true;

    } else {
      for (int i = 0; i < player.getHand().size(); i++) {
        if (player.getHand().get(i).getColour() == firstCard.getColour()) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * calculates the the Matadors the hand has to be sorted before! with the (chosen/possible) trump
   * 
   * @return
   * @author awesch
   */
  public int calculateMatador() {
    // seperated to give a better overview and to have the possibility to give "with i" / "against
    // i"
    int with = 0;
    int against = 0;
    // play with(if first card is the clubs jack)
    if (this.player.getHand().get(0).getMatadorValue() == 0) {
      // go through the hand until the row of trumps stops
      for (int i = 0; i < this.player.getHand().size(); i++) {
        if (this.player.getHand().get(i).getMatadorValue() == i) {
          with++;
        } else {
          return with;
        }
      }
    }
    // play against(if first card is not clubs jack)
    else {
      // matadorValue of first card is number
      against = this.player.getHand().get(0).getMatadorValue();
      return against;
    }
    return 0;
  }

  /**
   * calculates the multiplier to calculate the value of a suit or grand game
   * 
   * @author awesch
   * @param ps
   * @return
   */

  public int calculateMultiplier(PlayState ps) {
    int result = 1; // 1 for the game
    result += this.calculateMatador(); // + matadors
    // 1 point for schneider
    if (ps.isSchneider()) {
      result++;
      // 1 point for schwarz
      if (ps.isSchwarz()) {
        result++;
      }
    }


    // possibilities if the Player plays hand
    if (ps.getHandGame()) {
      // 1 point for hand game
      result++;
      // 1 point for schneider announced
      if (ps.getSchneiderAnnounced()) {
        result++;
      }
      // 1 point for schwarz announced AND for schneider announced
      if (ps.getSchwarzAnnounced()) {
        result += 2;;
      }
      // 1 point for open
      if (ps.isOpen()) {
        result += 1;
      }
    }

    return result;
  }

  /**
   * calculates the play value for suit or grand plays
   * 
   * @author awesch
   * @param ps
   * @return
   */
  public int calculatePlayValueSuitorGrand(PlayState ps) {
    int multiplier = this.calculateMultiplier(ps);
    return ps.getBaseValue() * multiplier;
  }

  /**
   * initializes the play value for a null play
   * 
   * @author awesch
   * @param ps
   * @return
   */
  public int calculatePlayValueNull(PlayState ps) {
    int result = 23;
    if (ps.getHandGame()) {
      result = 35;
    }
    if (ps.isOpen()) {
      result = 46;
    }
    if (ps.getHandGame() && ps.isOpen()) {
      result = 59;
    }
    return result;
  }

  /**
   * calculates the play value with the other methods implemented for the special contracts
   * 
   * @author awesch
   * @param ps
   * @return
   */
  public int calculatePlayValue(PlayState ps) {
    int result = 0;
    if (ps.getPlayMode() == PlayMode.NULL) {
      result = this.calculatePlayValueNull(ps);
    } else {
      result = this.calculatePlayValueSuitorGrand(ps);
    }
    return result;
  }

  public void addToGamePoints(int points) {
    this.player.addToGamePoints(points);
  }

  // /**
  // * returns the given player in the playstate group array
  // *
  // * @author awesch
  // * @param player
  // * @return
  // */
  // public Player searchPlayer(Player player) {
  // for (Player p : this.playState.getGroup()) {
  // if (p.equals(player)) {
  // return p;
  // }
  // }
  // return null;
  // }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveLobby(java.util.List, logic.GameSettings)
   */
  @Override
  public void receiveLobby(List<Player> player, GameSettings gs) {
    this.gameSettings = gs;
    this.group = player;

    // Achtung!!!! Methode noch nicht implementiert
    // this.guiController.updateLobby(gs, group);
  }

  public void startPlay() {
    // First shuffle cards
    Tools.shuffleCards(this.cards);
    // secound deal out cards
    this.dealOutCards();
    // Play.dealOutCards(Tools.getPlayingGroup(this.playState.getGroup()), cards, this.playState);
    // this.netController.sendPlayState(playState); // hands are saved in playState
    // if (this.inGameController == null) {
    // this.inGameController = this.guiController.startInGameScreen();
    // }

    this.inGameController.startPlay(this.player.getHand(), this.player.getPosition());

  }

  public void dealOutCards() {
    // idea: deal out as in the original game, just because we want it intern
    // needed : position forehand, players of the game, how many players?,

    // forehand is the position 0 of group array
    ArrayList<Card> handF = new ArrayList<Card>();
    ArrayList<Card> handM = new ArrayList<Card>();
    ArrayList<Card> handR = new ArrayList<Card>();
    ArrayList<ArrayList<Card>> crew = new ArrayList<ArrayList<Card>>();
    crew.add(handF);
    crew.add(handM);
    crew.add(handR);
    Card[] skat = new Card[2];
    int counter = 0; // points on first card (next to deal out)

    // deal out first 9 cards (3 each)
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(this.cards.get(counter));
        counter++;
      }
    }

    // deal out skat
    for (int i = 0; i < 2; i++) {
      skat[i] = this.cards.get(counter);
      counter++;
    }

    // deal out 4 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        crew.get(i).add(this.cards.get(counter));
        counter++;
      }
    }

    // deal out 3 cards each
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        crew.get(i).add(this.cards.get(counter));
        counter++;
      }
    }

    this.playState.setSkat(skat);
    for (Player p : this.playState.getGroup()) {
      Position pos = p.getPosition();
      switch (pos) {
        case FOREHAND:
          p.setHand(handF);
          this.netController.dealCards(p, handF, this.playState);
          break;
        case MIDDLEHAND:
          p.setHand(handM);
          this.netController.dealCards(p, handM, this.playState);
          break;
        case REARHAND:
          p.setHand(handR);
          this.netController.dealCards(p, handR, this.playState);
          break;
        case DEALER:
          break;
        default:
          break;
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveGameSettings(logic.GameSettings)
   */
  @Override
  public void receiveGameSettings(GameSettings gs) {
    this.gameSettings = gs;

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveChatMessage(logic.Player, java.lang.String)
   */
  @Override
  public void receiveChatMessage(Player player, String msg) {

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveStartGame()
   */
  @Override
  public void receiveStartGame() {
    // check to have received the right nr of players
    if (this.group.size() == this.gameSettings.getNrOfPlayers()) {
      // random number points on the one in the list to be the forehand
      Player[] group = new Player[this.group.size()];
      for (int i = 0; i < this.group.size(); i++) {
        group[i] =
            this.group.get((this.gameSettings.getRandomSeatingIndex() + i) % this.group.size());
      }
      this.playState = new PlayState(group);


      //instead gui should open the ingameScreen in startPlay
//      // TODO Auto-generated method stub
//      if (this.inGameController == null) {
//        this.guiController.startInGameScreen();
//
//        InGameInterface igf = new InGameController();
//        this.inGameController = igf;
//        System.out.println(this.inGameController);
//      }
      

      // set position
      System.out.println(
          "Bei " + this.player.getName() + " groesse group: " + this.playState.getGroup().length);
      this.playState.getGroup()[0].setPosition(Position.FOREHAND);
      this.playState.getGroup()[1].setPosition(Position.MIDDLEHAND);
      this.playState.getGroup()[2].setPosition(Position.REARHAND);
      if (this.playState.getGroup().length == 4) {
        this.playState.getGroup()[2].setPosition(Position.DEALER);
      }

      // set player position
      for (int i = 0; i < this.playState.getGroup().length; i++) {
        // change comparism to id!!! (if implemented in network)
        if (this.playState.getGroup()[i].getName().equals(this.player.getName())) {
          this.player.setPosition(this.playState.getGroup()[i].getPosition());
        }
      }

      // Start Game if Player sits forehand
      if (this.player.getPosition() == Position.FOREHAND) {
        this.startPlay();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveBet(logic.Player, int)
   */
  @Override
  /**
   * works with a received bet
   * 
   * @author awesch
   * @param player
   * @param bet
   */
  public void receiveBet(Player player, int bet) {
    // if auction is still running
    if (!this.checkIfAuctionIsOver(bet)) {
      int newBet = this.calculateNewBet(bet);
      // if it is my turn
      if (this.checkIfItsMyTurnAuction(player)) {
        // if the player goes with the bet
        if (this.inGameController.askForBet(newBet)) {
          this.netController.bet(newBet, this.player);
        } else {
          this.netController.bet(-1, this.player);
        }
      }
      this.updateBet(player, bet);
    } else {
      this.updateBet(player, bet);
      this.setAuctionWinner();
      this.checkIfAuctionWinner();
    }

  }

  /**
   * @author awesch
   * @param currentBet
   * @return
   */
  public int calculateNewBet(int currentBet) {
    int lastBet = this.playState.getAuction().getBetValue();
    int lastBetIndex = this.playState.getAuction().getIndexOfBetValue();

    if (lastBet == currentBet) {
      return this.playState.getAuction().getPossibleBets()[lastBetIndex + 1];
    }
    return lastBet;
  }

  /**
   * @author awesch
   * @param player
   * @param bet
   */
  public void updateBet(Player player, int bet) {
    if (bet != -1) {
      this.playState.setBetValue(bet);
    }
    // change to ID later !!!!
    for (Player p : this.group) {
      if (p.getName().equals(player.getName())) {
        p.setBet(bet);
      }
    }

    // change to id if network sets them!!
    if (player.getName().equals(this.player.getName())) {
      this.player.setBet(bet);
    }
  }

  /**
   * @author awesch
   * @param player
   * @return
   */
  public boolean checkIfItsMyTurnAuctionForehand(Player player) {
    if (player.getPosition() == Position.MIDDLEHAND && this.player.getBet() != -1) {
      return true;
    }
    if (player.getPosition() == Position.REARHAND && this.player.getBet() != -1) {
      return true;
    }
    return false;
  }

  /**
   * @author awesch
   * @param player
   * @return
   */
  public boolean checkIfItsMyTurnAuctionMiddlehand(Player player) {
    if (this.player.getBet() != -1 && player.getPosition() != Position.MIDDLEHAND) {
      return true;
    }
    return false;
  }

  /**
   * @author awesch
   * @param player
   * @return
   */
  public boolean checkIfItsMyTurnAuctionRearHand(Player player) {
    if (this.oneOfThePlayersPassedAlready() && player.getPosition() != Position.REARHAND) {
      return true;
    }
    return false;
  }

  /**
   * @author awesch
   * @return
   */
  public boolean oneOfThePlayersPassedAlready() {
    for (Player p : this.playState.getGroup()) {
      if (p.getBet() == -1) {
        return true;
      }
    }
    return false;
  }

  /**
   * @author awesch
   * @param player
   * @return
   */
  public boolean checkIfItsMyTurnAuction(Player player) {
    if (this.player.getPosition() == Position.FOREHAND
        && this.checkIfItsMyTurnAuctionForehand(player)) {
      return true;
    }
    if (this.player.getPosition() == Position.MIDDLEHAND
        && this.checkIfItsMyTurnAuctionMiddlehand(player)) {
      return true;
    }
    if (this.player.getPosition() == Position.REARHAND
        && this.checkIfItsMyTurnAuctionRearHand(player)) {
      return true;
    }
    return false;
  }

  /**
   * @author awesch
   * @param bet
   * @return
   */
  public boolean checkIfAuctionIsOver(int bet) {
    if (bet == -1 && this.oneOfThePlayersPassedAlready()) {
      return true;
    }
    return false;
  }

  /**
   * @author awesch
   */
  public void setAuctionWinner() {
    for (Player p : this.playState.getGroup()) {
      if (p.getBet() != -1) {
        this.playState.getAuction().setWinner(p);
      }
    }
  }

  /**
   * @author awesch
   */
  public void checkIfAuctionWinner() {
    // change to id if setted by network !!!!!!!
    if (this.playState.getAuction().getWinner().getName().equals(this.player.getName())) {
      this.inGameController.askToTakeUpSkat(this.playState);
      this.inGameController.setPlaySettings(this.playState);
      this.netController.sendPlayState(this.playState);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receivePlayState(logic.PlayState)
   */
  @Override
  public void receivePlayState(PlayState ps) {
    // TODO Auto-generated method stub
    this.playState = ps;
    this.inGameController.setPlaySettings(ps);

    // damn did not see that its already written something like this ..
    // // check if we start a new play
    // if(this.checkIfAuctionIsOver(18)) {
    //
    // // get cards for gui
    // this.player.setHand(Tools.searchPlayer(this.player, this.playState.getGroup()).getHand());
    //
    // // start new game on gui
    // this.inGameController.startPlay(this.player.getHand(), this.player.getPosition());
    //
    // // evt. start new auction
    // if(this.checkIfItsMyTurnAuction(this.player)) {
    // this.inGameController.askForBet(18);
    // }
    // }

    // yes.. BUT we should start the play here right??

    if (this.player.getPosition().equals(Position.FOREHAND)) {
      this.netController.sendCardPlayed(this.playCard(null));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveCardPlayed(logic.Player, logic.Card)
   */
  @Override
  public void receiveCardPlayed(Player player, Card card) {
    // TODO Auto-generated method stub
    // update current trick
    this.playState.getCurrentTrick().addCard(card);

    // update players hand
    try {
      player.removeCardFromHand(card);
    } catch (LogicException e) {
      e.printStackTrace();
    }
    // show update on gui/ai
    this.inGameController.updateTrick(this.playState.getCurrentTrick().getTrickCards());

    try {
      this.checkWhatHappensNext(player);
    } catch (LogicException e) {
      e.printStackTrace();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveYourTurn()
   */
  @Override
  public void receiveYourTurn() {
    // TODO Auto-generated method stub
    Card playedCard = this.playCard(this.playState.getCurrentTrick().getFirstCard());
    // send played card
    this.netController.sendCardPlayed(playedCard);
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receivePlayerDisconnected(logic.Player)
   */
  @Override
  public void receivePlayerDisconnected(Player player) {
    // TODO Auto-generated method stub
    this.inGameController.stopGame("player disconnected");
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveCards(java.util.List)
   */
  @Override
  public void receiveCards(List<Card> cards, PlayState ps) {
    // TODO Auto-generated method stub
    this.playState = ps;
    this.player.setHand((ArrayList<Card>) cards);
    this.player.sortHand(this.playState);

    System.out.println("print hand from" + this.player.getName() + ":");
    for (Card c : this.player.getHand()) {
      System.out.println(c.getColour() + " " + c.getNumber());
    }
    this.inGameController.startPlay(this.player.getHand(), this.player.getPosition());

    // i think
    // this has
    // to be done
    // here???
    // this.inGameController.updateHand(this.player.getHand());

    if (this.player.getPosition() == Position.MIDDLEHAND) {
      // go with first bet
      if (this.inGameController.askForBet(this.playState.getAuction().getPossibleBets()[0])) {
        this.netController.bet(this.playState.getAuction().getPossibleBets()[0], this.player);
      } else {
        // pass
        this.netController.bet(-1, this.player);
      }

    }
  }


  public void checkWhatHappensNext(Player playedLastCard) throws LogicException {

    Player trickWinner;
    Player[] playWinner;
    Player gameWinner;

    Thread t = new Thread(); // waits after telling gui/ai what is to do

    // check if trick is over
    if (this.playState.getCurrentTrick().isFull()) {
      // trick is over

      // calculate winner trick
      trickWinner = this.playState.getCurrentTrick().calculateWinner(playState);

      // put cards on winners stack
      if (trickWinner.IsDeclarer()) {
        this.playState.getDeclarerStack()
            .addCards(this.playState.getCurrentTrick().getTrickCards());
      } else {
        this.playState.getOpponentsStack()
            .addCards(this.playState.getCurrentTrick().getTrickCards());
      }

      // show winner of trick
      this.inGameController.showWinnerTrick(trickWinner);
      try {
        t.wait(3000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      // check if play is over
      if (this.playState.getTrickNr() == 10) {
        // calculate winner play
        playWinner = Play.calculateWinner(playState);

        // calculate points
        if (playWinner[0].IsDeclarer()) {
          // calculate points: declarer won
          Play.calculatePoints(playState, gameSettings, true);
        } else {
          // calculate points: opponents won
          Play.calculatePoints(playState, gameSettings, false);
        }
        // show winner of play
        this.inGameController.showWinnerPlay(playWinner[0], playWinner[1]);
        try {
          t.wait(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        // check if the whole game is over
        if (this.gameSettings.getNrOfPlays() == this.playState.getPlayNr()) {

          // game is over
          // calculate winner game
          gameWinner = Game.calculateWinner(this.playState);
          // show winner of game
          this.inGameController.showWinnerGame(gameWinner);
          try {
            t.wait(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        } else {
          // game is not over
          // createNewPlay!
          this.playState.resetPlayState();
          this.playState.setPlayNr(this.playState.getPlayNr() + 1);

          // update position
          Tools.updatePosition(this.playState.getGroup());

          // start auction if "i am" middlehand
          if (this.player.getPosition() == Position.MIDDLEHAND) {
            this.inGameController.askForBet(18);
          }
        }
      } else {
        // generate new trick
        this.playState.setTrickNr(this.playState.getTrickNr() + 1);
        this.playState.setCurrentTrick(new Trick());

        // if "i am" winner of last trick --> ask inGameController to play new card
        if (this.player.equals(trickWinner)) {
          this.inGameController.askToPlayCard();
        }
      }
    }
    // trick is not over
    // check if it is "my" turn
    if (this.checkIfMyTurnTrick(playedLastCard)) {
      this.inGameController.askToPlayCard();
    }
  }

  public boolean checkIfTrickIsFull() {
    if (this.playState.getCurrentTrick().isFull()) {

      this.playState.setCurrentTrick(new Trick(this.playState));
    }

    return false;
  }

  public boolean checkIfMyTurnTrick(Player playedLastCard) {
    if (this.player.getPosition() == Position.FOREHAND
        && playedLastCard.getPosition() == Position.REARHAND) {
      return true;
    } else if (this.player.getPosition() == Position.MIDDLEHAND
        && playedLastCard.getPosition() == Position.FOREHAND) {
      return true;
    } else if (this.player.getPosition() == Position.REARHAND
        && playedLastCard.getPosition() == Position.MIDDLEHAND) {
      return true;
    } else {
      return false;
    }
  }

  public void setNetworkController(LogicNetwork networkController) {
    this.netController = networkController;
  }

  @Override
  public Player copyPlayer(Player player) {
    // TODO Auto-generated method stub
    return null;
  }

  public void setInGameController(InGameInterface inGameController) {
    this.inGameController = inGameController;
  }

  @Override
  public void announceKontra() {
    if (this.playState.getTrickNr() == 0) {
      this.netController.sendKontra();
    }

  }


}
