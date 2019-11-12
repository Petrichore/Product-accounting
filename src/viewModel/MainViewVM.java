package viewModel;

import model.Product;
import model.util.NameComparator;
import model.util.PriceComparator;
import model.util.ReceiptComparator;
import javafx.scene.control.Dialog;
import liveData.LiveData;
import model.Repository;
import view.addElementView.DialogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MainViewVM {

    private final String NAME_SORT_FIELD = "Название";
    private final String PRICE_SORT_FIELD = "Цена";
    private final String RECEIPT_DATE_SORT_FIELD = "Дата поступления";

    private static final String ALL_PRODUCTS_CATEGORY = "Все товары";
    public static final String CARS_CATEGORY = "Машины";
    public static final String STUFFED_TOYS_CATEGORY = "Мягкие игрушки";

    private LiveData<List<Product>> productsList;
    private LiveData<List<String>> categoryList;
    private Repository repository;

    public MainViewVM() {
        repository = Repository.getInstance();
        categoryList = new LiveData<>();
        categoryList.setValue(new ArrayList<>(List.of(ALL_PRODUCTS_CATEGORY, CARS_CATEGORY, STUFFED_TOYS_CATEGORY)));
        productsList = new LiveData<>();
        productsList.setValue(repository.getProductList());
    }

    public LiveData<List<Product>> getProductsList() {
        return productsList;
    }

    public LiveData<List<String>> getCategoryList() {
        return categoryList;
    }

    public void onAddProductBtnPressed() {
        Dialog<HashMap<String, String>> dialog = DialogManager.getAddProductInputDialog(categoryList.getValue());
        Optional<HashMap<String, String>> result = dialog.showAndWait();
        if (result.isPresent()) {
            repository.addProduct(result.get());
            productsList.setValue(repository.getProductList());
        }
    }

    public void onEditProductBtnPressed(long productId) {

        Optional<Product> optionalProduct = repository.getProductById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            Dialog<HashMap<String, String>> dialog = DialogManager.getEditProductInputDialog(product);
            Optional<HashMap<String, String>> result = dialog.showAndWait();

            if(result.isPresent()){
                HashMap<String,String> inputParams = result.get();
                boolean isResultValid = Boolean.parseBoolean(inputParams.get(DialogManager.VALIDATION_RESULT));
                if(isResultValid){
                    repository.updateProduct(inputParams);
                    productsList.setValue(repository.getProductList());
                }
            }else{
                //show editDialog with warning and last input data
            }
        }
    }

    public void onDelProductBtnPressed(long id) {
        repository.delProductById(id);
        productsList.setValue(repository.getProductList());
    }

    public void onSortFieldChanged(String sortField){
        switch (sortField){
            case NAME_SORT_FIELD:
                productsList.setValue(repository.getSortedList(new NameComparator()));
                break;
            case PRICE_SORT_FIELD:
                productsList.setValue(repository.getSortedList(new PriceComparator()));
                break;
            case RECEIPT_DATE_SORT_FIELD:
                productsList.setValue(repository.getSortedList(new ReceiptComparator()));
                break;
        }
    }

    public void onCategoryChanged(String category) {
        switch (category) {
            case ALL_PRODUCTS_CATEGORY:
                productsList.setValue(repository.getProductList());
                break;
            case CARS_CATEGORY:
                productsList.setValue(repository.getCarsProductList(category));
                break;
            case STUFFED_TOYS_CATEGORY:
                productsList.setValue(repository.getStuffedToysProductList(category));
                break;
        }
    }
}
