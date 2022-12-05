package com.novita.ugd_rumahsakit

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.novita.ugd_rumahsakit.Maker.MakerLocation
import com.novita.ugd_rumahsakit.profile.ProfileActivity
import com.novita.ugd_rumahsakit.qrcode.qrcode
import com.novita.ugd_rumahsakit.qrcode.tampilanlibary
import com.novita.ugd_rumahsakit.spesialisnamaDokter.DokterHome

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        changeFragment(FragmentHome())
    }

    fun changeFragment(fragment: Fragment){
        if(fragment!=null){
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_fragment, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.bottom_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home){
            changeFragment(FragmentHome())
        } else if(item.itemId == R.id.profil) {
            val moveHome = Intent(this@HomeActivity, ProfilActivity::class.java)
            startActivity(moveHome)
        }else if(item.itemId == R.id.konsultasi){
            val moveHome = Intent(this@HomeActivity, DokterHome::class.java)
            startActivity(moveHome)
        }else if(item.itemId == R.id.Map) {
            val moveHome = Intent(this@HomeActivity, MakerLocation::class.java)
            startActivity(moveHome)
        }else if(item.itemId == R.id.Profile) {
            val moveHome = Intent(this@HomeActivity, ProfileActivity::class.java)
            startActivity(moveHome)
        }else if(item.itemId == R.id.qrcode) {
            val moveHome = Intent(this@HomeActivity, tampilanlibary::class.java)
            startActivity(moveHome)
        }else{

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
            builder.setMessage("Are you sure want to exit?")
                .setPositiveButton("YES", object : DialogInterface.OnClickListener{
                    override fun onClick(dialogInterface: DialogInterface, i: Int){
                        finishAndRemoveTask()
                    }
                })
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}