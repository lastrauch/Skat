package logic;

import interfaces.AiLogic;
import interfaces.InGameInterface;
import interfaces.LogicGui;
import interfaces.LogicNetwork;
import interfaces.NetworkLogic;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is created for every Client, the whole play organization is happening in here.
 * 
 * @author awesch
 * @author sandfisc
 */
public class ClientLogic implements NetworkLogic, AiLogic {

  Player player;
  InGameInterface inGameController; // implemented by Gui or Ai
  LogicNetwork netController;
  LogicGui guiController;
  PlayState playState;
  GameSettings gameSettings;
  List<Card> cards;
  List<Player> group;
  boolean inGame;

  /*------------------------  CONSTRUCTOR  ----------------------------------*/

  /**
   * constructor.
   * 
   * @param player of this clientLogic
   */
  public ClientLogic(Player player) {
    this.player = player;
    this.playState = new PlayState(new Player[4]);
    this.initializeCards();
    this.group = new ArrayList<Player>();
    this.group.add(this.player);
    this.inGame = false;
  }

  /*---------------------  PREPARE PLAY/GAME --------------------------------*/

  /**
   * initializes the cards.
   * 
   * @author awesch
   */
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
        default:
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
          default:
            break;
        }
        // cards are generated in the order of their value

        Card c = new Card(col, nr);
        this.cards.add(c);
      }
    }
  }

  /**
   * updates the lobby.
   */
  @Override
  public void receiveLobby(List<Player> player, GameSettings gs) {
    this.gameSettings = gs;
    this.group = player;

    if (!this.player.isBot() && !this.inGame) {
      this.guiController.updateLobby(gs, this.group);
    }
  }

  /**
   * shuffles the cards after they have been initialized.
   * 
   * @author awesch
   */
  public void shuffleCards() {
    int index;
    Card temp = null;
    for (int i = 0; i < 32; i++) {
      index = (int) (Math.random() * 32);
      temp = this.cards.get(i);
      this.cards.set(i, this.cards.get(index));
      this.cards.set(index, temp);
    }
  }


  /**
   * deals out the cards to every player.
   */
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

  /**
   * sets the gameSettings.
   */
  @Override
  public void receiveGameSettings(GameSettings gs) {
    this.gameSettings = gs;
  }

  /**
   * sets the seaing order, sets the positions, generates playState, starts play if rearhand and
   * initializes inGameController, if not a bot.
   */
  @Override
  public void receiveStartGame() {
    // check to have received the right nr of players
    if (this.group.size() == this.gameSettings.getNrOfPlayers()) {
      // Sort group
      List<Player> temp = new ArrayList<Player>();
      for (int i = 0; i < this.group.size(); i++) {
        temp.add(
            this.group.get(((this.gameSettings.getRandomSeatingIndex()) + i) % this.group.size()));
      }
      this.group = new ArrayList<Player>();
      this.group.addAll(temp);

      // set position
      System.out.println("Bei " + this.player.getName() + " groesse group: " + this.group.size());
      this.group.get(0).setPosition(Position.FOREHAND);
      this.group.get(1).setPosition(Position.MIDDLEHAND);
      this.group.get(2).setPosition(Position.REARHAND);

      if (this.group.size() == 4) {
        this.group.get(3).setPosition(Position.DEALER);
        if (this.player.getName().equals(this.group.get(3).getName())) {
          this.player.setPosition(Position.DEALER);
        }
      }
      // Generate new PlayState
      this.playState = new PlayState(this.getPlayingGroup());

      // set player position
      for (int i = 0; i < this.playState.getGroup().length; i++) {
        // change comparism to id!!! (if implemented in network)
        if (this.playState.getGroup()[i].getName().equals(this.player.getName())) {
          this.player.setPosition(this.playState.getGroup()[i].getPosition());
        }
      }

      // Start Game if Player sits rearhand (rearhand is also the dealer)
      if (this.player.getPosition() == Position.REARHAND) {
        this.startPlay();
      }

      if (!this.player.isBot()) {
        this.guiController.startInGameScreen();
        this.inGameController = this.guiController.getInGameController();
      }
      this.waitFor(2000);
    }
  }

  @Override
  public Player copyPlayer(Player player) {
    return this.player.copyMe();
  }

  /**
   * player receives cards after they were dealt out, the playState and the hand are initialized and
   * the auction is started by the middlehand.
   */
  @Override
  public void receiveCards(List<Card> cards, PlayState ps) {

    this.playState = ps;
    this.player.setHand((ArrayList<Card>) cards);
    this.player.sortHand(this.playState);

    this.inGameController.startPlay(this.player.getHand(), this.player.getPosition());

    // Start auction here
    if (this.player.getPosition() == Position.MIDDLEHAND) {
      // go with first bet
      this.inGameController.openAskForBet(this.playState.getAuction().getPossibleBets()[0]);
      if (this.inGameController.askForBet(this.playState.getAuction().getPossibleBets()[0], null)) {
        this.player.setBet(this.playState.getAuction().getPossibleBets()[0]);
        this.netController.bet(this.playState.getAuction().getPossibleBets()[0],
            this.player.copyMe());
      } else {
        // pass
        this.netController.bet(-1, this.player.copyMe());
      }

    }
  }
  /*----------------- RUN AUCTION -------------------------------------------*/

  /**
   * is called every time someone bets, checks if the auction is over and/or if this player is
   * supposed to bet next.
   * 
   * @author awesch
   */
  public void receiveBet(Player player, int bet) {
    if (!(this.playState.getAuction().getLastOneWhoBet().getName().equals(player.getName())
        && (this.playState.getAuction().getBetValue() == bet))) {

      this.inGameController.receivedNewBet(bet, player);
      this.playState.getAuction().setLastOneWhoBet(player);
      // if auction is still running
      if (!this.checkIfAuctionIsOver(bet)) {
        // Update in auction
        this.playState.getAuction().addToBets(bet);
        if (bet != -1) {
          this.playState.setBetValue(bet);
          this.playState.getAuction().setBetValue(bet);
        }
        int newBet = this.playState.getAuction().calculateNewBet();
        // if it is my turn
        if (this.checkIfItsMyTurnAuction(player, bet)) {
          // if the player goes with the bet
          if (this.player.getBet() == 0) {
            this.inGameController.openAskForBet(newBet);
          } else {
            this.inGameController.openAskForBet(newBet);
          }
          if (this.inGameController.askForBet(newBet, player)) {
            this.netController.bet(newBet, this.player.copyMe());
          } else {
            this.netController.bet(-1, this.player.copyMe());
          }
        }
        this.updateBet(player, bet);
      } else {
        this.updateBet(player, bet);
        this.setAuctionWinner();
        this.checkIfAuctionWinner();
      }
    }
  }

  /**
   * updates the bet everywhere.
   * 
   * @author awesch
   * @param player who bet
   * @param bet which was the last one
   */
  public void updateBet(Player player, int bet) {

    for (Player p : this.playState.getGroup()) {
      if (p.getName().equals(player.getName())) {
        p.setBet(bet);
      }
    }

    if (player.getName().equals(this.player.getName())) {
      this.player.setBet(bet);
    }
  }

  /**
   * checks if the player is supposed to bet next if he sits forehand.
   * 
   * @author awesch
   * @param player who bet last
   * @return
   */
  public boolean checkIfItsMyTurnAuctionForehand(Player player, int bet) {
    if (player.getPosition() == Position.MIDDLEHAND && this.player.getBet() != -1 && bet != -1) {
      return true;
    }
    if (player.getPosition() == Position.REARHAND && this.player.getBet() != -1) {
      return true;
    }
    return false;
  }

  /**
   * checks if the player is supposed to bet next, if he sits middlehand.
   * 
   * @author awesch
   * @param player who bet last
   * @return
   */
  public boolean checkIfItsMyTurnAuctionMiddlehand(Player player, int bet) {
    if (player.getPosition() == Position.FOREHAND && bet != -1 && this.player.getBet() != -1) {
      return true;
    }
    if (player.getPosition() == Position.REARHAND && this.player.getBet() != -1) {
      return true;
    }
    return false;
  }

  /**
   * checks if the player is supposed to bet next, if he sits rearhand.
   * 
   * @author awesch
   * @param player who bet last
   * @return
   */
  public boolean checkIfItsMyTurnAuctionRearHand(Player player, int bet) {
    if (bet == -1) {
      return true;
    }
    if (this.oneOfThePlayersPassedAlready() && player.getPosition() != Position.REARHAND) {
      return true;
    }
    return false;
  }

  /**
   * returns true, if one player passed already.
   * 
   * @author awesch
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
   * checks if this player is supposed to bet next, by combining the specific methods for the
   * different positions.
   * 
   * @author awesch
   * @param player who bet last
   * @return
   */
  public boolean checkIfItsMyTurnAuction(Player player, int bet) {
    if (this.player.getPosition() == Position.FOREHAND
        && this.checkIfItsMyTurnAuctionForehand(player, bet)) {
      return true;
    }
    if (this.player.getPosition() == Position.MIDDLEHAND
        && this.checkIfItsMyTurnAuctionMiddlehand(player, bet)) {
      return true;
    }
    if (this.player.getPosition() == Position.REARHAND
        && this.checkIfItsMyTurnAuctionRearHand(player, bet)) {
      return true;
    }
    return false;
  }

  /**
   * returns true if the auction is over.
   * 
   * @author awesch
   * @param bet which came in last
   */
  public boolean checkIfAuctionIsOver(int bet) {
    if (bet == -1 && this.oneOfThePlayersPassedAlready()) {
      return true;
    }
    return false;
  }

  /**
   * looks for the player who did not pass and sets the auction winner.
   * 
   * @author awesch
   */
  public void setAuctionWinner() {
    for (Player p : this.playState.getGroup()) {
      if (p.getBet() != -1) {
        this.playState.getAuction().setWinner(p);
      }
    }

    // test reset the bets of every player to 0 for the next auction
    for (Player p : this.group) {
      p.setBet(0);
    }
    this.player.setBet(0);
  }

  /**
   * checks if this player is auction winner, sets the declarer and if he is the winner, organizes
   * inGameController communication to take up the skat and set the playSettings.
   * 
   * @author awesch
   */
  public void checkIfAuctionWinner() {
    // change to id if setted by network !!!!!!!
    if (this.playState.getAuction().getWinner().getName().equals(this.player.getName())) {
      // this player is declarer
      this.player.setDeclarer(true);
      // the others not(update after the last auction) ... maybe not important later (if we reset
      // everything after one play)
      for (Player p : this.group) {
        // !!! change to id later
        if (!p.getName().equals(this.player.getName())) {
          p.setDeclarer(false);
        }
      }
      // also in playState
      for (Player p : this.playState.getGroup()) {
        // !!! change to id later
        if (!p.getName().equals(this.player.getName())) {
          p.setDeclarer(false);
        } else {
          p.setDeclarer(true);
        }
      }

      System.out.println("I won the auctiooooon !! (" + this.player.getName() + ")");
      this.inGameController.openTakeUpSkat();
      if (this.inGameController.askToTakeUpSkat()) {
        this.inGameController.openSwitchSkat(this.playState);
        this.playState.getDeclarerStack()
            .addCards(this.inGameController.switchSkat(this.playState));
      }
      this.inGameController.openAuctionWinnerScreen();
      this.playState = this.inGameController.askToSetPlayState(this.playState);

      this.playState.setPlayValue(this.calculatePlayValue());
      this.netController.sendPlayState(this.playState.copyMe());
    } else {

      // set declarer
      for (Player p : this.group) {
        if (this.playState.getAuction().getWinner().getName().equals(p.getName())) {
          p.setDeclarer(true);
        } else {
          p.setDeclarer(false);
        }
      }
      // also in playState
      for (Player p : this.playState.getGroup()) {
        if (this.playState.getAuction().getWinner().getName().equals(p.getName())) {
          p.setDeclarer(true);
        } else {
          p.setDeclarer(false);
        }
      }
      this.player.setDeclarer(false);
    }
  }


  /*---------------------  RUN GAME -----------------------------------------*/

  /**
   * is called after the auction winner set the playSettings, if this player sits forehand, the play
   * is started here, by playing the first card.
   * 
   * @author awesch
   */
  @Override
  public void receivePlayState(PlayState ps) {
    // TODO Auto-generated method stub
    this.playState = ps;

    this.player.sortHand(this.playState);
    this.inGameController.updateHand(this.player.getHand());
    this.inGameController.setPlaySettingsAfterAuction(this.playState);
    if (this.player.getPosition().equals(Position.FOREHAND)) {
      this.playCard(null);
    }
  }

  /**
   * is called by player who starts the play, shuffles and deals out the cards.
   * 
   * @author awesch
   */
  public void startPlay() {
    // First shuffle cards
    this.shuffleCards();
    // secound deal out cards
    this.dealOutCards();
  }

  /**
   * called after one of the opponents announces kontra and asks to recontra, if this player is
   * declarer.
   */
  @Override
  public void receiveKontra() {
    this.playState.setAnnouncedKontra(true);
    if (this.player.isDeclarer()) {
      this.inGameController.askToRekontra();
    }
  }

  /**
   * called after someone announced recontra.
   */
  @Override
  public void receiveRekontra() {
    this.playState.setAnnouncedRekontra(true);
  }

  /**
   * can be called by the inGameController, checks if controller is allowed to announce it.
   */
  @Override
  public void announceKontra() {
    if (this.playState.getTrickNr() == 1 && this.gameSettings.isEnableKontra()
        && !this.player.isDeclarer()) {
      this.netController.sendKontra();
    }
  }

  /**
   * can be called by the inGameController, checks if controller is allowed to announce it.
   */
  @Override
  public void announceRekontra() {
    if (this.player.isDeclarer() && this.gameSettings.isEnableKontra()) {
      this.netController.sendRekontra();
    }
  }

  /**
   * to wait with the ui methods.
   * 
   * @author awesch
   * @param time to wait
   */
  public void waitFor(long time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * asks the ui/AI to play a card and checks if it is possible to play it.
   * 
   * @author awesch
   * @author sandfisc
   * @param firstCard (it depends on the first played card if it is possible to play the following)
   */
  public void playCard(Card firstCard) {
    this.inGameController.itsYourTurn();
    this.waitFor(1000);
    int indexNewCard =
        this.inGameController.askToPlayCard(this.gameSettings.getTimeLimit(), this.playState);
    // because we had some to high results from askToPlayCard
    if (indexNewCard >= this.player.getHand().size()) {
      this.playCard(firstCard);
    } else {
      Card playedCard = this.player.getHand().get(indexNewCard);

      // the first card is null it is allowed to play any card
      if (firstCard == null) {
        // update this players hand
        try {
          this.player.removeCardFromHand(playedCard);
        } catch (LogicException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        this.inGameController.updateHand(this.player.getHand());
        this.netController.sendCardPlayed(playedCard, this.player.copyMe());

      } else {
        // if it is not possible to play the card the gui/AI is asked to play another card
        try {
          if (checkIfCardPossible(playedCard, firstCard, this.playState, this.player)) {
            // update this players hand
            try {
              this.player.removeCardFromHand(playedCard);
              if (!this.player.isBot()) {
                for (Card c : this.player.getHand()) {
                  System.out.println(c.toString());
                }
              }
            } catch (LogicException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            this.inGameController.updateHand(this.player.getHand());
            this.netController.sendCardPlayed(playedCard, this.player.copyMe());

          } else {
            this.showPossibleCards(firstCard);
            this.playCard(firstCard);
          }
        } catch (LogicException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * called, after the controller wanted to play a card, he's not allowed to play.
   * 
   * @param firstCard on current trick
   * @throws LogicException if card not possible
   */
  public void showPossibleCards(Card firstCard) throws LogicException {
    List<Card> cards = new ArrayList<Card>();
    for (Card c : this.player.getHand()) {
      if (checkIfCardPossible(c, firstCard, this.playState, this.player)) {
        cards.add(null);
      } else {
        cards.add(c);
      }
    }
    this.inGameController.showPossibleCards(cards);
  }

  /**
   * adds the points to the players score.
   * 
   * @param points to add
   */
  public void addToGamePoints(int points) {
    this.player.addToGamePoints(points);
  }

  /**
   * called every time a player played a card, saves card, updates the inGameController and checks
   * what happens next.
   */
  @Override
  public void receiveCardPlayed(Player player, Card card) {
    // show update on gui/ai
    this.inGameController.receivedNewCard(card, player);
    // check if open and player is declarer to showOpen
    if (this.playState.isOpen()
        && player.getName().equals(this.playState.getAuction().getWinner().getName())
        && !this.player.isDeclarer()) {
      player.setDeclarer(true);
      this.inGameController.showOpen(player);
    }
    try {
      this.checkWhatHappensNext(player, card);
    } catch (LogicException e) {
      e.printStackTrace();
    }
  }

  /**
   * checks what happens next, by updating the playState, checks if trick/play/game is over and
   * calculates the respective winners, also checks if this player is supposed to play the next
   * card.
   * 
   * @param playedLastCard player
   * @param card he played
   * @throws LogicException if something goes wrong
   */
  public void checkWhatHappensNext(Player playedLastCard, Card card) throws LogicException {

    this.playState.getCurrentTrick().addCard(card, playedLastCard);

    Player trickWinner;
    List<Player> playWinner = new ArrayList<Player>();
    List<Player> gameWinner = new ArrayList<Player>();
    boolean nullBreak;

    // check if trick is over
    if (this.playState.getCurrentTrick().isFull()) {
      // trick is over
      // calculate winner trick
      trickWinner = this.playState.getCurrentTrick().calculateWinner(playState);

      // put cards on winners stack
      if (trickWinner.isDeclarer()) {
        this.playState.getDeclarerStack()
            .addCards(this.playState.getCurrentTrick().getTrickCards());
      } else {
        this.playState.getOpponentsStack()
            .addCards(this.playState.getCurrentTrick().getTrickCards());
      }

      // show winner of trick
      this.waitFor(1000);
      this.inGameController.showWinnerTrick(trickWinner);
      this.waitFor(2000);

      // check if play is over
      nullBreak = (this.playState.getPlayMode() == PlayMode.NULL) && trickWinner.isDeclarer();
      if (this.playState.getTrickNr() == 10 || nullBreak) {

        // calculate winner play
        if (nullBreak) {
          for (Player p : this.group) {
            if (!p.isDeclarer()) {
              playWinner.add(p);
            }
          }
        } else {
          playWinner = Play.calculateWinner(playState);
        }
        for (Player p : playWinner) {
          System.out.print(p.getName() + " ");
        }
        // calculate points
        if (playWinner.get(0).isDeclarer()) {
          // calculate points: declarer won
          this.playState = Play.calculatePoints(this.playState, this.gameSettings, true);
        } else {
          // calculate points: opponents won
          this.playState = Play.calculatePoints(this.playState, this.gameSettings, false);
        }

        // save playPionts from playState in this group
        for (Player pg : this.group) {
          for (Player ps : this.playState.getGroup()) {
            if (pg.getName().equals(ps.getName())) {
              pg.setPlayScore(ps.getPlayScore());
            }
          }
          if (pg.getPosition().equals(Position.DEALER)) {
            pg.addToPlayScore(-1);
          }
          if (pg.getName().equals(this.player.getName())) {
            this.player.setPlayScore(pg.getPlayScore());
          }

        }

        // show winner of play
        // this.inGameController.showWinnerPlay(playWinner[0], playWinner[1]);
        this.inGameController.showScore(this.group);
        this.waitFor(3000);

        // check if the whole game is over
        if (this.gameSettings.getNrOfPlays() == this.playState.getPlayNr()
            || this.checkIfGameOverBierlachs()) {

          // game is over
          // calculate winner game
          gameWinner = Game.calculateWinner(this.group);

          this.waitFor(3000);
          // show winner of game
          this.inGameController.showScore(this.group);
          this.waitFor(3000);

        } else {

          // game is not over
          this.updatePosition();

          // createNewPlay!
          this.playState.resetPlayState();
          this.playState.setGroup(this.getPlayingGroup());
          this.playState.setPlayNr(this.playState.getPlayNr() + 1);

          for (Player p : this.group) {
            if (p.getName().equals(this.player.getName())) {
              this.player.setPosition(p.getPosition());
            }
          }

          // restart inGameController
          if (!this.player.isBot()) {
            this.guiController.startInGameScreen();
            this.inGameController = this.guiController.getInGameController();
          }

          // with start play you deal out cards and in receive cards the auction will start
          if (this.player.getPosition() == Position.FOREHAND) {
            this.startPlay();
          }

        }
      } else {
        // generate new trick
        this.playState.setTrickNr(this.playState.getTrickNr() + 1);
        this.playState.setCurrentTrick(new Trick());

        // if "i am" winner of last trick --> ask inGameController to play new card
        if (this.player.getName().equals(trickWinner.getName())) {
          this.playCard(null);
        }
      }
    } else if (this.checkIfMyTurnTrick(playedLastCard)) {
      this.playCard(this.playState.getCurrentTrick().getFirstCard());
    }
  }

  /**
   * position (forehand, middlehand, rearhand) changes after every play, this method updates them
   * after the respective ones.
   * 
   * @author sandfisc
   */
  public void updatePosition() {
    int pointerForehand = this.searchForehand();

    this.group.get((pointerForehand + 1) % this.group.size()).setPosition(Position.FOREHAND);
    this.group.get((pointerForehand + 2) % this.group.size()).setPosition(Position.MIDDLEHAND);
    this.group.get((pointerForehand + 3) % this.group.size()).setPosition(Position.REARHAND);

    if (this.group.size() == 4) {
      this.group.get((pointerForehand + 4) % this.group.size()).setPosition(Position.DEALER);
    }
  }

  /**
   * to help update position, returns the index where the forehand sits.
   */
  public int searchForehand() {
    for (int i = 0; i < this.group.size(); i++) {
      if (this.group.get(i).getPosition() == Position.FOREHAND) {
        return i;
      }
    }
    return 0;
  }

  /**
   * returns true if the game score is over the bierlachs piont limit.
   */
  public boolean checkIfGameOverBierlachs() {
    for (Player p : this.playState.getGroup()) {
      if (p.getGameScore() >= this.gameSettings.getEndPointsBierlachs()) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns true if the currentTrick is full.
   */
  public boolean checkIfTrickIsFull() {
    if (this.playState.getCurrentTrick().isFull()) {
      this.playState.setCurrentTrick(new Trick());
      return true;
    }
    return false;
  }

  /**
   * returns true if the played card comes from the player with the position, who plays before this
   * player.
   * 
   * @param playedLastCard player
   */
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

  /**
   * returns the three players of this group who are part of the next play.
   * 
   * @author sandfisc
   */
  public Player[] getPlayingGroup() {
    // the playing group consists of forehand, middlehand, rarehand, NOT dealer
    Player[] playingGroup = new Player[3];
    int index = 0;
    for (Player p : this.group) {
      if (p.getPosition() != Position.DEALER) {
        playingGroup[index] = p;
        index++;
      }
    }
    return playingGroup;
  }

  /*--------------  CHECK IF A CARD IS POSSIBLE TO PLAY  ----------------------------------------*/

  /**
   * its is checked if the card can be played by the player depending on his hand, the first Colour
   * of the trick and the PlayMode.
   * 
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @return if card can be played
   * @throws LogicException if card not allowed to play
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
   * submethod of checkIfCardPossible.
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
   * checks if the serving card serves the served card --> checks if both are trump/jack or have the
   * same color, returns true if served.
   * 
   * @author sandfisc
   * @param servingCard new card
   * @param servedCard first card of trick
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
   * @param servingCard new card
   * @param servedCard first card on trick
   * @return if card serves grand
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

  /*-------------------------  CALCULATE PLAY VALUE ----------------------------------------------*/

  /**
   * calculates the the Matadors the hand has to be sorted before! with the (chosen/possible) trump.
   * 
   * @return matador
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
    } else {
      // matadorValue of first card is number
      against = this.player.getHand().get(0).getMatadorValue();
      return against;
    }
    return 0;
  }

  /**
   * calculates the multiplier to calculate the value of a suit or grand game.
   * 
   * @author awesch
   * @return multiplier
   */
  public int calculateMultiplier() {
    int result = 1; // 1 for the game
    result += this.calculateMatador(); // + matadors
    // 1 point for schneider
    if (this.playState.isSchneider()) {
      result++;
      // 1 point for schwarz
      if (this.playState.isSchwarz()) {
        result++;
      }
    }


    // possibilities if the Player plays hand
    if (this.playState.getHandGame()) {
      // 1 point for hand game
      result++;
      // 1 point for schneider announced
      if (this.playState.getSchneiderAnnounced()) {
        result++;
      }
      // 1 point for schwarz announced AND for schneider announced
      if (this.playState.getSchwarzAnnounced()) {
        result += 2;;
      }
      // 1 point for open
      if (this.playState.isOpen()) {
        result += 1;
      }
    }
    return result;
  }

  /**
   * calculates the play value for suit or grand plays.
   * 
   * @author awesch
   * @return play value
   */
  public int calculatePlayValueSuitorGrand() {
    int multiplier = this.calculateMultiplier();
    return this.playState.getBaseValue() * multiplier;
  }

  /**
   * initializes the play value for a null play.
   * 
   * @author awesch
   * @return play value
   */
  public int calculatePlayValueNull() {
    int result = 23;
    if (this.playState.getHandGame()) {
      result = 35;
    }
    if (this.playState.isOpen()) {
      result = 46;
    }
    if (this.playState.getHandGame() && this.playState.isOpen()) {
      result = 59;
    }
    return result;
  }

  /**
   * calculates the play value with the other methods implemented for the special contracts.
   * 
   * @author awesch
   * @return play value
   */
  public int calculatePlayValue() {
    int result = 0;
    if (this.playState.getPlayMode() == PlayMode.NULL) {
      result = this.calculatePlayValueNull();
    } else {
      result = this.calculatePlayValueSuitorGrand();
    }
    return result;
  }


  /*------------------------------  CHAT ---------------------------------------------------------*/
  @Override
  public void receiveChatMessage(Player player, String msg) {
    this.guiController.showReceivedChatMessage(msg, player);
  }

  public void sendChatMessage(String msg) {
    this.netController.sendChatMessage(this.player.copyMe(), msg);
  }

  /*------------------------------ BREAK ---------------------------------------------------------*/
  @Override
  public void receivePlayerDisconnected(Player player) {
    if (this.inGame) {
      this.inGameController.stopGame("player disconnected");
    }
  }

  /*------------------------  SETTER AND GETTER  -------------------------------------------------*/

  public void setGameSetting(GameSettings gs) {
    this.gameSettings = gs;
  }

  public void setPlayState(PlayState ps) {
    this.playState = ps;
  }

  public void setLogicGui(LogicGui lg) {
    this.guiController = lg;
  }

  public void setInGame(boolean inGame) {
    this.inGame = inGame;
  }

  public List<Player> getGroup() {
    return this.group;
  }

  public GameSettings getGameSettings() {
    return this.gameSettings;
  }

  public void setInGameController(InGameInterface inGameController) {
    this.inGameController = inGameController;
  }

  public void setNetworkController(LogicNetwork networkController) {
    this.netController = networkController;
  }

  public Player getPlayer() {
    return this.player;
  }

  public List<Player> getLobby() {
    return this.group;
  }

}
