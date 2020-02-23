package app.parth.in.capstoneprojectstage_2.model;

public class Author {
    private String name;
    private String profilePicture;

    public Author() { }

    public Author(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }


    public String getName() {
        return name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
