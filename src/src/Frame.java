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
	
	// CardLayoutは画面の切り替えで使うらしい contentに追加して、cl.show(content, パラメタ？)で切り替えてそう
	private CardLayout cl = new CardLayout(); //this layout is used to switch from different JPanel
	private JPanel content = new JPanel(); //this JPanel will contain the different panels
	//the five panels for the program :
	private Game game;
	private Menu menu;
	// リザルト画面の表示
	private ScoreBoard scoreboard;
	// オプション設定
	private Options options;
	// ルール説明
	private Rules rules;
	
	// BGM
	private BackgroundSound bg;
	
	// 以下は設定する必要はないと思います
	private static String playerName; //this String contains the name set by the player in the Options
	
	private static String theme = "pokemon"; //this is the skin theme of the game (pokemon by default)
	
	public static boolean previousmenu = true; //this boolean is true if the previous panel displayed was the main menu
	
	private boolean delete = false; //this boolean is set to true if the player decides and confirm the deletion of his scores

	
	public Frame(){
		
		Frame.frame = this;
		
		setTitle("School Love");
		setSize(new Dimension(500, 379));    // 画面サイズどうしますか
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// 実行時のアイコンを設定してます いい感じの探して！
		ImageIcon img = new ImageIcon(getClass().getResource("/logo.png")); //icon of the frame (on the windows bar and top left of the window)
		this.setIconImage(img.getImage());
		
		// メニュー画面
		menu = new Menu();
		content.setLayout(cl); //the content panel is managed by the CardLayout
		content.add(menu, "Menu"); //the first panel is added to content
	    this.getContentPane().add(content); // content is add to the frame
		
		// BGMの設定
	    //bg = new BackgroundSound();
	    //bg.play("on"); //plays the launch sound
		
		// リザルト画面
	    scoreboard = new ScoreBoard();
		
		// オプション画面 名前とかスキンとかの設定
	    options = new Options();
		content.add(options, "Options");
	
	}
	
	/*
	 * The actionPerformed defines the action to do according to the button.
	 * Getting the name of the button, the corresponding method is called.
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		// ボタンをクリックされた時だけ対応の動作を行います switchで書いた方が分かりやすいかも
		if(((JButton)e.getSource()).getName().equals("startButton"))
			doStart();    // ゲームスタート
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
		// 切り替えですね
		cl.show(content, "Options");
		
	}
	
	/*
	 * Display the rules panel.
	 * A new rules panel is created everytime to prevent a skin change
	 */
	public void doRules(){
		// スキンによってルール変えてたのでここで呼び出しているんだと思います
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
	// 必要なさげ
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
		
		if(delete){
			scoreboard.deleteScores();
			delete = false;
		}
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
		// Frame.getFrame()でいつでもframeを取得できる
		return frame;
		
	}
	
	// 名前の登録いります？ 名前入れて若干のストーリーとかあったら面白いけどね(時間がない...)
	public static void setPlayerName(String name){
		
		playerName = name;
		
	}
	
	public static String getPlayerName(){
		
		if(playerName == null)
			return "Anonymous";
		
		return playerName;
		
	}
	
	// スキン設定がいらないので以下は消してよさそう

	public static String getTheme() {
		return theme;
	}

	public static void setTheme(String theme) {
		Frame.theme = theme;
	}
}
