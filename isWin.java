import java.awt.*;
import java.io.PrintWriter;

public class isWin{

    public Color color;
    public Plansza plansza;

    public void isWin(Plansza Plansza, Color color) {
        if( plansza.uzywanePola[9][13].zwrocPionek().getColor().equals(Color.RED) &&  
            plansza.uzywanePola[10][14].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[11][13].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[11][15].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[12][14].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[12][16].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[13][13].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[13][15].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[14][14].zwrocPionek().getColor().equals(Color.RED) &&
            plansza.uzywanePola[15][13].zwrocPionek().getColor().equals(Color.RED)){

                //Wygrana gracza czerwonego


        }else if(plansza.uzywanePola[12][0].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&  
                 plansza.uzywanePola[11][1].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[13][1].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[10][2].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[12][2].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[14][2].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&                     plansza.uzywanePola[9][3].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[11][3].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[13][3].zwrocPionek().getColor().equals(new Color(0, 153, 0)) &&
                 plansza.uzywanePola[15][3].zwrocPionek().getColor().equals(new Color(0, 153, 0))){

                //Wygrana gracza zielonego
                output.println("MESSAGE wygrana gracza zielonego");


        }else if(plansza.uzywanePola[18][4].zwrocPionek().getColor().equals(Color.BLUE) &&  
                 plansza.uzywanePola[19][5].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[20][4].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[20][6].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[21][5].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[21][7].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[22][4].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[22][6].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[23][5].zwrocPionek().getColor().equals(Color.BLUE) &&
                 plansza.uzywanePola[24][4].zwrocPionek().getColor().equals(Color.BLUE)){

                    //Wygrana gracza niebieskiego
                    output.println("MESSAGE wygrana gracza niebieskiego");


        }else if(plansza.uzywanePola[0][12].zwrocPionek().getColor().equals(Color.ORANGE) &&  
                 plansza.uzywanePola[1][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[2][10].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[2][12].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[3][9].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[3][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[4][10].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[4][12].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[5][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
                 plansza.uzywanePola[6][12].zwrocPionek().getColor().equals(Color.ORANGE)){

           //Wygrana gracza pomarańczowego aka żółtego
           output.println("MESSAGE wygrana gracza pomarańczowego");


        }else if(plansza.uzywanePola[18][12].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&  
                 plansza.uzywanePola[19][11].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[20][10].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[20][12].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[21][9].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[21][11].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[22][10].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[22][12].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[23][11].zwrocPionek().getColor().equals(new Color(102, 53, 0)) &&
                 plansza.uzywanePola[24][12].zwrocPionek().getColor().equals(new Color(102, 53, 0))){

           //Wygrana gracza brazowego
           output.println("MESSAGE wygrana gracza brazowego");

           
        }else if(plansza.uzywanePola[0][4].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&  
                 plansza.uzywanePola[1][5].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[2][4].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[2][6].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[3][5].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[3][7].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[4][4].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[4][6].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[5][5].zwrocPionek().getColor().equals(new Color(102, 0, 153)) &&
                 plansza.uzywanePola[6][4].zwrocPionek().getColor().equals(new Color(102, 0, 153))){

           //Wygrana gracza fioletowego
           output.println("MESSAGE wygrana gracza fioletowego");


        }
    }
    
}