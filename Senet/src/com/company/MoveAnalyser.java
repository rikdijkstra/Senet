package com.company;

import util.Move;

import java.util.List;

public class MoveAnalyser {

    public MoveAnalyser(int dp)
    {
        dataPoints = dp;
    }

    int dataPoints;

    public int[][] getMoveData(List<Move> ms)
    {
        int moves = ms.size();
        int nulls = 0;
//        for (Move m: ms) {
//            if(m.mover() == 0)
//            {
//                nulls++;
//            }
//        }
        int[][] moveData = new int[moves][dataPoints];
        int size = ms.size()-nulls;
//        moveData[size][0] = "Mover";
//        moveData[size][1] = "From";
//        moveData[size][2] = "To";
//        moveData[size][3] = "Count";
//        moveData[size][4] = "Distance";
//        moveData[size][5] = "Pass";
//        moveData[size][6] = "Rescue";
//        moveData[size][7] = "Rescue count player 1";
//        moveData[size][8] = "Rescue count player 2";
        int rc1 = 0;
        int rc2 = 0;

        for (int i = 0; i<ms.size(); i++)
        {
            Move m = ms.get(i);

//            String mover = Integer.toString(m.mover());
            int f = m.from();
            int t = m.to();
            int from = getGridIndex(f);
            int to = getGridIndex(t);
            String count = Integer.toString(m.count());
            int distance = getDistance(f, t);
            int pass = 0;
            if(m.isPass()) pass = 1;
            moveData[i][0] = m.mover();
            moveData[i][1] = from;
            moveData[i][2] = to;
            moveData[i][3] = m.count();
            moveData[i][4] = distance;
            moveData[i][5] = pass;
            //rescue = 1, no rescue = 0
            if(f == t && f < 10 && f >= 5)
            {
                if(m.mover() == 1) rc1++;
                else if(m.mover() == 2) rc2++;
                moveData[i][6] = 1;
            }
            else moveData[i][6] = 0;
            moveData[i][7] = rc1;
            moveData[i][8] = rc2;
        }

        return moveData;
    }



    private int getGridIndex(int x) {
        if(x == 20) return 1;//"A3";
        if(x == 21) return 2;//"B3";
        if(x == 22) return 3;//"C3";
        if(x == 23) return 4;//"D3";
        if(x == 24) return 5;//"E3";
        if(x == 25) return 6;//"F3";
        if(x == 26) return 7;//"G3";
        if(x == 27) return 8;//"H3";
        if(x == 28) return 9;//"I3";
        if(x == 29) return 10;//"J3";

        if(x == 10) return 20;//"A2";
        if(x == 11) return 19;//"B2";
        if(x == 12) return 18;//"C2";
        if(x == 13) return 17;//"D2";
        if(x == 14) return 16;//"E2";
        if(x == 15) return 15;//"F2";
        if(x == 16) return 14;//"G2";
        if(x == 17) return 13;//"H2";
        if(x == 18) return 12;//"I2";
        if(x == 19) return 11;//"J2";

        if(x == 0) return 21;//"A1";
        if(x == 1) return 22;//"B1";
        if(x == 2) return 23;//"C1";
        if(x == 3) return 24;//"D1";
        if(x == 4) return 25;//"E1";
        if(x == 5) return 26;//"F1";
        if(x == 6) return 27;//"G1";
        if(x == 7) return 28;//"H1";
        if(x == 8) return 29;//"I1";
        if(x == 9) return 30;//"J1";
        //NN indicates that a negative entry was given
        return 0;
    }

    private boolean row1(int x)
    {
        if(x>=20) return true;
        return false;
    }
    private boolean row2(int x)
    {
        if(x>=10 && x<20) return true;
        return false;
    }
    private boolean row3(int x)
    {
        if(x<10 && x>=0) return true;
        return false;
    }

    private int getDistance(int f, int t) {

        int dis = 0;
        if( row1(f) && row1(t))
        {
            dis = t - f;
        }
        else if( row1(f) && row2(t))
        {
            dis = 30 - f + 19 - t;
        }
        else if( row2(f) && row2(t))
        {
            dis = f - t;
        }
        else if( row2(f) && row3(t))
        {
            dis = f - 9 + t;
        }
        else if( row3(f) && row3(t))
        {
            dis = t - f;
        }

        // account for backward moves

        else if( row3(f) && row2(t))
        {
            dis = -t -(f - 9);
        }

        else if( row2(f) && row1(t))
        {
            dis = -(20 - f) - (29 - t);
        }

        return dis;
    }

    public void print(int[][] md, int iteration)
    {
        System.out.println();
        System.out.println("Printing move data of game " + iteration);
        for(int i = 0; i<md.length; i++)
        {
            for(int j = 0; j<md[0].length-(dataPoints-1); j+=dataPoints)
            {
                System.out.print("Player " + md[i][j]);
                for(int k = 1; k<dataPoints; k++)
                {
                    System.out.print(" | " + md[i][j+k]);
                }
                System.out.println();
            }
        }

        System.out.println();
        System.out.println();
    }

}
