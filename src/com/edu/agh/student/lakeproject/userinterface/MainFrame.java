package com.edu.agh.student.lakeproject.userinterface;

import java.awt.Canvas;
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
import com.edu.agh.student.lakeproject.fish.Gender;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;
import java.awt.Panel;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1231864224720584963L;

	private static String frameTitle = "Lake Project";
	
	private static String playpauseButtonTitle = "Start/Pauza";
	private static String forwardButtonTitle = "Przyspieszenie";
	private static String saveLakeButtonTitle = "Zapisz";
	private static String openLakeButtonTitle = "Otworz";
	private static String recButtonTitle = "Nagrywaj";
	
	private static String newLakeObjectButtonTitle = "Nowy";
	private static String openLakeObjectButtonTitle = "Otworz";
	private static String saveLakeObjectButtonTitle = "Zapisz";
	private static String modifyLakeObjectButtonTitle = "Modyfikuj";

	private static Panel canvas;
	
	JButton playpauseButton;
	JButton forwardButton;
	JButton saveLakeButton;
	JButton openLakeButton;
	JButton recButton;
	
	JButton newLakeObjectButton;
	JButton openLakeObjectButton;
	JButton saveLakeObjectButton;
	JButton modifyLakeObjectButton;
	
	JPanel lakeWorldPanel;

	static int fps = 60;
	
	public MainFrame() {
		super(frameTitle);
		init();
	}
	
	protected void createLakeObjects(LakeWorld lakeWorld){
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(300, 300), Gender.FEMALE));
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(250, 350), Gender.MALE));
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(200, 100), Gender.FEMALE));
		lakeWorld.addLakeObject(new Veiltail(lakeWorld, new Vec2(450, 350), Gender.MALE));
		lakeWorld.addLakeObject(new Obstacle(lakeWorld, 50.0f, new Vec2(100, 200)));
		lakeWorld.addLakeObject(new Food(lakeWorld, 10.0f, new Vec2(124, 267), 100));
		lakeWorld.addLakeObject(new Food(lakeWorld, 5.0f, new Vec2(200, 450), 10));
		lakeWorld.addLakeObject(new Food(lakeWorld, 5.0f, new Vec2(320, 260), 200));
		lakeWorld.addLakeObject(new MouseControlledFish(lakeWorld, new Vec2(100, 100)));
		lakeWorld.addLakeObject(new NeuralNetworkBrainFish(lakeWorld, new Vec2(300, 150)));
	}

	public void init(){
		
		// creating Components
		playpauseButton = new JButton(playpauseButtonTitle);
		forwardButton = new JButton(forwardButtonTitle);
		saveLakeButton = new JButton(saveLakeButtonTitle);
		openLakeButton = new JButton(openLakeButtonTitle);
		recButton = new JButton(recButtonTitle);
		
		newLakeObjectButton = new JButton(newLakeObjectButtonTitle);
		openLakeObjectButton = new JButton(openLakeObjectButtonTitle);
		saveLakeObjectButton = new JButton(saveLakeObjectButtonTitle);
		modifyLakeObjectButton = new JButton(modifyLakeObjectButtonTitle);
		
		
		
		canvas = new Panel();
		
		
		this.setLayout(null);
		this.setBounds(0, 0, LakeConfiguration.width + 130, LakeConfiguration.height + 100);
		LakeWorld lakeWorld = new LakeWorld();
		lakeWorldPanel = new JPanel();
		
		lakeWorld.setFrame(lakeWorldPanel);
		
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(0, -10), new Vec2(LakeConfiguration.width, -10), new Vec2(LakeConfiguration.width, 0));
		lakeWorld.addBound(new Vec2(0, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(-10, 0), new Vec2(-10, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(LakeConfiguration.width, 0), new Vec2(LakeConfiguration.width+10, 0), new Vec2(LakeConfiguration.width+10, LakeConfiguration.height), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		
		createLakeObjects(lakeWorld);
		
		// placing Components
		
		add(newLakeObjectButton);
		add(openLakeObjectButton);
		add(saveLakeButton);
		add(modifyLakeObjectButton);
		add(saveLakeObjectButton);
		add(canvas);
		add(playpauseButton);
		add(recButton);
		add(forwardButton);
		add(openLakeButton);
		add(lakeWorldPanel);
		this.setVisible(true);
		
		newLakeObjectButton.setBounds(5, 5, 100, 45);
		openLakeObjectButton.setBounds(5, 50, 100, 45);
		saveLakeObjectButton.setBounds(5, 95, 100, 45);
		modifyLakeObjectButton.setBounds(5, 140, 100, 45);
		lakeWorldPanel.setSize(LakeConfiguration.width, LakeConfiguration.height);
		lakeWorldPanel.setLocation(110, 5);
		canvas.setSize(100,10);
		canvas.setLocation(5, 235);
		playpauseButton.setBounds(110, LakeConfiguration.height + 10, 100, 45);
		recButton.setBounds(215, LakeConfiguration.height + 10, 100, 45);
		forwardButton.setBounds(320, LakeConfiguration.height + 10, 100, 45);
		openLakeButton.setBounds(425, LakeConfiguration.height + 10, 100, 45);
		saveLakeButton.setBounds(530, LakeConfiguration.height + 10, 100, 45);
		// adding Listeners
		playpauseButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				playpauseButtonActionPerformed();
				
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
	
	protected void playpauseButtonActionPerformed() {
		// TODO Auto-generated method stub
		
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
		
		
	}

	public void mainWindowClosing(){
		System.exit(0);
	}

	public static Panel getCanvas() {
		return canvas;
	}
}
