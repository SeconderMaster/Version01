package com.example.lmj.a2hm2.Myhome;

/**
 * Created by lmj on 2016/9/11.
 */
public class Product {
    public String productImage;
    public String productName;
    public String productValue;
    public String productPlace;
    Product(String productImage,String productName,String productValue,String productPlace){
        this.productImage=productImage;
        this.productName=productName;
        this.productValue=productValue;
        this.productPlace=productPlace;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPlace() {
        return productPlace;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPlace(String productPlace) {
        this.productPlace = productPlace;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }
}
