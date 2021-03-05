package com.example.compose.model

import android.os.Parcel
import android.os.Parcelable

data class LeaveTypes (
        val id:Long,
        val types: String,
        val description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString().toString(),
            parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(types)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LeaveTypes> {
        override fun createFromParcel(parcel: Parcel): LeaveTypes {
            return LeaveTypes(parcel)
        }

        override fun newArray(size: Int): Array<LeaveTypes?> {
            return arrayOfNulls(size)
        }
    }

}