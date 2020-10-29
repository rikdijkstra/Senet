package com.company;

import game.Game;
import util.*;
import util.locations.FullLocation;
import util.model.Model;
import utils.RandomAI;
import java.util.ArrayList;
import java.util.List;

public class Main {
// For testing:
//    Be able to run the game, with certain settings
//    Create a method that runs a number of iterations as fast as possible and export data.
//    Possibly use multiple threads, research risk of damaging cpu
//    Visualise data
//    Ludii’s player.utils.loading.GameLoader class provides static helper methods that may be used to programmatically load games. The simplest such method only takes a single argument; a String representing the name of a game. This argument should always include a .lud extension, and at least the filename of the game to load. Note that this can only be used to load games that are built into the Ludii.jar file, and not for loading games from external .lud files. It may be called as follows:
//    final String[] allGameNames = FileHandling.listGames();

    public static int NUM_TRIALS = 1;

    public static void main(String[] args)
    {
        Tester tester = new Tester(NUM_TRIALS);
    }
}