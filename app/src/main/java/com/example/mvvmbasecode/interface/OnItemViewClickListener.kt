package com.example.mvvmbasecode.`interface`

import android.view.View

interface OnItemViewClickListener {
    fun onItemViewClick(view: View, position: Int)
}

interface OnItemViewControlClickListener {
    fun onItemViewEdit(view: View, position: Int)
    fun onItemViewDelete(view: View, id: String)
}

interface OnAcceptRejectRequestListener {
    fun onAcceptRejectClick(id: Int, isAccept: Int)
}


interface SendAnyTwoParameterInterface {
    fun sendAction(id: Any, isAccept: Any)
}