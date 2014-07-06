package com.edu.agh.student.lakeproject.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class MainFrame extends JFrame {
	private static String frameTitle = "Lake Project";
	
	private static String playButtonTitle = "Start";
	private static String pauseButtonTitle = "Pauza";
	private static String forwardButtonTitle = "Przyœpieszenie";
	private static String saveLakeButtonTitle = "Zapisz";
	private static String openLakeButtonTitle = "Otwórz";
	private static String recButtonTitle = "Nagrywaj";
	
	private static String newLakeObjectButtonTitle = "Nowy";
	private static String openLakeObjectButtonTitle = "Otwórz";
	private static String saveLakeObjectButtonTitle = "Zapisz";
	private static String modifyLakeObjectButtonTitle = "Modyfikuj";
	
	JButton playButton;
	JButton pauseButton;
	JButton forwardButton;
	JButton saveLakeButton;
	JButton openLakeButton;
	JButton recButton;
	
	JButton newLakeObjectButton;
	JButton openLakeObjectButton;
	JButton saveLakeObjectButton;
	JButton modifyLakeObjectButton;
	
	JFrame lakeWorldFrame;
	
	public MainFrame() {
		super(frameTitle);
		init();
	}

	public void init(){
		
		// creating Components
		playButton = new JButton(playButtonTitle);
		pauseButton = new JButton(pauseButtonTitle);
		forwardButton = new JButton(forwardButtonTitle);
		saveLakeButton = new JButton(saveLakeButtonTitle);
		openLakeButton = new JButton(openLakeButtonTitle);
		recButton = new JButton(recButtonTitle);
		
		newLakeObjectButton = new JButton(newLakeObjectButtonTitle);
		openLakeObjectButton = new JButton(openLakeObjectButtonTitle);
		saveLakeObjectButton = new JButton(saveLakeObjectButtonTitle);
		modifyLakeObjectButton = new JButton(modifyLakeObjectButtonTitle);
		
		
		LakeWorld lakeWorld = new LakeWorld();
		lakeWorldFrame = lakeWorld.getFrame();
		
		
		// placing Components
		
		// adding Listeners
		playButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				playButtonActionPerformed();
				
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				pauseButtonActionPerformed();
				
			}
		});

		forwardButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				forwardButtonActionPerformed();
				
			}
		});

		openLakeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				openLakeButtonActionPerformed();
				
			}
		});

		saveLakeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				saveLakeButtonActionPerformed();
				
			}
		});

		recButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				recButtonButtonActionPerformed();
				
			}
		});

		newLakeObjectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				newLakeObjectButtonActionPerformed();
				
			}
		});

		openLakeObjectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				openLakeObjectButtonActionPerformed();
				
			}
		});

		saveLakeObjectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				saveLakeObjectButtonActionPerformed();
				
			}
		});
		
		modifyLakeObjectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				modifyLakeObjectButtonActionPerformed();
				
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
			}
			
			public void windowIconified(WindowEvent arg0) {
			}
			
			public void windowDeiconified(WindowEvent arg0) {
			}
			
			public void windowDeactivated(WindowEvent arg0) {
			}
			
			public void windowClosing(WindowEvent arg0) {
				mainWindowClosing();
			}
			
			public void windowClosed(WindowEvent arg0) {
			}
			
			public void windowActivated(WindowEvent arg0) {
			}
		});
	}
	
	protected void modifyLakeObjectButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void saveLakeObjectButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void openLakeObjectButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void newLakeObjectButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void recButtonButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void saveLakeButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void openLakeButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void forwardButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void pauseButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void playButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	public void mainWindowClosing(){
		System.exit(0);
	}
}
