package hongyu;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.Font;

public class score {

	static JFrame frame;
	private static String rank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					score window = new score();
					window.frame.setVisible(true);
					
	}

	/**
	 * Create the application.
	 */
	public score() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("����", Font.PLAIN, 20));
		rank = readscore();
		textPane.setText(rank);
		frame.getContentPane().add(textPane, BorderLayout.CENTER);
	}
	
	public static void getscore(int score){
		String name;
		name = JOptionPane.showInputDialog(null,"����ǳƣ�\n","���а�",JOptionPane.PLAIN_MESSAGE);
            File file = new File("F:/eclipse file/score.txt");
            FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(file,true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(name != null) {
				outputStream.write(("��ң�"+name).getBytes());
				outputStream.write(("   ������"+score).getBytes());
				outputStream.write("\r\n".getBytes());
				}
				else
					System.exit(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public static String readscore() {
		File file = new File("F:/eclipse file/score.txt");
		InputStream input = null ;    // ׼����һ������Ķ���
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		byte b[] = new byte[1024];
		try {
			input.read(b);
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rank = new String(b);
		return rank;
	}
			
			
			
		
	
	
	
	

}
