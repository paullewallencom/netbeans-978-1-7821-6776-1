/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package refactor.factory;

/**
 *
 * @author david
 */
public class GameLevel {

    public static GameLevel levelWithDifficulty(int difficulty) {
        return new GameLevel(difficulty);
    }
    private int difficulty;
    
    private GameLevel(int difficulty) {
        this.difficulty = difficulty;
    }
}
