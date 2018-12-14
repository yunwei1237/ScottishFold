package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

public class Animation {
    /**
     * used for referencing animations in other files. The prefix anim_ is automatically added before each animation-id .
     */
    private String animationId;
    /**
     * could be anything beginning with acf_ defined in header_animations.py
     */
    private FlagList animationFlags;
    /**
     * Animation sequences (list).
     */
    private FlagList animationSequences;

    public Animation(String animationId, FlagList animationFlags, FlagList animationSequences) {
        this.animationId = animationId;
        this.animationFlags = animationFlags;
        this.animationSequences = animationSequences;
    }

    public String getAnimationId() {
        return animationId;
    }

    public void setAnimationId(String animationId) {
        this.animationId = animationId;
    }

    public FlagList getAnimationFlags() {
        return animationFlags;
    }

    public void setAnimationFlags(FlagList animationFlags) {
        this.animationFlags = animationFlags;
    }

    public FlagList getAnimationSequences() {
        return animationSequences;
    }

    public void setAnimationSequences(FlagList animationSequences) {
        this.animationSequences = animationSequences;
    }

    @Override
    public String toString() {
        return "[" +
                "\"" + animationId +
                "\", " + animationFlags +
                ", " + animationSequences.join(",") +
                ']';
    }

    public static class AnimationSequence{
        /**
         * Duration of the sequence.
         */
        private Double duration;
        /**
         * Name of the animation resource.
         */
        private String resource;
        /**
         * Beginning frame of the sequence within the animation resource.
         */
        private Integer beginningFrame;
        /**
         * Ending frame of the sequence within the animation resource.
         */
        private Integer endingFrame;
        /**
         * Sequence flags: could be anything beginning with arf_ defined in header_animations.py
         */
        private FlagList sequenceFlags;

        public AnimationSequence(Double duration, String resource, Integer beginningFrame, Integer endingFrame, FlagList sequenceFlags) {
            this.duration = duration;
            this.resource = resource;
            this.beginningFrame = beginningFrame;
            this.endingFrame = endingFrame;
            this.sequenceFlags = sequenceFlags;
        }

        public Double getDuration() {
            return duration;
        }

        public void setDuration(Double duration) {
            this.duration = duration;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public Integer getBeginningFrame() {
            return beginningFrame;
        }

        public void setBeginningFrame(Integer beginningFrame) {
            this.beginningFrame = beginningFrame;
        }

        public Integer getEndingFrame() {
            return endingFrame;
        }

        public void setEndingFrame(Integer endingFrame) {
            this.endingFrame = endingFrame;
        }

        public FlagList getSequenceFlags() {
            return sequenceFlags;
        }

        public void setSequenceFlags(FlagList sequenceFlags) {
            this.sequenceFlags = sequenceFlags;
        }

        @Override
        public String toString() {
            return "[" + duration +
                    ",\"" + resource + '\"' +
                    ", " + beginningFrame +
                    ", " + endingFrame +
                    ", " + sequenceFlags +
                    ']';
        }
    }

    public static void main(String[] args) {
        FlagList<AnimationSequence> sequences = new FlagList<>();
        sequences.add(new AnimationSequence(1.09,"jump",22,48,FlagList.asList("arf_blend_in_1")));
        sequences.add(new AnimationSequence(1.09,"jump-2",22,48,FlagList.asList("arf_blend_in_1")));
        sequences.add(new AnimationSequence(1.09,"jump-3",22,48,FlagList.asList("arf_blend_in_1")));
        sequences.add(new AnimationSequence(1.09,"jump-4",22,48,FlagList.asList("arf_blend_in_1")));
        Animation animation = new Animation("jump",FlagList.asList("acf_enforce_lowerbody"),sequences);
        System.out.println(animation);
    }
}
