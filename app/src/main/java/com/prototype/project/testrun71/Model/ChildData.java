package com.prototype.project.testrun71.Model;

import java.util.List;

public class ChildData {

    private String childName;
    private String childDeviceModel;
    private String childAppName;
    private String childPkgApps;
    private boolean isSelected;

    public ChildData() {
    }

    public ChildData(String childName, String childDeviceModel, String childAppName, String childPkgApps) {
        this.childName = childName;
        this.childDeviceModel = childDeviceModel;
        this.childAppName = childAppName;
        this.childPkgApps = childPkgApps;
    }

    public String getChildName() {
        return childName;
    }

    public String getChildDeviceModel() {
        return childDeviceModel;
    }

    public String getChildAppName() {
        return childAppName;
    }

    public String getChildPkgApps() {
        return childPkgApps;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
