
package com.moringaschool.allrecipe.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Digest {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("tag")
    @Expose
    private String tag;
//    @SerializedName("schemaOrgTag")
//    @Expose
//    private Object schemaOrgTag;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("hasRDI")
    @Expose
    private Boolean hasRDI;
    @SerializedName("daily")
    @Expose
    private Double daily;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("sub")
    @Expose
    private List<Sub> sub = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Digest() {
    }

    /**
     * 
     * @param schemaOrgTag
     * @param sub
     * @param total
     * @param unit
     * @param daily
     * @param hasRDI
     * @param label
     * @param tag
     */
    public Digest(String label, String tag, Object schemaOrgTag, Double total, Boolean hasRDI, Double daily, String unit, List<Sub> sub) {
        super();
        this.label = label;
        this.tag = tag;
//        this.schemaOrgTag = schemaOrgTag;
        this.total = total;
        this.hasRDI = hasRDI;
        this.daily = daily;
        this.unit = unit;
        this.sub = sub;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

//    public Object getSchemaOrgTag() {
//        return schemaOrgTag;
//    }
//
//    public void setSchemaOrgTag(Object schemaOrgTag) {
//        this.schemaOrgTag = schemaOrgTag;
//    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getHasRDI() {
        return hasRDI;
    }

    public void setHasRDI(Boolean hasRDI) {
        this.hasRDI = hasRDI;
    }

    public Double getDaily() {
        return daily;
    }

    public void setDaily(Double daily) {
        this.daily = daily;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Sub> getSub() {
        return sub;
    }

    public void setSub(List<Sub> sub) {
        this.sub = sub;
    }

}
