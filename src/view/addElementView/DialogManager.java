package view.addElementView;

import javafx.collections.FXCollections;
import model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.List;

public class DialogManager {

    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_RECEIPT_DATE = "receiptDate";
    public static final String PRODUCT_CATEGORY = "productCtegory";

    public static final String PRODUCT_EDIT_NAME = "nameEdit";
    public static final String PRODUCT_EDIT_PRICE = "priceEdit";
    public static final String PRODUCT_EDIT_RECEIPT_DATE = "receiptDateEdit";
    public static final String PRODUCT_ID = "ID";

    public static Dialog<HashMap<String, String>> getAddProductInputDialog(List<String> productTypeList) {
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Новый продукт");
        dialog.setHeaderText("Заполните все ячейки, чтобы добавить новый продукт");

        ButtonType commitAddingBtnType = new ButtonType("Добавить", ButtonBar.ButtonData.APPLY);
        ButtonType cancelAddingBtnType = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(commitAddingBtnType, cancelAddingBtnType);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(30);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField productName = new TextField();
        productName.setPromptText("Название игрушки");

        TextField productPrice = new TextField();
        productPrice.setPromptText("Цена в BYN");

        TextField receiptDate = new TextField();
        receiptDate.setPromptText("dd/MM/YY");

        ComboBox<String> categoryComboBox = new ComboBox<>();
        categoryComboBox.setValue(productTypeList.get(0));
        categoryComboBox.setItems(FXCollections.observableArrayList(productTypeList));

        gridPane.add(new Label("Название"), 0, 0);
        gridPane.add(productName, 1, 0);
        gridPane.add(new Label("Цена"), 0, 1);
        gridPane.add(productPrice, 1, 1);
        gridPane.add(new Label("Дата поступления"), 0, 2);
        gridPane.add(receiptDate, 1, 2);
        gridPane.add(new Label("Категория товара"),2,0);
        gridPane.add(categoryComboBox,2,1);

        //Focus on productName field
        //Platform.runLater(productName::requestFocus);

//        Node commitAddingBtn = dialog.getDialogPane().lookupButton(commitAddingBtnType);
//        commitAddingBtn.setDisable(true);

        productPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}([.]\\d{0,2})?")) {
                productPrice.setText(oldValue);
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == commitAddingBtnType) {
                return new HashMap<>() {{
                    put(PRODUCT_NAME, productName.getText());
                    put(PRODUCT_PRICE, productPrice.getText());
                    put(PRODUCT_RECEIPT_DATE, receiptDate.getText());
                    put(PRODUCT_CATEGORY, categoryComboBox.getValue());
                }};
            }
            return null;
        });

        dialog.getDialogPane().setContent(gridPane);
        return dialog;
    }

    public static Dialog<HashMap<String, String>> getEditProductInputDialog(Product product) {
        Dialog<HashMap<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Редактирование продукта");
        dialog.setHeaderText("Измените необходимые поля");

        ButtonType commitAddingBtnType = new ButtonType("Сохранить", ButtonBar.ButtonData.APPLY);
        ButtonType cancelAddingBtnType = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(commitAddingBtnType, cancelAddingBtnType);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField productName = new TextField(product.getName());
        productName.setPromptText("Название игрушки");

        TextField productPrice = new TextField(String.valueOf(product.getPrice()));
        productPrice.setPromptText("Цена в BYN");

        TextField receiptDate = new TextField(product.getReceiptDate());
        receiptDate.setPromptText("dd/MM/YY");

        gridPane.add(new Label("Название"), 0, 0);
        gridPane.add(productName, 1, 0);
        gridPane.add(new Label("Цена"), 0, 1);
        gridPane.add(productPrice, 1, 1);
        gridPane.add(new Label("Дата поступления"), 0, 2);
        gridPane.add(receiptDate, 1, 2);

        productPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}([.]\\d{0,2})?")) {
                productPrice.setText(oldValue);
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == commitAddingBtnType) {
                return new HashMap<>() {{
                    put(PRODUCT_ID, String.valueOf(product.getId()).trim());
                    put(PRODUCT_EDIT_NAME, productName.getText().trim());
                    put(PRODUCT_EDIT_PRICE, productPrice.getText().trim());
                    put(PRODUCT_EDIT_RECEIPT_DATE, receiptDate.getText().trim());
                }};
            }
            return null;
        });

        dialog.getDialogPane().setContent(gridPane);
        return dialog;
    }

//    private boolean isInputValid(String name, String price, String receiptDate, String category){
//        boolean isValid = true;
//        if(name.isEmpty() || price.isEmpty() ){
//            return false;
//        }else{
//            for(int i = 0;i < receiptDate.length(); i++){
//
//            }
//        }
//    }
}
