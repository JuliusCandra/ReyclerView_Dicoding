package com.dev_candra.reyclerview.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev_candra.reyclerview.R
import com.dev_candra.reyclerview.adapter.CardViewAdapter
import com.dev_candra.reyclerview.adapter.GridAdapter
import com.dev_candra.reyclerview.adapter.ListHeroAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.dev_candra.reyclerview.model.DataHero as DataHero

class MainActivity : AppCompatActivity() {

    private lateinit var adapterHero: ListHeroAdapter
    private lateinit var cardViewAdapter: CardViewAdapter
    private lateinit var gridViewAdapter : GridAdapter
    private val listHero = ArrayList<DataHero>()
    private var tittle = "Candra Julius Sinaga"
    private var subtittle = "List Hero"
    private var mode = 0

    companion object{
        private const val STATE_MODE = "state_mode"
        private const val STATE_SUBTITLE = "state_subtittle"
        private const val STATE_LIST = "state_list"
        private const val STATE_TITTLE = "state_tittle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterHero = ListHeroAdapter(listHero,this)
        cardViewAdapter = CardViewAdapter(this,listHero)
        gridViewAdapter = GridAdapter(this,listHero)
        rv_heroes.setHasFixedSize(true)
        if (savedInstanceState != null){
            savedInstanceState.getInt(STATE_MODE,mode)
            val statelist = savedInstanceState.getParcelableArrayList<DataHero>(STATE_LIST)
            tittle = savedInstanceState.getString(STATE_TITTLE).toString()
            subtittle = savedInstanceState.getString(STATE_SUBTITLE).toString()
            val mode1 = savedInstanceState.getInt(STATE_MODE,mode)
            if (statelist != null){
                listHero.addAll(statelist)
            }
            setMode(mode1)
        }else {
            setActionBar(tittle,subtittle)
            listHero.addAll(getDataAllList())
            recyclerViewList()
            mode = R.id.list
        }
    }

    // diunakan untuk membuat sebuah menu pada action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // method yang digunakan untuk membuat fungsi dari menu di acton bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    // method yang digunakan ketika suatu action bar dipilih
    private fun setMode(itemId : Int){
        when(itemId){
            R.id.list -> {
                tittle = "Candra Julius Sinaga"
                subtittle = "Mode List"
                recyclerViewList()
            }
            R.id.grid -> {
                tittle = "Candra Julius Sinaga"
                subtittle = "Mode Grid"
                recyclerViewGrid()
            }
            R.id.withCard -> {
                tittle = "Candra Julius Sinaga"
                subtittle = "Mode Card View"
                recyclerViewCard()
            }
        }
        mode = itemId
        setActionBar(tittle,subtittle)
    }

    private fun recyclerViewList(){
        rv_heroes.adapter = adapterHero
        rv_heroes.layoutManager = LinearLayoutManager(this)
        adapterHero.setOnItemClickCallback(object: ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(hero: DataHero) {
                showSelectedHero(hero)
            }

        })
    }

    private fun recyclerViewGrid(){
        rv_heroes.adapter = gridViewAdapter
        // membuat layout manager
        rv_heroes.layoutManager = GridLayoutManager(this,2)
    }

    private fun recyclerViewCard(){
        rv_heroes.adapter = cardViewAdapter
        // membuat layout manager
        rv_heroes.layoutManager = LinearLayoutManager(this)
    }

    // mengambil semua data yang ada pada list
    private fun getDataAllList() : ArrayList<DataHero>{
        val heroList = ArrayList<DataHero>()
        val dataName = resources.getStringArray(R.array.data_name)
        val deskripsi = resources.getStringArray(R.array.data_description)
        val photo = resources.getStringArray(R.array.data_photo)

        // melakukan semua proses perulangan  untuk mengambil semua list yang ada
        for( position in dataName.indices){
            val hero = DataHero(
                dataName[position],
                deskripsi[position],
                photo[position]
            )
            heroList.add(hero)
        }
        return heroList
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITTLE,tittle)
        outState.putString(STATE_SUBTITLE,subtittle)
        outState.putParcelableArrayList(STATE_LIST,listHero)
        outState.putInt(STATE_MODE,mode)
    }

    // digunakan untuk membuat action bar tittle dan subtittle
    private fun setActionBar(tittle : String, subtittle : String){
        supportActionBar?.title = tittle
        supportActionBar?.subtitle = subtittle
    }
    // digunakan ketika anda memilih sebuah hero dan akan memunculkan aksi
    private fun showSelectedHero(hero: DataHero){
        Toast.makeText(this,"Kamu memilih ${hero.name}",Toast.LENGTH_SHORT).show()
    }
}