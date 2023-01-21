package com.example.hci_app

class Task(firstRowPosition: Int, secondRowPosition: Int, thirdRowPosition: Int) {
    var first: Int = firstRowPosition
    var second: Int = secondRowPosition
    var third: Int = thirdRowPosition


    public fun firstRow(): Int {
        return first
    }
    public fun secondRow(): Int {
        return second
    }
    public fun thirdRow(): Int {
        return third
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (first != other.first) return false
        if (second != other.second) return false
        if (third != other.third) return false

        return true
    }



}