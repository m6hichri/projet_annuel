package com.annuel.project.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category = "Player";

    //Player Name
    private String Player;

    //Team Name
    private String Team;

    //Games Played
    private String GP;

    //Minutes Per Game
    private String MPG;

    //Points Per Game
    private String PPG;

    //Field Goals Made
    private String FGM;

    // Field Goals Attempted
    private String FGA;

    //Field Goal Percentage
    private String FG;

    //Three Pointers Made
    private String ThreePM;

    // Three Pointers Attempted
    private String ThreePA;

    // Three Pointers Percentage
    private String ThreeP;

    //Free Throws Made
    private String FTM;

    // Free Throws Attempted
    private String FTA;

    //Free Throw Percentage
    private String FT;

}
