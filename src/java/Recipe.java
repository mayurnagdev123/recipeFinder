/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author mnagdev
 */
import javax.persistence.*;

@Entity
@Table(name="Recipe")
public class Recipe {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
private int s_no;
    private String recipe_id;
    private String recipe_name;
    private String recipe_URL;
    @ManyToOne
    @JoinColumn(name="recipe_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    
   

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_URL() {
        return recipe_URL;
    }

    public void setRecipe_URL(String recipe_URL) {
        this.recipe_URL = recipe_URL;
    }


}
