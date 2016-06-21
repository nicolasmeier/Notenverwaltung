package com.factbz.notenverwaltung.Model;

import java.util.Date;

/**
 * Created by Nicolas on 21.06.2016.
 */
public class Semester {
    private Date datumVon;
    private Date datumBis;
    private String name;

    public Date getDatumVon() {
        return datumVon;
    }

    public void setDatumVon(Date datumVon) {
        this.datumVon = datumVon;
    }

    public Date getDatumBis() {
        return datumBis;
    }

    public void setDatumBis(Date datumBis) {
        this.datumBis = datumBis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
