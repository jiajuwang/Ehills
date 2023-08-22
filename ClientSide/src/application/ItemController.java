package application;

import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
	
	@FXML
	private Label BiddingPrice;
	
	@FXML
	private Label Duration;
	
	public Label getBiddingPrice() {
		return BiddingPrice;
	}
	public void setBiddingPrice(Label biddingPrice) {
		BiddingPrice = biddingPrice;
	}
	public Label getDuration() {
		return Duration;
	}
	public void setDuration(Label duration) {
		Duration = duration;
	}
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
				//notify the client that button is pressed
				if(!(ItemDescription.getText().equals("sold"))){
					messageSent = true;
				}
				else {
					BuyButton.setDisable(true);
					Message.setText("sold");
				}
			}
		});
		
	}
	
	
	public void process(String[] msg) {
		//check message sent from clienthandler and set the GUI accordingly
		Platform.runLater(()->{
	
		if(msg[1].equals("success")) {
			if(msg[0].equals("buy")) {
				Message.setText("join bidding");
			}
			ItemPrice.setText(msg[5]);
		}
		else if(msg[1].equals("complete")) {
			if(msg[0].equals("buy")) {
				Message.setText("Congradulations");
			}
			ItemName.setText("Bought by "+msg[2]);
			ItemDescription.setText("sold");
			ItemPrice.setText(msg[5]);
			BuyButton.setDisable(true);
		}
		else if(msg[2].equals("low")){
			Message.setText("Bid again with more money!");
		}
		else if(msg[2].equals("sold")){
			Message.setText("Already sold, please bid another item");
		}
		}
	
		);
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
