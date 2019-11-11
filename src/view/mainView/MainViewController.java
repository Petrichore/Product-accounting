package view.mainView;

import model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import viewModel.MainViewVM;

public class MainViewController {

    @FXML
    private TableColumn<Product, String> receiptDateColumn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Long> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<String, Double> priceColumn;

    @FXML
    private ToggleGroup sortRadioBtnGroup;

    @FXML
    private Button addProduct;
    @FXML
    private Button editProduct;
    @FXML
    private Button deleteProduct;

    @FXML
    private ComboBox<String> selectCategoryComboBox;

    private MainViewVM viewModel;

    public MainViewController(){
        viewModel = new MainViewVM();
    }

    public void initialize(){

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        receiptDateColumn.setCellValueFactory(new PropertyValueFactory<>("receiptDate"));

        viewModel.getCategoryList().subscribe(categoryList ->{
            selectCategoryComboBox.setValue(categoryList.get(0));
            selectCategoryComboBox.setItems(FXCollections.observableArrayList(categoryList));
        });

        viewModel.getProductsList().subscribe(productList -> {
            productTable.getItems().clear();
            productTable.getItems().addAll(productList);
        });

        selectCategoryComboBox.setOnAction(event -> {
            String value = selectCategoryComboBox.getValue();
            viewModel.onCategoryChanged(value);
        });

        addProduct.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            viewModel.onAddProductBtnPressed();
        });

        deleteProduct.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            long  productId = productTable.getSelectionModel().getSelectedItem().getId();
            viewModel.onDelProductBtnPressed(productId);
        });

        editProduct.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            long productId = productTable.getSelectionModel().getSelectedItem().getId();
            viewModel.onEditProductBtnPressed(productId);
        });

        sortRadioBtnGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedRadioBtn = (RadioButton)newValue;
            viewModel.onSortFieldChanged(selectedRadioBtn.getText());
        });
    }
}

