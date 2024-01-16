package be.vdab.movies.DTO;

public class DirectorDTO {
    private String fullName;

    public DirectorDTO(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}