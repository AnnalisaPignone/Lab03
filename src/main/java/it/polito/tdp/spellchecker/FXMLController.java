/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="cmbLingua"
    private ComboBox<String> cmbLingua; // Value injected by FXMLLoader

    @FXML // fx:id="lblErrori"
    private Label lblErrori; // Value injected by FXMLLoader

    @FXML // fx:id="lblTempo"
    private Label lblTempo; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtUtente"
    private TextArea txtUtente; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    	txtRisultato.setText(null);
    	txtUtente.setText(null);
    	cmbLingua.getItems().clear();
    	cmbLingua.getItems().addAll("English","Italiano");
    	lblErrori.setText(null);
    	lblTempo.setText(null); 
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	List <RichWord> daRestituire = new ArrayList <RichWord>(); 
    long start=System.nanoTime();
    	//controllo che lingua ha inserito 
    if (cmbLingua.getValue()==null)
    {
    	txtRisultato.setText("Please insert a language");
    	return; 
    }
    String supporto= cmbLingua.getValue();
    if(supporto.compareTo("English")==0) { 
    	model.letturaFileInglese(); 
    	if (txtUtente.getText()==null) {
    		txtRisultato.setText("Please insert some words");	
    		return;
    	}
    }
    if(supporto.compareTo("Italiano")==0) {
    	model.letturaFileItaliano(); 
    	if (txtUtente.getText()==null) {
    		txtRisultato.setText("Per favore inserisci delle parole");
    		return; 
    	}
    }
    //prendo la lista di parole
    List <String> inputTextList= new ArrayList <String>(); 
    String supporto2=txtUtente.getText();
    supporto2.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    String[] array=supporto2.split(" "); 
    for (int k=0; k<array.length; k++)
    {
    inputTextList.add(array[k]); 	
    }
    daRestituire = new ArrayList <RichWord>(model.spellCheckTextDichotomic(inputTextList));
    for (int k=0; k<daRestituire.size(); k++)
    {
    txtRisultato.appendText(daRestituire.get(k).getParola()); 	
    txtRisultato.appendText("\n"); 
    }
    if(supporto.compareTo("English")==0) {
    	if (model.numeroErrori()==1)
    		lblErrori.setText("The text contains "+model.numeroErrori()+" error"); 
    	else lblErrori.setText("The text contains "+model.numeroErrori()+" errors"); 
    }
    if(supporto.compareTo("Italiano")==0) {
    	if (model.numeroErrori()==1)
    	lblErrori.setText("Il testo contiene "+model.numeroErrori()+" errore"); 
    	else lblErrori.setText("Il testo contiene "+model.numeroErrori()+" errori");
    }
    long end=System.nanoTime();
    
    if(supporto.compareTo("English")==0)
    lblTempo.setText("Spell checked completed in "+ (end-start)/1E9+ " seconds"); 
    
    if(supporto.compareTo("Italiano")==0)
    lblTempo.setText("Spell checked completato in "+ (end-start)/1E9+ " secondi"); 	
    
    }

    public void setModel(Dictionary model) {
    	this.model=model; 
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtUtente != null : "fx:id=\"txtUtente\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingua.getItems().clear();
        cmbLingua.getItems().addAll("English","Italiano");

        }
    }






