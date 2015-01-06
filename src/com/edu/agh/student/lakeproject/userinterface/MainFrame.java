package com.edu.agh.student.lakeproject.userinterface;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.fish.mousecontrolled.MouseControlledFish;
import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFish;
import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
import com.edu.agh.student.lakeproject.food.Food;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;

public class MainFrame extends JFrame implements LakeObjectFocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1231864224720584963L;

	private static String frameTitle = "Lake Project";
	
	private static String playpauseButtonTitle = "Pauza";
	private static String forwardButtonTitle = "Przyspiesz";
	private static String saveLakeButtonTitle = "Zapisz";
	private static String openLakeButtonTitle = "Otworz";
	private static String recButtonTitle = "Nagrywaj";
	
	private static String newLakeObjectButtonTitle = "Nowy";
	private static String openLakeObjectButtonTitle = "Otworz";
	private static String openLibraryButtonTitle = "Wczytaj Biblioteke";
	private static String saveLakeObjectButtonTitle = "Zapisz";
	private static String removeLakeObjectButtonTitle = "Usun";
	
	private static String reportTitle = "Raport";
	private static String playpauseReportButtonTitle = "Start";
	private static String resetReportButtonTitle = "Reset";
	private static String saveReportButtonTitle = "Zapisz";
	
	private static Panel canvas;
	
	JButton playpauseButton;
	JButton forwardButton;
	JButton saveLakeButton;
	JButton openLakeButton;
	JButton recButton;
	
	JButton newLakeObjectButton;
	JButton openLakeObjectButton;
	JButton openLibraryButton;
	JLabel classLabel;
	JLabel ageLabel;
	JLabel healthLabel;
	JLabel energyLabel;
	JButton saveLakeObjectButton;
	JButton removeLakeObjectButton;
	
	JLabel reportLabel;
	JButton playpauseReportButton;
	JButton resetReportButton;
	JButton saveReportButton;
	
	JPanel lakeWorldPanel;

	static int fps = 60;

	private LakeWorld lakeWorld;

	private LakeObject chosenObject;

	private Label feederLabel;

	private JSlider feederSlider;
	
	public MainFrame() {
		super(frameTitle);
		init();
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
		removeLakeObjectButton = new JButton(removeLakeObjectButtonTitle);
		removeLakeObjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeLakeObjectButtonActionPerformed();
			}
		});
		openLibraryButton = new JButton(openLibraryButtonTitle);
		
		classLabel = new JLabel();
		ageLabel = new JLabel();
		healthLabel = new JLabel();
		energyLabel = new JLabel();
		
		
		reportLabel = new JLabel(reportTitle);
		playpauseReportButton = new JButton(playpauseReportButtonTitle);
		playpauseReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lakeWorld.getReportManager().isStopped()){
					lakeWorld.getReportManager().start();
					playpauseReportButton.setText("Pauza");
					resetReportButton.setEnabled(false);
					saveReportButton.setEnabled(false);
					
				}else{
					lakeWorld.getReportManager().stop();
					playpauseReportButton.setText("Start");
					resetReportButton.setEnabled(true);
					saveReportButton.setEnabled(true);
				}
			}
		});
		resetReportButton = new JButton(resetReportButtonTitle);
		resetReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lakeWorld.getReportManager().reset();
			}
		});
		saveReportButton = new JButton(saveReportButtonTitle);
		saveReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveReportButtonActionPerformed();
			}
		});
		
		
		canvas = new Panel();
		
		getContentPane().setLayout(null);
		this.setBounds(0, 0, LakeConfiguration.width + 235, LakeConfiguration.height + 100);
		lakeWorld = new LakeWorld();
		lakeWorldPanel = new JPanel();
		
		lakeWorld.setFrame(lakeWorldPanel);
		lakeWorld.addLakeObjectFocusListener(this);
		
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(0, -10), new Vec2(LakeConfiguration.width, -10), new Vec2(LakeConfiguration.width, 0));
		lakeWorld.addBound(new Vec2(0, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height+10), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(0, 0), new Vec2(-10, 0), new Vec2(-10, LakeConfiguration.height), new Vec2(0, LakeConfiguration.height));
		lakeWorld.addBound(new Vec2(LakeConfiguration.width, 0), new Vec2(LakeConfiguration.width+10, 0), new Vec2(LakeConfiguration.width+10, LakeConfiguration.height), new Vec2(LakeConfiguration.width, LakeConfiguration.height));
		
		// placing Components
		
		getContentPane().add(newLakeObjectButton);
		getContentPane().add(openLakeObjectButton);
		getContentPane().add(saveLakeButton);
		getContentPane().add(saveLakeObjectButton);
		getContentPane().add(canvas);
		getContentPane().add(playpauseButton);
		getContentPane().add(recButton);
		getContentPane().add(forwardButton);
		getContentPane().add(openLakeButton);
		getContentPane().add(lakeWorldPanel);
		getContentPane().add(removeLakeObjectButton);
		//getContentPane().add(openLibraryButton);
		getContentPane().add(classLabel);
		getContentPane().add(ageLabel);
		getContentPane().add(healthLabel);
		getContentPane().add(energyLabel);
		getContentPane().add(reportLabel);
		getContentPane().add(playpauseReportButton);
		getContentPane().add(resetReportButton);
		getContentPane().add(saveReportButton);
		
		
		this.setVisible(true);
		
		newLakeObjectButton.setBounds(5, 5, 100, 45);
		openLakeObjectButton.setBounds(5, 55, 100, 45);
		//openLibraryButton.setBounds(5, 105, 100, 45);
		
		canvas.setBounds(5, 155, 100, 10);
		
		classLabel.setBounds(5, 175, 100, 22);
		ageLabel.setBounds(5, 200, 100, 22);
		healthLabel.setBounds(5, 225, 100, 22);
		energyLabel.setBounds(5, 250, 100, 22);
		
		saveLakeObjectButton.setBounds(5, 280, 100, 45);
		removeLakeObjectButton.setBounds(5, 330, 100, 45);
		
		lakeWorldPanel.setBounds(110, 5, LakeConfiguration.width, LakeConfiguration.height);
		
		reportLabel.setBounds(LakeConfiguration.width + 115, 5, 100, 22);
		
		playpauseReportButton.setBounds(LakeConfiguration.width + 115, 55, 100, 45);
		resetReportButton.setBounds(LakeConfiguration.width + 115, 105, 100, 45);
		saveReportButton.setBounds(LakeConfiguration.width + 115, 155, 100, 45);
		
		playpauseButton.setBounds(110, LakeConfiguration.height + 10, 100, 45);
		recButton.setBounds(215, LakeConfiguration.height + 10, 100, 45);
		forwardButton.setBounds(320, LakeConfiguration.height + 10, 100, 45);
		openLakeButton.setBounds(425, LakeConfiguration.height + 10, 100, 45);
		saveLakeButton.setBounds(530, LakeConfiguration.height + 10, 100, 45);
		
		feederSlider = new JSlider();
		feederSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					lakeWorld.setFeedProbability((int)source.getValue());
				}
			}
		});
		feederSlider.setValue(0);
		
		feederSlider.setBounds(5, 530, 100, 26);
		getContentPane().add(feederSlider);
		
		feederLabel = new Label("Karmnik");
		feederLabel.setBounds(5, 510, 100, 22);
		getContentPane().add(feederLabel);
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
	
	protected void removeLakeObjectButtonActionPerformed() {
		this.lakeWorld.removeLakeObject(chosenObject);
		
	}

	protected void saveReportButtonActionPerformed() {
		JFileChooser fc=new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				lakeWorld.getReportManager().generateReport(fc.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void playpauseButtonActionPerformed() {
		if(lakeWorld.isTimerStarted()){
		  lakeWorld.stopTimer();
		  playpauseButton.setText("Start");
		  forwardButton.setText("Przyspiesz");
		} else{
		  lakeWorld.startTimer();
		  playpauseButton.setText("Pauza");
		}
	}

	protected void saveLakeObjectButtonActionPerformed() {
		JFileChooser fc=new JFileChooser();
		if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
				objectOutput.writeObject(chosenObject);
				objectOutput.flush();
				objectOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void openLakeObjectButtonActionPerformed() {
		JFileChooser fc=new JFileChooser();
		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
				Fish newFish = (Fish) objectInput.readObject();
				lakeWorld.addLakeObject(newFish);
				objectInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	protected void newLakeObjectButtonActionPerformed() {
		try {
			Object[] options = getLakeObjectClassList();
			String chosenClass = (String) JOptionPane.showInputDialog(
				this,
				"Wybierz typ obiektow:",
				"Nowy Obiekt",
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				(String) options[0]);
			if(chosenClass != null){
				SpinnerNumberModel sModel = new SpinnerNumberModel(1, 0, 100, 1);
				JPanel msgDialogPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				JSpinner spinner = new JSpinner(sModel);
				msgDialogPanel.add(new JLabel("Wybierz ilosc obiektow:"));
				msgDialogPanel.add(spinner);
				if(JOptionPane.showConfirmDialog(this, msgDialogPanel, "Nowy Obiekt", JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION){
					int numberOfObjects = (int) spinner.getValue();
					synchronized (lakeWorld) {
						Class<?> clazz = Class.forName(chosenClass);
						for(int ii = 0; ii < numberOfObjects; ii++){
							Constructor<?> constructor = clazz.getConstructor(LakeWorld.class,Vec2.class);
							LakeObject object = (LakeObject) constructor.newInstance(new Object[]{lakeWorld,lakeWorld.getNewObjectPosition()});
							lakeWorld.addLakeObject(object);
						}
						//System.out.println(chosenClass+" "+numberOfObjects);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String[] getLakeObjectClassList() {
		
		try {
			String filename = "availableLakeObjectsClassNames";
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			ArrayList<String> lakeObjectClassList = new ArrayList<String>();
			while(reader.ready()){
				String x = reader.readLine();
				x = x.trim();
				lakeObjectClassList.add(x);
			}
			reader.close();
			return Arrays.copyOf(lakeObjectClassList.toArray(), lakeObjectClassList.size(), String[].class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] lakeObjectClassList = {"com.edu.agh.student.lakeproject.fish.veiltail.Veiltail"};
		return lakeObjectClassList;
		
	}

	protected void recButtonButtonActionPerformed() {
		if(lakeWorld.isWorldRecorded()){
			lakeWorld.stopRecording();
			recButton.setText("Nagrywaj");
		} else{
			JFileChooser fc=new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION && fc.getSelectedFile() != null && !fc.getSelectedFile().isDirectory()) {
				lakeWorld.startRecording(fc.getSelectedFile().getPath());
				recButton.setText("Zatrzymaj nagrywanie");
			}
			
		}
		
	}

	protected void saveLakeButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}

	protected void openLakeButtonActionPerformed() {
		// TODO Auto-generated method stub
		
	}
	
	protected void forwardButtonActionPerformed() {
		if(!lakeWorld.isTimerForwarded()){
		  lakeWorld.forwardTimer((long)Math.floor(1000*LakeConfiguration.stepTime/10));
		  playpauseButton.setText("Pauza");
		  forwardButton.setText("Normalna predkosc");
		} else{
		  lakeWorld.startTimer();
		  playpauseButton.setText("Pauza");
		  forwardButton.setText("Przyspiesz");
		}
		
	}

	public void mainWindowClosing(){
		System.exit(0);
	}

	public static Panel getCanvas() {
		return canvas;
	}

	private void updateFishLabels(){
		if(chosenObject instanceof Fish){
			Fish chosenFish = (Fish) this.chosenObject;
			String name = chosenFish.getSpecies().substring(chosenFish.getSpecies().lastIndexOf('.')+1);
			classLabel.setText(name);
			ageLabel.setText(		"A : " + Integer.toString(chosenFish.getAge()));
			energyLabel.setText(	"E : " + Integer.toString(chosenFish.getEnergy()) + "/" + Integer.toString(chosenFish.getMaxEnergy()));
			healthLabel.setText(	"H : " + Integer.toString(chosenFish.getHealth()) + "/" + Integer.toString(chosenFish.getMaxHealth()));
		}
	}
	
	@Override
	public void setChosenLakeObject(LakeObject chosen) {
		if(chosen instanceof Fish){
			chosenObject = chosen;
			updateFishLabels();
		} else if(chosen != null){
			chosenObject = chosen;
			clearFishLabels();
			classLabel.setText(chosen.getClass().getSimpleName());
		} else {
			clearFishLabels();
			classLabel.setText("");
		}
		
	}

	private void clearFishLabels() {
		Graphics gd = MainFrame.getCanvas().getGraphics();
		gd.clearRect(0, 0, 100, 10);
		ageLabel.setText("");
		energyLabel.setText("");
		healthLabel.setText("");
	}
}
