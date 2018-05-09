package network.messages;

/**
 * Different Message types that exist.
 * 
 * @author fkleinoe
 */
public enum MessageType {
  CLIENT_DISCONNECT,
  CARD_PLAYED,
  BET,
  CHAT_MESSAGE,
  GAME_SETTINGS,
  PLAY_STATE,
  DEALT_CARDS,
  CONNECTION_REQUEST,
  CONNECTION_ANSWER,
  START_GAME,
  LOBBY,
  KONTRA,
  REKONTRA;
}
