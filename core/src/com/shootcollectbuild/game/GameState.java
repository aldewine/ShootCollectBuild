package com.shootcollectbuild.game;

public class GameState {
    private static String gameState;
    private static final String RUNNING = "RUNNING";
    private static final String PAUSED = "PAUSED";

    public static String getGameState() {
        return gameState;
    }

    public static void setGameState(String gameState) {
        gameState = gameState;
    }

    public static void startGame(){
        gameState = RUNNING;
    }

    public static void pauseGame(){
        gameState = PAUSED;
    }

    public static boolean isGamePaused(){
        if(gameState.equals(PAUSED)){
            return true;
        }
        return false;
    }
}
