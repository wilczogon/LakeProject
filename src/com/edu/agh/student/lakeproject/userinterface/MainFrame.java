package com.edu.agh.student.lakeproject.userinterface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.fish.mousecontrolled.MouseControlledFish;
import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFish;
import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1231864224720584963L;

	private static String frameTitle = "Lake Project";
	
	private static String playButtonTitle = "Start";
	private static String pauseButtonTitle = "Pauza";
	private static String forwardButtonTitle = "Przyspieszenie";
	private static String saveLakeButtonTitle = "Zapisz";
	private static String openLakeButtonTitle = "Otworz";
	private static String recButtonTitle = "Nagrywaj";
	
	private static String newLakeObjectButtonTitle = "Nowy";
	private static String openLakeObjectButtonTitle = "Otworz";
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
	
	JPanel lakeWorldPanel;
	
	public MainFrame() {
		super(frameTitle);
		init();
	}
	
	protected void createLakeObjects(LakeWorld lakeWorld){
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(300, 300)));
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(250, 350)));
		lakeWorld.addLakeObject(new Obstacle(lakeWorld, 50.0f, new Vec2(100, 200)));
		lakeWorld.addLakeObject(new Food(lakeWorld, 10.0f, new Vec2(124, 267), 100));
		lakeWorld.addLakeObject(new Food(lakeWorld, 5.0f, new Vec2(200, 450), 10));
		lakeWorld.addLakeObject(new Food(lakeWorld, 5.0f, new Vec2(320, 260), 200));
		lakeWorld.addLakeObject(new MouseControlledFish(lakeWorld, new Vec2(100, 100)));
		lakeWorld.addLakeObject(new NeuralNetworkBrainFish(lakeWorld, new Vec2(300, 150)));
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
		
		this.setBounds(0, 0, 800, 600);
		LakeWorld lakeWorld = new LakeWorld();
		lakeWorldPanel = new JPanel();
		lakeWorldPanel.setBounds(10, 10, LakeConfiguration.width, LakeConfiguration.height);
		lakeWorld.setFrame(lakeWorldPanel);
		//lakeWorldFrame = lakeWorld.getFrame();
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(0, -10), new Vec2(LakeConfiguration.width, -10), new Vec2(LakeConfiguration.width, 0));
		lakeWorld.addBound(new Vec2(0, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(-10, 0), new Vec2(-10, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(LakeConfiguration.width, 0), new Vec2(LakeConfiguration.width+10, 0), new Vec2(LakeConfiguration.width+10, LakeConfiguration.height), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		
		createLakeObjects(lakeWorld);
		
		// placing Components
		
		Container pane = this.getContentPane();
		pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
		
		JPanel objectPanel = new JPanel();
		JPanel lakePanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.X_AXIS));
		//((BoxLayout)controlPanel.getLayout()).;
		objectPanel.setLayout(new BoxLayout(objectPanel,BoxLayout.Y_AXIS));
		lakePanel.setLayout(new BoxLayout(lakePanel,BoxLayout.Y_AXIS));
		
		objectPanel.add(newLakeObjectButton);
		objectPanel.add(openLakeObjectButton);
		objectPanel.add(saveLakeButton);
		objectPanel.add(modifyLakeObjectButton);
		controlPanel.add(playButton);
		controlPanel.add(pauseButton);
		controlPanel.add(recButton);
		controlPanel.add(forwardButton);
		controlPanel.add(openLakeButton);
		controlPanel.add(saveLakeObjectButton);
		pane.add(objectPanel);
		pane.add(lakePanel);
		lakePanel.add(lakeWorldPanel);
		lakePanel.add(controlPanel);
		this.setVisible(true);
		
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
		
		lakeWorld.start();
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
