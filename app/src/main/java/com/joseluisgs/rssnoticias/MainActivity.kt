package com.joseluisgs.rssnoticias



import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.joseluisgs.rssnoticias.ui.acerca_de.AcercaDeFragment


class MainActivity : AppCompatActivity() {

	// Elementos a usar
	public var menu: Menu? = null
		private set

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setSupportActionBar(findViewById(R.id.toolbar))
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)
		this.menu = menu
        initMenuOpciones()
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		initMenuOpciones()
		return when (item.itemId) {
			R.id.menu_acerca_de -> {
				this.abrirAcercaDe()
				return true
			}
			R.id.menu_atras -> {
				onBackPressed()
				return true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	/**
	 * Oculta las opciones del menú
	 */
	private fun initMenuOpciones() {
        this.menu?.findItem(R.id.menu_atras)?.isVisible = false
        this.menu?.findItem(R.id.menu_settings)?.isVisible = false
        this.menu?.findItem(R.id.menu_compartir_noticia)?.isVisible = false
		this.menu?.findItem(R.id.menu_acerca_de)?.isVisible = true
	}


	private fun abrirAcercaDe() {
		/**
		 * Transacción entre fragments. Lo primero es llamar al fragment manager
		 * A continuación instanciamos un objeto fragment correspondiente al
		 * fragment que va a entrar y le pasamos el objeto noticia en la posición de
		 * la lista correspondiente. Iniciamos la transacción, dándole si queremos
		 * animaciones. Lo más importante es el replace, en el que remplazamos el
		 * host por el objeto fragment detalle
		 */
		val acercaDe = AcercaDeFragment()
		val transaction = supportFragmentManager.beginTransaction()
		// animaciones
//		transaction.setCustomAnimations(R.anim.animacion_fragment1,
//			R.anim.animacion_fragment1, R.anim.animacion_fragment2,
//			R.anim.animacion_fragment1)
		//Llamamos al replace
		transaction.replace(R.id.nav_host_fragment, acercaDe)
		transaction.addToBackStack(null)
		transaction.commit()
		//Esto es para que no se quede con el color del deslizamiento

	}

	/**
	 * Elimina un fragment de la pila
	 */
	override fun onBackPressed() {
		try {
				if (supportFragmentManager.backStackEntryCount > 0) {
					supportFragmentManager.popBackStackImmediate()
				} else {
					super.onBackPressed()
				}
		} catch (ex: Exception) {
			super.onBackPressed()
		}
	}
}