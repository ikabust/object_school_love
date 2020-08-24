package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

/*
 * Rules panel is composed of two pans in a JTabbedPane
 * The first pan shows the commands and the second one the different kind of bonuses
 * It also contains another JPanel for the EXIT button
 */
@SuppressWarnings("serial")
public class Rules extends JPanel{

	private JPanel tab1; //command tab
	private JPanel tab2; // bonus tab

	public Rules(){

		super(new BorderLayout());

		tab1 = new JPanel();
		tab2 = new JPanel();

		initTab1();
		initTab2();

		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE); //make the background of the tabs transparent
		JTabbedPane tab = new JTabbedPane();
		tab.setOpaque(false);
		Myfont.setMyfont(tab);
		tab.setBackground(new Color(255,255,255,120));

		tab.addTab("Commands", tab1);
		tab.addTab("character data", tab2);

		this.add(tab);

		JPanel pan = new JPanel();

		Button exitButton = new Button("Exit");
		exitButton.setName("exitButton");

		pan.add(exitButton);
		pan.setBackground(new Color(0,0,0,0));

		this.add(pan, BorderLayout.SOUTH);

		Frame frame = Frame.getFrame();
		exitButton.addActionListener(frame);

	}

	public void initTab1(){

		tab1.setLayout(new GridBagLayout());

		tab1.setBackground(new Color(255,255,255,120));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);

		JLabel jinst1 = new JLabel("Use Left Arrow Key and Right Arrow Key to move");
		Myfont.setMyfont(jinst1);
		c.gridy = 1;
		tab1.add(jinst1, c);

		JLabel jinst2 = new JLabel("Use SPACE to jump");
		Myfont.setMyfont(jinst2);
		c.gridy = 2;
		tab1.add(jinst2, c);

		JLabel jinst3 = new JLabel("Use P to pause the game");
		Myfont.setMyfont(jinst3);
		c.gridy = 3;
		tab1.add(jinst3, c);

		JLabel jinst4 = new JLabel("This game has a time limit.Aim for your goal!");
		Myfont.setMyfont(jinst4);
		c.gridy = 4;
		tab1.add(jinst4, c);

	}

	public void initTab2(){

		tab2.setLayout(new GridBagLayout());

		tab2.setBackground(new Color(255,255,255,120));

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(0,0,5,15);

		ImageIcon i1 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus3rules.png"));
		JLabel jlab = new JLabel(i1);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 1;
		tab2.add(jlab, c);

		JLabel jlab2 = new JLabel("dog:The more you bump into the dog the more your time limit will approach.");
		Myfont.setMyfont(jlab2);
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 0;
		c.gridx = 2;
		tab2.add(jlab2, c);

		ImageIcon i2 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus2rules.png"));
		JLabel jlab3 = new JLabel(i2);
		c.gridy = 6;
		c.gridx = 1;
		tab2.add(jlab3, c);

		JLabel jlab4 = new JLabel("My fate:If you bump into him, it's game over.");
		Myfont.setMyfont(jlab4);
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 6;
		c.gridx = 2;
		tab2.add(jlab4, c);

		ImageIcon i3 = new ImageIcon(getClass().getResource("/" + Frame.getTheme() + "_bonus1rules.png"));
		JLabel jlab5 = new JLabel(i3);
		c.anchor = GridBagConstraints.WEST;
		c.gridy = 7;
		c.gridx = 1;
		tab2.add(jlab5, c);

		JLabel jlab6 = new JLabel("obstacle:Aboid it!");
		Myfont.setMyfont(jlab6);
		c.gridy = 7;
		c.gridx = 2;
		tab2.add(jlab6, c);
	}

	/*
	 * This method is used to paint the background image of the rules
	 * The image change according to the theme
	 */
	@Override
	protected void paintComponent(Graphics g){

		super.paintComponent(g);
		ImageIcon ii = new ImageIcon(getClass().getResource("/surprise15.png"));
        Image image = ii.getImage();
		g.drawImage(image, 0, 0, null);

		//a transparent white square is drawn on the background image to make it transparent
		g.drawRect(0, 0, 500, 500);
		g.setColor(new Color(255,255,255,200));
		g.fillRect(0, 0, 500, 500);

	}

}
