package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ShoppingList_Product extends Product{

    private IntegerProperty productQuantity;

    public ShoppingList_Product(String productName, String productType, String productID, int productPrize, String productDetail, int productQuantity) {
        super(productName, productType, productID, productPrize, productDetail);
        this.productQuantity = new SimpleIntegerProperty(productQuantity);
    }

    public int getProductQuantity() {
        return productQuantity.get();
    }

    public IntegerProperty productQuantityProperty() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity.set(productQuantity);
    }
}
