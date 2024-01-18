package org.mpn;

import java.util.List;

public class Dataset {

    private final List<ProcessUnit> data;

    public Dataset(List<ProcessUnit> data) {
        this.data = data;
    }

    public int size() {
        return data.size();
    }
}
