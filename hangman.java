
    import java.applet.Applet;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.Random;
    import java.lang.reflect.Array;
    public class hangman extends Applet implements ActionListener{
      
            static final int DEAD=8;   
            private int errors;        
            private String message;   
            private String information; 
            private String rword;      
            private StringBuffer gword;
            private Button bStart;      
            private Button bGo;         
            private TextField tfLetter; 
            private Font fnt;           
    		
            public void init(){
    		fnt = new Font( "Arial", 0, 20 );
    		setFont(fnt);
    				setBackground(Color.green);
					setForeground(Color.red);                    
                    tfLetter = new TextField();
                    bStart = new Button("Restart");
                    bGo = new Button("Go");
                    add(bStart);
                    add(new Label("Guess a letter:"));
                    add(tfLetter);
                    add(bGo);
                    bStart.addActionListener(this);
                    bGo.addActionListener(this);
                    initGame();
            }
            
            public void initGame(){
                    errors=0;
                    String str = "sanjay|vikram|rachana|prakash|pawan|yashwanth|trinath|suraj|abhishek|aakash|aditya|rishi";
                    String[] temp;
                    String delimiter = "\\|";
                    temp = str.split(delimiter);
    		Random randomGenerator = new Random();
    		int randomInt = randomGenerator.nextInt(temp.length);
                    rword = new String(temp[randomInt]);
                    char positions[] = new char[rword.length()];
                    for (int i=0; i<rword.length(); i++) {
                            positions[i] = '.';
                    }
                    String s = new String(positions);
                    gword = new StringBuffer(s);
                    tfLetter.setText("");
                    message="";
                    information = "";
                    repaint();
            }
            public void paint(Graphics g) {
                    int baseY = 250;
                    g.drawString( message, 40, baseY+25 );
                    if (errors >  0){    
                            g.drawLine(90, baseY,200,baseY);
                    }
                    if (errors >  1){   
                            g.drawLine(125,baseY,125,baseY-100);
                    }
                    if (errors >  2){
                            g.drawLine(110,baseY,125,baseY-15);
                    }
                    if (errors >  3){
                            g.drawLine(140,baseY,125,baseY-15);
                    }
                    if (errors >  4){    
                            g.drawLine(125,baseY-100,175,baseY-100);
                    }
                    if (errors >  5){
                            g.drawLine(125,baseY-85,140,baseY-100);
                    }
                    if (errors >  6){   
                            g.drawLine(175,baseY-100,175,baseY-75);
                    }
                    if (errors >  7){    
                            g.drawOval(170,baseY-75,10,12);
                            g.drawOval(170,baseY-65,15,25);
                            g.drawLine(160,baseY-65,170,baseY-60);
                            g.drawLine(183,baseY-60,193,baseY-65);
                            g.drawLine(165,baseY-30,170,baseY-45);
                            g.drawLine(183,baseY-45,193,baseY-30);
                    }
                    
                    
                    g.drawString( message, 40, baseY+25 );
                    g.drawString( information, 25, baseY+45 );
    				g.drawString( new String (gword), 110, 60);
            
            }
            
            public void actionPerformed(ActionEvent e){
                    if (e.getSource() == bStart){
                            initGame();
                    }
                    if (e.getSource() == bGo){
                            processTurn();
                          
                            tfLetter.setText("");
                            repaint();
                    }
            }
            private void processTurn(){
                    String s, t;
                    char a;
                    
                    s = tfLetter.getText();
                    a = s.charAt(0);
                    
                    if (!Character.isLetter(a)){
                              message="Only enter letters!";
                              return;
                    }
                    if (s.length()>1){
                              message="One letter at a time!";
                              return;
                    }
            
                  
        
                    t = new String(gword);
                    if (t.indexOf(s) != -1){
                            message="Letter has already been guessed";
                            return;
                    }
                    if (rword.indexOf(s) == -1){
                            message="";
                            errors++;
                            if (errors==DEAD){
                                    message="You lost!";
                                    information = 
                                          "Click on restart for another chance!";
                            }
                            int che=DEAD-errors;
							message="YOU HAVE "+che+" CHANCES MORE";
                            return;
                    }
                    for (int i=0; i<rword.length(); i++){
                            if (rword.charAt(i) == a){
                                    gword.setCharAt(i, a);
									message="YOU ARE DOING WELL GO ON";
                            }

                    }
                    t = new String(gword);
                    if (t.indexOf('.') == -1){
                            message="You win!";
                            return;
                    }
                    message="";
    				repaint();
            }
    }

