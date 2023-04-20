package application;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ItemController implements Initializable{

	
	
	public TextField getEnterField() {
		return EnterField;
	}
	public void setEnterField(TextField enterField) {
		EnterField = enterField;
	}
	public Label getItemName() {
		return ItemName;
	}
	public void setItemName(Label itemName) {
		ItemName = itemName;
	}
	public Label getItemPrice() {
		return ItemPrice;
	}
	public void setItemPrice(Label itemPrice) {
		ItemPrice = itemPrice;
	}
	public Label getItemDescription() {
		return ItemDescription;
	}
	public void setItemDescription(Label itemDescription) {
		ItemDescription = itemDescription;
	}
	public Label getMessage() {
		return Message;
	}
	public void setMessage(Label message) {
		Message = message;
	}
	public Button getBuyButton() {
		return BuyButton;
	}
	public void setBuyButton(Button buyButton) {
		BuyButton = buyButton;
	}
	@FXML
	private TextField EnterField;
	
	@FXML
	private Label ItemName;
	
	@FXML
	private Label ItemPrice;
	
	@FXML
	private Label ItemDescription;
	
	@FXML
	private Label Message;
	
	@FXML
	private Button BuyButton;
	
	@FXML
	private Label Index;
	
	private boolean messageSent = false;
	
	public boolean isMessageSent() {
		return messageSent;
	}
	public void setMessageSent(boolean messageSent) {
		this.messageSent = messageSent;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		BuyButton.setOnAction(new EventHandler<ActionEvent>() { // what to do when butt is pressed
			@Override
			public void handle(ActionEvent event) {
				//buy 0 item 5000
				messageSent = true;
			}
		});
		
	}
	
	public void process(String[] msg) {
		//buy fail low index item price CustomerName
		//buy fail sold
		//buy success index item price
		//buy complete
		if(msg[1].equals("success")) {
			Message.setText("join bidding");
			ItemPrice.setText(msg[5]);
		}
		else if(msg[1].equals("complete")) {
			Message.setText("Congradulations");
			ItemName.setText(msg[6]);
			ItemDescription.setText("sold");
			ItemPrice.setText(msg[5]);
			BuyButton.setDisable(false);
		}
		else if(msg[2].equals("low")){
			Message.setText("Bid again with more money!");
		}
		else if(msg[2].equals("sold")){
			Message.setText("Already sold, please bid another item");
		}
	}
	public Label getIndex() {
		return Index;
	}
	public void setIndex(Label index) {
		Index = index;
	}
	public String writeMessage() {
		// TODO Auto-generated method stub
		String toReturn;
		toReturn = "buy"+" "+Index.getText()+" "+ItemName.getText()+" "+EnterField.getText();
		return toReturn;
	}
	
	

}
