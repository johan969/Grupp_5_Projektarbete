package se.iths.johan.grupp_5_projektarbete.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String level;
    private String description;
    private boolean isManager;


    public Role() {
    }

    public Role(String title, String level, String description, boolean isManager) {
        this.title = title;
        this.level = level;
        this.description = description;
        this.isManager = isManager;
    }

    public Role(Long id, String title, String level, String description, boolean isManager) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.description = description;
        this.isManager = isManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }
}


