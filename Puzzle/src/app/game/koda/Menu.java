package app.game.koda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class Menu extends JMenuBar implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	Menu(){
		JMenu menu = new JMenu("File");
		
		JMenuItem mItem = new JMenuItem("Start/Restart");
		mItem.addActionListener(this);
		menu.add(mItem);

		mItem = new JMenuItem("Select a Picture");
		mItem.addActionListener(this);
		menu.add(mItem);

		mItem = new JMenu("Set Size");
		mItem.addActionListener(this);
		
		JMenuItem mSubItem = new JMenuItem("3 X 3");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);

		mSubItem = new JMenuItem("3 X 4");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);

		mSubItem = new JMenuItem("3 X 5");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);

		mSubItem = new JMenuItem("4 X 4");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);

		mSubItem = new JMenuItem("5 X 5");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);
		((JMenu)mItem).addSeparator();
		
		mSubItem = new JMenuItem("Customize");
		mSubItem.addActionListener(this);
		mItem.add(mSubItem);
		

		menu.add(mItem);
		menu.addSeparator();

		mItem = new JMenuItem("Exit");
		mItem.addActionListener(this);
		menu.add(mItem);
		
		this.add(menu);
	}

	public void actionPerformed(ActionEvent arg0) {
		Main obj = (Main)getParent().getParent().getParent();
		String cmd = arg0.getActionCommand();
		if (cmd.equals("Exit")){
			System.exit(0);
		}
		else if(cmd.equals("Select a Picture")){
			JFileChooser fc = new JFileChooser();
			fc.addChoosableFileFilter(new imgFilter());
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			if (fc.showOpenDialog(this) == 0){
				obj.setBImg(new BaseImg(fc.getSelectedFile().getPath(), 3, 3));
				obj.setMoveAble(false);
				obj.init();
			}
		}
		else if(cmd.equals("Start/Restart")){
			obj.setMoveAble(true);
			obj.init();
		}
		else if(cmd.equals("3 X 3")){
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, 3, 3));
			obj.setMoveAble(false);
			obj.init();
		}
		else if(cmd.equals("3 X 4")){
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, 3, 4));
			obj.setMoveAble(false);
			obj.init();
		}
		else if(cmd.equals("3 X 5")){
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, 3, 5));
			obj.setMoveAble(false);
			obj.init();
		}
		else if(cmd.equals("4 X 4")){
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, 4, 4));
			obj.setMoveAble(false);
			obj.init();
		}
		else if(cmd.equals("5 X 5")){
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, 5, 5));
			obj.setMoveAble(false);
			obj.init();
		}
		else if(cmd.equals("Customize")){
			int r = 0, c = 0;
			try{
				r = Integer.parseInt(JOptionPane.showInputDialog("Input Rows(3 - 8): "));
				c = Integer.parseInt(JOptionPane.showInputDialog("Input Columns(3 - 8): "));
				System.out.println(r+","+c);
			} catch (Exception e){
				JOptionPane.showConfirmDialog(this,"Wrong number format!","Puzzle",JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (r > 8 || c > 8 || r < 3 || c < 3){
				JOptionPane.showConfirmDialog(this,"Wrong number range(3 - 8)!","Puzzle",JOptionPane.WARNING_MESSAGE);
				return;				
			}
			
			String fn = obj.getBImg().getImgFile();
			obj.setBImg(new BaseImg(fn, c, r));
			obj.setMoveAble(false);
			obj.init();
		}
	}
	
	private String getExtension(File f){
		String ext=null;
		String s=f.getName();
		int i=s.lastIndexOf('.');
		if (i>0 && i<s.length()-1) ext=s.substring(i+1).toLowerCase();
		return ext;
	}
	
	class imgFilter extends FileFilter{
		public boolean accept(File f){
			if (f.isDirectory()){
				return true;
			}
			String ext=getExtension(f);
			return (ext!=null && ext.equals("jpg"));
		}

		public String getDescription(){
			return "JPEG Files";
		}
	}

}
