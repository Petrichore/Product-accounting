package model.util;

import model.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ReceiptComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        Date receiptDate1 = null;
        Date receiptDate2 = null;
        try {
            receiptDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(o1.getReceiptDate());
            receiptDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(o2.getReceiptDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (receiptDate1 != null && receiptDate2 != null) {
            if (receiptDate1.equals(receiptDate2)) {
                return 0;
            } else if (receiptDate1.before(receiptDate2)) {
                return -1;
            } else {
                return 1;
            }
        } else return 0;
    }
}
