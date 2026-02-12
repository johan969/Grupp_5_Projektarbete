package se.iths.johan.grupp_5_projektarbete.model;

import jakarta.persistence.*;


@Entity
@Table()
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String level;

    @Column
    private String description;

    @Column(name = "is_manager", nullable = false)
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


