package com.company;

import util.Move;

import java.util.List;

public class MoveAnalyser {

    public MoveAnalyser(int dp)
    {
        dataPoints = dp;
    }

    int dataPoints;

    public String[][] getMoveData(List<Move> ms)
    {
        int moves = ms.size();

        String[][] moveData = new String[moves+1][dataPoints];

        moveData[ms.size()][0] = "Mover";
        moveData[ms.size()][1] = "From";
        moveData[ms.size()][2] = "To";
        moveData[ms.size()][3] = "Count";
        moveData[ms.size()][4] = "Distance";
        moveData[ms.size()][5] = "Pass";
        moveData[ms.size()][6] = "Rescue";
        moveData[ms.size()][7] = "Rescue count player 1";
        moveData[ms.size()][8] = "Rescue count player 2";
        int rc1 = 0;
        int rc2 = 0;

        for (int i = 0; i<ms.size(); i++)
        {
            Move m = ms.get(i);

            String mover = Integer.toString(m.mover());
            int f = m.from();
            int t = m.to();
            String from = getGridIndex(f);
            String to = getGridIndex(t);
            String count = Integer.toString(m.count());
            String distance = getDistance(f, t);
            String pass = Boolean.toString(m.isPass());

            moveData[i][0] = mover;
            moveData[i][1] = from;
            moveData[i][2] = to;
            moveData[i][3] = count;
            moveData[i][4] = distance;
            moveData[i][5] = pass;
            if(f == t && f < 10 && f >= 5)
            {
                if(m.mover() == 1) rc1++;
                else if(m.mover() == 2) rc2++;
                moveData[i][6] = "true";
            }
            else moveData[i][6] = "false";
            moveData[i][7] = Integer.toString(rc1);
            moveData[i][8] = Integer.toString(rc2);
        }

        return moveData;
    }



    private String getGridIndex(int x) {
        if(x == 20) return "A3";
        if(x == 21) return "B3";
        if(x == 22) return "C3";
        if(x == 23) return "D3";
        if(x == 24) return "E3";
        if(x == 25) return "F3";
        if(x == 26) return "G3";
        if(x == 27) return "H3";
        if(x == 28) return "I3";
        if(x == 29) return "J3";

        if(x == 10) return "A2";
        if(x == 11) return "B2";
        if(x == 12) return "C2";
        if(x == 13) return "D2";
        if(x == 14) return "E2";
        if(x == 15) return "F2";
        if(x == 16) return "G2";
        if(x == 17) return "H2";
        if(x == 18) return "I2";
        if(x == 19) return "J2";

        if(x == 0) return "A1";
        if(x == 1) return "B1";
        if(x == 2) return "C1";
        if(x == 3) return "D1";
        if(x == 4) return "E1";
        if(x == 5) return "F1";
        if(x == 6) return "G1";
        if(x == 7) return "H1";
        if(x == 8) return "I1";
        if(x == 9) return "J1";
        //NN indicates that a negative entry was given
        return "NN";
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

    private String getDistance(int f, int t) {

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

        return Integer.toString(dis);
    }

    public void print(String[][] md, int iteration)
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
