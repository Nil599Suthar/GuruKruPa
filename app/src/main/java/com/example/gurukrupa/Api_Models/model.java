package com.example.gurukrupa.Api_Models;

public class model {
    String title;
    String ammount;

    public model(String title,String ammount) {
        this.title = title;
        this.ammount= ammount;
        }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }
}
