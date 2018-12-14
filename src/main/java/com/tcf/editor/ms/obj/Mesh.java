package com.tcf.editor.ms.obj;

import com.tcf.editor.ms.obj.util.FlagList;

public class Mesh {
    /**
     * used for referencing meshes in other files. The prefix mesh_ is automatically added before each mesh id.
     */
    private String meshId;
    /**
     * See header_meshes.py for a list of available flags
     */
    private FlagList meshFlags;
    /**
     * Resource name of the mesh
     */
    private String meshResourceName;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshTranslationOnXAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshTranslationOnYAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshTranslationOnZAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshRotationAngleOverXAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshRotationAngleOverYAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshRotationAngleOverZAxis;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshXScale;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshYScale;
    /**
     * Will be done automatically when the mesh is loaded
     */
    private Integer meshZScale;

    public Mesh(String meshId, FlagList meshFlags, String meshResourceName, Integer meshTranslationOnXAxis, Integer meshTranslationOnYAxis, Integer meshTranslationOnZAxis, Integer meshRotationAngleOverXAxis, Integer meshRotationAngleOverYAxis, Integer meshRotationAngleOverZAxis, Integer meshXScale, Integer meshYScale, Integer meshZScale) {
        this.meshId = meshId;
        this.meshFlags = meshFlags;
        this.meshResourceName = meshResourceName;
        this.meshTranslationOnXAxis = meshTranslationOnXAxis;
        this.meshTranslationOnYAxis = meshTranslationOnYAxis;
        this.meshTranslationOnZAxis = meshTranslationOnZAxis;
        this.meshRotationAngleOverXAxis = meshRotationAngleOverXAxis;
        this.meshRotationAngleOverYAxis = meshRotationAngleOverYAxis;
        this.meshRotationAngleOverZAxis = meshRotationAngleOverZAxis;
        this.meshXScale = meshXScale;
        this.meshYScale = meshYScale;
        this.meshZScale = meshZScale;
    }

    public String getMeshId() {
        return meshId;
    }

    public void setMeshId(String meshId) {
        this.meshId = meshId;
    }

    public FlagList getMeshFlags() {
        return meshFlags;
    }

    public void setMeshFlags(FlagList meshFlags) {
        this.meshFlags = meshFlags;
    }

    public String getMeshResourceName() {
        return meshResourceName;
    }

    public void setMeshResourceName(String meshResourceName) {
        this.meshResourceName = meshResourceName;
    }

    public Integer getMeshTranslationOnXAxis() {
        return meshTranslationOnXAxis;
    }

    public void setMeshTranslationOnXAxis(Integer meshTranslationOnXAxis) {
        this.meshTranslationOnXAxis = meshTranslationOnXAxis;
    }

    public Integer getMeshTranslationOnYAxis() {
        return meshTranslationOnYAxis;
    }

    public void setMeshTranslationOnYAxis(Integer meshTranslationOnYAxis) {
        this.meshTranslationOnYAxis = meshTranslationOnYAxis;
    }

    public Integer getMeshTranslationOnZAxis() {
        return meshTranslationOnZAxis;
    }

    public void setMeshTranslationOnZAxis(Integer meshTranslationOnZAxis) {
        this.meshTranslationOnZAxis = meshTranslationOnZAxis;
    }

    public Integer getMeshRotationAngleOverXAxis() {
        return meshRotationAngleOverXAxis;
    }

    public void setMeshRotationAngleOverXAxis(Integer meshRotationAngleOverXAxis) {
        this.meshRotationAngleOverXAxis = meshRotationAngleOverXAxis;
    }

    public Integer getMeshRotationAngleOverYAxis() {
        return meshRotationAngleOverYAxis;
    }

    public void setMeshRotationAngleOverYAxis(Integer meshRotationAngleOverYAxis) {
        this.meshRotationAngleOverYAxis = meshRotationAngleOverYAxis;
    }

    public Integer getMeshRotationAngleOverZAxis() {
        return meshRotationAngleOverZAxis;
    }

    public void setMeshRotationAngleOverZAxis(Integer meshRotationAngleOverZAxis) {
        this.meshRotationAngleOverZAxis = meshRotationAngleOverZAxis;
    }

    public Integer getMeshXScale() {
        return meshXScale;
    }

    public void setMeshXScale(Integer meshXScale) {
        this.meshXScale = meshXScale;
    }

    public Integer getMeshYScale() {
        return meshYScale;
    }

    public void setMeshYScale(Integer meshYScale) {
        this.meshYScale = meshYScale;
    }

    public Integer getMeshZScale() {
        return meshZScale;
    }

    public void setMeshZScale(Integer meshZScale) {
        this.meshZScale = meshZScale;
    }

    @Override
    public String toString() {
        return "(" +
                "\"" + meshId +
                "\"," + meshFlags +
                ", \"" + meshResourceName + '\"' +
                ", " + meshTranslationOnXAxis +
                ", " + meshTranslationOnYAxis +
                ", " + meshTranslationOnZAxis +
                ", " + meshRotationAngleOverXAxis +
                ", " + meshRotationAngleOverYAxis +
                ", " + meshRotationAngleOverZAxis +
                ", " + meshXScale +
                ", " + meshYScale +
                ", " + meshZScale +
                ')';
    }

    public static void main(String[] args) {
        FlagList list = FlagList.asList("render_order_plus_1");
        Mesh mesh = new Mesh("pic_bandits",list,"pic_bandits",0,0,0,0,0,0,1,1,1);
        System.out.println(mesh);
    }
}
