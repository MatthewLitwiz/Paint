import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class PaintGui extends JFrame{
    public PaintGui(){
        super("Paint GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500,1000));
        pack(); 
        setLocationRelativeTo(null); 

        addGuiComponents();
    }

    private void addGuiComponents() {
        // JPanel Configs
        JPanel canvasPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        canvasPanel.setLayout(springLayout);

        // 1. Canvas
        Canvas canvas = new Canvas(1500, 950);
        canvasPanel.add(canvas);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 50, SpringLayout.NORTH, canvasPanel);
        
        // 2. Color Chooser
        JButton chooseColorButton = new JButton("Choose Color");
        chooseColorButton.setFocusable(false);
        chooseColorButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Color c = JColorChooser.showDialog(null, "Select a Color", Color.BLACK); // Opens a color chooser dialog
                chooseColorButton.setBackground(c); // Sets the background of the button to the color chosen
                canvas.setColor(c); // Sets the color of the canvas to the color chosen
            }

        });
        canvasPanel.add(chooseColorButton); // Adds the button to the panel
        springLayout.putConstraint(SpringLayout.NORTH, chooseColorButton, 10, SpringLayout.NORTH, canvasPanel); // Sets the position of the button
        springLayout.putConstraint(SpringLayout.WEST, chooseColorButton, 25, SpringLayout.WEST, canvasPanel); // Sets the position of the button

        // 3. Reset Button
        JButton resetButton = new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                canvas.resetCanvas();
            }

        }
);
        canvasPanel.add(resetButton); // Adds the button to the panel
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 10, SpringLayout.NORTH, canvasPanel); // Sets the position of the button
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 150, SpringLayout.WEST, canvasPanel); // Sets the position of the button











        this.getContentPane().add(canvasPanel);


    }


    
}
