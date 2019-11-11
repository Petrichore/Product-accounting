package model;

import data.DataManager;
import view.addElementView.DialogManager;
import viewModel.MainViewVM;

import java.util.*;

public class Repository {

    private List<Product> productList;
    private static Repository instance = null;
    private DataManager dataManager = new DataManager();

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        productList = new ArrayList<>(List.of(
                new Product(generateId(), "Машина", 30, "12/11/2018", MainViewVM.CARS_CATEGORY),
                new Product(131200032L, "Мишка", 20, "13/11/2018", MainViewVM.STUFFED_TOYS_CATEGORY)));
        updateDataInFile();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(HashMap<String, String> productParams) {
        String productName = productParams.get(DialogManager.PRODUCT_NAME);
        double productPrice = Double.parseDouble(productParams.get(DialogManager.PRODUCT_PRICE));
        String receiptDate = productParams.get(DialogManager.PRODUCT_RECEIPT_DATE);
        String category = productParams.get(DialogManager.PRODUCT_CATEGORY);
        productList.add(new Product(generateId(), productName, productPrice, receiptDate, category));
        updateDataInFile();
    }

    public void delProductById(long id) {
        productList.removeIf(product -> product.getId() == id);
        updateDataInFile();
    }

    public Optional<Product> getProductById(long id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public void updateProduct(HashMap<String, String> productParams) {
        int index = 0;

        String productName = productParams.get(DialogManager.PRODUCT_EDIT_NAME);
        double productPrice = Double.parseDouble(productParams.get(DialogManager.PRODUCT_EDIT_PRICE));
        String receiptDate = productParams.get(DialogManager.PRODUCT_EDIT_RECEIPT_DATE);
        long id = Long.parseLong(productParams.get(DialogManager.PRODUCT_ID));

        for (Product product : productList) {
            if (product.getId() == id) {
                productList.set(index, new Product(id, productName, productPrice, receiptDate, ""));
                break;
            }
            index++;
        }
        updateDataInFile();
    }

    public ArrayList<Product> getCarsProductList(String carCategory) {
        ArrayList<Product> carsList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals(carCategory)) {
                carsList.add(product);
            }
        }
        return carsList;
    }

    public ArrayList<Product> getStuffedToysProductList(String stuffedToysCategory) {
        ArrayList<Product> stuffedToysList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals(stuffedToysCategory)) {
                stuffedToysList.add(product);
            }
        }
        return stuffedToysList;
    }

    public List<Product> getSortedList(Comparator<Product> comparator) {
        productList.sort(comparator);
        return productList;
    }

    private long generateId() {
        return System.currentTimeMillis();
    }

    private void updateDataInFile() {
        dataManager.writeProductDataToFile(productList, "product_list.txt");
    }
}
