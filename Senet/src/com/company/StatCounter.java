package com.company;

public class StatCounter {
    
    int statCount = 5;
    public float[] getStats(int[][] moveData)
    {
        float[] list = new float[statCount];

        // TODO: 28-10-2020

        list[0] = getAverageDistance(moveData);
        list[1] = getSwaps(moveData);
        list[2] = getSwapDistance(moveData);
        list[3] = list[2]/list[1];
        list[4] = getWinningPlayer(moveData);
        
        return list;
    }

    private int getWinningPlayer(int[][] moveData) {
        for(int i = moveData.length-1; i>=0; i--)
        {
            if(moveData[i][7] == 7)
            {
                System.out.println("Player 1 won!");
                return 1;
            }
            else if(moveData[i][8] == 7)
            {
                System.out.println("Player 2 won!");

                return 2;
            }
        }
        return 0;
    }

    private int getSwapDistance(int[][] distances) {
        int total = 0;
        for(int i = 0; i < distances.length; i++)
        {
            int x = distances[i][4];

            //negative distances are swaps
            if(x<0)
            {
                total -= x;
//                System.out.println(total + " total swap distance");
            }

        }
        // TODO: 29-10-2020 returns total 
        return total;
    }

    private int getSwaps(int[][] distances) {
        int total = 0;
        for(int i = 0; i < distances.length; i++)
        {
            int x = distances[i][4];

            //negative distances are swaps
            if(x<0)
            {
                total ++;
//                System.out.println(total + " total swaps");
            }

        }
        return total;
    }

    // TODO: 29-10-2020 Average distance does not take double moves into account 
    private float getAverageDistance(int[][] distances) {
        int total = 0;
        for(int i = 0; i < distances.length; i++)
        {
            int x = distances[i][4];

            //only incorporate forward moves
            if(x>0&&distances[i][0]>0)
            {
                total += x;
                //System.out.println(total + " total distance, incremented by " + x);
            }

        }
        float avg = (float)(total)/(float)(distances.length);
        System.out.println("total " + total + ", average " + avg + ", length " + distances.length);

        return avg;
    }

    public void print(float[] list, int game)
    {
        System.out.println();
        System.out.println("Statistics of game " + game);
        for(int i = 0; i<list.length; i++)
        {
            System.out.print(list[i] + " | ");
        }
        System.out.println();
    }
}
