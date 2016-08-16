package com.tobiascode.marvelclient.service.model;

import java.io.Serializable;
import java.util.List;

public class Backup implements Serializable {
    private int lastOffset;
    private List<? extends Serializable> items;

    public int getLastOffset() {
        return lastOffset;
    }

    public void setLastOffset(int lastOffset) {
        this.lastOffset = lastOffset;
    }

    public List<? extends Serializable> getItems() {
        return items;
    }

    public void setItems(List<? extends Serializable> items) {
        this.items = items;
    }
}
