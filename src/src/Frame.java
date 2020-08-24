package src;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * This class is the main windows frame.
 * It manages all the different panel of the program and the actions associated with buttons.
 */
@SuppressWarnings("serial")
public class Frame extends JFrame implements ActionListener{

	private static Frame frame;

	// CardLayout縺ｯ逕ｻ髱｢縺ｮ蛻�繧頑崛縺医〒菴ｿ縺�繧峨＠縺� content縺ｫ霑ｽ蜉�縺励※縲…l.show(content, 繝代Λ繝｡繧ｿ�ｼ�)縺ｧ蛻�繧頑崛縺医※縺昴≧
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanel will contain the different panels
	//the five panels for the program :
	private Game game;
	private Menu menu;
	// 繝ｪ繧ｶ繝ｫ繝育判髱｢縺ｮ陦ｨ遉ｺ
	private ScoreBoard scoreboard;
	// 繧ｪ繝励す繝ｧ繝ｳ險ｭ螳�
	private Options options;
	// 繝ｫ繝ｼ繝ｫ隱ｬ譏�
	private Rules rules;

	// BGM
	private BackgroundSound bg;

	// 莉･荳九�ｯ險ｭ螳壹☆繧句ｿ�隕√�ｯ縺ｪ縺�縺ｨ諤昴＞縺ｾ縺�
	private static String playerName; //this String contains the name set by the player in the Options

	private static String theme = "pokemon"; //this is the skin theme of the game (pokemon by default)

	public static boolean previousmenu = true; //this boolean is true if the previous panel displayed was the main menu

	private boolean delete = false; //this boolean is set to true if the player decides and confirm the deletion of his scores


	public Frame(){

		Frame.frame = this;

		setTitle("School Love");
		setSize(new Dimension(500, 379));    // 逕ｻ髱｢繧ｵ繧､繧ｺ縺ｩ縺�縺励∪縺吶°
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// 螳溯｡梧凾縺ｮ繧｢繧､繧ｳ繝ｳ繧定ｨｭ螳壹＠縺ｦ縺ｾ縺� 縺�縺�諢溘§縺ｮ謗｢縺励※�ｼ�
		ImageIcon img = new ImageIcon(getClass().getResource("/logo.png")); //icon of the frame (on the windows bar and top left of the window)
		this.setIconImage(img.getImage());

		// 繝｡繝九Η繝ｼ逕ｻ髱｢
		menu = new Menu();
		content.setLayout(cl); //the content panel is managed by the CardLayout
		content.add(menu, "Menu"); //the first panel is added to content
	    this.getContentPane().add(content); // content is add to the frame

		// BGM縺ｮ險ｭ螳�
	    //bg = new BackgroundSound();
	    //bg.play("on"); //plays the launch sound

		// 繝ｪ繧ｶ繝ｫ繝育判髱｢
	    scoreboard = new ScoreBoard();

		// 繧ｪ繝励す繝ｧ繝ｳ逕ｻ髱｢ 蜷榊燕縺ｨ縺九せ繧ｭ繝ｳ縺ｨ縺九�ｮ險ｭ螳�
	    options = new Options();
		content.add(options, "Options");

	}

	/*
	 * The actionPerformed defines the action to do according to the button.
	 * Getting the name of the button, the corresponding method is called.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		// 繝懊ち繝ｳ繧偵け繝ｪ繝�繧ｯ縺輔ｌ縺滓凾縺�縺大ｯｾ蠢懊�ｮ蜍穂ｽ懊ｒ陦後＞縺ｾ縺� switch縺ｧ譖ｸ縺�縺滓婿縺悟�縺九ｊ繧�縺吶＞縺九ｂ
		if(((JButton)e.getSource()).getName().equals("startButton"))
			doStart();    // 繧ｲ繝ｼ繝�繧ｹ繧ｿ繝ｼ繝�
		else if(((JButton)e.getSource()).getName().equals("optionsButton"))
			doOptions();
		else if(((JButton)e.getSource()).getName().equals("okButton"))
			doValidate();
		else if(((JButton)e.getSource()).getName().equals("skinButton"))
			doSkinChange();
		else if(((JButton)e.getSource()).getName().equals("deleteButton"))
			doDelete();
		else if(((JButton)e.getSource()).getName().equals("rulesButton"))
			doRules();
		else if(((JButton)e.getSource()).getName().equals("exitButton"))
			doExitRules();
		else if(((JButton)e.getSource()).getName().equals("Yes"))
			delete = true;
		else if(((JButton)e.getSource()).getName().equals("No"))
			delete = false;

	}

	/*
	 * Displays the option panel.
	 */
	public void doOptions(){
		// 蛻�繧頑崛縺医〒縺吶�ｭ
		cl.show(content, "Options");

	}

	/*
	 * Display the rules panel.
	 * A new rules panel is created everytime to prevent a skin change
	 */
	public void doRules(){
		// 繧ｹ繧ｭ繝ｳ縺ｫ繧医▲縺ｦ繝ｫ繝ｼ繝ｫ螟峨∴縺ｦ縺溘�ｮ縺ｧ縺薙％縺ｧ蜻ｼ縺ｳ蜃ｺ縺励※縺�繧九ｓ縺�縺ｨ諤昴＞縺ｾ縺�
		rules = new Rules();
		content.add(rules,"Rules");
		cl.show(content, "Rules");

	}

	/*
	 * Displays the menu panel.
	 */
	public void doExitRules(){

		cl.show(content,"Menu");

	}

	/*
	 * This set the new skin theme for the game.
	 * Every panel is affected by the design change (especially for the background)
	 */
	// 蠢�隕√↑縺輔￡
	private void doSkinChange() {

		options.setPlayerName();

		if(Frame.getTheme() == "pokemon")
			Frame.setTheme("theme2");
		else
			Frame.setTheme("pokemon");


		//a new options panel is created to change its background image
		options = new Options();
		content.add(options, "Options");
		cl.show(content, "Options");
	}

	/*
	 * This method is called with the "delete scores" button.
	 * It first displays an option window to confirm the choice.
	 * If the player click the yes button, the delete boolean is set to true and the delete function of the score board is called.
	 * Finally, the delete boolean returns to false.
	 */
	public void doDelete(){

		options.showMessage();

		/*if(delete){
			scoreboard.deleteScores();
			delete = false;
		}*/
	}

	/*
	 * This method corresponds to the ok button of the Options panel
	 * As the Options can be reach either from the menu or the scoreboard,
	 * the previous panel must be displayed.
	 * The boolean previousmenu is true if the Options panel was reached from the menu.
	 */
	public void doValidate(){

		options.setPlayerName(); //the name is set if the player has changed it
		if(previousmenu){
			cl.show(content, "Menu");
		}
		else{
			scoreboard = new ScoreBoard(); //a new scoreboard is created to update it if the player has deleted the scores
			content.add(scoreboard, "ScoreBoard");
			cl.show(content, "ScoreBoard");
		}
	}

	/*
	 * This function defines what happen when a player click on the "Try Again" button
	 * A new game panel is created, added to the content and the game starts with its background music
	 */
	public void doTryAgain(){
		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		//bg.play("game");
	}

	/*
	 * This launches the game from the menu panel.
	 * A new game panel is created, added to the content and the game starts with its background music
	 */
	public void doStart(){

		game = new Game();
		content.add(game, "Game");
		cl.show(content, "Game");
		//bg.play("game");
	}

	/*
	 * This function is called by the game panel when the game is finished
	 * The score is added to the score list, then the score panel is displayed by the cardlayout and the game over sound is played.
	 */
	public void gameOver(int pscore, int result, int life){

		scoreboard.addScore(Frame.getPlayerName(), pscore);
		content.add(scoreboard, "ScoreBoard");
		cl.show(content, "ScoreBoard");

		//bg.play("gameover");
	}


	/*
	 * This method is called by the game class when the boss phase starts.
	 */
	public void playBossTheme(){
		//bg.play("boss");
	}

	/*
	 * This is the main music theme played during the normal game.
	 */
	public void playTheme(){
		//bg.play("game");
	}

	public static Frame getFrame(){
		// Frame.getFrame()縺ｧ縺�縺､縺ｧ繧Ｇrame繧貞叙蠕励〒縺阪ｋ
		return frame;

	}

	// 蜷榊燕縺ｮ逋ｻ骭ｲ縺�繧翫∪縺呻ｼ� 蜷榊燕蜈･繧後※闍･蟷ｲ縺ｮ繧ｹ繝医�ｼ繝ｪ繝ｼ縺ｨ縺九≠縺｣縺溘ｉ髱｢逋ｽ縺�縺代←縺ｭ(譎る俣縺後↑縺�...)
	public static void setPlayerName(String name){

		playerName = name;

	}

	public static String getPlayerName(){

		if(playerName == null)
			return "Anonymous";

		return playerName;

	}

	// 繧ｹ繧ｭ繝ｳ險ｭ螳壹′縺�繧峨↑縺�縺ｮ縺ｧ莉･荳九�ｯ豸医＠縺ｦ繧医＆縺昴≧

	public static String getTheme() {
		return theme;
	}

	public static void setTheme(String theme) {
		Frame.theme = theme;
	}
}
