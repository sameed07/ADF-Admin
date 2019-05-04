package Model;

public class Notes {

    private String Notes;
    private String Image;

    public Notes(String notes, String image) {
        Notes = notes;
        Image = image;
    }

    public Notes() {
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}


