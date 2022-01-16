import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.awt.Color;

public class Gra {
	
	protected Plansza plansza;
	
	protected Random rand = new Random();
	
	/*protected boolean sprawdzSkok(int x1, int y1, int x2, int y2, Plansza p) {
		
		if((Math.abs(x1-x2) == 2 && Math.abs(y1-y2) == 2) && (p.czyJestPionek((x1+x2)/2, (y1+y2)/2)) ) {
			return true;
		} else if( (Math.abs(x1-x2) == 4 && Math.abs(y1-y2) == 0) && (p.czyJestPionek((x1+x2)/2, (y1+y2)/2)) ) {
			return true;
		} else {
			boolean b1 = false;
			boolean b2 = false;
			boolean b3 = false;
			boolean b4 = false;
			boolean b5 = false;
			boolean b6 = false;
			//Plansza p1 = p.kopiujPlansze();
			//p1.usunPionekNaPlanszy(x1, y1);
			if(x1-4 >= 0 && p.czyJestPionek(x1-2, y1) && !p.czyJestPionek(x1-4, y1)) {
				b1 = sprawdzSkok(x1-4, y1, x2, y2, p);
			}
			if(x1-2 >=0 && y1+2 <= 16 && p.czyJestPionek(x1-1, y1+1) && !p.czyJestPionek(x1-2, y1+2)) {
				b2 = sprawdzSkok(x1-2, y1+2, x2, y2, p);
			}
			if(x1+2 <= 24 && y1+2 <= 16 && p.czyJestPionek(x1+1, y1+1) && !p.czyJestPionek(x1+2, y1+2)) {
				b3 = sprawdzSkok(x1+2, y1+2, x2, y2, p);
			}
			if(x1+4 <= 24 && p.czyJestPionek(x1+2, y1) && !p.czyJestPionek(x1+4, y1)) {
				b4 = sprawdzSkok(x1+4, y1, x2, y2, p);
			}
			if(x1+2 <= 24 && y1-2 >=0 && p.czyJestPionek(x1+1, y1-1) && !p.czyJestPionek(x1+2, y1-2)) {
				b5 = sprawdzSkok(x1+2, y1-2, x2, y2, p);
			}
			if(x1-2 >= 0 && y1-2 >= 0 && p.czyJestPionek(x1-1, y1-1) && !p.czyJestPionek(x1-2, y1-2)) {
				b6 = sprawdzSkok(x1-2, y1-2, x2, y2, p);
			}
			return b1 || b2 || b3 || b4 || b5 || b6;
		}
		
		//return false;
	} */
	
    protected Color stringToColor(String s) {
    	if(s.startsWith("Czerwony")) {
    		return Color.RED;
    	} else if(s.startsWith("Zielony")) {
    		return Color.GREEN;
    	} else if(s.startsWith("Fioletowy")) {
    		return Color.PINK;
    	} else if(s.startsWith("Niebieski")) {
    		return Color.BLUE;
    	} else if(s.startsWith("Brazowy")) {
    		return Color.BLACK;
    	} else if(s.startsWith("Pomaranczowy")) {
    		return Color.ORANGE;
    	}
    	return null;
    }
	
	protected boolean sprawdzSkok(int x1, int y1, int x2, int y2) {
	
	if((Math.abs(x1-x2) == 2 && Math.abs(y1-y2) == 2) && (plansza.czyJestPionek((x1+x2)/2, (y1+y2)/2)) ) {
		return true;
	} else if( (Math.abs(x1-x2) == 4 && Math.abs(y1-y2) == 0) && (plansza.czyJestPionek((x1+x2)/2, (y1+y2)/2)) ) {
		return true;
	} else {
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		boolean b4 = false;
		boolean b5 = false;
		boolean b6 = false;

		if(x1-4 >= 0 && plansza.czyJestPionek(x1-2, y1) && !plansza.czyJestPionek(x1-4, y1)) {
			plansza.ustawTymczasowy(x1-4, y1);
			b1 = sprawdzSkok(x1-4, y1, x2, y2);
			plansza.usunPionekNaPlanszy(x1-4, y1);
		}
		if(x1-2 >=0 && y1+2 <= 16 && plansza.czyJestPionek(x1-1, y1+1) && !plansza.czyJestPionek(x1-2, y1+2)) {
			plansza.ustawTymczasowy(x1-2, y1+2);
			b2 = sprawdzSkok(x1-2, y1+2, x2, y2);
			plansza.usunPionekNaPlanszy(x1-2, y1+2);
		}
		if(x1+2 <= 24 && y1+2 <= 16 && plansza.czyJestPionek(x1+1, y1+1) && !plansza.czyJestPionek(x1+2, y1+2)) {
			plansza.ustawTymczasowy(x1+2, y1+2);
			b3 = sprawdzSkok(x1+2, y1+2, x2, y2);
			plansza.usunPionekNaPlanszy(x1+2, y1+2);
		}
		if(x1+4 <= 24 && plansza.czyJestPionek(x1+2, y1) && !plansza.czyJestPionek(x1+4, y1)) {
			plansza.ustawTymczasowy(x1+4, y1);
			b4 = sprawdzSkok(x1+4, y1, x2, y2);
			plansza.usunPionekNaPlanszy(x1+4, y1);
		}
		if(x1+2 <= 24 && y1-2 >= 0 && plansza.czyJestPionek(x1+1, y1-1) && !plansza.czyJestPionek(x1+2, y1-2)) {  //Tu był błąd, było y2<=0, powinno być y2>=0
			plansza.ustawTymczasowy(x1+2, y1-2);
			b5 = sprawdzSkok(x1+2, y1-2, x2, y2);
			plansza.usunPionekNaPlanszy(x1+2, y1-2);
		}
		if(x1-2 >= 0 && y1-2 >= 0 && plansza.czyJestPionek(x1-1, y1-1) && !plansza.czyJestPionek(x1-2, y1-2)) {
			plansza.ustawTymczasowy(x1-2, y1-2);
			b6 = sprawdzSkok(x1-2, y1-2, x2, y2);
			plansza.usunPionekNaPlanszy(x1-2, y1-2);
		}
		return b1 || b2 || b3 || b4 || b5 || b6;
	}
	
	//return false;
} 
	
	protected boolean sprawdzRuch(int x1, int y1, int x2, int y2, String name) {
		
		//0. Sprawdzamy, czy ruch nie wychodzi z docelowego trójąta
		if(czyDocelowe(x1, y1, name) && !czyDocelowe(x2, y2, name)) {
			return false;
		}
		
		//1. Sprawdzamy, czy gracz zostaje w miejscu 
		if(x1 == x2 && y1 == y2) {
			return true;
		}
		//2. Sprawdzamy, czy ruch jest prostym przesunięciem na sąsiednie pole
		else if( (Math.abs(x1-x2) == 1 && Math.abs(y1-y2) == 1) || (Math.abs(x1-x2) == 2 && Math.abs(y1-y2) == 0) ) {
			return true;
		}
		//3. Sprawdzamy, czy został wykonany poprawny skok
		else if(sprawdzSkok(x1, y1, x2, y2)) {
			return true;
		}
		
		return false;
	}
	
	private boolean czyDocelowe(int x, int y, String name) {
		
		if(name == "Czerwony") {
			int docelowe_x[] = {9, 10, 11, 11, 12, 12, 13, 13, 14, 15};
			int docelowe_y[] = {13, 14, 13, 15, 14, 16, 13, 15, 14, 13};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		} else if(name == "Zielony") {
			int docelowe_x[] = {12, 11, 13, 10, 12, 14, 11, 13, 15, 9};
			int docelowe_y[] = {0, 1, 1, 2, 2, 2, 3, 3, 3, 3};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		} else if(name == "Niebieski") {
			int docelowe_x[] = {18, 19, 20, 20, 21, 21, 22, 22, 23, 24};
			int docelowe_y[] = {4, 5, 4, 6, 5, 7, 4, 6, 5, 4};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		} else if(name == "Fioletowy") {
			int docelowe_x[] = {0, 1, 2, 2, 3, 3, 4, 4, 5, 6};
			int docelowe_y[] = {4, 5, 4, 6, 5, 7, 4, 6, 5, 4};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		} else if(name == "Brazowy") {
			int docelowe_x[] = {18, 19, 20, 20, 21, 21, 22, 22, 23, 24};
			int docelowe_y[] = {12, 11, 10, 12, 9, 11, 10, 12, 11, 12};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		} else if(name == "Pomaranczowy") {
			int docelowe_x[] = {0, 1, 2, 2, 3, 3, 4, 4, 5, 6};
			int docelowe_y[] = {12, 11, 10, 12, 9, 11, 10, 12, 11, 12};
			
			for(int i=0; i<=9; i++) {
				if(x == docelowe_x[i] && y == docelowe_y[i]) {
					return true;
				}
			}
			
		}
		
		
		return false;
	}
	
	protected boolean mockHasWinner() {
		// SPRAWDZAMY, CZY WYGRAŁ GRACZ CZERWONY
		if(    
	            plansza.uzywanePola[12][16].zwrocPionek() != null &&
	            plansza.uzywanePola[12][16].zwrocPionek().getColor().equals(Color.RED))
	          {

	            return true;
	        } 	else if(		//CZY WYGRAŁ ZIELONY
	        		 plansza.uzywanePola[12][0].zwrocPionek() != null &&  
	        		 plansza.uzywanePola[12][0].zwrocPionek().getColor().equals(Color.GREEN)) {
	        		
	        		 return true;
	        } else if(				//CZY WYGRAŁ NIEBIESKI

	                 plansza.uzywanePola[24][4].zwrocPionek() != null &&
	                 plansza.uzywanePola[24][4].zwrocPionek().getColor().equals(Color.BLUE)) {

	        		 return true;
	        } else if(					//CZY WYGRAŁ POMARAŃCZOWY	        			
	        		 plansza.uzywanePola[0][12].zwrocPionek() != null &&  
	        		 plansza.uzywanePola[0][12].zwrocPionek().getColor().equals(Color.ORANGE)) {
	        		
	        		return true;
	        } else if(		//CZY WYGRAŁ BRĄZOWY
	        		
	                 plansza.uzywanePola[24][12].zwrocPionek() != null  &&
	                 plansza.uzywanePola[24][12].zwrocPionek().getColor().equals(Color.BLACK)) {

	        		 return true;
	        } else if(		//CZY WYGRAŁ FIOLETOWY
	        		
	        		 plansza.uzywanePola[0][4].zwrocPionek() != null &&  	        		
	        		 plansza.uzywanePola[0][4].zwrocPionek().getColor().equals(Color.PINK)) {

	        		 return true;
	        }
		
		
		return false;
	}
	
	protected boolean hasWinner() {
		// SPRAWDZAMY, CZY WYGRAŁ GRACZ CZERWONY
		if(     plansza.uzywanePola[9][13].zwrocPionek() != null &&  
	            plansza.uzywanePola[10][14].zwrocPionek() != null &&
	            plansza.uzywanePola[11][13].zwrocPionek() != null &&
	            plansza.uzywanePola[11][15].zwrocPionek() != null &&
	            plansza.uzywanePola[12][14].zwrocPionek() != null &&
	            plansza.uzywanePola[12][16].zwrocPionek() != null &&
	            plansza.uzywanePola[13][13].zwrocPionek() != null &&
	            plansza.uzywanePola[13][15].zwrocPionek() != null &&
	            plansza.uzywanePola[14][14].zwrocPionek() != null &&
	            plansza.uzywanePola[15][13].zwrocPionek() != null &&
	 
				plansza.uzywanePola[9][13].zwrocPionek().getColor().equals(Color.RED) &&  
	            plansza.uzywanePola[10][14].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[11][13].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[11][15].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[12][14].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[12][16].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[13][13].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[13][15].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[14][14].zwrocPionek().getColor().equals(Color.RED) &&
	            plansza.uzywanePola[15][13].zwrocPionek().getColor().equals(Color.RED)) {

	            return true;
	        } 	else if(		//CZY WYGRAŁ ZIELONY
	        		 plansza.uzywanePola[12][0].zwrocPionek() != null &&  
	                 plansza.uzywanePola[11][1].zwrocPionek() != null &&
	                 plansza.uzywanePola[13][1].zwrocPionek() != null &&
	                 plansza.uzywanePola[10][2].zwrocPionek() != null &&
	                 plansza.uzywanePola[12][2].zwrocPionek() != null &&
	                 plansza.uzywanePola[14][2].zwrocPionek() != null &&                     
	                 plansza.uzywanePola[11][3].zwrocPionek() != null &&
	                 plansza.uzywanePola[13][3].zwrocPionek() != null &&
	                 plansza.uzywanePola[15][3].zwrocPionek() != null &&
	                 plansza.uzywanePola[9][3].zwrocPionek() != null &&
	                
	        		 plansza.uzywanePola[12][0].zwrocPionek().getColor().equals(Color.GREEN) &&  
	                 plansza.uzywanePola[11][1].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[13][1].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[10][2].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[12][2].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[14][2].zwrocPionek().getColor().equals(Color.GREEN) &&                     
	                 plansza.uzywanePola[11][3].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[13][3].zwrocPionek().getColor().equals(Color.GREEN) &&
	                 plansza.uzywanePola[15][3].zwrocPionek().getColor().equals(Color.GREEN) && 
	                 plansza.uzywanePola[9][3].zwrocPionek().getColor().equals(Color.GREEN)) {
	        		
	        		 return true;
	        } else if(				//CZY WYGRAŁ NIEBIESKI
	        		 plansza.uzywanePola[18][4].zwrocPionek() != null &&  
	                 plansza.uzywanePola[19][5].zwrocPionek() != null &&
	                 plansza.uzywanePola[20][4].zwrocPionek() != null &&
	                 plansza.uzywanePola[20][6].zwrocPionek() != null &&
	                 plansza.uzywanePola[21][5].zwrocPionek() != null &&
	                 plansza.uzywanePola[21][7].zwrocPionek() != null &&
	                 plansza.uzywanePola[22][4].zwrocPionek() != null &&
	                 plansza.uzywanePola[22][6].zwrocPionek() != null &&
	                 plansza.uzywanePola[23][5].zwrocPionek() != null &&
	                 plansza.uzywanePola[24][4].zwrocPionek() != null &&
	        		 
	        		 plansza.uzywanePola[18][4].zwrocPionek().getColor().equals(Color.BLUE) &&  
	                 plansza.uzywanePola[19][5].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[20][4].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[20][6].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[21][5].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[21][7].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[22][4].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[22][6].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[23][5].zwrocPionek().getColor().equals(Color.BLUE) &&
	                 plansza.uzywanePola[24][4].zwrocPionek().getColor().equals(Color.BLUE)) {

	        		 return true;
	        } else if(					//CZY WYGRAŁ POMARAŃCZOWY	        			
	        		 plansza.uzywanePola[0][12].zwrocPionek() != null &&  
	                 plansza.uzywanePola[1][11].zwrocPionek() != null &&
	                 plansza.uzywanePola[2][10].zwrocPionek() != null &&
	                 plansza.uzywanePola[2][12].zwrocPionek() != null &&
	                 plansza.uzywanePola[3][9].zwrocPionek() != null &&
	                 plansza.uzywanePola[3][11].zwrocPionek() != null &&
	                 plansza.uzywanePola[4][10].zwrocPionek() != null &&
	                 plansza.uzywanePola[4][12].zwrocPionek() != null &&
	                 plansza.uzywanePola[5][11].zwrocPionek() != null &&
	                 plansza.uzywanePola[6][12].zwrocPionek() != null &&
	        		
	        		 plansza.uzywanePola[0][12].zwrocPionek().getColor().equals(Color.ORANGE) &&  
	                 plansza.uzywanePola[1][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[2][10].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[2][12].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[3][9].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[3][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[4][10].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[4][12].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[5][11].zwrocPionek().getColor().equals(Color.ORANGE) &&
	                 plansza.uzywanePola[6][12].zwrocPionek().getColor().equals(Color.ORANGE)) {
	        		
	        		return true;
	        } else if(		//CZY WYGRAŁ BRĄZOWY
	        		
	        		 plansza.uzywanePola[18][12].zwrocPionek() != null && 
	                 plansza.uzywanePola[19][11].zwrocPionek() != null && 
	                 plansza.uzywanePola[20][10].zwrocPionek() != null &&
	                 plansza.uzywanePola[20][12].zwrocPionek() != null  &&
	                 plansza.uzywanePola[21][9].zwrocPionek() != null &&
	                 plansza.uzywanePola[21][11].zwrocPionek() != null &&
	                 plansza.uzywanePola[22][10].zwrocPionek() != null &&
	                 plansza.uzywanePola[22][12].zwrocPionek() != null  &&
	                 plansza.uzywanePola[23][11].zwrocPionek() != null  &&
	                 plansza.uzywanePola[24][12].zwrocPionek() != null  &&
	        		
	        		 plansza.uzywanePola[18][12].zwrocPionek().getColor().equals(Color.BLACK) &&  
	                 plansza.uzywanePola[19][11].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[20][10].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[20][12].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[21][9].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[21][11].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[22][10].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[22][12].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[23][11].zwrocPionek().getColor().equals(Color.BLACK) &&
	                 plansza.uzywanePola[24][12].zwrocPionek().getColor().equals(Color.BLACK)) {

	        		 return true;
	        } else if(		//CZY WYGRAŁ FIOLETOWY
	        		
	        		 plansza.uzywanePola[0][4].zwrocPionek() != null &&  
	                 plansza.uzywanePola[1][5].zwrocPionek() != null &&
	                 plansza.uzywanePola[2][4].zwrocPionek() != null &&
	                 plansza.uzywanePola[2][6].zwrocPionek() != null &&
	                 plansza.uzywanePola[3][5].zwrocPionek() != null &&
	                 plansza.uzywanePola[3][7].zwrocPionek() != null &&
	                 plansza.uzywanePola[4][4].zwrocPionek() != null &&
	                 plansza.uzywanePola[4][6].zwrocPionek() != null &&
	                 plansza.uzywanePola[5][5].zwrocPionek() != null  &&
	                 plansza.uzywanePola[6][4].zwrocPionek() != null  &&
	        		
	        		 plansza.uzywanePola[0][4].zwrocPionek().getColor().equals(Color.PINK) &&  
	                 plansza.uzywanePola[1][5].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[2][4].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[2][6].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[3][5].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[3][7].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[4][4].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[4][6].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[5][5].zwrocPionek().getColor().equals(Color.PINK) &&
	                 plansza.uzywanePola[6][4].zwrocPionek().getColor().equals(Color.PINK)) {

	        		 return true;
	        }
		
		
		return false;
	}
   
}