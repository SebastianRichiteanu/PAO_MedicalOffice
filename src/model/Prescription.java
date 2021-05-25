package model;

import java.sql.Date;

public class Prescription {
    private String barCode;
    private Date date;
    private Medication[] medications;

    public Prescription(String barCode, Date date, Medication[] medications) {
        this.barCode = barCode;
        this.date = date;
        this.medications = medications;
    }

    public String getBarCode() { return barCode; }

    public void setBarCode(String barCode) { this.barCode = barCode; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Medication[] getMedications() {
        return medications;
    }

    public void setMedications(Medication[] medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        String str = "BarCode: " + barCode + " Date: " + date + " ";
        if (medications != null) {
            int cnt = 1;
            for (Medication m : medications)
                if (m != null) {
                    str += "\nMedication " + cnt + ": ";
                    str += m.toString();
                    str += '\n';
                    ++cnt;
                }
        }
        return str;
    }
}
