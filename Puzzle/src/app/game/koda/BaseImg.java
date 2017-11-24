package app.game.koda;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class BaseImg extends Component{
	private static final long serialVersionUID = 1L;

	int order[][] = null;
	ImgBlock block[][] = null;
	String imgFile = null;
	Image img = null;
	int xCount = 0;
	int yCount = 0;
	int imgWidth = 0;
	int imgHeight = 0;
	int bWidth = 0;
	int bHeight = 0;
	int cx = 0, cy = 0;

	public int getImgHeight() {
		return imgHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public BaseImg(String file, int w, int h) {
		super();
		int c = 0;
		yCount = h;
		imgFile = file;
		xCount = w;
		img = Toolkit.getDefaultToolkit().getImage(imgFile);
		
		imgWidth = new ImageIcon(imgFile).getIconWidth();
		imgHeight = new ImageIcon(imgFile).getIconHeight();
		
		bWidth = imgWidth / w;
		bHeight = imgHeight / h;
		
		order = new int[h][w];
		block = new ImgBlock[h][w];
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				order[i][j] = c++;
				block[i][j] = new ImgBlock(j*bWidth, i*bHeight, bWidth, bHeight);
			}
		}
	}
	
	public void mixUp(){
		int direct = 0;
//		int h1 = 0, h2= 0, w1 = 0, w2 =0;
		for (int i = 0, limit = xCount * yCount * 100; i < limit; i++){
			direct = (int)(Math.random()*4.0);
			switch (direct) {
			case 0:
				shift(38);
				break;
			case 1:
				shift(40);
				break;
			case 2:
				shift(37);
				break;
			case 3:
				shift(39);
				break;
			}
		}
	}

	public void shift(int keyVal){
		int tmpInt = 0;
		switch (keyVal){
		case 38:			//up
			if (cy + 1 == yCount) break;
			tmpInt = order[cy][cx];
			order[cy][cx] = order[++cy][cx];
			order[cy][cx] = tmpInt;
			break;
		case 40 :			//down
			if (cy - 1 < 0) break;
			tmpInt = order[cy][cx];
			order[cy][cx] = order[--cy][cx];
			order[cy][cx] = tmpInt;
			break;
		case 37 :			//left
			if (cx + 1 == xCount) break;
			tmpInt = order[cy][cx];
			order[cy][cx] = order[cy][++cx];
			order[cy][cx] = tmpInt;
			break;
		case 39 :			//right
			if (cx - 1 < 0) break;
			tmpInt = order[cy][cx];
			order[cy][cx] = order[cy][--cx];
			order[cy][cx] = tmpInt;
			break;
		}
	}

	public ImgBlock getBlock(int i, int j) {
		ImgBlock tmpBlock = null;
		int x = order[i][j] / xCount;
		int y = order[i][j] % xCount;
		tmpBlock = block[x][y];
		tmpBlock.setBounds(j*bWidth, i*bHeight, bWidth, bHeight);
		return tmpBlock;
	}
	
	public boolean done(){
		int c = 0;
		for (int i=0;i<yCount;i++){
			for (int j=0;j<xCount;j++){
				if (order[i][j] != c++) return false;
			}
		}
		return true;
	}

	public int getYCount() {
		return yCount;
	}

	public void setYCount(int count) {
		yCount = count;
	}

	public String getImgFile() {
		return imgFile;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}

	public int getOrder(int i, int j) {
		return order[i][j];
	}

	public int[][] getOrder() {
		return order;
	}

	public void setOrder(int[][] order) {
		this.order = order;
	}

	public int getXCount() {
		return xCount;
	}

	public void setXCount(int count) {
		xCount = count;
	}
	
	class ImgBlock extends Component{

		private static final long serialVersionUID = 1L;
		
		int width = 0,height = 0;
		int x = 0, y = 0;
		
		ImgObserver sp =new ImgObserver(this);

		public ImgBlock(int x, int y, int width, int height) {
			super();
			this.height = height;
			this.width = width;
			this.x = x;
			this.y = y;
			setBounds(x,y,width+1,height+1);
		}
		
		public void paint(Graphics g){
			g.drawImage(img,0,0,width,height,x,y,x+width,y+height,sp);
			g.drawRect(0,0,width,height);
		}
	}
}
