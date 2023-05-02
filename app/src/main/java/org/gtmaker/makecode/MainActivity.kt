package org.gtmaker.makecode

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    private lateinit var buttonCargar: Button
    private lateinit var editTextIdGame: EditText
    private lateinit var imageViewInstructions: ImageView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        myWebView = findViewById(R.id.webView)
        this.buttonCargar = findViewById(R.id.buttonLoad)
        this.editTextIdGame = findViewById(R.id.editTextIdGame)
        myWebView.setBackgroundResource(R.drawable.emulador_makecode_arcade)
        myWebView.settings.javaScriptEnabled = true

        this.buttonCargar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                if(!editTextIdGame.text.equals("")) {
                    imageViewInstructions.visibility=View.INVISIBLE
                    myWebView.visibility=View.VISIBLE
                    openGame(editTextIdGame.text.toString())
                }

            }
        })
    }

    fun openGame(idGame: String): Boolean {
        val projectUrl: String = "<div style=\"position:relative;height:0;padding-bottom:117.6%;" +
                "overflow:hidden;\"><iframe style=\"position:absolute;top:0;left:0;width:100%;" +
                "height:100%;\" src=\"https://arcade.makecode.com/---run?" +
                "id=" +
                idGame +
                " " +
                "\" allowfullscreen=\"allowfullscreen\" sandbox=\"allow-popups " +
                "allow-forms allow-scripts allow-same-origin\" frameborder=\"0\"></iframe></div>"
        val mimeType = getString(R.string.mimetype)
        val encoding = getString(R.string.encoding)
        myWebView.loadDataWithBaseURL("", projectUrl, mimeType, encoding, "")
        myWebView.reload()

        return true
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if (id == R.id.about) {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
        return true
    }

}
