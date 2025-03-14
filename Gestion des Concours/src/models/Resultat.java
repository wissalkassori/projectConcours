/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
public class Resultat {
    private int id;
    private int concoursId;
    private int candidatId;
    private float note;

    public Resultat(int id, int concoursId, int candidatId, float note) {
        this.id = id;
        this.concoursId = concoursId;
        this.candidatId = candidatId;
        this.note = note;
    }

    public Resultat(int concoursId, int candidatId, float note) {
        this(0, concoursId, candidatId, note);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getConcoursId() { return concoursId; }
    public void setConcoursId(int concoursId) { this.concoursId = concoursId; }
    public int getCandidatId() { return candidatId; }
    public void setCandidatId(int candidatId) { this.candidatId = candidatId; }
    public float getNote() { return note; }
    public void setNote(float note) { this.note = note; }
}
