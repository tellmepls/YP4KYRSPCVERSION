package com.example.Vlarionov.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class furniture {
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100, message = "Поле должно содержать от 1 до 100 символов")
    public String type;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100, message = "Поле должно содержать от 1 до 100 символов")
    public String name;
    @NotBlank(message = "Поле не должно быть пустым!")
    @NotNull(message = "Поле не моет быть пустым")
    public String size;
    @NotBlank(message = "Поле не должно быть пустым!")
    @NotNull(message = "Поле не моет быть пустым")
    public String color;
    @NotNull(message = "Поле не может быть пустым")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]", message = "Дата не соотвествует маске ввода")
    public String releaseDate;

    @OneToOne(optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name="dopInformation_id")
    private dopInformation DopInformation;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Premise premise;

    @ManyToMany
    @JoinTable(name = "shop_furniture",
            joinColumns = @JoinColumn(name ="furniture_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<shop> shops;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsize() {
        return size;
    }

    public void setsize(String size) {
        this.size = size;
    }

    public String getcolor() {
        return color;
    }

    public void setcolor(String color) {
        this.color = color;
    }

    public String getreleaseDate() {
        return releaseDate;
    }

    public void setreleaseDate(String  releaseDate) {
        this.releaseDate = releaseDate;
    }

    public dopInformation getDopInformation() {
        return DopInformation;
    }
    public void setDopInformation(dopInformation DopInformation) {
        this.DopInformation = DopInformation;
    }
    public Premise getPremise() {
        return premise;
    }
    public void setPremise(Premise premise) {
        this.premise = premise;
    }

    public String getDesign(){return DopInformation.getdesign();}
    public String getnumberOfLockers(){return DopInformation.getnumberOfLockers();}

    public String getmeaning(){return premise.getmeaning();}
    public String getsquare(){return premise.getsquare();}

    private String username;
    private String password;
    private Boolean active;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }


    public List<shop> getShops() {
        return shops;
    }
    public void setShops(List<shop> shops1) {
        this.shops = shops1;
    }


    public furniture() { }

    public furniture(String username, String password, Boolean active,Set<Role> roles, String type, String name, String size, String color, String releaseDate, dopInformation DopInformation, Premise premise) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.type = type;
        this.name = name;
        this.size = size;
        this.color = color;
        this.releaseDate = releaseDate;
        this.DopInformation = DopInformation;
        this.premise = premise;
    }
}

