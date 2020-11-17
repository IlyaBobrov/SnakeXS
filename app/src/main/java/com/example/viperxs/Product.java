package com.example.viperxs;


public class Product {
    public String productName;
    public double productPrise;
    public long productWeight;
    public double productForOneGram;
    public double productForAnotherGram;

    public Product() {}

    public Product(String productName, double productPrise, long productWeight) {
        this.productName = productName;
        this.productPrise = productPrise;
        this.productWeight = productWeight;
        this.productForOneGram = 0.;
        this.productForAnotherGram = 0.;
    }

    public Product(String productName, double productPrise, long productWeight, double productForOneGram, double productForAnotherGram) {
        this.productName = productName;
        this.productPrise = productPrise;
        this.productWeight = productWeight;
        this.productForOneGram = productForOneGram;
        this.productForAnotherGram = productForAnotherGram;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrise() {
        return productPrise;
    }

    public void setProductPrise(double productPrise) {
        this.productPrise = productPrise;
    }

    public long getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(long productWeight) {
        this.productWeight = productWeight;
    }

    public double getProductForOneGram() {
        return productForOneGram;
    }

    public void setProductForOneGram(double productForOneGram) {
        this.productForOneGram = productForOneGram;
    }

    public double getProductForAnotherGram() {
        return productForAnotherGram;
    }

    public void setProductForAnotherGram(double productForAnotherGram) {
        this.productForAnotherGram = productForAnotherGram;
    }
}
