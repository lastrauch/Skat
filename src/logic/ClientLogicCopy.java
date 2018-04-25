
package logic;

import java.util.ArrayList;
import java.util.List;
import gui.InGameController;
import interfaces.AILogic;
import interfaces.GuiLogic;
import interfaces.InGameInterface;
import interfaces.NetworkLogic;
import javafx.scene.image.Image;
import network.NetworkController;

public class ClientLogicCopy implements NetworkLogic, AILogic {

  Player player;
  InGameInterface inGameController; // implemented by Gui or Ai
  NetworkController netController;
  PlayState playState;


  public ClientLogicCopy(Player player, InGameInterface inGameController,
      NetworkController netController) {
    this.player = player;
    this.inGameController = inGameController;
    this.netController = netController;
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
      if (this.checkIfCardPossible(playedCard, firstCard)) {
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
   * maybe something for the logic gui interface??? created for the auction
   * 
   * @author awesch
   * @param bet
   * @return
   */
  public boolean askForBet(int bet) {
    return this.inGameController.askForBet(bet);
  }

  /**
   * its is checked if the card can be played by the player depending on his hand, the first Colour
   * of the trick and the PlayMode
   * 
   * @param card (the player wants to play)
   * @param firstCard (the first played card in the current trick)
   * @param player (who wants to play the card)
   * @return if card can be played
   * @throws LogicException
   * @author sandfisc
   */
  public boolean checkIfCardPossible(Card card, Card firstCard) throws LogicException {
    if (this.playState.getPlayMode() == PlayMode.SUIT) {
      return this.checkIfCardPossibleColour(card, firstCard);
    } else if (this.playState.getPlayMode() == PlayMode.GRAND) {
      return this.checkIfCardPossibleGrand(card, firstCard);
    } else if (this.playState.getPlayMode() == PlayMode.NULL) {
      return this.checkIfCardPossibleNull(card, firstCard);
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
  public boolean checkIfCardPossibleColour(Card card, Card firstCard) {

    // check if card serves first played card
    if (this.checkIfServedColour(card, firstCard)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < this.player.getHand().size(); i++) {
      if (this.checkIfServedColour(this.player.getHand().get(i), firstCard)) {
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
  public boolean checkIfServedColour(Card servingCard, Card servedCard) {

    if (servedCard.getColour() == this.playState.getTrump()
        || servedCard.getNumber() == Number.JACK) {
      // first card is trump
      if (servingCard.getColour() == this.playState.getTrump()
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
  public boolean checkIfCardPossibleGrand(Card card, Card firstCard) {

    // check if card serves first played card
    if (this.checkIfServedColour(card, firstCard)) {
      return true;
    }

    // check if the player has a card which would serve the first card
    for (int i = 0; i < this.player.getHand().size(); i++) {
      if (this.checkIfServedGrand(this.player.getHand().get(i), firstCard)) {
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
  public boolean checkIfServedGrand(Card servingCard, Card servedCard) {

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
  public boolean checkIfCardPossibleNull(Card card, Card firstCard) {

    if (card.getColour() == firstCard.getColour()) {
      return true;

    } else {
      for (int i = 0; i < this.player.getHand().size(); i++) {
        if (this.player.getHand().get(i).getColour() == firstCard.getColour()) {
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

  // public Player searchPlayer(Player player) {
  // for(int i = 0; i < )
  // }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveConnectionRequestAsnwer(boolean)
   */
  @Override
  public void receiveConnectionRequestAsnwer(boolean accepted) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveLobby(java.util.List, logic.GameSettings)
   */
  @Override
  public void receiveLobby(List<Player> player, GameSettings gs) {
    // TODO Auto-generated method stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveGameSettings(logic.GameSettings)
   */
  @Override
  public void receiveGameSettings(GameSettings gs) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveChatMessage(logic.Player, java.lang.String)
   */
  @Override
  public void receiveChatMessage(Player player, String msg) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveStartGame()
   */
  @Override
  public void receiveStartGame() {
    // TODO Auto-generated method stub

  }

  public Player searchPlayer(Player player) {
    for (Player p : this.playState.getGroup()) {
      if (p.equals(player)) {
        return p;
      }
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveBet(logic.Player, int)
   */
  @Override
  public void receiveBet(Player player, int bet) {
    // if auction is still running
    if (!this.checkIfAuctionIsOver(bet)) {
      int newBet = this.calculateNewBet(bet);
      this.updateBet(player, bet);
      // if it is my turn
      if (this.checkIfItsMyTurnAuction(player)) {
        // if the player goes with the bet
        if (this.inGameController.askForBet(newBet)) {
          this.netController.bet(newBet);
        } else {
          this.netController.bet(-1);
        }
      }
    } else {
      this.updateBet(player, bet);
      this.setAuctionWinner();
      this.checkIfAuctionWinner();
    }

  }

  // possible bets.. if the last two bets were the same nr, you go one bet higher
  // exceptions: the first two Players passed --> still 18, the last Player passed.. then still the
  // current betValue
  public int calculateNewBet(int currentBet) {
    int lastBet = this.playState.getAuction().getBetValue();
    int lastBetIndex = this.playState.getAuction().getIndexOfBetValue();

    if (lastBet == currentBet) {
      return this.playState.getAuction().getPossibleBets()[lastBetIndex + 1];
    }
    return lastBet;
  }

  public void updateBet(Player player, int bet) {
    if (bet != -1) {
      this.playState.setBetValue(bet);
    }
    this.searchPlayer(player).setBet(bet);
  }

  public boolean checkIfItsMyTurnAuctionForehand(Player player) {
    if (player.getPosition() == Position.MIDDLEHAND && this.player.getBet() != -1) {
      return true;
    }
    if (player.getPosition() == Position.REARHAND && this.player.getBet() != -1) {
      return true;
    }
    return false;
  }

  public boolean checkIfItsMyTurnAuctionMiddlehand(Player player) {
    if (this.player.getBet() != -1 && player.getPosition() != Position.MIDDLEHAND) {
      return true;
    }
    return false;
  }

  public boolean checkIfItsMyTurnAuctionRearHand(Player player) {
    if (this.oneOfThePlayersPassedAlready() && player.getPosition() != Position.REARHAND) {
      return true;
    }
    return false;
  }

  public boolean oneOfThePlayersPassedAlready() {
    for (Player p : this.playState.getGroup()) {
      if (p.getBet() == -1) {
        return true;
      }
    }
    return false;
  }

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

  // Auction is over, if the last person passed and someone else passed as well
  public boolean checkIfAuctionIsOver(int bet) {
    if (bet == -1 && this.oneOfThePlayersPassedAlready()) {
      return true;
    }
    return false;
  }

  public void setAuctionWinner() {
    for (Player p : this.playState.getGroup()) {
      if (p.getBet() != -1) {
        this.playState.getAuction().setWinner(p);
      }
    }
  }

  public void checkIfAuctionWinner() {
    if (this.playState.getAuction().getWinner().equals(this.player)) {
      this.inGameController.setPlaySettings(this.playState);
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
  public void receiveCards(List<Card> cards) {
    // TODO Auto-generated method stub
    this.player.setHand((ArrayList<Card>) cards);
    this.inGameController.updateHand(this.player.getHand());
  }



}
