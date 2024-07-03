/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author CanhPC
 */
public class model_Permissons {

    private int id;
    private int user_id;
    private int can_remove;
    private int can_update;
    private int can_add;

    public model_Permissons() {
    }

    public model_Permissons(int id, int user_id, int can_remove, int can_update, int can_add) {
        this.id = id;
        this.user_id = user_id;
        this.can_remove = can_remove;
        this.can_update = can_update;
        this.can_add = can_add;
    }

    public model_Permissons(int can_remove, int can_update, int can_add) {
        this.can_remove = can_remove;
        this.can_update = can_update;
        this.can_add = can_add;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCan_remove() {
        return can_remove;
    }

    public void setCan_remove(int can_remove) {
        this.can_remove = can_remove;
    }

    public int getCan_update() {
        return can_update;
    }

    public void setCan_update(int can_update) {
        this.can_update = can_update;
    }

    public int getCan_add() {
        return can_add;
    }

    public void setCan_add(int can_add) {
        this.can_add = can_add;
    }

}
