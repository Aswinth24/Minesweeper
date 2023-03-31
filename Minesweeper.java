import java.util.*;
public class Minesweeper
{
	char[][] board;
	char[][] board1;
	int n;
	 Scanner input=new Scanner(System.in);
	public void start()
	{
	 
      n=input.nextInt();
	  board=new char[n][n];
	  board1=new char[n][n];
	  
	  // initialize Boord
	  for(int i=0;i<n;i++){
		  for(int j=0;j<n;j++){
			  board1[i][j]='?';
			  board[i][j]=' ';
		  }
	  }
	//displayBoard1();
	//displayBoard();
	
	clearScreen();
    displayBoard();
	
	
	int len=input.nextInt();
	 
	 // place BOOM 
	 int i=0;
	 while(i<len)
	  {
		  System.out.print("Enter i : ");
		  int x=(int)(Math.random()*n);
		  System.out.print("Enter j : ");
		  int y=(int)(Math.random()*n);
		  if(board[x][y]!='*')
		     board[x][y]='*';
		  else
		  {
			  //System.out.println("Invalid");
			  continue;
		  }
		  clearScreen();
		  displayBoard();
		  i++;
	  }
	  initializeCounts();
	 input.nextLine();
	 input.nextLine();
	 clearScreen();
	 displayBoard1();
	 while(true)
	 {
		 if(winner()==len)
		 {
			 clearScreen();
			 displayBoard1();
			 System.out.println("\n\t\t\tWinner\n\n");
			 return;
		 }
		 System.out.println("\n\t1)Pop\n\t2)Flag\n\t3)Exit");
		 System.out.print("Enter the option : ");
		 int option=input.nextInt();
		 int x;
		 int y;
		 switch(option)
		 {
			 case 1:
			    System.out.print("Enter i : ");
			    x=input.nextInt();
				System.out.print("Enter j : ");
		        y=input.nextInt();
				
				if(board[x][y]=='*'&&board1[x][y]!='#')
				{
				 System.out.println("\n\t\tGameover");
				 copy();
				 clearScreen();
				 displayBoard1();
				 return ;
			   }
			   check(x,y);
			   input.nextLine();
			   clearScreen();
			   displayBoard1();
			   break;
			 case 2:
			    System.out.print("Enter i : ");
			    x=input.nextInt();
				System.out.print("Enter j : ");
		        y=input.nextInt();
				if(x<0||x>n||y<0||y>=n||board1[x][y]!='?')
				{
				  System.out.println("Invalid option ");
				  continue;
				}
			   board1[x][y]='#';
			   input.nextLine();
			   clearScreen();
			   displayBoard1();
			   break;
			case 3:
			   return;
			 
		 }
	 }
	}
	public int winner()
	{
		int ct=0;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(board1[i][j]=='?'||board1[i][j]=='#')
					ct++;
			}
		}
		return ct;
		
	}
	public void copy()
	{
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(board[i][j]=='*')
					board1[i][j]='*';
	}
	public void initializeCounts()
	{
		for(int i=0;i<n;i++)
	  {
		  
		  for(int j=0;j<n;j++)
		  {
			  int count=0;
			  if(i>0&&board[i-1][j]=='*')//top
				  count++;
			  if(i<n-1&&board[i+1][j]=='*')//bottom
				  count++;
			  if(j<n-1&&board[i][j+1]=='*')//right
				  count++;
			  if(i<n-1&&j<n-1&&board[i+1][j+1]=='*')//bottom right corner
				  count++;
			  if(i>0&&j<n-1&&board[i-1][j+1]=='*')// top right corner
				  count++;
			  if(i>0&&j>0&&board[i-1][j-1]=='*')//top left corner
				  count++;
			 if(i<n-1&&j>0&&board[i+1][j-1]=='*')//bottom left corner
				 count++;
			 if(j>0&&board[i][j-1]=='*')
				count++;
			 if(count>0)
				board[i][j]=(char)(48+count);
		  }
	  }
	  
      clearScreen();
	  System.out.println("\n\t\t Back End ");
	  displayBoard();	  
	}		
	public void check(int i,int j)
	{
		if(i<0||i>=n||j<0||j>=n)
			return;
		if(board[i][j]>='1'&&board[i][j]<'9'&&board1[i][j]=='?')
		{
			board1[i][j]=board[i][j];
			return ;
		}
		else if(board[i][j]!='*'&&board1[i][j]=='?')
		{
			board1[i][j]=' ';
			check(i-1,j-1);
			check(i-1,j);
			check(i-1,j+1);
			check(i,j+1);
			check(i,j+1);
			check(i+1,j-1);
			check(i+1,j);
			check(i+1,j+1);
		}
		return ;
	}
	public void displayBoard()
	{
		System.out.print("\n\t+");
	  for(int i=0;i<n;i++)
		 System.out.print("-------+");
	  //System.out.println();
	  for(int i=0;i<n;i++)
	  {
		  System.out.print("\n\t|");
		  for(int j=0;j<n;j++)
		  {
			if(board[i][j]=='*')
			  System.out.print(String.format("  %-3s |","BOOM"));
		 else
		    System.out.print(String.format("    %-3s|",board[i][j]));
		  }
		  System.out.print("\n\t+");
		 for(int k=0;k<n;k++)
		 System.out.print("-------+");
		  
	  }
	  System.out.println();
	}
	
	
	
	
	
	 public  void displayBoard1()
     {
		System.out.print("\n\t\t  ");
		for(int i=0;i<n;i++)
			 System.out.print(String.format("    %-4d",i));
		 
		 System.out.print("\n\t\t   +");
	  for(int i=0;i<n;i++)
		  System.out.print("-------+");
	 
	  for(int i=0;i<n;i++)
	  {
		  if(i<10)
		   System.out.print("\n\t\t"+i+"  |");
	      else
			  System.out.print("\n\t\t"+i+" |");
		  
		  for(int j=0;j<n;j++)
		  {
			if(board1[i][j]=='*')
			  System.out.print(String.format("  %-3s |","BOOM"));
		    else
			  System.out.print(String.format("    %-3c|",board1[i][j]));
		  }
		  System.out.print("\n\t\t   +");
		 for(int k=0;k<n;k++)
		  System.out.print("-------+");
		  
	  }
	 System.out.println();
    }
	 public void clearScreen()
     {
	   try{
		   Thread.sleep(50);
	      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	   }
	   catch(Exception e)
	   {	   
	   }
     }

}