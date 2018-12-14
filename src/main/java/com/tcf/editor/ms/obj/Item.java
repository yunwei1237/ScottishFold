package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.Function;
import com.tcf.editor.ms.obj.util.FlagList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Item {
    /**
     * used for referencing items in other files.
     * The prefix itm_ is automatically added before each item id.
     */
    private String itemId;
    /**
     * Name of item as it'll appear in inventory window
     */
    private String itemName;
    /**
     * Each mesh record is a tuple containing the following fields:
     */
    private List<MeshObj> listOfMeshes;
    /**
     * See header_items.py for a list of available flags.
     */
    private FlagList itemFlags;
    /**
     * Used for which animations this item is used with. See header_items.py for a list of available flags.
     */
    private FlagList itemCapabilities;
    /**
     * price
     */
    private Integer itemValue;
    /**
     * Bitwise-or of various stats about the item such as:
     * #      weight, abundance, difficulty, head_armor, body_armor,leg_armor, etc...
     */
    private FlagList itemStats;
    /**
     * Modifiers that can be applied to this item.
     */
    private FlagList modifierBits;
    /**
     * List of simple triggers to be associated with the item.
     */
    private List<Trigger> triggers = new ArrayList<>();

    public Item(String itemId, String itemName, List<MeshObj> listOfMeshes, FlagList itemFlags, FlagList itemCapabilities, Integer itemValue, FlagList itemStats, FlagList modifierBits) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.listOfMeshes = listOfMeshes;
        this.itemFlags = itemFlags;
        this.itemCapabilities = itemCapabilities;
        this.itemValue = itemValue;
        this.itemStats = itemStats;
        this.modifierBits = modifierBits;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<MeshObj> getListOfMeshes() {
        return listOfMeshes;
    }

    public void setListOfMeshes(List<MeshObj> listOfMeshes) {
        this.listOfMeshes = listOfMeshes;
    }

    public FlagList getItemFlags() {
        return itemFlags;
    }

    public void setItemFlags(FlagList itemFlags) {
        this.itemFlags = itemFlags;
    }

    public FlagList getItemCapabilities() {
        return itemCapabilities;
    }

    public void setItemCapabilities(FlagList itemCapabilities) {
        this.itemCapabilities = itemCapabilities;
    }

    public Integer getItemValue() {
        return itemValue;
    }

    public void setItemValue(Integer itemValue) {
        this.itemValue = itemValue;
    }

    public FlagList getItemStats() {
        return itemStats;
    }

    public void setItemStats(FlagList itemStats) {
        this.itemStats = itemStats;
    }

    public FlagList getModifierBits() {
        return modifierBits;
    }

    public void setModifierBits(FlagList modifierBits) {
        this.modifierBits = modifierBits;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

    @Override
    public String toString() {
        return "[" +
                "\"" + itemId +
                "\", \"" + itemName + '\"' +
                ", " + listOfMeshes +
                ", " + itemFlags +
                ", " + itemCapabilities +
                ", " + itemValue +
                ", " + itemStats +
                ", " + modifierBits +
                (triggers.size()>0?", " + triggers:"")+
                ']';
    }

    public static class MeshObj{
        /**
         * Mesh name
         */
        private Mesh mesh;
        /**
         * modifier Modifier bits that this mesh matches.
         */
        private Integer modifier;

        public MeshObj(Mesh mesh, Integer modifier) {
            this.mesh = mesh;
            this.modifier = modifier;
        }

        public Mesh getMesh() {
            return mesh;
        }

        public void setMesh(Mesh mesh) {
            this.mesh = mesh;
        }

        public Integer getModifier() {
            return modifier;
        }

        public void setModifier(Integer modifier) {
            this.modifier = modifier;
        }

        @Override
        public String toString() {
            return "(" +
                    "\"" + mesh.getMeshId() +
                    "\", " + modifier +
                    ')';
        }
    }

    public static void main(String[] args) {
        FlagList list = FlagList.asList("render_order_plus_1");
        Mesh mesh = new Mesh("pic_bandits",list,"pic_bandits",0,0,0,0,0,0,1,1,1);
        MeshObj obj = new MeshObj(mesh,0);
        List<MeshObj> meshObjs= new ArrayList<>();
        meshObjs.add(obj);
        Function fun = new Function("weapon_length", Arrays.asList("x"));
        FunctionInstance call = new FunctionInstance(fun,FlagList.asList("200"));
        FlagList funs = FlagList.asList(call,call);
        Item item = new Item("practice_sword","practice_sword",meshObjs,FlagList.asList("itp_primary","itp_type_one_handed_wpn"),FlagList.asList("itc_dagger"),3,funs,FlagList.asList("imodbits_none"));
        System.out.println(item);
    }
}
