package Model;

import javafx.beans.property.*;

public class Product {


    private final StringProperty productName;
    private final StringProperty productType;
    private final StringProperty productID;
    private final IntegerProperty productPrize;
    private final StringProperty productDetail;
    private final IntegerProperty productState;

    public Product(String productName, String productType, String productID, int productPrize,
                   String productDetail, int productState) {
        this.productName = new SimpleStringProperty(productName);
        this.productType = new SimpleStringProperty(productType);
        this.productID = new SimpleStringProperty(productID);
        this.productPrize = new SimpleIntegerProperty(productPrize);
        this.productDetail = new SimpleStringProperty(productDetail);
        this.productState = new SimpleIntegerProperty(productState);
    }

    public String getProductDetail() {
        return productDetail.get();
    }

    public StringProperty productDetailProperty() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail.set(productDetail);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getProductType() {
        return productType.get();
    }

    public StringProperty productTypeProperty() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType.set(productType);
    }

    public String getProductID() {
        return productID.get();
    }

    public StringProperty productIDProperty() {
        return productID;
    }

    public int getProductState() {
        return productState.get();
    }

    public IntegerProperty productStateProperty() {
        return productState;
    }

    public void setProductState(int productState) {
        this.productState.set(productState);
    }

    public int getProductPrize() {
        return productPrize.get();
    }

    public IntegerProperty productPrizeProperty() {
        return productPrize;
    }

    public void setProductPrize(int productPrize) {
        this.productPrize.set(productPrize);
    }
}