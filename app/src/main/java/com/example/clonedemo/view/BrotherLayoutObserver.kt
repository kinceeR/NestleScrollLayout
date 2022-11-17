package com.example.clonedemo.view

interface BrotherLayoutObserver {
    fun notifyIgnore()
}
interface BrotherLayoutObservable{
    fun setObserver(brother: BrotherLayoutObserver)
}