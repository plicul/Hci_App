package com.example.hci_app.utils


import android.content.Context
import android.os.Environment
import android.util.Log
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import java.io.File

class Excel(path: File?) {
    //val root = Environment.getExternalStorageDirectory()
    var dir : File = File(path!!.absolutePath)
    var file : File
    var workbook : WritableWorkbook
    var sheet : WritableSheet
    var next_row=1
    var counter = 0

    init{
        dir.mkdirs()
        val files = dir.listFiles()


        file = File(dir, "log"+counter+".xls")
        while(file.exists()){
            counter++
            file=File(dir, "log"+counter+".xls")
        }
        workbook = Workbook.createWorkbook(file)
        sheet = workbook.createSheet("Log", 0)

        val timeLabel = Label(0, 0, "Time")
        val taskLabel = Label(1, 0, "Task")
        val difficultyLabel = Label(2, 0, "Difficulty")
        val itemsClickedLabel = Label(3, 0, "Items Clicked")

        sheet.addCell(timeLabel)
        sheet.addCell(taskLabel)
        sheet.addCell(difficultyLabel)
        sheet.addCell(itemsClickedLabel)


    }

    public fun addData(time: Double, currentTask: Int, difficulty: Int, itemsClicked: Int){

        val timeLabel = Label(0, next_row, time.toString())
        val taskLabel = Label(1, next_row, currentTask.toString())
        val difficultyLabel = Label(2, next_row, difficulty.toString())
        val itemsClickedLabel = Label(3, next_row, itemsClicked.toString())

        next_row++
        sheet.addCell(timeLabel)
        sheet.addCell(taskLabel)
        sheet.addCell(difficultyLabel)
        sheet.addCell(itemsClickedLabel)

    }



    public fun saveFile(){
        workbook.write()
        workbook.close()

    }
}