
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

public class ClientLogic implements NetworkLogic, AILogic {

  Player player;
  InGameInterface inGameController; // implemented by Gui or Ai
  NetworkController netController;
  PlayState playState;
  GameSettings gameSettings;
  Game game; // we need this for calcutlating the winner --> maybe in playstate

  public ClientLogic(Player player, GameSettings gameSettings, InGameInterface inGameController,
      NetworkController netController) {
    this.player = player;
    this.gameSettings = gameSettings;
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

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.NetworkLogic#receiveBet(logic.Player, int)
   */
  @Override
  public void receiveBet(Player player, int bet) {
    // TODO Auto-generated method stub

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

  public void checkIfTrickIsOver() {
    if (this.playState.getCurrentTrick().isFull()) {
      // calculate winner trick

      // check if play is over
      if (this.playState.getTrickNr() == 10) {
        // calculate winner play

        if (this.gameSettings.getNrOfPlays() == this.playState.getPlayNr()) {// check if the whole
                                                                             // game is over
          // game is over
          // calculate winner game
          Player winner = this.game.calculateWinner(this.playState);
          // show winner on gui
        } else {
          // game is not over
          this.playState.setPlayNr(this.playState.getPlayNr() + 1);
          // createNewPlay!!

          // start auction if "i am" middlehand
          if (this.player.getPosition() == Position.MIDDLEHAND) {
            this.inGameController.askForBet(18);
          }
        }
      } else {
        // generate new trick
        // if "i am" winner of last trick --> ask inGameController to play new card
      }
    } // if its my turn.. #checkifblatrick .. play card
  }

  public boolean checkifTrickIsFull() {
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



}
