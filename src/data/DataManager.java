package data;

import model.Product;

import java.io.*;
import java.util.List;

public class DataManager {

    public void writeProductDataToFile(List<Product> productList, String fileName) {
        try ( FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Product product: productList){
                bufferedWriter.write(product.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getProductDataFromFile(String fileName) {

    }
}
