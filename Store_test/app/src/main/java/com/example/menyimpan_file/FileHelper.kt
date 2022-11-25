package com.example.menyimpan_file

import android.content.Context

class FileHelper {


    internal object  FileHelper{
        fun writeToFile(fileModel: FileModel,context: Context){
            context.openFileOutput(fileModel.fileName, Context.MODE_PRIVATE).use {
                it.write(fileModel.data?.toByteArray())
            }
        }
        fun  readFromFile(context: Context,filename: String):FileModel{
            val fileModel = FileModel()
            fileModel.fileName = filename
            fileModel.data = context.openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold(""){some,text ->
                    "$some\n$text"
                }
            }
            return fileModel
        }

    }
}