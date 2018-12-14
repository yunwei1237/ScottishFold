package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

public class MapIcon {
    /**
     * used for referencing map icons in other files.
     * The prefix icon_ is automatically added before each map icon id.
     */
    private String mapIconId;
    /**
     * See header_map icons.py for a list of available flags
     */
    private FlagList mapIconFlags;
    /**
     * Mesh name.
     */
    private String meshName;
    /**
     * Scale
     */
    private Double scale;
    /**
     * Sound
     */
    private Sound sound;
    /**
     * Offset x position for the flag icon.
     */
    private Double offsetX;
    /**
     * Offset y position for the flag icon.
     */
    private Double offsetY;
    /**
     * Offset z position for the flag icon.
     */
    private Double offsetZ;

    public MapIcon(String mapIconId, FlagList mapIconFlags, String meshName, Double scale, Sound sound, Double offsetX, Double offsetY, Double offsetZ) {
        this.mapIconId = mapIconId;
        this.mapIconFlags = mapIconFlags;
        this.meshName = meshName;
        this.scale = scale;
        this.sound = sound;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public String getMapIconId() {
        return mapIconId;
    }

    public void setMapIconId(String mapIconId) {
        this.mapIconId = mapIconId;
    }

    public FlagList getMapIconFlags() {
        return mapIconFlags;
    }

    public void setMapIconFlags(FlagList mapIconFlags) {
        this.mapIconFlags = mapIconFlags;
    }

    public String getMesh() {
        return meshName;
    }

    public void setMesh(String meshName) {
        this.meshName = meshName;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        scale = scale;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    public Double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Double offsetY) {
        this.offsetY = offsetY;
    }

    public Double getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(Double offsetZ) {
        this.offsetZ = offsetZ;
    }

    @Override
    public String toString() {
        return "(" +
                "\"" + mapIconId +
                "\", " + mapIconFlags +
                ", \"" + meshName +
                "\"," + scale +
                ", snd_" + sound.getSoundId() +
                ", " + offsetX +
                ", " + offsetY +
                ", " + offsetZ +
                ')';
    }

    public static void main(String[] args) {
        Sound sound = new Sound("footstep_grass",FlagList.asList("sf_2d","sf_vol_1"),FlagList.asList("drum_3.ogg","drum_3.ogg"));
        MapIcon icon = new MapIcon("player",FlagList.Empty(),"player",0.3,sound,0.15,0.172,0.0);
        System.out.println(icon);
    }
}
