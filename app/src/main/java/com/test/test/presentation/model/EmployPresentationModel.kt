package com.test.test.presentation.model

import android.os.Parcel
import android.os.Parcelable

data class EmployPresentationModel(
    val id: Int= 0,
    val address: String="",
    val age: Int=0,
    val contactNumber: String="",
    //dd/MM/YYYY
    val dob: String="",
    val email: String="",
    val name: String="",
    val imageUrl: String="",
    val salary: Double = 0.0
) : Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readDouble()
    )
    {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeInt(id)
        parcel.writeString(address)
        parcel.writeInt(age)
        parcel.writeString(contactNumber)
        parcel.writeString(dob)
        parcel.writeString(email)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeDouble(salary)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployPresentationModel>
    {
        override fun createFromParcel(parcel: Parcel): EmployPresentationModel
        {
            return EmployPresentationModel(parcel)
        }

        override fun newArray(size: Int): Array<EmployPresentationModel?>
        {
            return arrayOfNulls(size)
        }
    }
}