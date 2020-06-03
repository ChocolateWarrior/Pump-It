package com.pumpit.app.ui.viewmodel.home;

import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private String name;
    private String description;
    private String optionalInfo;
    private String sideNumber;
    private String sideTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOptionalInfo() {
        return optionalInfo;
    }

    public void setOptionalInfo(String optionalInfo) {
        this.optionalInfo = optionalInfo;
    }

    public String getSideNumber() {
        return sideNumber;
    }

    public void setSideNumber(String sideNumber) {
        this.sideNumber = sideNumber;
    }

    public String getSideTitle() {
        return sideTitle;
    }

    public void setSideTitle(String sideTitle) {
        this.sideTitle = sideTitle;
    }
}
