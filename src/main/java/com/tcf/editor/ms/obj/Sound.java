package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

import java.util.List;

public class Sound {
    /**
     * The prefix snd_ is automatically added before each sound id.
     */
    private String soundId;
    private FlagList soundFlags;
    private FlagList<String> soundFileNames;

    public String getSoundId() {
        return soundId;
    }

    public void setSoundId(String soundId) {
        this.soundId = soundId;
    }

    public FlagList getSoundFlags() {
        return soundFlags;
    }

    public void setSoundFlags(FlagList soundFlags) {
        this.soundFlags = soundFlags;
    }

    public List<String> getSoundFileNames() {
        return soundFileNames;
    }

    public void setSoundFileNames(FlagList<String> soundFileNames) {
        this.soundFileNames = soundFileNames;
    }

    public Sound(String soundId, FlagList soundFlags, FlagList<String> soundFileNames) {
        this.soundId = soundId;
        this.soundFlags = soundFlags;
        this.soundFileNames = soundFileNames;
    }

    @Override
    public String toString() {
        return "(" +
                "\"" + soundId +
                "\", " + soundFlags +
                ", [" + soundFileNames.join(",","\"") +
                "])";
    }

    public static void main(String[] args) {
        Sound sound = new Sound("click",FlagList.asList("sf_2d","sf_vol_1"),FlagList.asList("drum_3.ogg","drum_3.ogg"));
        System.out.println(sound);
    }
}
