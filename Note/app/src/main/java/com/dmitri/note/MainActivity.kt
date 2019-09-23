package com.dmitri.note

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.util.*


 class MainActivity : AppCompatActivity() {
    var outputStream: FileOutputStream? = null
    var fileInputStream: FileInputStream? = null
    var selectedDate :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEventButton.setOnClickListener{SaveNote()}
        mainCalendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Note that months are indexed from 0. So, 0 means January, 1 means february, 2 means march etc.
            selectedDate = dayOfMonth.toString() +(month + 1).toString()+year.toString()
            ReadNote(selectedDate.toString())
        }

    }
   private fun SaveNote(){
         val file: String? = selectedDate
         val date = Note()
         date.Name = selectedDate
         date.Text = newNote.text.toString()
        var gson = Gson()
        var jsonString = gson.toJson(date)

        val fileOutputStream:FileOutputStream
        try {
            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(jsonString.toByteArray())
            Toast.makeText(this@MainActivity, "Заметка сохранена", Toast.LENGTH_SHORT).show()
            newNote.setText("")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
     private fun DeleteFile(filename:String){
         try {
             deleteFile(filename)
             note.visibility = View.INVISIBLE
             deleteButton.visibility = View.INVISIBLE
             Toast.makeText(this@MainActivity, "Заметка удалена", Toast.LENGTH_SHORT).show()
         }
         catch (ex:Exception){
             ex.printStackTrace()
         }
     }
    private fun ReadNote(filename:String){
        try{
              fileInputStream = openFileInput(filename)

              var inputStreamReader = InputStreamReader(fileInputStream)
              val bufferedReader = BufferedReader(inputStreamReader)
              val stringBuilder: StringBuilder = StringBuilder()
              var text: String? = null
              while ({ text = bufferedReader.readLine(); text }() != null) {
                  stringBuilder.append(text)
              }
              if (stringBuilder != null) {
                  var jsonString : String = stringBuilder.toString()
                  var gson = Gson()
                  val noteText: Note = gson.fromJson<Note>(jsonString, Note::class.java)
                  newNote.setText("")
                  note.setText(noteText.Text)
                  note.visibility = View.VISIBLE
                  deleteButton.visibility = View.VISIBLE
                  deleteButton.setOnClickListener {DeleteFile(noteText.Name)}
              }

    }
        catch (ex:Exception){
            ex.printStackTrace()
            note.visibility = View.INVISIBLE
            deleteButton.visibility = View.INVISIBLE
        }
    }


}
