package com.example.qlthuvien.data.model;

import java.util.Comparator;

public class TimesComparator implements Comparator<ThaoMet> {
    @Override
    public int compare(ThaoMet thaoMet, ThaoMet t1) {
        return Integer.compare(thaoMet.getSolanmuon(), t1.getSolanmuon());
    }
}
