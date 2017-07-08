import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ControlPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private static final int LINE_HEIGHT = 22;
	private static final int SPACE = 5;
	
	private JButton inputSelect = new JButton("...");
	private JLabel inputLabel = new JLabel("Profil file :");
	
	private JTextField windSpeed = new JTextField("");
	private JLabel windSpeedLabel = new JLabel("Wind speed :");
	
	private JTextField maxRotorRotation = new JTextField("");
	private JLabel maxRotorRotationLabel = new JLabel("Rotation speed (RPS):");
	
	private JTextField bladeLength = new JTextField("");
	private JLabel bladeLengthLabel = new JLabel("Blade length (m):");
	
	private JLabel outputLabel = new JLabel("Target file :");
	
	private JButton outputSelect = new JButton("...");
	
	private JButton generate = new JButton("Generate");

	private ProfilViewPanel profilView = new ProfilViewPanel();
	
	private FileReader reader;
	private Profil p;

	
	public ControlPanel() {
		
		SpringLayout layout = new SpringLayout();
		int lineHeight = 0;
		
		//Ligne 1 #######################################
		lineHeight++;
		
		//placer le label inputLabel dans le coin en haut a gauche a 5 pixel du bord.
		layout.putConstraint(SpringLayout.WEST, inputLabel, SPACE, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, inputLabel, SPACE, SpringLayout.NORTH, this);
		
		//place le button de selection de fichier dans le coin en haut a droit.
		layout.putConstraint(SpringLayout.EAST, inputSelect, -SPACE, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, inputSelect, SPACE, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, inputSelect, SPACE, SpringLayout.EAST, inputLabel);
		
		//placer la textbox en haut au milieu de sorte a remplir tout l'espace restant.
//		layout.putConstraint(SpringLayout.WEST, input, SPACE, SpringLayout.EAST, inputLabel);
//		layout.putConstraint(SpringLayout.EAST, input, -SPACE, SpringLayout.WEST, inputSelect);
//		layout.putConstraint(SpringLayout.NORTH, input, SPACE, SpringLayout.NORTH, this);
		
		//placer le point le plus bas de la zone de texte au point le plus bas de la ligne
//		layout.putConstraint(SpringLayout.SOUTH, input, lineHeight*LINE_HEIGHT+SPACE, SpringLayout.NORTH, this);
		//pareil pour le label
		layout.putConstraint(SpringLayout.SOUTH, inputLabel, lineHeight*LINE_HEIGHT+SPACE, SpringLayout.NORTH, this);
		//et pareil pour le button
		layout.putConstraint(SpringLayout.SOUTH, inputSelect, lineHeight*LINE_HEIGHT+SPACE, SpringLayout.NORTH, this);
		
		//Ligne 2 #######################################
		lineHeight++;
		
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, windSpeedLabel, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, windSpeed, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, windSpeedLabel, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, windSpeed, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		
		//placer le windSpeedLabel tout a gauche
		layout.putConstraint(SpringLayout.WEST, windSpeedLabel, SPACE, SpringLayout.WEST, this);
		
		//placer la zone de texte entre le label et la fin du panel
		layout.putConstraint(SpringLayout.WEST, windSpeed, SPACE, SpringLayout.EAST, windSpeedLabel);
		layout.putConstraint(SpringLayout.EAST, windSpeed, -SPACE, SpringLayout.EAST, this);
		
		//Ligne 3 #######################################
		lineHeight++;
		
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, maxRotorRotationLabel, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, maxRotorRotation, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, maxRotorRotationLabel, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, maxRotorRotation, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		
		//placer le maxRotorRotationLabel tout a gauche
		layout.putConstraint(SpringLayout.WEST, maxRotorRotationLabel, SPACE, SpringLayout.WEST, this);
				
		//placer la zone de texte entre le label et la fin du panel
		layout.putConstraint(SpringLayout.WEST, maxRotorRotation, SPACE, SpringLayout.EAST, maxRotorRotationLabel);
		layout.putConstraint(SpringLayout.EAST, maxRotorRotation, -SPACE, SpringLayout.EAST, this);
		
		//Ligne 4 #######################################
		lineHeight++;
								
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, bladeLengthLabel, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, bladeLength, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, bladeLengthLabel, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, bladeLength, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
								
		//placer le outputLabel tout a gauche
		layout.putConstraint(SpringLayout.WEST, bladeLengthLabel, SPACE, SpringLayout.WEST, this);
				
		//placer le outputSelect tout a droit
		layout.putConstraint(SpringLayout.EAST, bladeLength, -SPACE, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, bladeLength, SPACE, SpringLayout.EAST, bladeLengthLabel);
		
		//Ligne 5 #######################################
		lineHeight++;
				
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, outputLabel, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
//		layout.putConstraint(SpringLayout.NORTH, output, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, outputSelect, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, outputLabel, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
//		layout.putConstraint(SpringLayout.SOUTH, output, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, outputSelect, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
				
		//placer le outputLabel tout a gauche
		layout.putConstraint(SpringLayout.WEST, outputLabel, SPACE, SpringLayout.WEST, this);
		
		//placer le outputSelect tout a droit
		layout.putConstraint(SpringLayout.EAST, outputSelect, -SPACE, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, outputSelect, SPACE, SpringLayout.EAST, outputLabel);
						
		//placer la zone de texte entre le label et le button
//		layout.putConstraint(SpringLayout.WEST, output, SPACE, SpringLayout.EAST, outputLabel);
//		layout.putConstraint(SpringLayout.EAST, output, -SPACE, SpringLayout.WEST, outputSelect);
								
		
		//Ligne 6 #######################################
		lineHeight++;
						
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, generate, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, generate, lineHeight*(LINE_HEIGHT+SPACE), SpringLayout.NORTH, this);
								
		//placer la zone de texte entre le label et le button
		layout.putConstraint(SpringLayout.WEST, generate, SPACE, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, generate, -SPACE, SpringLayout.EAST, this);
		
		//Ligne 7 #######################################
		lineHeight++;
						
		//placer le haut de chaque element de la ligne a la bonne hauteur
		layout.putConstraint(SpringLayout.NORTH, profilView, lineHeight*(LINE_HEIGHT+SPACE)-LINE_HEIGHT, SpringLayout.NORTH, this);
		//placer le bas de chaque  element de la ligne en bas de la ligne
		layout.putConstraint(SpringLayout.SOUTH, profilView, -SPACE, SpringLayout.SOUTH, this);
								
		//placer la zone de texte entre le label et le button
		layout.putConstraint(SpringLayout.WEST, profilView, SPACE, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, profilView, -SPACE, SpringLayout.EAST, this);
		
		
		
		super.setLayout(layout);
		
		inputSelect.addActionListener(this);
		outputSelect.addActionListener(this);
		generate.addActionListener(this);
		
		super.add(inputLabel);
		super.add(inputSelect);
		
		super.add(windSpeedLabel);
		super.add(windSpeed);
		
		super.add(maxRotorRotationLabel);
		super.add(maxRotorRotation);
		
		super.add(bladeLengthLabel);
		super.add(bladeLength);
		
		super.add(outputLabel);
		super.add(outputSelect);
		
		super.add(generate);
		
		super.add(profilView);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser();
		if (event.getSource() == inputSelect) {
			int result = fc.showOpenDialog(ControlPanel.this);
			if (result == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            inputSelect.setText(file.getPath());
	            reader = new FileReader(file.getPath());
	            p = new Profil();
	            p.setProfil(reader.getText());
	            p.center();
	            profilView.setProfil(p);
	            System.out.println("repaint");
	            profilView.repaint();
	            System.out.println(reader.getText());
	        } else {
	            System.out.println("invalide file");
	        }
		} else if (event.getSource() == outputSelect) {
			int result = fc.showSaveDialog(ControlPanel.this);
			if (result == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	             outputSelect.setText(file.getPath());
	        } else {
	            System.out.println("invalide file");
	        }
		} else if (event.getSource() == generate) {
			RotorBladeBuilder builder = new RotorBladeBuilder(p, Double.parseDouble(windSpeed.getText()), Double.parseDouble(maxRotorRotation.getText()), Double.parseDouble(bladeLength.getText()));
			BladeExporter exporter = new BladeExporter(outputSelect.getText());
			exporter.export(builder.build());
		}
	}
}