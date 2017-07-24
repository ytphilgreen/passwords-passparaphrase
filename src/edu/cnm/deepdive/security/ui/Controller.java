/**
 * 
 */
package edu.cnm.deepdive.security.ui;

import edu.cnm.deepdive.security.core.SecurePasswordGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @author Yolanda Philgreen
 *
 */
public class Controller {

  private SecurePasswordGenerator passwordGenerator = new SecurePasswordGenerator();
  
  @FXML
  private CheckBox passwordOptions;
  @FXML
  private CheckBox passphraseOptions;
  @FXML
  private CheckBox upperIncluded;
  @FXML
  private CheckBox lowerIncluded;
  @FXML
  private CheckBox punctuationIncluded;
  @FXML
  private CheckBox ambiguousExcluded;
  @FXML
  private Label passwordLengthLabel;
  @FXML
  private CheckBox digitsIncluded;
  @FXML
  private Slider passwordLength;
  @FXML
  private Button generatePassword;
  @FXML
  private TextField password;
  
 @FXML 
  private void initialize() {
    upperIncluded.setSelected(passwordGenerator.isUpperCaseInclude());
    lowerIncluded.setSelected(passwordGenerator.isLowerCaseInclude());
    digitsIncluded.setSelected(passwordGenerator.isNumbersInclude());
    punctuationIncluded.setSelected(passwordGenerator.isPunctuationInclude());
    ambiguousExcluded.setSelected(passwordGenerator.isAmbiguousExclude());
    passwordLengthLabel.setLabelFor(passwordLength);
    passwordLength.setValue(passwordGenerator.getMinLength());
    checkPasswordOptions();
    
  }
  
  @FXML
  private void checkPasswordOptions() {
    generatePassword.setDisable(! upperIncluded.isSelected()
        && !lowerIncluded.isSelected()
        && !digitsIncluded.isSelected()
        && !punctuationIncluded.isSelected());
  }
  
  @FXML
  private void generatePassword() {
    int length = (int) Math.round(passwordLength.getValue());
    passwordGenerator.setUpperCaseInclude(upperIncluded.isSelected());
    passwordGenerator.setLowerCaseInclude(lowerIncluded.isSelected());
    passwordGenerator.setNumbersInclude(digitsIncluded.isSelected());
    passwordGenerator.setPunctuationInclude(punctuationIncluded.isSelected());
    passwordGenerator.setAmbiguousExclude(ambiguousExcluded.isSelected());
    if(length> passwordGenerator.getMaxLength()){
      passwordGenerator.setMaxLength((int ) Math.round(passwordLength.getValue()));
      passwordGenerator.setMinLength((int) Math.round(passwordLength.getValue()));
    } else {
      passwordGenerator.setMinLength((int) Math.round(passwordLength.getValue()));
      passwordGenerator.setMaxLength((int) Math.round(passwordLength.getValue()));
        }
    password.setText(passwordGenerator.generate());
      }
}
