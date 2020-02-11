package com.gmail.ahuliaiev;

import javax.persistence.*;

@Entity
public class MenuRestaraunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String dishName;
    private Double dishCost;
    private Double dishWeight;
    private Boolean discount;

    public MenuRestaraunt() {
    }

    public MenuRestaraunt(String dishName, double dishCost, double dishWeight, boolean discount) {
        this.dishName = dishName;
        this.dishCost = dishCost;
        this.dishWeight = dishWeight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishCost() {
        return dishCost;
    }

    public void setDishCost(double dishCost) {
        this.dishCost = dishCost;
    }

    public double getDishWeight() {
        return dishWeight;
    }

    public void setDishWeight(double dishWeight) {
        this.dishWeight = dishWeight;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "MenuRestaraunt{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", dishCost=" + dishCost +
                ", dishWeight=" + dishWeight +
                ", discount=" + discount +
                '}';
    }
}



