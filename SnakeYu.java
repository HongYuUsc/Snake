package hongyu;


import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Component;
import hongyu.score;



public class SnakeYu {

	private JFrame frame;
	private JComboBox<String> comboBox;
	private JButton btnNewButton,button;
	static JEditorPane editorPane;
	static int start=0;
	static Timer timer;
	static TimerTask task;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		
			SnakeYu window = new SnakeYu();
			Panel jp = new Panel();
			Snake sk = new Snake();
			timer = new Timer();
			task = new TimerTask() {
				
				@Override
				public void run() {
				    jp.move();
					sk.eatfood();
				}
				
			};
            
			//初始化
			jp.initMap();
			Food.createFood();
			Egg.createEgg();
			sk.initialSnake();
			
			jp.setBounds(0, 0, 700, 600);
			window.frame.setVisible(true);
			window.frame.getContentPane().add(jp);
			
			//模式选择
			window.comboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					@SuppressWarnings("unchecked")
					JComboBox<String> jbox = (JComboBox<String>)e.getSource();
					String level = (String) jbox.getSelectedItem();
					switch(level) {
					case "简单":
						sk.speed = 1000;
						if(start == 1) 		
							forward(sk,jp);
					    break;
					case "中等":
						sk.speed = 700;
						if(start == 1) 		
							forward(sk,jp);
						break;
					case "难":
						sk.speed = 500;
						if(start == 1) 		
							forward(sk,jp);
						break;
					default:
						break;
					}
					window.frame.requestFocus();
				}
			});
			
			//开始
			window.btnNewButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					start = 1;
					forward(sk,jp);
					window.frame.setFocusable(true);
					window.frame.requestFocus();
				}
				
			});
			
			//排行榜
			window.button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						score score = new score();
						score.frame.setVisible(true);
						window.frame.requestFocus();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});


			//开始时键盘不可用   
			window.frame.setFocusable(false);
			//键盘监听
			window.frame.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					int keycode = e.getKeyCode();
					switch(keycode) {
					case KeyEvent.VK_UP:
						jp.Setdirection(jp.Up);
						break;
					case KeyEvent.VK_DOWN:
						jp.Setdirection(jp.Down);
						break;
					case KeyEvent.VK_LEFT:
						jp.Setdirection(jp.Left);
						break;
					case KeyEvent.VK_RIGHT:
						jp.Setdirection(jp.Right);
						break;
					default:
						break;		
				    }   
						jp.move();		
						sk.eatfood();
						sk.eategg(sk, jp);
					
				}
			});
			
	}

	/**
	 * Create the application.
	 */
	public SnakeYu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setTitle("亚索的贪吃蛇");
		frame.setBounds(600, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("\u5F00\u59CB");
		btnNewButton.setBounds(720, 38, 135, 39);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		frame.getContentPane().add(btnNewButton);
		
		
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 26));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"\u7B80\u5355", "\u4E2D\u7B49", "\u96BE"}));
		comboBox.setBounds(720, 118, 135, 39);
		frame.getContentPane().add(comboBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.YELLOW);
		textPane.setForeground(Color.RED);
		textPane.setFont(new Font("宋体", Font.PLAIN, 31));
		textPane.setText("\u9009\u62E9\u6A21\u5F0F\u540E\u518D\u6309\u5F00\u59CB\uFF01");
		textPane.setBounds(709, 449, 167, 83);
		frame.getContentPane().add(textPane);
		
		editorPane = new JEditorPane();
		editorPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		editorPane.setFont(new Font("Gill Sans MT Condensed", Font.PLAIN, 38));
		editorPane.setText("4");
		editorPane.setBounds(720, 320, 117, 45);
		frame.getContentPane().add(editorPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("宋体", Font.PLAIN, 26));
		textPane_1.setBackground(Color.YELLOW);
		textPane_1.setText("\u5206\u6570\uFF1A");
		textPane_1.setBounds(720, 275, 117, 45);
		frame.getContentPane().add(textPane_1);
		
		button = new JButton("\u6392\u884C\u699C");
		button.setFont(new Font("宋体", Font.PLAIN, 16));
		button.setBounds(720, 560, 117, 39);
		frame.getContentPane().add(button);
		
	}
	
	public static void forward(Snake sk, Panel jp) {
    	timer.cancel();
		task.cancel();
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
			    jp.move();
				sk.eatfood();
				sk.eategg(sk, jp);
			}
			
		};
	    timer.schedule(task, 200, sk.speed);
    }
}


class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x_width = 32;
	int y_width = 30;
    static int cell_width = 20;
	static int cell_height = 20;
	public static final int X_Width = 32;
	public static final int Y_Width = 30;
	final int Up = 1;
	final int Down = -1;
	final int Left = 2;
	final int Right = -2;
	int direction = Right;
	
	static boolean [][]map = new boolean [X_Width][Y_Width];
	
	public void initMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(i==0 || i==map.length-1 || j==0 || j == map[i].length-1)
                    map[i][j] = true;
                else
                    map[i][j] = false;
            }
        }
    }
	
	
	public void paint(Graphics g) {
		
		super.paint(g);
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j])
					g.setColor(Color.blue);
				else
					g.setColor(Color.white);
				g.fill3DRect(i*cell_height, j*cell_width, cell_width, cell_height, true);
			}
		}
		Point snakeHead = Snake.snake.getFirst();
		System.out.println(snakeHead);
		//蛇头
		g.setColor(Color.RED);
		g.fill3DRect(snakeHead.x*Panel.cell_width, snakeHead.y*Panel.cell_height,
				Panel.cell_width, Panel.cell_height,true);
		//蛇身
		g.setColor(Color.green);
		Point snakeBody;
		for(int i=1;i<Snake.length;i++) {
			snakeBody = Snake.snake.get(i);
			g.fill3DRect(snakeBody.x*Panel.cell_width, snakeBody.y*Panel.cell_height,
					Panel.cell_width, Panel.cell_height,true);
		}
		
		//食物
		g.setColor(Color.pink);
		g.fill3DRect(Food.food.x*cell_width, Food.food.y*cell_width, cell_width, cell_height, true);
		
		//彩蛋
		if(Snake.length == 22) {
		g.setColor(Color.yellow);
		g.fill3DRect(Egg.egg.x*cell_width, Egg.egg.y*cell_width, cell_width, cell_height, true);
		}

	}
	
    public void Setdirection(int dirc) {
		
		System.out.println("newdirection="+dirc);
		if(dirc+direction == 0) {
			String message = "初级用户不能玩180度掉头!";
			JOptionPane.showMessageDialog(this, message, "警告", JOptionPane.WARNING_MESSAGE);
		}
		else
			direction = dirc;
		
	}	
    
    public void move(){
		
		Point k = Snake.snake.getFirst();
		switch(direction) {
		case Up:
			Snake.snake.addFirst(new Point(k.x,k.y-1));
			break;
		case Down:
			Snake.snake.addFirst(new Point(k.x,k.y+1));
			break;
		case Left:
			Snake.snake.addFirst(new Point(k.x-1,k.y));
			break;
		case Right:
			Snake.snake.addFirst(new Point(k.x+1,k.y));
			break;
		default:
		    break;
		}
		Point j = Snake.snake.getFirst();
		
		//判断蛇是否吃到自己
		for(int i =1;i<Snake.length;i++)
			if(Snake.snake.get(i).x == j.x && Snake.snake.get(i).y == j.y) {
				SnakeYu.timer.cancel();
				String mes = "Gameover";
			    JOptionPane.showMessageDialog(this, mes);
			    score.getscore(Snake.length);
			    SnakeYu.start = 0;
			    System.exit(0);
			}
        //判断是否撞壁       
		if(j.x == 0 || j.x == Panel.X_Width -1 || j.y == Panel.Y_Width -1 || j.y == 0) {
			SnakeYu.timer.cancel();
			String message = "Gameover";
			JOptionPane.showMessageDialog(this, message);
			score.getscore(Snake.length);
			SnakeYu.start = 0;
            System.exit(0);
		}
		else
		repaint();
   }
	
    public void eatfood(){
		if(Snake.snake.getFirst().x == Food.food.x && Snake.snake.getFirst().y == Food.food.y)
		{
			Snake.length ++;
			SnakeYu.editorPane.setText(Snake.length+"");
			if(Snake.length == 22) {
				Egg.createEgg();
			}
			Food.createFood();
			repaint();
		}
	}
    
    public void eategg(Snake sk,Panel jp){
		if(Snake.snake.getFirst().x == Egg.egg.x && Snake.snake.getFirst().y == Egg.egg.y) {
			SnakeYu.timer.cancel();
			SnakeYu.task.cancel();
			Snake.length = Snake.length - 4;
			SnakeYu.editorPane.setText(Snake.length+"");
			Food.createFood();
			String mes = "Author: Hongyu\nTime: 2018 Jul";
		    JOptionPane.showMessageDialog(this, mes,"An egg!", JOptionPane.PLAIN_MESSAGE);
		    SnakeYu.timer = new Timer();
			SnakeYu.task = new TimerTask() {
				
				@Override
				public void run() {
				    jp.move();
					sk.eatfood();
					sk.eategg(sk, jp);
				}
				
			};
		    SnakeYu.timer.schedule(SnakeYu.task, 200, sk.speed);
			repaint();
		}
	}
	
    
	
}


class Snake extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int length = 4;
	int speed = 1000;
	
	public static LinkedList<Point> snake = new LinkedList<Point>();
	
	public void initialSnake() {
		int x = Panel.X_Width/2-1;
		int y = Panel.Y_Width/2-1;
		for(int i=0;i<length;i++) {
			snake.add(new Point(x-i,y));
		}
	}
	
	
}
	


class Food {
	
	static Point food;
	
	static public void createFood() {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(Panel.X_Width-1);
			int y = random.nextInt(Panel.Y_Width-1);
			if(!Panel.map[x][y])
			{
				food = new Point(x,y);
				break;
			}
		}
		
	}
	
}

class Egg {
	
	static Point egg;
	
	static public void createEgg() {
		Random random = new Random();
		while(true) {
			int x = random.nextInt(Panel.X_Width-1);
			int y = random.nextInt(Panel.Y_Width-1);
			if(!Panel.map[x][y])
			{
				egg = new Point(x,y);
				break;
			}
		}
		
	}
	
}
