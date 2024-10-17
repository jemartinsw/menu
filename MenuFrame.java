import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuFrame extends JFrame //declara classe q tem msm nome do arquivo e está herdando JFrame
{// inicio do bloco da classe
   private final Color[] colorValues = 
      {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};   

   private final JRadioButtonMenuItem[] colorItems; // (color menu items) 
   private final JRadioButtonMenuItem[] fonts; // font menu items
   private final JRadioButtonMenuItem[] sizes; // size menu items

   private final JCheckBoxMenuItem[] styleItems; // font style menu items

   private final JLabel displayJLabel; // displays sample text

   private final ButtonGroup fontButtonGroup; // manages font menu items
   private final ButtonGroup colorButtonGroup; // manages color menu items
   private final ButtonGroup sizeButtonGroup; // manages size menu items
   
   private final int [] fontSizes = {12, 24, 48, 72};

   public MenuFrame()//declara método construtor que precisa ser exportavel para que outras classes possam acessa-lo
   {//
      super("Using JMenus");     

      JMenu fileMenu = new JMenu("File"); // declara menu. create file menu
      fileMenu.setMnemonic('F'); // set mnemonic to F (define mnemonic: atalho de teclado alt+F)

      // create About... menu item
      JMenuItem aboutItem = new JMenuItem("About...");// cria o item de menu que fica dentro do menu
      aboutItem.setMnemonic('A'); // set mnemonic to A. depoi sd ecriado é adicionado ao filemenu
      fileMenu.add(aboutItem); // add about item to file menu
      aboutItem.addActionListener(
         new ActionListener() // anonymous inner class. O escutador de ação é adicionado.
         {  
            // display message dialog when user selects About...
            @Override//substitui o método actionperformed
            public void actionPerformed(ActionEvent event)
            {
               JOptionPane.showMessageDialog(MenuFrame.this,
                  "This is an example\nof using menus",
                  "About", JOptionPane.PLAIN_MESSAGE);// o painel de opções é invocado para exibir a janela de dialogo. contrabarra/n quebra linha. 
            } 
         } 
      ); 
 
      JMenuItem exitItem = new JMenuItem("Exit"); // create exit item
      exitItem.setMnemonic('x'); // set mnemonic to x
      fileMenu.add(exitItem); // add exit item to file menu
      exitItem.addActionListener(
         new ActionListener() // anonymous inner class
         {  
            // terminate application when user clicks exitItem
            @Override
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0); // exit application. invoca o metodo de saida do sistema
            } 
         }
      ); 

      JMenuBar bar = new JMenuBar(); // create menu bar
      setJMenuBar(bar); // add menu bar to application. setjmenu é o metodo que define  o menu jmenubar
      bar.add(fileMenu); // add file menu to menu bar

      JMenu formatMenu = new JMenu("Format"); // create format menu
      formatMenu.setMnemonic('r'); // set mnemonic to r

      // array listing string colors
      String[] colors = {"Black", "Blue", "Red", "Green"};

      JMenu colorMenu = new JMenu("Color"); // create color menu
      colorMenu.setMnemonic('C'); // set mnemonic to C

      // create radio button menu items for colors
      colorItems = new JRadioButtonMenuItem[colors.length];
      colorButtonGroup = new ButtonGroup(); // manages colors
      ItemHandler itemHandler = new ItemHandler(); // handler for colors. Manipulador de item

      // create color radio button menu items
      for (int count = 0; count < colors.length; count++) //primeira ação é sobre o inicio, a segunda é enquanto a . terceir aação soma de um em um. 
      {
         colorItems[count] = 
            new JRadioButtonMenuItem(colors[count]); // create item
         colorMenu.add(colorItems[count]); // add item to color menu
         colorButtonGroup.add(colorItems[count]); // add to group
         colorItems[count].addActionListener(itemHandler);
      }

      colorItems[0].setSelected(true); // select first Color item. metodo que seleciona primeiro vetor

      formatMenu.add(colorMenu); // add color menu to format menu. adiciona menu color dentro do menu format
      formatMenu.addSeparator(); // add separator in menu. adicionar linha de separação dos menus

      // array listing font names
      String[] fontNames = {"Serif", "Monospaced", "SansSerif"};//declara variavel fonnames que eh uma matriz de string. 
      JMenu fontMenu = new JMenu("Font"); // create font menu. cria o menu de fonte
      fontMenu.setMnemonic('n'); // set mnemonic to. define mnemonico do menu fonte

      // create radio button menu items for font names
      fonts = new JRadioButtonMenuItem[fontNames.length];// define matris de botoes de menu
      fontButtonGroup = new ButtonGroup(); // manages font names. criado para desmarcar uma opção ao selecionar outra.

      // create Font radio button menu items
      for (int count = 0; count < fonts.length; count++) //
      {
         fonts[count] = new JRadioButtonMenuItem(fontNames[count]);
         fontMenu.add(fonts[count]); // add font to font menu
         fontButtonGroup.add(fonts[count]); // add to button group
         fonts[count].addActionListener(itemHandler); // add handler
      } 

      fonts[0].setSelected(true); // select first Font menu item. 
      fontMenu.addSeparator(); // add separator bar to font menu. coloca separador no menu fonte

      String[] styleNames = {"Bold", "Italic"}; // names of styles. 
      styleItems = new JCheckBoxMenuItem[styleNames.length];//deifne qantidade de vetores no objeto no formato de caixa
      StyleHandler styleHandler = new StyleHandler(); // style handler

      // create style checkbox menu items
      for (int count = 0; count < styleNames.length; count++) 
      {
         styleItems[count] = 
            new JCheckBoxMenuItem(styleNames[count]); // for style
         fontMenu.add(styleItems[count]); // add to font menu
         styleItems[count].addItemListener(styleHandler); // handler
      }

      fontMenu.addSeparator(); // add separator bar to font menu. coloca separador no menu fonte

      sizes = new JRadioButtonMenuItem[fontSizes.length];//define qantidade de vetores no objeto no formato de caixa
      sizeButtonGroup = new ButtonGroup();

      // create style checkbox menu items
      for (int count = 0; count < fontSizes.length; count++) 
      {
         sizes[count] = 
            new JRadioButtonMenuItem(String.valueOf(fontSizes[count])); // for style
         fontMenu.add(sizes[count]); // add to font menu
         sizeButtonGroup.add(sizes[count]);
         sizes[count].addActionListener(itemHandler); // handler
      }

      sizes[3].setSelected(true);

      formatMenu.add(fontMenu); // add Font menu to Format menu
      bar.add(formatMenu); // add Format menu to menu bar
     
      // set up label to display text
      displayJLabel = new JLabel("Sample Text", SwingConstants.CENTER);//swingconstant.center é o alinhamento do texto.
      displayJLabel.setForeground(colorValues[0]);//edfine cor do texto
      displayJLabel.setFont(new Font("Serif", Font.PLAIN, 72));//plain significa que nao tem estilo nenhum

      getContentPane().setBackground(Color.CYAN); // set background
      add(displayJLabel, BorderLayout.CENTER); // add displayJLabel. borderlayout é a definição do layout do texto central na tela.
   } // end MenuFrame constructor

   // inner class to handle action events from menu items
   private class ItemHandler implements ActionListener //handler muda cor quando o usuario clica. actionlistener é o contrutora da classe qnd usa implements. 
   {
      // process color and font selections
      @Override//sobrescreve o actionperformed
      public void actionPerformed(ActionEvent event)
      {
         // process color selection
         // for (int count = 0; count < colorItems.length; count++)
         // {
         //    if (colorItems[count].isSelected()) //identifica se o item estiver seleiconado (coloritens)
         //    {
         //       displayJLabel.setForeground(colorValues[count]);//definido cnforme matriz de cor criada anterioemente
         //       break;
         //    } 
         // } 
         definirCor();
         // process font selection
         // for (int count = 0; count < fonts.length; count++)//lê as fontes
         // {
         //    if (event.getSource() == fonts[count]) 
         //    {
         //       displayJLabel.setFont(
         //          new Font(fonts[count].getText(), style, 72));
         //    }
         // }
         definirFonte();
         // for (int count = 0; count < sizes.length; count++)//lê as fontes
         // {
         //    if (sizes[count].isSelected()) 
         //    {
         //       displayJLabel.setFont(
         //          new Font(displayJLabel.getFont().getName(), style, fontSizes[count]));
         //    }
         // }
         definirTamanho();

         repaint(); // redraw application re-renderiza 
      } 
   }

   public void definirCor() {
      // process color selection
      for (int count = 0; count < colorItems.length; count++)
      {
         if (colorItems[count].isSelected()) //identifica se o item estiver seleiconado (coloritens)
         {
            displayJLabel.setForeground(colorValues[count]);//definido cnforme matriz de cor criada anterioemente
            break;
         } 
      } 
   }   
   public void definirFonte() {
      for (int count = 0; count < fonts.length; count++)
      {
         if (fonts[count].isSelected())
         {
            displayJLabel.setFont(
               new Font(fonts[count].getText(), displayJLabel.getFont().getStyle(), displayJLabel.getFont().getSize()));
         }
      }
   }
   public void definirTamanho() {
      for (int count = 0; count < sizes.length; count++)
      {
         if (sizes[count].isSelected())
         {
            displayJLabel.setFont(
               new Font(displayJLabel.getFont().getName(), displayJLabel.
               getFont().getStyle(), fontSizes[count]));
         }
      }
   }
   // inner class to handle item events from checkbox menu items
   private class StyleHandler implements ItemListener 
   {
      // process font style selections
      @Override
      public void itemStateChanged(ItemEvent e)
      {
         String name = displayJLabel.getFont().getName(); // current Font
         Font font; // new font based on user selections

         // determine which CheckBoxes are checked and create Font
         if (styleItems[0].isSelected() && 
              styleItems[1].isSelected())
            font = new Font(name, Font.BOLD + Font.ITALIC, displayJLabel.getFont().getSize());
         else if (styleItems[0].isSelected())
            font = new Font(name, Font.BOLD, displayJLabel.getFont().getSize());
         else if (styleItems[1].isSelected())
            font = new Font(name, Font.ITALIC, displayJLabel.getFont().getSize());
         else
            font = new Font(name, Font.PLAIN, displayJLabel.getFont().getSize());

         displayJLabel.setFont(font);
         
         definirCor();
         definirFonte();
         definirTamanho();
         repaint(); // redraw application
      } 
   } // end class StyleHandler
} 
