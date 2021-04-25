package model;

public class Prescription {
    private int id;
    private String date;
    private Medication[] medications;

    public Prescription(int id, String date, Medication[] medications) {
        this.id = id;
        this.date = date;
        this.medications = medications;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    @Override
    public String toString() {
        String str = "Id: " + id + "Date: " + date;
        if (medications != null)
            for (Medication m : medications)
                if (m != null) {
                    str += m.toString();
                    str += '\n';
                }
        return str;
    }
}
