package com.company;

import game.Game;
import util.*;
import util.model.Model;
import utils.RandomAI;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    int NUM_TRIALS;
    public static int dataPoints = 9;
    boolean DEBUG = true;

    ArrayList<String[][]> moveDataList = new ArrayList<>();
    ArrayList<String[]> statList = new ArrayList<>();
    public Tester(int NUM_TRIALS)
    {
        this.NUM_TRIALS = NUM_TRIALS;
        // TODO: 28-10-2020 Add AI and Rule parameters
        test();
    }

    private void test()
    {
        final Game senet = GameLoader.loadGameFromName("Senet.lud");
        final Trial trial = new Trial(senet);
        final Context context = new Context(senet, trial);
        final List<AI> ais = new ArrayList<AI>();
        MoveAnalyser moveAnalyser = new MoveAnalyser(dataPoints);
        StatCounter statCounter = new StatCounter();
        ais.add(null);

        for (int p = 1; p <= senet.players().count(); ++p) {
            ais.add(new RandomAI());
        }

        for (int i = 0; i < NUM_TRIALS; ++i) {
            senet.start(context);

            for (int p = 1; p <= senet.players().count(); ++p) {
                ais.get(p).initAI(senet, p);
            }

            final Model model = context.model();

            while (!trial.over()) {
                model.startNewStep(context, ais, 1.0);
            }

            final double[] ranking = trial.ranking();
            List<Move> moves = trial.moves();



            String[][] moveData = moveAnalyser.getMoveData(moves);
            String[] statistics = statCounter.getStats(moveData);
            moveDataList.add(moveData);
            statList.add(statistics);
            if(DEBUG == true)
            {
                moveAnalyser.print(moveData, i);
                statCounter.print(statistics, i);
            }


            for (int p = 1; p <= senet.players().count(); ++p) {
                System.out.println("Agent " + context.state().playerToAgent(p) + " achieved rank: " + ranking[p]);
            }
        }
    }

    public void exportDataFrames()
    {

    }
}
