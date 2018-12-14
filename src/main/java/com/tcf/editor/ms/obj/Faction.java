package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    /**
     * Faction id: used for referencing factions in other files.
     * #     The prefix fac_ is automatically added before each faction id.
     */
    private String factionId;
    /**
     * Faction name.
     */
    private String factionName;
    /**
     * Faction flags. See header_factions.py for a list of available flags
     */
    private FlagList factionFlags;
    /**
     * Faction coherence. Relation between members of this faction.
     */
    private Double factionCoherence;
    /**
     * Relations. This is a list of relation records.
     * #     Each relation record is a tuple that contains the following fields:
     * #    5.1) Faction. Which other faction this relation is referring to
     * #    5.2) Value: Relation value between the two factions.
     * #         Values range between -1 and 1.
     */
    private List<Relation> relations;
    /**
     * list:[]
     */
    private Integer Ranks;
    /**
     * Faction color
     */
    private String factionColor;

    public Faction(String factionId, String factionName, FlagList factionFlags, Double factionCoherence, List<Relation> relations, String factionColor) {
        this.factionId = factionId;
        this.factionName = factionName;
        this.factionFlags = factionFlags;
        this.factionCoherence = factionCoherence;
        this.relations = relations;
        this.factionColor = factionColor;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public FlagList getFactionFlags() {
        return factionFlags;
    }

    public void setFactionFlags(FlagList factionFlags) {
        this.factionFlags = factionFlags;
    }

    public Double getFactionCoherence() {
        return factionCoherence;
    }

    public void setFactionCoherence(Double factionCoherence) {
        this.factionCoherence = factionCoherence;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public Integer getRanks() {
        return Ranks;
    }

    public void setRanks(Integer ranks) {
        Ranks = ranks;
    }

    public String getFactionColor() {
        return factionColor;
    }

    public void setFactionColor(String factionColor) {
        this.factionColor = factionColor;
    }

    @Override
    public String toString() {
        return "(\"" + factionId  +
                "\", \"" + factionName +
                "\", " + factionFlags +
                ", " + factionCoherence +
                ", " + relations +
                ", []" +
                ", " + factionColor +
                ')';
    }
    public static class Relation{
        private Faction faction;
        private Double value;

        public Relation(Faction faction, Double value) {
            this.faction = faction;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(\"" + faction.getFactionId() + "\", " + value + ')';
        }
    }

    public static void main(String[] args) {
        List<Relation> relations = new ArrayList<>();
        FlagList flagList = new FlagList();
        Faction swadia = new Faction("kingdom_1", "Kingdom of Swadia", flagList, 0.9,relations,"0xDD8844");
        Faction swadia2 = new Faction("kingdom_1", "Kingdom of Swadia", flagList, 0.9,relations,"0xDD8844");
        swadia2.getRelations().add(new Relation(swadia,0.1));
        swadia2.getRelations().add(new Relation(swadia,0.8));
        swadia2.getRelations().add(new Relation(swadia,0.9));
        swadia2.getRelations().add(new Relation(swadia,0.10));
        System.out.println(swadia2);
    }
}
