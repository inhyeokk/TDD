package com.rkddlsgur983.test.view.memo.entity

import android.os.Parcel
import android.os.Parcelable

class MemoItem(
    val title: String?,
    val category: MemoCategory,
    val contents: String?,
    val date: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        MemoCategory.values()[source.readInt()],
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeInt(category.ordinal)
        writeString(contents)
        writeString(date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MemoItem> = object : Parcelable.Creator<MemoItem> {
            override fun createFromParcel(source: Parcel): MemoItem = MemoItem(source)
            override fun newArray(size: Int): Array<MemoItem?> = arrayOfNulls(size)
        }
    }
}