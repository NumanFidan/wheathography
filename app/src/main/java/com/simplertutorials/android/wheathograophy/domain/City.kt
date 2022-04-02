package com.simplertutorials.android.wheathograophy.domain

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

class City : Parcelable {
    var name: String
    var weather: Weather? = null

    constructor(name: String) {
        this.name = name
    }

    override fun equals(obj: Any?): Boolean {
        //To compare two city use name
        return if (obj !is City) false else name.equals(obj.name, ignoreCase = true)
    }

    override fun toString(): String {
        return name
    }

    protected constructor(`in`: Parcel) {
        name = (`in`.readValue(String::class.java.classLoader) as String?)!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Creator<City> = object : Creator<City> {
            override fun createFromParcel(`in`: Parcel): City? {
                return City(`in`)
            }

            override fun newArray(size: Int): Array<City?> {
                return arrayOfNulls(0)
            }
        }
    }
}
