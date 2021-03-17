package model;

public class Prescription {
    private String date;
    private Medication[] medications;

    public Prescription(String date, Medication[] medications) {
        this.date = date;
        this.medications = medications;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Medication[] getMedications() {
        return medications;
    }

    public void setMedications(Medication[] medications) {
        this.medications = medications;
    }
}
