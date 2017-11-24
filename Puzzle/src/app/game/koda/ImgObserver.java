package app.game.koda;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class ImgObserver implements ImageObserver{
	ImageObserver obs;
	int w,h;

	ImgObserver(ImageObserver obs){
		this.obs=obs;
	}
	
	public boolean imageUpdate(Image img, int inf, int x, int y, int width, int height){
		return obs.imageUpdate(img,inf,x,y,width,height);
	}
}
