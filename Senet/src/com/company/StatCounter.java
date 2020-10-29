package com.company;

public class StatCounter {
    
    int statCount = 4;
    public String[] getStats(String[][] moveData)
    {
        String[] list = new String[statCount];
        // TODO: 28-10-2020  
        list[0] = getAverageDistance(moveData[4]);
        list[1] = getSwaps(moveData[4]);
        list[2] = getSwapDistance(moveData[4]);
        list[3] = getWinningPlayer(moveData);
        
        return list;
    }

    private String getWinningPlayer(String[][] moveData) {
        for(int i = moveData[0].length-1; i>=0; i--)
        {
            if(Integer.getInteger(moveData[7][i]) == 7)
            {
                return "1";
            }
            else if(Integer.getInteger(moveData[8][i]) == 7)
            {
                return "2";
            }
        }
        return "0";
    }

    private String getSwapDistance(String[] distances) {
        int total = 0;
        for(String x:distances)
        {
            int y = Integer.getInteger(x);
            //negative distances are swaps
            if(y<0)
            {
                total -= y;
            }
        }
        return Integer.toString(total);
    }

    private String getSwaps(String[] distances) {
        int total = 0;
        for(String x:distances)
        {
            int y = Integer.getInteger(x);
            //negative distances are swaps
            if(y<0)
            {
                total ++;
            }
        }
        return Integer.toString(total);
    }

    private String getAverageDistance(String[] distances) {
        int total = 0;
        for(String x:distances)
        {
            System.out.println(x);
            if(!x.isEmpty())
            {
                int y = Integer.getInteger(x);
                //only incorporate forward moves
                if(y>0)
                {
                    total += y;
                }
            }

        }
        return Integer.toString(total);
    }

    public void print(String[] list, int game)
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
