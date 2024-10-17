import javax.swing.JFrame;

public class MenuTest
{
   public static void main(String[] args)
   { 
      MenuFrame menuFrame = new MenuFrame(); 
      menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      menuFrame.setSize(500, 200);//linha de código transportada para MenuFram.java
      menuFrame.setVisible(true);//linha de código transportada para MenuFram.java
   } 
} 

