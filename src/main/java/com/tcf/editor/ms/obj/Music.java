package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;
import sun.plugin2.main.server.ModalitySupport;

public class Music {
    /**
     * used for referencing tracks.
     */
    private String trackId;
    /**
     * filename of the track
     */
    private String trackFile;
    /**
     * See header_music.py for a list of available flags
     */
    private FlagList trackFlags;
    /**
     * Shows in which situations or cultures the track can continue playing. See header_music.py for a list of available flags
     */
    private FlagList continueTrackFlags;

    public Music(String trackId, String trackFile, FlagList trackFlags, FlagList continueTrackFlags) {
        this.trackId = trackId;
        this.trackFile = trackFile;
        this.trackFlags = trackFlags;
        this.continueTrackFlags = continueTrackFlags;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackFile() {
        return trackFile;
    }

    public void setTrackFile(String trackFile) {
        this.trackFile = trackFile;
    }

    public FlagList getTrackFlags() {
        return trackFlags;
    }

    public void setTrackFlags(FlagList trackFlags) {
        this.trackFlags = trackFlags;
    }

    public FlagList getContinueTrackFlags() {
        return continueTrackFlags;
    }

    public void setContinueTrackFlags(FlagList continueTrackFlags) {
        this.continueTrackFlags = continueTrackFlags;
    }

    @Override
    public String toString() {
        return "(" +
                "\"" + trackId + '\"' +
                ", \"" + trackFile +
                "\", " + trackFlags +
                ", " + continueTrackFlags +
                ')';
    }

    public static void main(String[] args) {
        Music music = new Music("bogus","cant_find_this.ogg",FlagList.asList("mtf_sit_ambushed","mtf_sit_siege"),FlagList.asList("mtf_sit_fight"));
        System.out.println(music);
    }
}
