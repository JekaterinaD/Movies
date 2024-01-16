package be.vdab.movies.DTO;

import be.vdab.movies.Actor;
import be.vdab.movies.Gender;

public class RoleDTO {
    private final String roleName;
    private final String actorFullName;
    private final Gender gender;

    public RoleDTO(String roleName, Actor actor, Gender gender) {
        this.roleName = roleName;
        this.actorFullName = actor != null ? actor.getFirstName() + " " + actor.getLastName() : null;
        this.gender = gender;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getActorFullName() {
        return actorFullName;
    }

    public Gender getGender() {
        return gender;
    }
}
