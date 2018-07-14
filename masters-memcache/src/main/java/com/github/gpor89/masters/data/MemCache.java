package com.github.gpor89.masters.data;

import com.github.gpor89.masters.data.model.EpgData;

import java.util.LinkedList;
import java.util.List;

public class MemCache {

    private static List<EpgData> EPG_DATA_LIST;

    public static final void load(int size) {
        EPG_DATA_LIST = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            EPG_DATA_LIST.add(new EpgData());
        }
        System.out.println(EPG_DATA_LIST.size()+" items initialized.");
    }

    public static List<EpgData> getEpgDataList() {
        return EPG_DATA_LIST;
    }
}
