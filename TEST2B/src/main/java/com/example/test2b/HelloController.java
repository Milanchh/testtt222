package com.example.test2b;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class HelloController implements Initializable {
    public Label welcomeText;
    public TextField uid;
    public TextField uname;
    public TextField uquantity;
    public TextField uprice;
    @FXML
    private TableView<inventory_management> tableview;
    @FXML
    private TableColumn<inventory_management, Integer> item_id;
    @FXML
    private TableColumn<inventory_management, String> item_name;
    @FXML
    private TableColumn<inventory_management, Integer> item_quantity;
    @FXML
    private TableColumn<inventory_management, Integer> item_price;
    ObservableList<inventory_management> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item_id.setCellValueFactory(new
                PropertyValueFactory<inventory_management, Integer>("item_id"));
        item_name.setCellValueFactory(new
                PropertyValueFactory<inventory_management, String>("item_name"));
        item_quantity.setCellValueFactory(new
                PropertyValueFactory<inventory_management, Integer>("item_quantity"));
        item_price.setCellValueFactory(new
                PropertyValueFactory<inventory_management, Integer>("item_price"));
        tableview.setItems(list);
    }

    public void fetchdata(ActionEvent actionEvent) {
        populateTable();

    }
    public void populateTable() {
        list.clear();
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/test2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM inventory_management";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id");
                String item_name = resultSet.getString("item_name");
                int item_quantity = resultSet.getInt("item_quantity");
                int item_price = resultSet.getInt("item_price");
                tableview.getItems().add(new inventory_management(item_id, item_name, item_quantity,
                        item_price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertdata(ActionEvent actionEvent) {
        String item_name = uname.getText();
        String item_quantity = uquantity.getText();
        String item_price = uprice.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/test2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `inventory_management`( `item_name`, `item_quantity`, `item_price`) VALUES ('"+item_name+"','"+item_quantity+"','"+item_price+"')";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatedata(ActionEvent actionEvent) {
        String item_id = uid.getText();
        String item_name = uname.getText();
        String item_quantity = uquantity.getText();
        String item_price= uprice.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/test2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "UPDATE `inventory_management` SET `item_name`='"+item_name+"',`item_quantity`='"+item_quantity+"',`item_price`='"+item_price+"' WHERE item_id='"+item_id+"' ";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletedata(ActionEvent actionEvent) {
        String item_id = uid.getText();




        String jdbcUrl = "jdbc:mysql://localhost:3306/test2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `inventory_management` WHERE item_id='"+item_id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            // Populate the table with data from the database

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loaddata(ActionEvent actionEvent) {
        String item_id = uid.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/test2a";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM inventory_management WHERE item_id='"+item_id+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {

                String item_name = resultSet.getString("item_name");
                String item_quantity = resultSet.getString("item_quantity");
                String item_price = resultSet.getString("item_price");

                uname.setText(item_name);
                uquantity.setText(item_quantity);
                uprice.setText(item_price);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}