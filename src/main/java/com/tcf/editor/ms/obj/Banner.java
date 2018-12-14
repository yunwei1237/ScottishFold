package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

public class Banner {
    /**
     * used for referencing map icons in other files.
     * The prefix icon_ is automatically added before each map icon id.
     */
    private String bannerId;
    /**
     * See header_map icons.py for a list of available flags
     */
    private FlagList bannerFlags;
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
     * trigger
     */
    private SimpleTrigger trigger;

    public Banner(String bannerId, FlagList bannerFlags, String meshName, Double scale, Sound sound, SimpleTrigger trigger) {
        this.bannerId = bannerId;
        this.bannerFlags = bannerFlags;
        this.meshName = meshName;
        this.scale = scale;
        this.sound = sound;
        this.trigger = trigger;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public FlagList getBannerFlags() {
        return bannerFlags;
    }

    public void setBannerFlags(FlagList bannerFlags) {
        this.bannerFlags = bannerFlags;
    }

    public String getMeshName() {
        return meshName;
    }

    public void setMeshName(String meshName) {
        this.meshName = meshName;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public SimpleTrigger getTrigger() {
        return trigger;
    }

    public void setTrigger(SimpleTrigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "[" +
                "\"" + bannerId +
                "\", " + bannerFlags +
                ", \"" + meshName +
                "\", " + scale +
                ", "+(sound == null?"0":"snd_"+sound.getSoundId()) +
                 (trigger == null?"":"," + trigger) +
                ']';
    }
    public static void main(String[] args) {
        Banner banner = new Banner("banner_01",FlagList.Empty(),"map_flag_01",0.13,null,null);
        System.out.println(banner);
    }
}
