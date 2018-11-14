package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class ShoppingList_Product extends Product{

    private IntegerProperty productQuantity;

    public ShoppingList_Product(StringProperty productName, StringProperty productType, StringProperty productID, IntegerProperty productPrize, StringProperty productDetail, IntegerProperty productQuantity) {
        super(productName, productType, productID, productPrize, productDetail);
        this.productQuantity = productQuantity;
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
