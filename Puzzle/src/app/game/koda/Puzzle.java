package app.game.koda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import app.game.koda.BaseImg.ImgBlock;

public class Puzzle extends JDialog implements KeyListener{
	private static final long serialVersionUID = 1L;
	BaseImg bImg = null;
	JPanel panel = null;
//	JMenuBar menuBar = null;
	boolean moveAble = false;
	
	public Puzzle(){
		super();
		setTitle("Puzzle");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setJMenuBar(new Menu());
		panel = new JPanel(null);
		bImg = new BaseImg("image.jpg", 3, 3);
		getContentPane().add(panel, BorderLayout.CENTER);
		addKeyListener(this);
		init();
	}
	
	public void init(){
		panel.removeAll();
		setSize(bImg.getImgWidth(),bImg.getImgHeight()+55);
		panel.setSize(bImg.getImgWidth(),bImg.getImgHeight());
		if (moveAble) bImg.mixUp();
		paint();
		setVisible(true);
	}
	
	private void paint(){
		for (int i=0;i<bImg.getYCount();i++){
			for (int j=0;j<bImg.getXCount();j++){
				if (moveAble && bImg.getOrder(i,j) == 0) continue;
				panel.add(bImg.getBlock(i,j));
			}
		}
	}
	
	public static void main(String[] args){
		new Puzzle();
	}
	
	public void keyPressed(KeyEvent arg0) {
		if (!moveAble) return;
		bImg.shift((int)arg0.getKeyCode());
		paint();
		if (bImg.done()){
			JOptionPane.showMessageDialog(this, "Puzzle Completed", "Puzzle", JOptionPane.INFORMATION_MESSAGE);
			moveAble=false;
			init();
		}
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
			
	}

	public BaseImg getBImg() {
		return bImg;
	}

	public void setBImg(BaseImg img) {
		bImg = img;
	}

	public boolean isMoveAble() {
		return moveAble;
	}

	public void setMoveAble(boolean moveAble) {
		this.moveAble = moveAble;
	}
}
