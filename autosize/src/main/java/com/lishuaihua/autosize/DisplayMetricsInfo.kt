package com.lishuaihua.autosize

import android.os.Parcel
import android.os.Parcelable

/**
 * ================================================
 * [DisplayMetrics] 封装类
 * ================================================
 */
class DisplayMetricsInfo : Parcelable {
    var density: Float
    var densityDpi: Int
    var scaledDensity: Float
    var xdpi: Float
    var screenWidthDp = 0
    var screenHeightDp = 0

    constructor(density: Float, densityDpi: Int, scaledDensity: Float, xdpi: Float) {
        this.density = density
        this.densityDpi = densityDpi
        this.scaledDensity = scaledDensity
        this.xdpi = xdpi
    }

    constructor(density: Float, densityDpi: Int, scaledDensity: Float, xdpi: Float, screenWidthDp: Int, screenHeightDp: Int) {
        this.density = density
        this.densityDpi = densityDpi
        this.scaledDensity = scaledDensity
        this.xdpi = xdpi
        this.screenWidthDp = screenWidthDp
        this.screenHeightDp = screenHeightDp
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeFloat(density)
        dest.writeInt(densityDpi)
        dest.writeFloat(scaledDensity)
        dest.writeFloat(xdpi)
        dest.writeInt(screenWidthDp)
        dest.writeInt(screenHeightDp)
    }

    protected constructor(`in`: Parcel) {
        density = `in`.readFloat()
        densityDpi = `in`.readInt()
        scaledDensity = `in`.readFloat()
        xdpi = `in`.readFloat()
        screenWidthDp = `in`.readInt()
        screenHeightDp = `in`.readInt()
    }

    override fun toString(): String {
        return "DisplayMetricsInfo{" +
                "density=" + density +
                ", densityDpi=" + densityDpi +
                ", scaledDensity=" + scaledDensity +
                ", xdpi=" + xdpi +
                ", screenWidthDp=" + screenWidthDp +
                ", screenHeightDp=" + screenHeightDp +
                '}'
    }

    companion object {
        val CREATOR: Parcelable.Creator<DisplayMetricsInfo> = object : Parcelable.Creator<DisplayMetricsInfo> {
            override fun createFromParcel(source: Parcel): DisplayMetricsInfo? {
                return DisplayMetricsInfo(source)
            }

            override fun newArray(size: Int): Array<DisplayMetricsInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}