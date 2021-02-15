package com.example.lottoexam;

public class ItemList {
    private  String icon;
    private String itemname;

    public ItemList(String icon, String itemname) {
        this.icon = icon;
        this.itemname = itemname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }


}
