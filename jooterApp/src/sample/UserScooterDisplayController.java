package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserScooterDisplayController {

    private ObservableList<Scooter> data;

    @FXML
    AnchorPane UsersScooterAnorchPane = new AnchorPane();
    @FXML
    TableView<Scooter> UserScootersTable = new TableView<>();
    @FXML
    TableColumn<Scooter,String> UserScooterModel = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterMaxVelocity = new TableColumn<>();
    @FXML
    TableColumn<Scooter,String> UserScooterColor = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterAvailability = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterBasket = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterRange = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterPrice = new TableColumn<>();
    @FXML
    TableColumn<Scooter,Integer> UserScooterBattery = new TableColumn<>();
    @FXML
    Label UserLabel = new Label();
    @FXML
    ImageView UserScooterImage = new ImageView();


    public void initialize(){

        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = DataSource.getInstance().queryScooters();
            while(rs.next()){

                data.add(new Scooter(rs.getString(DataSource.getColumnScooterModel()),rs.getInt(DataSource.getColumnScooterMaxVelocity()),rs.getString(DataSource.getColumnScooterColor()),rs.getInt(DataSource.getColumnScooterAvailability()),rs.getInt(DataSource.getColumnScooterBasket()),rs.getInt(DataSource.getColumnScooterRange()),rs.getInt(DataSource.getColumnScooterPrice()),rs.getInt(DataSource.getColumnScooterBattery())));

                }

            }catch(SQLException e){
            System.out.println("Cant query scooters");
            e.printStackTrace();
        }

        UserScootersTable.setItems(data);
        UserScooterModel.setCellValueFactory(new PropertyValueFactory<>("scooterModel"));
        UserScooterMaxVelocity.setCellValueFactory(new PropertyValueFactory<>("scooterMaxVelocity"));
        UserScooterColor.setCellValueFactory(new PropertyValueFactory<>("scooterColor"));
        UserScooterAvailability.setCellValueFactory(new PropertyValueFactory<>("scooterAvailability"));
        UserScooterBasket.setCellValueFactory(new PropertyValueFactory<>("scooterBasket"));
        UserScooterRange.setCellValueFactory(new PropertyValueFactory<>("scooterRange"));
        UserScooterPrice.setCellValueFactory(new PropertyValueFactory<>("scooterPrice"));
        UserScooterBattery.setCellValueFactory(new PropertyValueFactory<>("scooterBattery"));

        }



    }



