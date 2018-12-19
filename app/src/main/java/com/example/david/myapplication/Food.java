package com.example.david.myapplication;

import org.json.JSONObject;

public class Food extends WelcomeActivity {
    private String productName;
    private float productPrice;
    private int productQty=0;

    public Food(String productName, float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
    public int getProductQty() {
        return productQty;
    }
    public void decreaseProductQty() {
        if(productQty == 0) return;
        this.productQty--;
    }
    public void increaseProductQty() {
        this.productQty++;
    }

    public void setProductQty(int productQty)
    {
        this.productQty = productQty;
    }
    public Food(JSONObject jsonObject){

    }
}
